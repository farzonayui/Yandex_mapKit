package com.example.map

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView

    private val placemarkTapListener = MapObjectTapListener { _, point ->
        Toast.makeText(
            this@MainActivity,
            "Tapped the point (${point.longitude}, ${point.latitude})",
            Toast.LENGTH_SHORT
        ).show()
        true
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        MapKitFactory.setApiKey("81d5d669-40cd-4f44-80c5-9ec755bad873")
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapview)

        val imageProvider = ImageProvider.fromResource(this, R.drawable.location)
        val placemark = mapView.map.mapObjects.addPlacemark().apply {
            geometry = Point(40.281919, 69.617063)
            setIcon(imageProvider)
        }

        placemark.addTapListener(placemarkTapListener)
    }
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}

