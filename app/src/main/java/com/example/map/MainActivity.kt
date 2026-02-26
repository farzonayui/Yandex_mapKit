package com.example.map

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {


        MapKitFactory.setApiKey("81d5d669-40cd-4f44-80c5-9ec755bad873")
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mapView = findViewById(R.id.mapview)


        val map = mapView.mapWindow.map
        map.move(
            CameraPosition(
                Point(40.281919, 69.617063),
                17.0f,
                150.0f,
                30.0f
            )
        )
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

