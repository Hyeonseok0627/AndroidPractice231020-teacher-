package com.example.myapp_test_7_8_9_10_11_12.ch9_Test

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowMetrics
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.ch7_Test.TestActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest9Binding

class Test9Activity : AppCompatActivity() {
    // 바인딩의 이름 규칙은, 레이아웃 이름을 따라 갑니다.(자동생성)
    // 단, build.gradle 에서 설정을 반드시 하고, (모듈버전에서)
    // ex) activity_test9 -> ActivityTest9Binding
    lateinit var activityTest9Binding: ActivityTest9Binding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_test9)

        activityTest9Binding = ActivityTest9Binding.inflate(layoutInflater)
        setContentView(activityTest9Binding.root)

        activityTest9Binding.testImgRetangle.setOnClickListener {
            Toast.makeText(this@Test9Activity,"이미지 클릭됨", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Test9Activity,TestActivity::class.java)
            startActivity(intent)
        }
        //소스 코드로, 정적 자원 사용하기. 문자열
        activityTest9Binding.textView2.text ="Hello Testing App!!"
//        activityTest9Binding.textView2.text = getString(R.string.app_intro)

        // 해당 라이브러리의 기능을 확인 하는 부분 보다
        //  sdk 버전에 따라서, 라이브러리 패키지명 등, 다른 부분에 집중
        // 사용하는 라이브러리의 sdk 버전의 지원 여부 확인.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            Log.d("lhs", "width: ${windowMetrics.bounds.width()}, height: ${windowMetrics.bounds.height()}")
        } else {
            val display = windowManager.defaultDisplay
        }


        //10장의 내용. // 허가 확인 여부 테스트
        val status = ContextCompat.checkSelfPermission(this@Test9Activity,
            "android.permission.ACCESS_FINE_LOCATION")

        if (status == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this@Test9Activity, "위치 권한 승인됨", Toast.LENGTH_SHORT).show()
            Log.d("lhs", "권한이 승인됨 : ${status}")
        } else {
            Toast.makeText(this@Test9Activity, "위치 권한 승인안됨", Toast.LENGTH_SHORT).show()
            Log.d("lhs", "권한이 승인안됨 : ${status}")
        }

        // 후처리, 인텐트 기본적인 사용법 단순 페이지 이동만 사용했음.
        // 인텐트 ? -> 시스템에 메세지를 전달자.
        // 예) 페이지 이동 같은 경우, 시스템에 요청해서, 이동했음.
        // 예2) 특정 앱에 접근을 해서, 데이터를 가져오는 작업 (= 후처리)
        // 설정 1)
        val requestPermissionLauncher = registerForActivityResult(
            // 이 부분에 시스템에서 정해둔 함수들이 있음. 현재는, 허가를 확인하는 용도.
            // 나중에는, 이미지 등 데이터에 접근해서, 해당 데이터를 가지고 오는 용도로 사용할 예정.
            // 이것에 따라 ActivityResultContracts.RequestPermission() 이 부분이 바뀜
            ActivityResultContracts.RequestPermission() ) {
                isGranted ->
            if(isGranted) {
                Log.d("lsy","권한이 승인됨 , call back 후처리 요청. ")
            } else {
                Log.d("lsy","권한이 승인안됨 , call back 후처리 요청. ")
            }
        }
        // 이용 -> 호출, 위에 설정으로 감 / 여기에서 호출하면 67열 내용으로 가서 생성자 생성과 조건 설정하고, if절로 조건에 따라 후처리 요청해서 나옴
        requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")


        // activityTest9Binding.testToastBtn?.setOnClickListener 여기에서 ?.인 널 체크 안해주면 에러가 뜸
        activityTest9Binding.testToastBtn?.setOnClickListener {
            // 기존 사용법
            Toast.makeText(this@Test9Activity,"후처리 확인 중", Toast.LENGTH_LONG).show()
            // 콜백을 익명 클래스를 추가해서, 사라지거나, 또는 나타나거나 했을 경우 추가 로직 넣기.
            val toast = Toast.makeText(this@Test9Activity,"후처리 확인 중", Toast.LENGTH_LONG)
            toast.addCallback(
                object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("lhs", "토스트 후처리 작업: 사라질 경우")
                    val intent = Intent(this@Test9Activity,TestActivity::class.java)
                    startActivity(intent)
                }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("lhs", "토스트 후처리 작업: 나타날 경우")
                    }
                }
            )
            toast.show()
        }


        // 날짜 다이얼 로그 출력 해보기
        // ${month+1}월 : 기본이 0~11이니 +1해서 1~12월로 표기
        activityTest9Binding.dateBtn?.setOnClickListener {
            DatePickerDialog(this@Test9Activity, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    // 로그캣(콘솔)에 찍어보기
                    Log.d("lhs","년도: ${year}년, 월: ${month+1}월, 일: ${dayOfMonth}")
                    // 토스트로 구현해보기
                    Toast.makeText(this@Test9Activity,"년도: ${year}년, 월: ${month+1}월, 일: ${dayOfMonth}"
                        ,Toast.LENGTH_SHORT).show()

                    // 텍스트 뷰에 설정해보기.
                    activityTest9Binding.dateTextView?.text = "${year}년, ${month+1}월, ${dayOfMonth}일"
                }
            }, 2023,9,25).show()
        }

        // 시간 다이얼 로그 테스트 해보기.
        // 안드로이드스튜디오에서 기본으로 제공해주는 이게 맘에 안들면 dependency해서 외부 api불러오면 됨
        activityTest9Binding.timeBtn?.setOnClickListener {
            TimePickerDialog(this@Test9Activity, object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    // 로그캣(콘솔)에 찍어보기
                    Log.d("lhs","시간: ${hourOfDay}시, 분: ${minute}분")
                    // 토스트로 구현해보기
                    Toast.makeText(this@Test9Activity,"시간: ${hourOfDay}시, 분: ${minute}분"
                        ,Toast.LENGTH_SHORT).show()

                    // 텍스트 뷰에 설정해보기.
                    activityTest9Binding.timeTextView?.text = "${hourOfDay}시, ${minute}분"
                }
            }, 14, 21, true).show()
        }


        // 커스텀 마이징 한 다이얼로그 출력 해보기. -기본값
        activityTest9Binding.customDialogBtn?.setOnClickListener {
            AlertDialog.Builder(this@Test9Activity).run {
                setTitle("커스텀 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("테스트 할까요?")
                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                show()
            }
        }

        // 목록 요소 선택 1.
        val items = arrayOf<String>("사과","바나나","수박","파인애플")

        activityTest9Binding.customDialogBtn2?.setOnClickListener {
            AlertDialog.Builder(this@Test9Activity).run {
                setTitle("커스텀 다이얼로그2")
                setIcon(android.R.drawable.ic_dialog_info)
//                setMessage("테스트 할까요?")
                // 추가 사항
                val objectListener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("lhs","선택한 과일 : ${items[which]}")
                    }
                }
                setItems(items,objectListener)


                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                show()
            }
        }


        // 목록 요소 선택 2.

        activityTest9Binding.customDialogBtn3?.setOnClickListener {
            AlertDialog.Builder(this@Test9Activity).run {
                setTitle("커스텀 다이얼로그3")
                setIcon(android.R.drawable.ic_dialog_info)
//                setMessage("테스트 할까요?")
                // 추가 사항
//                val objectListener = object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        Log.d("lsy","선택한 과일 : ${items[which]}")
//                    }
//                }

                // 체크박스용 클릭 리스너
                // 각 뷰마다 리스너 이름이 다르니 주의!!
                val objectListener = object : DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Log.d("lhs","${items[which]}이 ${if(isChecked) "선택됨." else "선택 해제됨"}")
                    }
                }

                // 목록요소 1
