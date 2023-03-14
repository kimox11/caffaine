package network
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
object MarsApiService{
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .baseUrl(BASE_URL)
        .build()

    fun <T> buildService(service : Class <T>): T{
        return retrofit.create(service)

    }

}
    interface ApiService {
        @GET("photos")
        fun getPhotos(): Call<MutableList<MarsPhoto>>
    }




