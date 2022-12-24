package com.example.sftassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.paging.ImageShowPagingAdapter
import com.example.sftassignment.databinding.ActivityMainBinding
import com.example.sftassignment.paging.PagingLoadingAdapter
import com.example.sftassignment.utils.ShowDetailsDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),ImageShowPagingAdapter.OnRecItemClickListener {

    private lateinit var binding:ActivityMainBinding
    private val viewModel:HomeViewModel by viewModels()
    private var mAdapter: ImageShowPagingAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recViewImages.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            mAdapter = ImageShowPagingAdapter(this@MainActivity,this@MainActivity)
            /*
            * setting recycler view adapter and also adding loading state adapter
            * for showing loading when page is getting loaded.
            * */
            adapter = mAdapter?.withLoadStateHeaderAndFooter(
                header = PagingLoadingAdapter(),
                footer = PagingLoadingAdapter()
            )
        }

        lifecycleScope.launch {
            /*
            * This is for showing the initial loader when the page is opened first time
            * and the first page is loading.
            * */
            mAdapter?.loadStateFlow?.collectLatest {
                if (it.prepend is LoadState.NotLoading && it.prepend.endOfPaginationReached) {
                    binding.progressBar.visibility = View.GONE
                }
                if (it.append is LoadState.NotLoading && it.append.endOfPaginationReached) {
                    binding.progressBar.isVisible = mAdapter?.itemCount!! < 1
                }
            }
        }

        binding.swipeRefresh.apply {
            setOnRefreshListener {
                //Do something
                mAdapter?.refresh()
                mAdapter
            }
        }


        viewModel._topMovieListData.observe(this@MainActivity){
            mAdapter?.submitData(lifecycle,it).also {
                //binding.progressBar.visibility = View.GONE
                lifecycleScope.launch {
                    /*
                    * The refresh happens too quick due to paging 3.
                    * So giving a artificial delay of 2sec.
                    * */
                    delay(2000)
                    binding.swipeRefresh.isRefreshing=false
                }
            }
        }
    }

    override fun onRecItemClicked(data: ImageItem?) {
        data?.let {
            val dialog = ShowDetailsDialog(this,it) { dialog ->
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}