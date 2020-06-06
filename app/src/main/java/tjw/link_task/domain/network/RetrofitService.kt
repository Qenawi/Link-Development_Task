package tjw.link_task.domain.network
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
class RetrofitService
@Inject constructor(retrofit: Retrofit) : Api
{
    private val appApi by lazy { retrofit.create(Api::class.java) }
    override fun articleOne(src: String, key: String): Call<JsonElement> = appApi.articleOne(src, key)
    override fun articleTwo(src: String, key: String): Call<JsonElement> =appApi.articleTwo(src, key)
}