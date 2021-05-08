package ru.senchenko.pictureloader

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {
    private var textLine: String = ""
    private lateinit var editText: EditText
    private lateinit var imageView: ImageView
    private lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
        toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("address", textLine)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textLine = savedInstanceState.getString("address").toString()
        reload()
    }

    fun download(view: View) {
        reload()
    }

    private fun reload() {
        textLine = editText.text.toString()
        try {
            Picasso.get().load(textLine).error(R.drawable.ic_launcher_foreground).into(imageView, object : Callback {
                override fun onSuccess() {}
                override fun onError(e: java.lang.Exception?) {
                   toast.show()
                }
            })
        } catch (e: Exception) {
            toast.show()
        }
    }
}