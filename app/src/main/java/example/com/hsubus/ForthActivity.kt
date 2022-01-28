package example.com.hsubus

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import example.com.hsubus.databinding.ActivityForthBinding
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class ForthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForthBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageView: ImageView = findViewById(R.id.imageview)
        val db = FirebaseFirestore.getInstance()
        val imagedata = arrayListOf<String>()

        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}") //全表示

                    val image = document.data["imageURL"]
                    imagedata.add(image.toString())
                    //Log.d(TAG, title.toString())
                    //Log.d(TAG, data[i]) //id表示
                }
                //受け取った変数を入れる
                val text = intent.getIntExtra("TEXT_NUM", 0)
                Log.d(TAG, text.toString()) //id表示

                Glide.with(this)
                    .load(imagedata[text]) // ロードしたいイメージのURLを入力
                    //.placeholder(R.raw.loading)ロード中に見せるイメージ設定
                    .into(imageView)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        binding.backbutton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}