//                setItems(items,objectListener)

                // 목록요소 2, 체크박스
                // 요소 4개 중 2개만 선택할 수 있게 함(true,true,false,false)
                setMultiChoiceItems(items, booleanArrayOf(true,true,false,false), objectListener)

                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                show()
            }
        }


        //목록 요소 선택3, 라디오
        activityTest9Binding.customDialogBtn4?.setOnClickListener {
            AlertDialog.Builder(this@Test9Activity).run {
                setTitle("커스텀 다이얼로그4")
                setIcon(android.R.drawable.ic_dialog_info)
                // 체크박스용 클릭 리스너 ,
//                val objectListener = object : DialogInterface.OnMultiChoiceClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
//                        Log.d("lsy","${items[which]}이 ${if(isChecked) "선택됨"  else "선택해제됨"}")
//                    }
//                }

                // 라디오 클릭 리스너
                val objectListener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("lsy","선택한 과일 : ${items[which]}")
                    }
                }
                // 목록요소 1
                //setItems(items,objectListener)

                // 목록요소2 , 체크박스
//                setMultiChoiceItems(items, booleanArrayOf(true,true,false,false),objectListener)

                // 목록 요소3, 라디오
                setSingleChoiceItems(items,1,objectListener )


                setPositiveButton("수락",null)
                setNegativeButton("취소",null)
                setNeutralButton("더보기",null)
                // 뒤로가기 버튼을 눌려도 ,알림창 닫아짐. 기본값.
                // 옵션으로 false 설정시, 창 닫힘 방지함.
                setCancelable(false)
                show()
                // 다이얼로그창이 나타났을 경우, 창 밖을 클릭 시
                // 기본이 알림창을 닫기가 기본인데, false
                // 창 밖을 클릭해도 창이 닫히지 않음.
            }.setCanceledOnTouchOutside(false)
        }

        // 소리 확인 테스트
        activityTest9Binding.soundTestBtn?.setOnClickListener {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()

        }


    }

}
