package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.Practice

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//출처: https://jw0652.tistory.com/590
// 변경 사항은, 자바 -> 코틀린 형식으로 변경.
// 뷰 객체 선택 방법이 findViewById -> 뷰 바인딩 형식으로 변경.
// 이 외에는 다 동일
// 데이터베이스 하나하나를 스키마라고 부름
class DatabaseHelper(context: Context?) : SQLiteOpenHelper
    (context, DATABASE_NAME, null, 1) {
    // 1번째 매개변수 context -> this 현재 호출이 된 액티비티를 의미.
    // 2번째 매개변수 데이터베이스 파일명 ->
    // 예) DATABASE_NAME, 클래스 변수로 선언해서 상수로 사용중
    // 4번째 매개변수 , 현재 사용하는 버전을 의미하고,
    // 이버전이 변경이 된다면, 아래에 있는 onUpgrade 함수 재 호출됨.
    override fun onCreate(db: SQLiteDatabase) {
        // 앱이 설치후, 해당 클래스가 최초에 실행이 되면,
        // onCreate 함수가 최초 1회 호출.
        db.execSQL("create table " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, ADDRESS TEXT)")
    }
    // ID INTEGER PRIMARY KEY AUTOINCREMENT: mysql에서 pk는 겹치면 안되니,
    // 새로 생성될때마다 번호를 자동으로 1씩 증가해서 매겨서 다름을 인식하게 한 것과 유사

    // 이 클래스 사용하면, 지정된 데이터베이스 파일에, 테이블이 생성이 됩니다.
    // 생성된 물리 경로 주소.
    // 에뮬레이터 탐색기 -> data->data -> 패키지명:com.example.test17_crud
    // -> databases , 파일명으로 있음.

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 위에 SQLiteOpenHelper
        //    (context, DATABASE_NAME, null, 1)
        // 4번째 매개변수가 변경이 될때마다, 실행이 되는 함수.
        // 예를 들어) 버전 ->2 로 변경시 아래에 함수가 실행됨.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // 데이터베이스 추가하기 insert
    fun insertData(nick: String?, name: String?, email: String?, password: String?): Boolean {
        // 디비 사용시 쓰기, 수정, 삭제 ->writableDatabase 사용함.
        val db = this.writableDatabase
        // execSQL -> 대신에 ContentValues() 를 이용하면
        // SQL  문장 없이, 바로 메서드에 값만 인자로 넣어서
        // 이용하면, 쉽게 insert를 구현가능.
        val contentValues = ContentValues()
        contentValues.put(COL_2, nick)
        contentValues.put(COL_3, name) //contentValues : 생성자, 이 생성자 이용해서 setter하여 key, value를 넣는 것
        contentValues.put(COL_4, email)
        contentValues.put(COL_5, password)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) false else true
    }

    //데이터베이스 항목 읽어오기 Read(데이터 조회 부분)
    // allData, 데이터베이스의 조회한 값이 테이블 형태로 들어 있다고 생각하면 됨.
    // Cursor -> 테이블인 개념
    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TABLE_NAME", null) //조회만 rawQuery 이것을 사용하고, 나머지는 다 execSQL 이것을 사용
        }

    // 데이터베이스 삭제하기
    fun deleteData(id: String): Int {
        val db = this.writableDatabase //db에 접근하기 위해선 해당 인스턴스 있어야 함
        return db.delete(TABLE_NAME, "ID = ? ", arrayOf(id))
    }

    //데이터베이스 수정하기
    fun updateData(id: String, nick: String?, name: String?, email: String?, password: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, nick)
        contentValues.put(COL_3, name)
        contentValues.put(COL_4, email)
        contentValues.put(COL_5, password)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }
    //저장
    // 자바(static) , 클래스 변수 -> 클래스명.  접근.
    companion object {
        // DatabaseHelper 클래스명 -> mydb
        // mydb.DATABASE_NAME -> 사용가능.
        const val DATABASE_NAME = "CUSTOMER.db" // 데이터베이스 명
        const val TABLE_NAME = "customer_table" // 테이블 명

        // 테이블 항목
        const val COL_1 = "ID"
        const val COL_2 = "Nick"
        const val COL_3 = "Name"
        const val COL_4 = "Email"
        const val COL_5 = "Password"
    }
}