package co.uk.happyapper.lushspacex

import co.uk.happyapper.lushspacex.repo.NetworkCall
import okhttp3.ResponseBody

class RocketRepo(private val apiService :SpaceXApiService)  {
    fun getPasLaunches() = NetworkCall<ResponseBody>().makeCall(apiService.getPastLaunches())
}