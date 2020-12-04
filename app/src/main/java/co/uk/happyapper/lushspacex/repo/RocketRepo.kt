package co.uk.happyapper.lushspacex

import co.uk.happyapper.lushspacex.repo.NetworkCall


class RocketRepo(private val apiService: SpaceXApiService) {
    fun getPastLaunches() = NetworkCall<List<Rocket>>().makeCall(apiService.getPastLaunches())
}

class Rocket {
    val mission_name: String? = null
    val launch_date_unix: Long? = null
    val links: Links? = null
    val launch_success: Boolean? = null
}

class Links {
    val mission_patch_small: String? = null
}