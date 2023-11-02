package com.example.myapp_test_7_8_9_10_11_12.ch16_Test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test_7_8_9_10_11_12.ch16_Test.adapter.MultiImageAdapter
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityMultiImageRecyclerBinding

class MultiImageRecyclerActivity : AppCompatActivity() {
    // 전역
    lateinit var binding : ActivityMultiImageRecyclerBinding
    // 사진의 위치 Uri를 가지는 리스트를 하나 만들기.
    var list = ArrayList<Uri>()
    var adapter = MultiImageAdapter(list,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiImageRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 갤러리 버튼 클릭시, 이벤트 처리하는 부분, -> 후처리, 재사용하고, 속성 몇개 고치기
        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

            // 추가
            list.clear()
            // 사진 여러 장을 선택시 사용할 키워드 : clipData
            if(it.data?.clipData != null) {
                // 선택된 사진 갯수
                // 10장까지 제한하는 로직
                val count = it.data!!.clipData?.itemCount
                if ( count !=null) {
                    if(count > 10) {
                        Toast.makeText(this,"사진은 10장까지만 가능",Toast.LENGTH_SHORT).show()
                    }
                }
                // 10장까지 제한하는 로직

                // 반복문으로 , 사진 데이터의 Uri 조회해서 -> 리스트에 담기
                for(i in 0 until count!!) {
                    // 여러 사진이 이터러블 (반복가능한 형태, 인덱스 있다.) 각 요소의 사진 Uri 가져오기.
                    val imageUri = it.data!!.clipData?.getItemAt(i)?.uri
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
                // 반복문으로 , 사진 데이터의 Uri 조회해서 -> 리스트에 담기

                //  if(it.data?.clipData != null) { 의 반대 경우, 한장 인경우
            } else {
                it.data.let {
                        uri -> val imageUri : Uri? = it.data?.data
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
            }
            adapter.notifyDataSetChanged()

        }
        // 후처리 닫는 블록

        // 버튼클릭시 : 갤러리 앱 호출,
        binding.multiGalleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            // 사진을 여러장 고르는 속성 추가. 시스템에게 사진 여러장 선택 한다는 신고.
            // 추가1
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            intent.setType("image/*")

            // 사진에 접근 하겠다는 정해진 액션 문자열
            // 추가2
            intent.action = Intent.ACTION_GET_CONTENT
            requestGalleryLauncher.launch(intent)

        }

        // 리사이클러뷰 준비 재료 1) 어댑터 2) 뷰홀더 3) 목록 요소의 레이아웃 구성.
        val layoutManager = LinearLayoutManager(this)
        binding.multiImagePickRecycler.layoutManager = layoutManager
        binding.multiImagePickRecycler.adapter = adapter


    }
    // onCreate


}