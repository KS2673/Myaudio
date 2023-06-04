package com.example.myaudio
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var textView: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var nextbutton: Button
    private lateinit var previousbutton: Button
    private lateinit var playButton: Button
    private lateinit var handler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seeksond)
        nextbutton = findViewById(R.id.nxtbtn)
        previousbutton = findViewById(R.id.prvbtn)
        playButton = findViewById(R.id.playPauseButton)
        textView = findViewById(R.id.txtsond)
        mediaPlayer = MediaPlayer.create(this, R.raw.lakshya_song)

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_PICK_AUDIO)



        handler = Handler(Looper.getMainLooper())




        seekBar.max = mediaPlayer.duration
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if (fromUser) {
                    mediaPlayer.seekTo(200)
                }


            }


            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.pause()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.release()
            }


        })
        seekBar.setOnClickListener {
            (View.OnClickListener {
                Toast.makeText(applicationContext, "Hello man", Toast.LENGTH_LONG).show()
            })
        }



        nextbutton.setOnClickListener {
            mediaPlayer.stop()


            val intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)

        }



        mediaPlayer.isLooping = true

        playButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()


                playButton.setBackgroundResource(R.drawable.baseline_play_circle_24)
            } else {
                mediaPlayer.start()
                playButton.setBackgroundResource(R.drawable.baseline_pause_circle_24)
            }
        }
        handler.post(object : Runnable {
            override fun run() {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }
        })
    }







       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_PICK_AUDIO && resultCode == RESULT_OK) {
                // Retrieve the selected audio file's URI
                val audioUri = data

                // Use the audioUri to do further processing (e.g., playback, uploading, etc.)
                // ...
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    companion object {
        private const val REQUEST_PICK_AUDIO = 1


    }
}


