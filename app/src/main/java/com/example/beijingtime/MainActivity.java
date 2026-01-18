package com.example.beijingtime;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TextView timeTextView;
    private TextView dateTextView;
    private Handler handler;
    private Runnable updateTimeRunnable;
    
    // 使用北京时间时区
    private static final String BEIJING_TIMEZONE = "Asia/Shanghai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);
        dateTextView = findViewById(R.id.dateTextView);
        
        handler = new Handler();
        
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                updateBeijingTime();
                // 每秒更新一次
                handler.postDelayed(this, 1000);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 开始更新时间
        handler.post(updateTimeRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 停止更新时间
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void updateBeijingTime() {
        // 创建北京时间的SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        
        // 设置时区为北京时间
        dateFormat.setTimeZone(TimeZone.getTimeZone(BEIJING_TIMEZONE));
        timeFormat.setTimeZone(TimeZone.getTimeZone(BEIJING_TIMEZONE));
        
        Date now = new Date();
        
        String dateStr = dateFormat.format(now);
        String timeStr = timeFormat.format(now);
        
        // 更新UI
        dateTextView.setText(dateStr);
        timeTextView.setText(timeStr);
    }
}