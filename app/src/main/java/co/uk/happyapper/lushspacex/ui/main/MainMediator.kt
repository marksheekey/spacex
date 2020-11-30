package co.uk.happyapper.lushspacex.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class MainMediator(
    error: LiveData<Boolean>,
    loading: LiveData<Boolean>,
    data: LiveData<List<String>>
) : MediatorLiveData<MainViewModel.RocketUI>() {
    init {
        addSource(error) { value = MainViewModel.RocketUI.Error }
        addSource(loading) { value = MainViewModel.RocketUI.Loading }
        addSource(data) { value = MainViewModel.RocketUI.RocketList(it) }
        //addSource(observe) {}
    }
}