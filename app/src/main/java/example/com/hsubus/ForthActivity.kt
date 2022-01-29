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

        //activity_forth.xmlのImageViewをimageViewとして用いるために用意
        val imageView: ImageView = findViewById(R.id.imageview)
        //dbにFirebaseのFirestoreDatabaseからの情報を代入している。
        val db = FirebaseFirestore.getInstance()
        //arrayListOfは要素数の追加、削除ができる。
        val imagedata = arrayListOf<String>()

        db.collection("posts")
            .get()
            //dbからデータを受け取れた場合の処理。resultにはdbのpostsの中身が入る。
            .addOnSuccessListener { result ->
                //resultをdocumentに代入し、それをresult分繰り返している。
                for (document in result) {
                    //dbのpostsの中身のひとつのフィールドであるimageURLをimageに代入している。
                    val image = document.data["imageURL"]
                    //imagedataにimageを追加している。
                    imagedata.add(image.toString())
                }
                //getIntExtraにより、TEXT_NUMという名前で受け取った値をtextに代入している。
                //また、デフォルト値は0と設定している。
                val text = intent.getIntExtra("TEXT_NUM", 0)
                //テスト：Logにより、textを表示させている。
                //Log.d(TAG, text.toString())

                //Glideを用いれば、imageViewに画像を表示させられる
                Glide.with(this)
                    //ロードしたいイメージのURLを入力
                    .load(imagedata[text])
                    //テスト：この文を追加することでロード中に見せるイメージが設定できる
                    //.placeholder(R.raw.loading)
                    .into(imageView)
            }
            //何らかの理由でdbからデータを受け取れなかった場合の処理
            .addOnFailureListener { exception ->
                //エラーをログに出力
                Log.d(TAG, "Error getting documents: ", exception)
            }

        //activity_forth.xmlのbackbuttonを押した際のクリック処理
        binding.backbutton.setOnClickListener {
            //intentに画面推移先(今回であればThirdActivity)を代入している
            val intent = Intent(this, ThirdActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }
    }
}
