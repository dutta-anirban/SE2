package com.se2.fabler.api

import com.se2.fabler.TestDataSource
import com.se2.fabler.models.BookData
import com.se2.fabler.models.UserData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class FablerService {
    val serverurl = "http://http://127.0.0.1:5000/";
    var client = OkHttpClient();
    fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        // Delay for 1 second to simulate network request
        println("reached")
        println(query)
        val request = Request.Builder().url(serverurl+"story"+query).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                // Handle failure
                println("FAILURE")
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle success
                val result = response.body?.string() ?: ""
                // Process the response data
                println(result)
            }
        })
        return List(itemsPerPage){TestDataSource().books[0]}
    }

    fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        return List(itemsPerPage){TestDataSource().userdata}
    }
}