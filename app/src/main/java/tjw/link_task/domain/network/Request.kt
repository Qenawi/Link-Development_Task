package tjw.link_task.domain.network

import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.Call
import tjw.go_plus_meeting.domain.network.Failure
import tjw.go_plus_meeting.domain.network.setArgs
import tjw.link_task.domain.base.Either

fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R?,
    validate: (R?) -> Boolean,
    default: R
): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            false -> Either.Left(errorBodyToFail(response.errorBody().toString()))
            true -> {
                val mResponseBodyHolder = response.body()
                  mResponseBodyHolder?.let { obj ->
                    if(!checkCode(obj as JsonElement)) return@let Either.Left(errorBodyToFail(obj.toString()))
                    val trans_out = transform(obj)
                    val valid_out = validate(trans_out)
                    if (valid_out) Either.Right(trans_out ?: default)
                    else Either.Left(errorBodyToFail(obj.toString()))
                } ?: Either.Left(Failure.ServerError)
            }
        }//when
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError.setArgs(exception.message, exception))
    }
}

private fun checkCode(d: JsonElement): Boolean {
    return try {
        d.asJsonObject.get("status").asString == "ok"
    } catch (e: Exception) {
        false
    }
}

private fun errorBodyToFail(err: String): Failure {
    return try {
        val jObjError = JSONObject(err)
        val msg = jObjError.get("message").toString()
        Failure.RequestError.setArgs(msg, null)
    } catch (e: Exception) {
        Failure.RequestError.setArgs(e.localizedMessage, e)
    }
}