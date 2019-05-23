package cn.edu.gdpt.xxgcx.topline170000hwh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }
    private void init() {
        Timer timer = new Timer();//定时器
        TimerTask task=new TimerTask() {//定时任务
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);//打开主界面
                SplashActivity.this.finish();//关闭欢迎界面
            }
        };
        timer.schedule(task,500);//延迟3秒这些
    }
}
