package com.example.paintdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.paintdemo.basecharacteristic.BasicCharacteristicActivity
import com.example.paintdemo.color.ShaderDemoActivity
import com.example.paintdemo.line.LineDemoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_bt1).setOnClickListener {
            val intent = Intent(this@MainActivity,BasicCharacteristicActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.main_bt2).setOnClickListener {
            val intent = Intent(this@MainActivity,ShaderDemoActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.main_bt3).setOnClickListener {
            val intent = Intent(this@MainActivity,LineDemoActivity::class.java)
            startActivity(intent)
        }


    }
}
