package com.cherrio.leap.features.fav

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.LinearLayoutManager
import com.cherrio.leap.common.BaseFragment
import com.cherrio.leap.data.model.Data
import com.cherrio.leap.data.remote.Result
import com.cherrio.leap.databinding.FragmentFavBinding
import com.cherrio.leap.databinding.FragmentNewsBinding
import com.cherrio.leap.utils.hide
import com.cherrio.leap.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavBinding>() {

    private val viewModel by viewModels<FavViewModel>()
    private val favAdapter = FavAdapter()



    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavBinding {
        return FragmentFavBinding.inflate(layoutInflater, container, false)
    }

    override fun useViews() {
        binding.apply {
            listFav.apply {
                adapter = favAdapter
            }
        }
        viewModel.dataLiveData.observe(viewLifecycleOwner){
            println("List Data ${it.size}")
            favAdapter.submitList(it)
            binding.loading.isVisible = false
        }


    }


}