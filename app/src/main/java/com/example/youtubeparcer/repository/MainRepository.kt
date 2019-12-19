package com.example.youtubeparcer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeparcer.api.RetrofitClient
import com.example.youtubeparcer.api.YoutubeApi
import com.example.youtubeparcer.model.PlaylistModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainRepository {
    companion object {
        val channel = "UC0C-w0YjGpqDXGB8IHb662A"
        val apiKey = "AIzaSyCWK-EoCHecYMMFAvl-DI5iegR9s1WW20Y"
        val part = "snippet,contentDetails"
        val maxResult = "50"

        private lateinit var apiService: YoutubeApi
        fun fetchYoutubePlayListData(): LiveData<PlaylistModel> {
            apiService = RetrofitClient.create()
            val data = MutableLiveData<PlaylistModel>()

            apiService.getPlaylist(part, apiKey, channel, maxResult)
                .enqueue(object : retrofit2.Callback<PlaylistModel> {
                    override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                        data.value = null
                    }

                    override fun onResponse(
                        call: Call<PlaylistModel>,
                        response: Response<PlaylistModel>
                    ) {
                        data.value = response.body()
                    }

                })
            return data
        }
    }
}