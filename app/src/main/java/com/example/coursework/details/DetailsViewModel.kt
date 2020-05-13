package com.example.coursework.details

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coursework.model.Book
import com.example.coursework.storage.Storage
import java.io.File
import javax.inject.Inject


class DetailsViewModel @Inject constructor(): ViewModel(){

    private val _isButtonVisible = MutableLiveData<Boolean>()
    val isButtonVisible: LiveData<Boolean>
        get() = _isButtonVisible

    fun down(context: Context, book: Book){
        if(book.filePath!=""){
            val sp=book.filePath.split("/").last()
            Storage.getCurrentRef(book.filePath).downloadUrl.addOnSuccessListener {uri ->
                downloadFile(context,uri,sp)
                _isButtonVisible.value=true
            }
        }

    }
    private  fun downloadFile(context: Context,uri : Uri, subPath: String){
        val downloadManager: DownloadManager=context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request=DownloadManager.Request(uri)

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(context,Environment.DIRECTORY_DOWNLOADS,subPath)

        downloadManager.enqueue(request)

    }

    fun open(context: Context, book: Book){
        val sp2=book.filePath.split("/").last()
        val p=context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath+"/"+sp2
        val file = File(p)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(file), "application/*")
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        context.startActivity(intent)
    }
}

