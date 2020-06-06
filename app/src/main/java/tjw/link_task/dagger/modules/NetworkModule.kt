package tjw.link_task.dagger.modules

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import tjw.link_task.dagger.modules.NetworkModule.Companion.NetworkRequestTag
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton



@Module
class NetworkModule {

    companion object {
        private val BASE_URL = "https://newsapi.org/v1/articles"
        const val NetworkRequestTag="NetworkRequestTag"
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): Interceptor = LoggingInterceptor()

    @Singleton
    @Provides
    fun provideheader(sharedPreferences: SharedPreferences): Interceptor =
        LoggingInterceptor.HeadersInterceptor(sharedPreferences)

    @Singleton
    @Provides
    fun provideOkhttpClient(headersInterceptor: LoggingInterceptor.HeadersInterceptor): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).writeTimeout(
            1,
            TimeUnit.MINUTES
        ).addInterceptor(provideOkHttpInterceptor()).addInterceptor(headersInterceptor).build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
}

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
           val request = chain.request()
           Timber.tag(NetworkRequestTag).v("Sending request ${request.url()}  \n  ${request.headers()}  ${request.body()} ")
           val response = chain.proceed(request)
           Timber.tag(NetworkRequestTag).v("Received response for ${response.request().url()} \n ${response.receivedResponseAtMillis()} \n ${response.headers()}  \n  ${request.body()} ")
           return response
    }

    class HeadersInterceptor @Inject constructor(val sharedPreferences: SharedPreferences) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            return chain.proceed(original)
        }
    }
}