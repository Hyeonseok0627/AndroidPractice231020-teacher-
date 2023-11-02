package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.Practice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityJoinDbTestBinding

class JoinDbTestActivity : AppCompatActivity() {
    // 전역으로 선언만 했지, 할당을 안했음.
    // 그래서, onCreate 라는 함수에서 , 최초 1회 실행시.
    // 할당을 하는 구조.
    var myDB: DatabaseHelper? = null

    lateinit var binding: ActivityJoinDbTestBinding
    // 전역으로 쓰기 위해 여기에 전역변수로 작성함
    var editTextNick: EditText? = null
    var editTextName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var editTextID: EditText? = null
    var buttonInsert: Button? = null
    var buttonView: Button? = null
    var buttonUpdate: Button? = null
    var buttonDelete: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// 전역에 선언된 변수들을 할당하는 구조.
        binding = ActivityJoinDbTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_main)
        //DatabaseHelper 클래스 를 사용한다.
        // 객체 생성한다. ->
        myDB = DatabaseHelper(this) // 이러면 crud 가능

        // 자바 버전에 코드 -> 코틀린 변경.
        // findViewById ->  바인딩 기법으로 사용했음.
        editTextNick = binding.editTextNick
        editTextName = binding.editTextName
        editTextEmail = binding.editTextEmail
        editTextPassword = binding.editTextPassword
        editTextID = binding.editTextID
        buttonInsert = binding.buttonInsert
        buttonView = binding.buttonView
        buttonUpdate = binding.buttonUpdate
//        buttonDelete = findViewById(R.id.buttonDelete) //이렇게 써도 되긴하는데, 쉽게 사용하려고 binding한 것
        buttonDelete = binding.buttonDelete
        // 최초 1회 실행시, 직접 만든 함수를 호출하는 부분.
        // 현재, 하나의 화면만 테스트를 하니, 즉, 다른 화면 이동이 없어서,
        // 액티비티 생명주기에서, 각 화면이 수행이 되는 구조를 이해하면,
        // 조금 더 효율적인 자원소모를 할 수 있음.
        AddData()
        viewAll()
        UpdateData()
        DeleteData()
    }

    //데이터베이스 추가하기
    fun AddData() {
        buttonInsert!!.setOnClickListener {
            val isInserted = myDB!!.insertData(
                editTextNick!!.text.toString(),
                editTextName!!.text.toString(),
                editTextEmail!!.text.toString(),
                editTextPassword!!.text.toString()
            )
            if (isInserted == true)
                Toast.makeText(this@JoinDbTestActivity, "데이터추가 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@JoinDbTestActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()
        }
    }

    // 데이터베이스 읽어오기
    fun viewAll() {
        // SAM(Single Abstrac Method) : 함수형 인터페이스, 추상 메서드가 하나인 메서드.
        // 람다식으로 표현할 때, 자주 이용되는 기법 중 하나임.
        buttonView!!.setOnClickListener(View.OnClickListener {
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }
            //결과가 있다면.
            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성안함.

            val buffer = StringBuffer()
            //res 형 ->Cursor , 쉽게 엑셀 마치 테이블 , 0행부터 시작한다.
            // res.moveToNext() -> 1행을 의미.
            while (res.moveToNext()) {
                buffer.append(
                    //코틀린 3중 따옴표, 멀티 라인.(해당 내부 부분은 전부 하나의 문자열이 됨)
                    // 1행의 첫번째 컬럼을 가져오기.
                    """
    ID: ${res.getString(0)} 
    
    """.trimIndent()
                )
                buffer.append(
                        """
    아이디: ${res.getString(1)} 
    
    """.trimIndent()
                        )
                buffer.append(
                    """
    이름: ${res.getString(2)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    이메일: ${res.getString(3)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    주소: ${res.getString(4)}
    
    
    """.trimIndent()
                )
            }
            ShowMessage("데이터", buffer.toString())
        })
    }

    //데이터베이스 수정하기
    fun UpdateData() {
        buttonUpdate!!.setOnClickListener {
            val isUpdated = myDB!!.updateData(
                editTextID!!.text.toString(),
                editTextNick!!.text.toString(),
                editTextName!!.text.toString(),
                editTextEmail!!.text.toString(),
                editTextPassword!!.text.toString()
            )
            if (isUpdated == true)
                Toast.makeText(this@JoinDbTestActivity, "데이터 수정 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@JoinDbTestActivity, "데이터 수정 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    // 데이터베이스 삭제하기
    fun DeleteData() {
        buttonDelete!!.setOnClickListener {
            val deleteRows = myDB!!.deleteData(editTextID!!.text.toString())
            if (deleteRows > 0)
                Toast.makeText(this@JoinDbTestActivity, "데이터 삭제 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@JoinDbTestActivity, "데이터 삭제 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    // 사용자 정의 다이얼로그창을 자주 이용할 때 사용하는 기본 샘플 코드.
    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
}