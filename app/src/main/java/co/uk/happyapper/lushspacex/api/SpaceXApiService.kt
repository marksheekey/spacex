package co.uk.happyapper.lushspacex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface SpaceXApiService {
    @GET("launches")
    fun getPastLaunches(
    ): Call<ResponseBody>

    companion object {
        fun create(): SpaceXApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://api.spacexdata.com/v3/")
                .build()

            return retrofit.create(SpaceXApiService::class.java)
        }
    }
}
