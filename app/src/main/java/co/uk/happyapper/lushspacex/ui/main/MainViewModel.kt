package co.uk.happyapper.lushspacex.ui.main
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import co.uk.happyapper.lushspacex.RocketRepo
import co.uk.happyapper.lushspacex.SpaceXApiService
import co.uk.happyapper.lushspacex.repo.Resource.Status.*
import co.uk.happyapper.lushspacex.ui.main.MainViewModel.RocketUI.Loading
import co.uk.happyapper.lushspacex.ui.main.MainViewModel.RocketUI.RocketList
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    //todo move these to KOIN
    private val spaceXService by lazy { SpaceXApiService.create() }
    private val rocketRepo = RocketRepo(spaceXService)

    val launches = rocketRepo.getPastLaunches().map {
        when (it.status) {
            ERROR -> RocketUI.RocketCrash(it.apiError + "")
            LOADING -> Loading
            SUCCESS -> it.data?.let { rockets ->
                val rocketList = rockets.map {
                    RocketUIModel(
                        name = it.mission_name ?: "Unknown",
                        badgeUrl = it.links?.mission_patch_small,
                        launchDate = convertUnixDate(it.launch_date_unix),
                        success = getSuccess(it.launch_success)
                    )
                }
                RocketList(rocketList)
            }
        }
    }

    sealed class RocketUI {
        object Loading : RocketUI()
        data class RocketCrash(val message: String) : RocketUI()
        data class RocketList(val rockets: List<RocketUIModel>) : RocketUI()
    }

    class RocketUIModel(
        val name: String = "",
        val badgeUrl: String? = null,
        val launchDate: String? = null,
        val success: String = ""
    )

    private fun convertUnixDate(unixDate: Long?): String {
        if (unixDate == null) {
            return "Unknown"
        } else {
            val date = Date(unixDate)
            return SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)
        }
    }

    private fun getSuccess(success: Boolean?): String {
        if (success == null) {
            return "Unknown"
        } else {
            when (success) {
                true -> return "Successful"
                false -> return "Failed"
            }
        }
    }

}