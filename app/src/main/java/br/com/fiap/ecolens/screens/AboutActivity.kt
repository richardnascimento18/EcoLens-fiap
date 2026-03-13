package br.com.fiap.ecolens.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.ecolens.R
import br.com.fiap.ecolens.ui.BottomMenuHelper

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)
        BottomMenuHelper.setup(this)

        findViewById<android.view.View>(R.id.menuAbout)
            .setBackgroundColor(getColor(R.color.menu_active))
    }
}