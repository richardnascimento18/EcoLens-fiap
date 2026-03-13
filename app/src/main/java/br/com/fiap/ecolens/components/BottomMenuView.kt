package br.com.fiap.ecolens.components;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import br.com.fiap.ecolens.R;
import br.com.fiap.ecolens.screens.*;
import kotlin.jvm.JvmOverloads;

class BottomMenuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()

        findViewById<LinearLayout>(R.id.menuHome).setOnClickListener {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.menuMap).setOnClickListener {
            context.startActivity(Intent(context, MapActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.menuDetails).setOnClickListener {
            context.startActivity(Intent(context, DetailActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.menuAbout).setOnClickListener {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }
}
