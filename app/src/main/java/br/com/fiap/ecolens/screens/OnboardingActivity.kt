package br.com.fiap.ecolens.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.fiap.ecolens.R
class OnboardingActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        pager = findViewById(R.id.viewPager)

        pager.adapter = OnboardingAdapter(pager)
    }
}