package com.example.myapp_test_7_8_9_10_11_12.ch10_Test

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent

import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest102Binding

class Test10_2Activity : AppCompatActivity() {
    // 여러 뷰를 한번에 쉽게 가져오도록 바인딩부터 작업을 시작
    lateinit var activityTest102Binding: ActivityTest102Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //추가
        activityTest102Binding = ActivityTest102Binding.inflate(layoutInflater)
        //변경
        setContentView(activityTest102Binding.root)

        //버튼의 요소를 선택해서, 알림을 보내는 로직을 추가할 예정
        activityTest102Binding.notiBtn.setOnClickListener {
            //알림 추가 설정.
            // getSystemService(NOTIFICATION_SERVICE) 결과 형이 object라서,
            // as NotificationManager -> 형 변환(다운캐스팅 개념)
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            //sdk 버전에 따라서, 분기, 기능의 패키지명 또는 구현 형식이 달라져서
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                // 채널 설정
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel (
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )

                // 채널의 정보 및 부가 옵션 설정.
                channel.description = "My Channel One 설정"
                // 알림의 갯수를 아이콘 표시
                channel.setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                channel.setSound(uri,audioAttributes)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                // 진동의 간격마다, 세기 주기 설정
                channel.vibrationPattern = longArrayOf(100,200,100,200)

                // Notification Manager에 채널 등록하기.
                manager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(this@Test10_2Activity, channelId)
            } else{
                // 26 버전 이하 버전 시 사용하는 형식.
                builder = NotificationCompat.Builder(this@Test10_2Activity)
            }

            // 아이콘, 테마, 툴바, 액션바 overlay 단어가 보이면 투명지, 뒤에 부분이 보인다.
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("알림 제목")
            builder.setContentText("알림의 메세지 내용")

            // 알림 메세지 창 클릭시, 페이지 이동. 기존에 사용했던 , 인텐트 방식과 비슷.
            val intent = Intent(this@Test10_2Activity,Test10_1Activity::class.java )
            val pendingIntent = PendingIntent.getActivity(this@Test10_2Activity,10,intent,
                PendingIntent.FLAG_IMMUTABLE)
            // 2번째 액션 인텐트 테스트 하기위해 잠시 주석.
            //builder.setContentIntent(pendingIntent)

            // 특정 액션 추가 기능 넣기. (첫번째 액션)
            val actionIntent = Intent(this@Test10_2Activity,OneReceiver::class.java)
            val actionPendingIntent = PendingIntent.getBroadcast(this@Test10_2Activity,20,
                actionIntent,PendingIntent.FLAG_IMMUTABLE)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "Action",
                    actionPendingIntent
                ).build()
            )


            // 특정 액션 추가 부분인데, 위에는 기본 액션 1개를 추가했고, 답장이라는 추가 액션 넣기. (두번째 액션)
            val KEY_TEXT_REPLY = "key_text_reply"
            val replyLabel : String = "답장"
            var remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }
            val replyIntent = Intent(this@Test10_2Activity, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(this@Test10_2Activity,30,
                replyIntent,PendingIntent.FLAG_MUTABLE)
            //답장 액션 추가하기.
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

            // 알림 발생 시키기
            manager.notify(11, builder.build())
        }
    }
}