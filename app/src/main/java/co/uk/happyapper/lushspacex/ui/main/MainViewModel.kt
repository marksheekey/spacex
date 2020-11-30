package co.uk.happyapper.lushspacex.ui.main
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import co.uk.happyapper.lushspacex.RocketRepo
import co.uk.happyapper.lushspacex.SpaceXApiService

class MainViewModel : ViewModel() {
    private val spaceXService by lazy { SpaceXApiService.create() }
    private val rocketRepo = RocketRepo(spaceXService)

    val launches = rocketRepo.getPasLaunches().switchMap {

    }

}