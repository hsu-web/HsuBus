package example.com.hsubus

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore
import example.com.hsubus.databinding.ActivityThirdBinding


class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2)Viewの取得＆表示させるデータ（配列）を用意
        val lv:ListView =findViewById(R.id.lv)
        val titledata = arrayListOf<String>()
        val db = FirebaseFirestore.getInstance()
        var i = 0

        //3)アダプタ
        val adapter=ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            titledata
        )
        //4)adapterをlistviewにセット
        lv.adapter = adapter

        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}") //全表示
                    Log.d(TAG, "こんにちは")

                    val title = document.data["contentsTitle"]
                    adapter.add(title.toString())
                    //Log.d(TAG, title.toString())
                    //Log.d(TAG, data[i]) //id表示
                    i += 1
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        //5)クリック処理
        lv.setOnItemClickListener { _, _, i, _->
            val intent = Intent(this, ForthActivity::class.java)
            intent.putExtra("TEXT_NUM", i)
            //画面推移
            startActivity(intent)
        }

        binding.button7.setOnClickListener {
            //Toast.makeText(applicationContext, "name", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.button8.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}