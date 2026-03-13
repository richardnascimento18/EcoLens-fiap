package br.com.fiap.ecolens.screens

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GasChartAdapter(
    private val charts: List<View>
) : RecyclerView.Adapter<GasChartAdapter.ChartViewHolder>() {

    class ChartViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        return ChartViewHolder(charts[viewType])
    }

    override fun getItemCount(): Int = charts.size

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {}
}