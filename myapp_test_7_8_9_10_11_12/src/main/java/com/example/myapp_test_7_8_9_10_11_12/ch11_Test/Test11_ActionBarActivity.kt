package com.example.myapp_test_7_8_9_10_11_12.ch11_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest11ActionBarBinding

class Test11_ActionBarActivity : AppCompatActivity() {
    // activityTest11ActionBarBinding 인스턴스 생성(여기에 관련 뷰들을 넣어서 꺼내서 쓰기 편하게 묶어두는 것)
    lateinit var activityTest11ActionBarBinding: ActivityTest11ActionBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTest11ActionBarBinding = ActivityTest11ActionBarBinding.inflate(layoutInflater)
        setContentView(activityTest11ActionBarBinding.root)

        // 액션바에 업버튼 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        }

    // 액션바 클릭 이벤트 처리.
    override fun onSupportNavigateUp(): Boolean {
        Log.d("lhs","test")
        onBackPressed() // 뒤로가기(이전화면으로 넘어가기)
        return super.onSupportNavigateUp()
    }

    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)


        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_main_search)
        // menuItem의 형을 SearchView 타입으로 변환, 형변환
        //?:널 허용 연산
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어가 변경 시 마다, 실행될 로직을 추가.
                Log.d("lhs","텍스트 변경시 마다 호출 : ${newText}") // 문자열에서 데이터값 가져올 때 ${}사용
                return  true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 DB에 검색을 하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@Test11_ActionBarActivity,"검색어가 전송됨 : ${query}",Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    // 오버플로우 메뉴 이벤트 핸들러 추가하기.
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.menu_main1 -> {
            Toast.makeText(this@Test11_ActionBarActivity,"메뉴1 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_main2 -> {
            Toast.makeText(this@Test11_ActionBarActivity,"메뉴2 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_main3 -> {
            Toast.makeText(this@Test11_ActionBarActivity,"메뉴3 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_main4 -> {
            Toast.makeText(this@Test11_ActionBarActivity,"메뉴4 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }

        // 람다식(화살표 함수("->"))에서 return 사용 못함.
        else ->  super.onOptionsItemSelected(item)
    }


}