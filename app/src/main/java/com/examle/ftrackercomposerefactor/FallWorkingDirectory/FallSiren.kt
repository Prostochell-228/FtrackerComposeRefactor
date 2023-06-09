package com.examle.ftrackercomposerefactor.FallWorkingDirectory

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import com.examle.ftrackercomposerefactor.R

class FallSiren private constructor(val context: TrackDelper) {
    private var pool: SoundPool
    private var id: Int

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes: AudioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            pool = SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            @Suppress("DEPRECATION")
            pool = SoundPool(5, AudioManager.STREAM_ALARM, 0)
        }
        id = pool.load(context.applicationContext, R.raw.alarm, 1)
    }

    companion object {
        private var singleton: FallSiren? = null

        internal fun instance(context: TrackDelper): FallSiren {
            var singleton = this.singleton
            if (singleton == null) {
                singleton = FallSiren(context)
                this.singleton = singleton
            }
            return singleton
        }

        internal fun siren(context: Context) {
            loudest(context, AudioManager.STREAM_ALARM)
            val singleton = this.singleton
            if (singleton != null) {
                val pool = singleton.pool
                pool.play(singleton.id, 1.0f, 1.0f, 1, 3, 1.0f)
            }
        }

        internal fun loudest(context: Context, stream: Int) {
            val manager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val loudest = manager.getStreamMaxVolume(stream)
            manager.setStreamVolume(stream, loudest, 0)
        }

        internal fun alert(context: Context) {

        }

        private val TAG: String = FallSiren::class.java.simpleName
    }
}