package com.vee.musicapp

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.vee.musicapp.modules.home.HomeScreen
import com.vee.musicapp.modules.immersive.MyImmersiveApp
import com.vee.musicapp.ui.theme.MusicAppTheme
import com.vee.musicapp.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    val tag = this.javaClass.name
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        setContent {
            MusicAppTheme {
//                MyImmersiveApp()
                HomeScreen(viewModel)
            }
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(tag, "keyCode:$keyCode ,  event:${event}")

        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                Log.d("TV Remote", "Up Pressed")
                true
            }

            KeyEvent.KEYCODE_DPAD_DOWN -> {
                Log.d("TV Remote", "Down Pressed")
                true
            }

            KeyEvent.KEYCODE_ENTER -> {
                Log.d("TV Remote", "Enter Pressed")
                true
            }

            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                Log.d("TV Remote", "Right Pressed")
                true
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                Log.d("TV Remote", "Left Pressed")
                true
            }

            KeyEvent.KEYCODE_DPAD_CENTER -> {
                Log.d("TV Remote", "Center Pressed")
                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
    }
}


