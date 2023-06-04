package com.example.myaudio

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import kotlin.time.toDuration

class MainActivity2 : AppCompatActivity() {
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var nextbutton: Button
    private lateinit var previousbutton: Button
    private lateinit var playButton: Button
    private lateinit var handler: Handler



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        seekBar=findViewById(R.id.seeksond)
        nextbutton=findViewById(R.id.nxtbtn)
        previousbutton=findViewById(R.id.prvbtn)
        playButton=findViewById(R.id.playPauseButton)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.tum_tak_02)
        handler = Handler(Looper.getMainLooper())



        seekBar.max=mediaPlayer2.duration
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
            {
                if (fromUser)
                {
                    mediaPlayer2.seekTo(200)
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?)
            {


            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer2.release()
            }
        })

        nextbutton.setOnClickListener {
            mediaPlayer2.stop()
            mediaPlayer2.setNextMediaPlayer(mediaPlayer2)

            var nxt = nextbutton
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        mediaPlayer2.isLooping = true

        playButton.setOnClickListener {
            if (mediaPlayer2.isPlaying) {
                mediaPlayer2.pause()


                playButton.setBackgroundResource(R.drawable.baseline_play_circle_24)
            } else {
                mediaPlayer2.start()
                playButton.setBackgroundResource(R.drawable.baseline_pause_circle_24)
            }
        }
        handler.post(object : Runnable {
            override fun run() {
                seekBar.progress = mediaPlayer2.currentPosition
                handler.postDelayed(this, 1000)
            }
        })


    }

}