package com.cherrio.leap.features.allnews

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
import com.cherrio.leap.databinding.FragmentNewsBinding
import com.cherrio.leap.utils.hide
import com.cherrio.leap.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(), CNewsAdapter {

    private val viewModel by viewModels<NewsViewModel>()
    val newsAdapter = NewsAdapter(this)


    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsBinding {
        return FragmentNewsBinding.inflate(layoutInflater, container, false)
    }

    override fun useViews() {
        binding.apply {
            listNews.apply {
                adapter = newsAdapter.withLoadStateFooter(
                    footer = FooterAdapter(newsAdapter::retry),
                )
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newsFlow.collectLatest {
                newsAdapter.submitData(it)
            }
        }
        newsAdapter.addLoadStateListener { loadstate ->
            handleState(loadstate.source)
        }


    }

    private fun handleState(states: LoadStates) {
        when (states.refresh) {
            is LoadState.Loading -> binding.loading.show()
            is LoadState.Error -> {
                binding.loading.hide()
                toast((states.refresh as LoadState.Error).error.localizedMessage)
            }
            is LoadState.NotLoading -> binding.loading.hide()
        }
    }


    private fun toast(result: String) {
        Toast.makeText(
            requireActivity(),
            result, Toast.LENGTH_SHORT
        ).show()
    }

    override fun addToFavorites(data: Data): Boolean {
        viewModel.addToFavorites(data)
        toast("Added successfully...")
        return true
    }
}