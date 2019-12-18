package com.example.youtubeparcer.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeparcer.R
import com.example.youtubeparcer.adapter.PlayListAdapter
import com.example.youtubeparcer.model.ItemsItem
import com.example.youtubeparcer.model.PlaylistModel
import com.example.youtubeparcer.ui.detail_playlist.DetailsPlayActivity
import com.example.youtubeparcer.utils.UiHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: PlayListAdapter? = null
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initAdapter()
        isNetworkConnected()
    }

    private fun isNetworkConnected() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        if (isConnected) {
            fetchPlaylist()
        } else {
            val intent = Intent(this, InternetConnectionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchPlaylist() {
        val data = viewModel?.getPlayListData()
        data?.observe(this, Observer<PlaylistModel> {
            val model: PlaylistModel? = data.value
            when {
                model != null -> {
                    updateAdapterData(model)
                }
            }
        })
    }

    private fun initAdapter() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = PlayListAdapter() { item: ItemsItem -> clickItem(item) }
        recyclerview.adapter = adapter
    }

    private fun clickItem(item: ItemsItem) {
        UiHelper().showToast(this, "${item.id} clicked")
        val intent = Intent(this, DetailsPlayActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("channelId", item.snippet.channelId)
        intent.putExtra("etag", item.etag)
        startActivity(intent)

    }

    private fun updateAdapterData(list: PlaylistModel?) {
        val data = list?.items
        adapter?.updateData(data as MutableList<ItemsItem>)
    }
}
