package com.example.myapp_test6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6.SQLiteDB.DatabaseHelper
import com.example.myapp_test6.SQLiteDB.adapter.MultiImageAdapter
import com.example.myapp_test6.databinding.ActivityUserTableBinding
import com.example.myapp_test6.model.User

class UserTableActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserTableBinding
    var list = ArrayList<User>()
    var adapter = MultiImageAdapter(list,this)
    var getUserBtn: Button? = null
    var myDB: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserBtn = binding.getUserBtn
        myDB = DatabaseHelper(this)

        // 데이터 가져오기.


        //리사이클러뷰 붙이기
        val layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.layoutManager = layoutManager
        binding.userRecyclerView.adapter = adapter


        binding.getUserBtn.setOnClickListener {
            viewAll()
        }

        binding.joinUserBtn.setOnClickListener {
            val intent = Intent(this@UserTableActivity,JoinActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
    // onCreate
    // 방법2
    fun viewAll() {
        // SAM (Single Abstrac Method) 함수형 인터페이스, 추상 메서드가 하나인 메서드.
        // 람다식으로 표현 할 때 , 자주 이용되는 기법 중 하나임.
        getUserBtn!!.setOnClickListener(View.OnClickListener {
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            // res -> Cursor = 테이블
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }

            list.clear()

            while (res.moveToNext()) {
                var user : User? = null

                val ID = res.getString(0)
                val EMAIL = res.getString(1)
                val PASSWORD = res.getString(2)
                val PROFILEURI = res.getString(3)
                user = User(ID,EMAIL,PASSWORD,PROFILEURI)
                list.add(user)
            }
            adapter.notifyDataSetChanged()
        })
    }

    // 출력 됨. 기존 방법1
//    fun viewAll() {
//        // SAM (Single Abstrac Method) 함수형 인터페이스, 추상 메서드가 하나인 메서드.
//        // 람다식으로 표현 할 때 , 자주 이용되는 기법 중 하나임.
//        getUserBtn!!.setOnClickListener(View.OnClickListener {
//            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
//            // res -> Cursor = 테이블
//            val res = myDB!!.allData
//            // 결과가 없을 때
//            if (res.count == 0) {
//                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
//                return@OnClickListener
//            }
//            //결과가 있다면.
//            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
//            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성안함.
//
//            val buffer = StringBuffer()
//            //res 형 ->Cursor , 쉽게 엑셀 마치 테이블 , 0행부터 시작한다.
//            // res.moveToNext() -> 1행을 의미.
//            while (res.moveToNext()) {
//                buffer.append(
//                    //코틀린 3중 따옴표, 멀티 라인.
//                    // 1행의 첫번째 컬럼을 가져오기.
//                    """
//    ID: ${res.getString(0)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    email: ${res.getString(1)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    password: ${res.getString(2)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    profileUri: ${res.getString(3)}
//
//
//    """.trimIndent()
//                )
//            }
//            ShowMessage("데이터", buffer.toString())
//        })
//    }
    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    //end
}