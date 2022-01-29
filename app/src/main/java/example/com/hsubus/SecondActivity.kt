package example.com.hsubus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import example.com.hsubus.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //activity_second.xmlのbutton4を押した際のクリック処理
        binding.button4.setOnClickListener {
            //intentに画面推移先(今回であればMainActivity)を定義する
            val intent = Intent(this, MainActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }
        //activity_second.xmlのbutton6を押した際のクリック処理
        binding.button6.setOnClickListener {
            //intentに画面推移先(今回であればThirdActivity)を定義する
            val intent = Intent(this, ThirdActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }
    }
}