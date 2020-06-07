package tjw.link_task.domain.network

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    companion object {
        private const val ARTICLE_Page1 = "the-next-web"
        private const val ARTICLE_Page2 = "associated-press"
        private const val API_KEY = "533af958594143758318137469b41ba9"
    }
    @GET("articles/")
    fun articleOne(@Query("source") src:String= ARTICLE_Page1,
                   @Query("apiKey") key:String= API_KEY
                   ): Call<JsonElement>

    @GET("articles/")
    fun articleTwo(@Query("source") src:String= ARTICLE_Page2,
                   @Query("apiKey") key:String= API_KEY): Call<JsonElement>

}