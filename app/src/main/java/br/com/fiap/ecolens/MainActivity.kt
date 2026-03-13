package br.com.fiap.ecolens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import br.com.fiap.ecolens.screens.OnboardingActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
    }
}