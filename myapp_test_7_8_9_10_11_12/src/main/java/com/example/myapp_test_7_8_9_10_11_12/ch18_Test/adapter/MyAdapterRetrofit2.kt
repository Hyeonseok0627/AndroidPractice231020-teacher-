package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.newsModel.ItemModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit.MyApplication
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemRetrofit2Binding
import com.example.myapp_test_7_8_9_10_11_12.databinding.ItemRetrofitBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 매개변수에는, 목록 요소의 뷰가,
// 이름 : item_retrofit.xml(레이아웃 파일은 소문자만 됨)
// 왼쪽 : 이미지 하나, 오른쪽에 : 세로 방향으로 텍스뷰 3개 정도 생성.
class MyViewHolderRestrofit2 (val binding: ItemRetrofit2Binding)
    : RecyclerView.ViewHolder(binding.root)

//리사이클러 뷰 준비물 1) 어댑터 2) 뷰홀더 3) 목록 요소의 뷰 4) 데이터
// 매개변수 구성 1) context, 2) 데이터
// datas -> [모델1, 모델2, 모델3,...]
// 클래스의 주생성자, 클래스명 뒤에 오는 소괄호 안에 내용. constructor생략하고 많이 씀.
// val context : Context, val datas : List<UserModel>? ,
// 변수에 타입을
// 그래서, 밑에 특정 함수 내부에서, datas를 쉽게 접근 및 사용이 가능.
class MyAdapterRetrofit2(val context : Context, val datas : MutableList<ItemModel>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    // 틀 요소 할 때,
    // 변경되는 부분은 목록 요소 뷰홀더 부분
    // 1) MyViewHolderRetrofit
    // 2) ItemRetrofitBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : RecyclerView.ViewHolder {
        // 목록 요소를 해당 어댑터에 적용한다.
        return MyViewHolderRestrofit2(
            ItemRetrofit2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 이코드 그대로 통으로 재사용.
    override fun getItemCount(): Int {
        // ? : 널 허용 연산자, ?: : 엘비스 연산자,
        // 해당 값이 있으면, 그 값을 사용하고 : datas.size  사용하고,
        // 이게 만약 널이라고 한다면, 엘비스 연산자 ?: 다음의 기본값을 사용하세요. (이런의미)
        return datas?.size ?: 0
    }

    // 실제로 해당 뷰에, 데이터를 연결하는 부분.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // 내부에서 뷰 작업 쉽게 하려고, 뷰 홀더로 형 변환 후 , 바인딩 재할당.
        val binding = (holder as MyViewHolderRestrofit2).binding

        // datas에 담겨진 모델을 하나씩 꺼내서, 뷰에 데이터를 붙이는 작업.
        // 리스트의 각 요소마다 하나씩 꺼내어, 임의의 user 변수에 담기.

        val item = datas?.get(position)
        // 뷰에 데이터 내용 붙이기. 1) 일반 텍스트 먼저 붙이기.
        binding.retrofitTitleView.text = item?.title
        binding.retrofitContentView.text = item?.content



        // glide 통해서, 이미지를 직접 가져와서 처리하는 부분. (URL 주소만 있으면 이렇게 받아올 수 있음-1번 방법보다 더 코드가 적어짐)
        // 방법2)
        Glide.with(context)
            // load 실제 URL 주소 직접 넣기
            .load(item?.urlToImage)
            .override(700,300)
            .into(binding.retrofitProfileImg2)

    }

}