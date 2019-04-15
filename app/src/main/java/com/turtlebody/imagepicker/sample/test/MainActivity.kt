package com.turtlebody.imagepicker.sample.test

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turtlebody.imagepicker.sample.R
import com.turtlebody.mediapicker.core.Constants
import com.turtlebody.mediapicker.core.MediaPicker
import com.turtlebody.mediapicker.core.PickerConfig
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.File


class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        id_pick_image.setOnClickListener {
            startSinglePicker()
        }

        id_pick_multi_image.setOnClickListener {
            startMultiPicker()
        }
    }

    @SuppressLint("CheckResult")
    private fun startMultiPicker() {
        MediaPicker.with(this, PickerConfig().setAllowMultiImages(true), Constants.FileTypes.FILE_TYPE_AUDIO)
                .onResult()
                .subscribe({
                    info { "success: $it" }
                },{
                    info { "error: $it" }
                })
    }

    @SuppressLint("CheckResult")
    private fun startSinglePicker() {
        MediaPicker.with(this, PickerConfig().setShowDialog(true), Constants.FileTypes.FILE_TYPE_AUDIO)
                .onResult()
                .subscribe({
                    info { "success: $it" }
                    val f = File(it[0].path)
                    info { "success: size: ${f.length()}" }
                    id_image.setImageURI(it[0])
                },{
                    info { "error: $it" }
                })
    }

}