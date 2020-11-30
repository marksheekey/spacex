package co.uk.happyapper.lushspacex.ui.main
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import co.uk.happyapper.lushspacex.RocketRepo
import co.uk.happyapper.lushspacex.SpaceXApiService
import co.uk.happyapper.lushspacex.repo.Resource

class MainViewModel : ViewModel() {
    private val spaceXService by lazy { SpaceXApiService.create() }
    private val rocketRepo = RocketRepo(spaceXService)
    private val error : MutableLiveData<Boolean> = MutableLiveData(false)
    private val loading : MutableLiveData<Boolean> = MutableLiveData(true)
    private val rockets : MutableLiveData<List<String>> = MutableLiveData()
    private val launches = rocketRepo.getPasLaunches().map{
        when(it.status){
            Resource.Status.ERROR -> error.value = true
            Resource.Status.LOADING -> error.value = true
            Resource.Status.SUCCESS -> rockets.value = emptyList()
        }
    }

    val show = MainMediator(error, loading, rockets)

    sealed class RocketUI() {
        object Loading : RocketUI()
        object Error : RocketUI()
        data class RocketList(val list: List<String>) : RocketUI()
    }
}