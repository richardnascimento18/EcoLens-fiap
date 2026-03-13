package br.com.fiap.ecolens.screens

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.ecolens.R
import br.com.fiap.ecolens.clients.ApiClient
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import kotlinx.coroutines.*
import android.content.Intent
import br.com.fiap.ecolens.components.BottomMenuView
import br.com.fiap.ecolens.ui.BottomMenuHelper
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class DetailActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        BottomMenuHelper.setup(this)

        findViewById<android.view.View>(R.id.menuDetails)
            .setBackgroundColor(getColor(R.color.menu_active))

        val sensorId = intent.getIntExtra("sensor_id", -1)
        val name = intent.getStringExtra("name")
        val country = intent.getStringExtra("country")

        val nameText = findViewById<TextView>(R.id.locationName)
        val countryText = findViewById<TextView>(R.id.locationCountry)
        val chart = findViewById<LineChart>(R.id.airQualityChart)

        nameText.text = name
        countryText.text = getString(R.string.country_label, country)

        loadMeasurements(sensorId, chart)
    }

    private fun loadMeasurements(sensorId: Int, chart: LineChart) {
        val pm25View = findViewById<TextView>(R.id.pm25Text)
        val pm10View = findViewById<TextView>(R.id.pm10Text)
        val o3View = findViewById<TextView>(R.id.o3Text)
        val no2View = findViewById<TextView>(R.id.no2Text)

        scope.launch {

            try {

                val response = withContext(Dispatchers.IO) {
                    ApiClient.service.getMeasurements(sensorId)
                }

                val pm25Entries = mutableListOf<Entry>()
                val pm10Entries = mutableListOf<Entry>()
                val o3Entries = mutableListOf<Entry>()
                val no2Entries = mutableListOf<Entry>()

                var index = 0

                var pm25Latest: Float? = null
                var pm10Latest: Float? = null
                var o3Latest: Float? = null
                var no2Latest: Float? = null

                response.results.forEach { measurement ->

                    when (measurement.parameter.name) {

                        "pm25" -> {
                            pm25Entries.add(Entry(index.toFloat(), measurement.value.toFloat()))
                            pm25Latest = measurement.value.toFloat()
                        }

                        "pm10" -> {
                            pm10Entries.add(Entry(index.toFloat(), measurement.value.toFloat()))
                            pm10Latest = measurement.value.toFloat()
                        }

                        "o3" -> {
                            o3Entries.add(Entry(index.toFloat(), measurement.value.toFloat()))
                            o3Latest = measurement.value.toFloat()
                        }

                        "no2" -> {
                            no2Entries.add(Entry(index.toFloat(), measurement.value.toFloat()))
                            no2Latest = measurement.value.toFloat()
                        }
                    }

                    index++
                }

                pm25View.text = pm25Latest?.let { "PM2.5: $it" }
                    ?: "PM2.5: ${getString(R.string.data_not_found)}"

                pm10View.text = pm10Latest?.let { "PM10: $it" }
                    ?: "PM10: ${getString(R.string.data_not_found)}"

                o3View.text = o3Latest?.let { "O3: $it" }
                    ?: "O3: ${getString(R.string.data_not_found)}"

                no2View.text = no2Latest?.let { "NO2: $it" }
                    ?: "NO2: ${getString(R.string.data_not_found)}"

                if (pm25Entries.isEmpty() && pm10Entries.isEmpty() &&
                    o3Entries.isEmpty() && no2Entries.isEmpty()) {

                    chart.clear()
                    chart.setNoDataText(getString(R.string.data_not_found))
                    return@launch
                }

                val dataSets = mutableListOf<ILineDataSet>()

                if (pm25Entries.isNotEmpty())
                    dataSets.add(LineDataSet(pm25Entries, "PM2.5"))

                if (pm10Entries.isNotEmpty())
                    dataSets.add(LineDataSet(pm10Entries, "PM10"))

                if (o3Entries.isNotEmpty())
                    dataSets.add(LineDataSet(o3Entries, "O3"))

                if (no2Entries.isNotEmpty())
                    dataSets.add(LineDataSet(no2Entries, "NO2"))

                chart.data = LineData(dataSets)

                chart.description.text = ""
                chart.setNoDataTextColor(android.graphics.Color.WHITE)

                chart.xAxis.textColor = android.graphics.Color.WHITE
                chart.axisLeft.textColor = android.graphics.Color.WHITE
                chart.axisRight.textColor = android.graphics.Color.WHITE

                chart.legend.textColor = android.graphics.Color.WHITE
                chart.invalidate()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}