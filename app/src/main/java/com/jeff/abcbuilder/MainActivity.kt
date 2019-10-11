package com.jeff.abcbuilder

import android.support.v7.app.AppCompatActivity
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity: AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        syncButton.setOnClickListener{
            sync()
        }

        buildButton.setOnClickListener {
            build()
        }

        downloadButton.setOnClickListener {
            download()
        }
    }

    private fun sync() {
        AsyncTaskSync().execute()
    }

    private fun build() {
        AsyncTaskBuild().execute()
    }

    private fun download() {
        val toast = Toast.makeText(applicationContext, "Download started", Toast.LENGTH_SHORT)
        toast.show()
    }

    inner class AsyncTaskSync: AsyncTask<Boolean, Void, Boolean>() {

        private val apiUrl = "http://10.0.0.220:8000/sync/"
        private val toastText = "Sync started"
        private val errorMessage = "Error in doInBackground: "

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: Boolean?): Boolean {
            try {
                val url = URL(apiUrl)
                val connect = url.openConnection() as HttpURLConnection

                connect.readTimeout = 8000
                connect.connectTimeout = 8000
                connect.requestMethod = "GET"
                connect.doOutput = true
                connect.connect()

                if (connect.responseCode == 200) {
                    val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            catch (exception: Exception) {
                Log.d(TAG, errorMessage + exception.message)
            }

            return true
        }
    }

    inner class AsyncTaskBuild: AsyncTask<Boolean, Void, Boolean>() {

        private val apiUrl = "http://10.0.0.220:8000/build/"
        private val toastText = "Build started"
        private val errorMessage = "Error in doInBackground: "

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: Boolean?): Boolean {
            try {
                val url = URL(apiUrl)
                val connect = url.openConnection() as HttpURLConnection

                connect.readTimeout = 8000
                connect.connectTimeout = 8000
                connect.requestMethod = "GET"
                connect.doOutput = true
                connect.connect()

                if (connect.responseCode == 200) {
                    val toast = Toast.makeText(applicationContext, toastText, Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            catch (exception: Exception) {
                Log.d(TAG, errorMessage + exception.message)
            }

            return true
        }
    }
}