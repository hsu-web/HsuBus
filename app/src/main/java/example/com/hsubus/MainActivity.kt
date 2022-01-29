package example.com.hsubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import example.com.hsubus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //activity_main.xmlのbutton2を押した際のクリック処理
        binding.button2.setOnClickListener {
            //intentに画面推移先(今回であればSecondActivity)を代入している
            val intent = Intent(this, SecondActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }
        //activity_main.xmlのbutton3を押した際のクリック処理
        binding.button3.setOnClickListener {
            //intentに画面推移先(今回であればThirdActivity)を代入している
            val intent = Intent(this, ThirdActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }
    }
}