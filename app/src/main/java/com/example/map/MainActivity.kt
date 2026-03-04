package com.example.map

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {

        MapKitFactory.setApiKey("81d5d669-40cd-4f44-80c5-9ec755bad873")
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapview)

        val startPoint = Point(40.281919, 69.617063)
        mapView.map.move(
            CameraPosition(
                startPoint,
                17.0f,
                150.0f,
                30.0f
            )
        )
        val imageProvider =
            ImageProvider.fromResource(this, R.drawable.location)
        val pointIcon =
            ImageProvider.fromResource(this, R.drawable.points)

        mapView.map.mapObjects.addPlacemark(startPoint).apply {
            setIcon(pointIcon)

        }


        mapView.map.addInputListener(object : InputListener {

            override fun onMapTap(map: Map, point: Point) {

                map.mapObjects.clear()

                map.mapObjects.addPlacemark(point).apply {
                    setIcon(imageProvider)
                }
            }

            override fun onMapLongTap(map: Map, point: Point) {
            }
        })
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

