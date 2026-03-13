package br.com.fiap.ecolens.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import br.com.fiap.ecolens.R
import br.com.fiap.ecolens.screens.*

object BottomMenuHelper {

    fun setup(activity: Activity) {

        val home = activity.findViewById<View>(R.id.menuHome)
        val map = activity.findViewById<View>(R.id.menuMap)
        val about = activity.findViewById<View>(R.id.menuAbout)

        home?.setOnClickListener {
            if (activity !is HomeActivity) {
                activity.startActivity(Intent(activity, HomeActivity::class.java))
            }
        }

        map?.setOnClickListener {
            if (activity !is MapActivity) {
                activity.startActivity(Intent(activity, MapActivity::class.java))
            }
        }

        about?.setOnClickListener {
            if (activity !is AboutActivity) {
                activity.startActivity(Intent(activity, AboutActivity::class.java))
            }
        }
    }
}