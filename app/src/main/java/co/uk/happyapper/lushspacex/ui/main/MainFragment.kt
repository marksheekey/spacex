package co.uk.happyapper.lushspacex.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.uk.happyapper.lushspacex.R
import co.uk.happyapper.lushspacex.ui.main.MainViewModel.RocketUI.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var rocketItemAdapter: RocketItemAdapter
    private var rockets = mutableListOf<MainViewModel.RocketUIModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rocketItemAdapter = RocketItemAdapter(rockets, requireContext())
        list_recycler.apply {
            adapter = rocketItemAdapter
        }
        swiperefresh.setOnRefreshListener {
            //viewModel.refresh()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.launches.observe(viewLifecycleOwner, { launch_status ->
            when (launch_status) {
                is Loading -> swiperefresh.isRefreshing = true
                is RocketCrash -> showError(launch_status.message)
                is RocketList -> refreshRocketList(launch_status.rockets)
            }
        }
        )
    }

    private fun refreshRocketList(rocketList: List<MainViewModel.RocketUIModel>) {
        rockets.clear()
        rockets.addAll(rocketList)
        rocketItemAdapter.notifyDataSetChanged()
        swiperefresh.isRefreshing = false
    }

    private fun showError(message: String) {
        swiperefresh.isRefreshing = false
    }

}