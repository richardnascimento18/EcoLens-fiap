package br.com.fiap.ecolens.screens

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.fiap.ecolens.R

class OnboardingAdapter(
    private val pager: ViewPager2
) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    private val layouts = listOf(
        R.layout.onboarding_page1,
        R.layout.onboarding_page2,
        R.layout.onboarding_page3
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(layouts[viewType], parent, false)

        return ViewHolder(view)
    }



    override fun getItemCount(): Int = layouts.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val arrow = holder.itemView.findViewById<ImageView>(R.id.nextButton)

        arrow?.setOnClickListener {

            if (position < itemCount - 1) {
                pager.currentItem = position + 1
            } else {
                val context = holder.itemView.context
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
        }
    }
}