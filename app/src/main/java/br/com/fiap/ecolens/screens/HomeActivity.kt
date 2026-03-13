package br.com.fiap.ecolens.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.ecolens.R
import br.com.fiap.ecolens.ui.BottomMenuHelper
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        BottomMenuHelper.setup(this)

        findViewById<android.view.View>(R.id.menuHome)
            .setBackgroundColor(getColor(R.color.menu_active))


        val button = findViewById<Button>(R.id.languageButton)

        button.setOnClickListener {

            val current = resources.configuration.locales[0].language

            val newLang = if (current == "en") "pt" else "en"

            val locale = Locale(newLang)
            Locale.setDefault(locale)

            val config = resources.configuration
            config.setLocale(locale)

            resources.updateConfiguration(config, resources.displayMetrics)

            recreate()
        }

    }
}