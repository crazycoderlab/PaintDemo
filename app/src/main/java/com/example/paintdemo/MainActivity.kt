package com.example.paintdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.paintdemo.basecharacteristic.BasicCharacteristicActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_bt1).setOnClickListener {
            val intent = Intent(this@MainActivity,BasicCharacteristicActivity::class.java)
            startActivity(intent)
        }

    }
}
