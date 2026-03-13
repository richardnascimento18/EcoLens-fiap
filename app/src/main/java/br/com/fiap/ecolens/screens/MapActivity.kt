package br.com.fiap.ecolens.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import br.com.fiap.ecolens.BuildConfig
import br.com.fiap.ecolens.R
import br.com.fiap.ecolens.clients.ApiClient
import br.com.fiap.ecolens.components.BottomMenuView
import br.com.fiap.ecolens.ui.BottomMenuHelper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView

    // Coroutine scope for async API calls
    private val scope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)
        BottomMenuHelper.setup(this)

        findViewById<android.view.View>(R.id.menuMap)
            .setBackgroundColor(getColor(R.color.menu_active))

        // Required for OSMDroid
        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        Configuration.getInstance().userAgentValue = packageName

        // Get map view
        map = findViewById(R.id.map)
        val zoomIn = findViewById<android.widget.ImageButton>(R.id.zoomIn)
        val zoomOut = findViewById<android.widget.ImageButton>(R.id.zoomOut)

        zoomIn.setOnClickListener {
            map.controller.zoomIn()
        }

        zoomOut.setOnClickListener {
            map.controller.zoomOut()
        }

        // Map settings
        map.setMultiTouchControls(true)
        map.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
        map.controller.setZoom(5.0)
        map.controller.setCenter(GeoPoint(20.0, 0.0))

        loadMarkers()
    }

    /**
     * Loads basic locations from OpenAQ
     * Only retrieves coordinates and id
     */
    private fun loadMarkers() {

        scope.launch {

            try {

                val response = withContext(Dispatchers.IO) {
                    ApiClient.service.getLocations()
                }

                println("Locations received: ${response.results.size}")

                for (location in response.results) {

                    val coords = location.coordinates
                    val lat = coords?.latitude ?: continue
                    val lon = coords.longitude ?: continue

                    val marker = Marker(map)
                    marker.icon = resources.getDrawable(R.drawable.marker_air, null)

                    marker.position = GeoPoint(lat, lon)
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    marker.title = location.name

                    marker.setOnMarkerClickListener { _, _ ->
                        fetchDetailsAndNavigate(location.id)
                        true
                    }

                    map.overlays.add(marker)
                }

                map.invalidate()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Fetch full information for a specific point
     * only AFTER user clicks marker
     */
    private fun fetchDetailsAndNavigate(locationId: Int) {

        scope.launch {

            try {

                val response = withContext(Dispatchers.IO) {
                    ApiClient.service.getLocationDetails(locationId)
                }

                val location = response.results.firstOrNull() ?: return@launch
                val sensorId = location.sensors.firstOrNull()?.id ?: return@launch

                val intent = Intent(this@MapActivity, DetailActivity::class.java)

                intent.putExtra("sensor_id", sensorId)
                intent.putExtra("name", location.name)
                intent.putExtra("country", location.country.name)

                startActivity(intent)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}