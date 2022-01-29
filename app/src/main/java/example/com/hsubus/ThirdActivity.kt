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

        //activity_third.xmlのlvをListViewとして用いるために用意
        val lv:ListView =findViewById(R.id.lv)
        //arrayListOfは要素数の追加、削除ができる。
        val titled = arrayListOf<String>()
        //dbにFirebaseのFirestoreDatabaseからの情報を代入している。
        val db = FirebaseFirestore.getInstance()
        //クリックしたリストの行番号を取得するために用いている。
        var i = 0

        //ArrayAdapterはListViewに使うAdapterのひとつでadapterに代入している。
        val adapter = ArrayAdapter(
            this,
            //simple_list_item_1は用意されている定義済みのレイアウトファイルのID
            android.R.layout.simple_list_item_1,
            titled
        )

        //adapterをlvにセットしている。
        lv.adapter = adapter

        db.collection("posts")
            .get()
            //dbからデータを受け取れた場合の処理。resultにはdbのpostsの中身が入る。
            .addOnSuccessListener { result ->
                //resultをdocumentに代入し、それをresult分繰り返している。
                for (document in result) {
                    //テスト：Logにより、dbのpostsを表示させている。
                    //Log.d(TAG, "${document.id} => ${document.data}")
                    //テスト：Logにより、こんにちはを表示させている。
                    //Log.d(TAG, "こんにちは")

                    //dbのpostsの中身のひとつのフィールドであるcontentsTitleをtitleに代入している。
                    val title = document.data["contentsTitle"]
                    //adapterにtitleを追加している。
                    adapter.add(title.toString())
                    //テスト：Logにより、titleを表示させている。
                    //Log.d(TAG, title.toString())
                    //iに1を加えている。
                    i += 1
                }
            }
            //何らかの理由でdbからデータを受け取れなかった場合の処理
            .addOnFailureListener { exception ->
                //エラーをログに出力
                Log.d(TAG, "Error getting documents: ", exception)
            }

        //activity_third.xmlのlvを押した際のクリック処理
        lv.setOnItemClickListener { _, _, _, _->
            //intentに画面推移先(今回であればForthActivity)を代入している
            val intent = Intent(this, ForthActivity::class.java)
            //putExtraによりintentにTEXT_NUMという名前でiを送っている
            intent.putExtra("TEXT_NUM", i)
            //intentに画面推移する処理
            startActivity(intent)
        }

        //activity_third.xmlのbutton7を押した際のクリック処理
        binding.button7.setOnClickListener {
            //テスト：トーストをアプリ下部に「name」という名前で表示させる。
            //Toast.makeText(applicationContext, "name", Toast.LENGTH_LONG).show()

            //intentに画面推移先(今回であればMainActivity)を代入している
            val intent = Intent(this, MainActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }

        //activity_third.xmlのbutton8を押した際のクリック処理
        binding.button8.setOnClickListener {
            //intentに画面推移先(今回であればSecondActivity)を代入している
            val intent = Intent(this, SecondActivity::class.java)
            //intentに画面推移する処理
            startActivity(intent)
        }
    }
}