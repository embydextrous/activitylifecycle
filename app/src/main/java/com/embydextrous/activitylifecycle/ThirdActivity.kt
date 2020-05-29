package com.embydextrous.activitylifecycle

import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Rational
import android.os.Build
import android.util.Log
import android.app.PendingIntent
import android.content.IntentFilter
import android.graphics.drawable.Icon

class ThirdActivity : AppCompatActivity() {

    private val TAG = ThirdActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            fun getActions(): List<RemoteAction> {
                val actions = mutableListOf<RemoteAction>()
                val intent = Intent("close")

                val pendingIntent = PendingIntent.getBroadcast(
                    this,
                    123,
                    intent,
                    PendingIntent.FLAG_ONE_SHOT
                )
                val icon: Icon = Icon.createWithResource(this, android.R.drawable.ic_menu_crop)
                actions.add(RemoteAction(icon, "Close", "Close PIP Window", pendingIntent))
                return actions
            }

            findViewById<Button>(R.id.button1).setOnClickListener {
                val params = PictureInPictureParams.Builder()
                    .setAspectRatio(Rational(window.decorView.width, window.decorView.height))
                    .setActions(getActions())
                    .build()
                enterPictureInPictureMode(params)
            }
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        Log.d(TAG, "PIP Mode: $isInPictureInPictureMode")
        if (isInPictureInPictureMode) {
            registerReceiver(pipActionsReceiver, IntentFilter("close"))
        } else {
            unregisterReceiver(pipActionsReceiver)
        }
    }

    private val pipActionsReceiver by lazy { PipActionsReceiver(this) }

    class PipActionsReceiver(private val activity: ThirdActivity) : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(PipActionsReceiver::class.java.simpleName, "${intent?.action}")
            intent?.action?.run {
                when (this) {
                    "close" -> {
                        activity.moveTaskToBack(true)
                        val intent = Intent(activity, ThirdActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        activity.startActivity(intent)
                        activity.overridePendingTransition(0, 0)
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
