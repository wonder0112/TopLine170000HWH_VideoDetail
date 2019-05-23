package cn.edu.gdpt.xxgcx.topline170000hwh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;

public class AndroidCountActivity extends AppCompatActivity {
    private PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_count);
        chart = findViewById(R.id.chart);

        ArrayList<PieEntry> entries = new ArrayList<>();//切片大小和文本
        entries.add(new PieEntry(4, "月薪8-15K"));
        entries.add(new PieEntry(3, "月薪15-20K"));
        entries.add(new PieEntry(2, "月薪20-30K"));
        entries.add(new PieEntry(1, "月薪30K+"));
        PieDataSet dataSet = new PieDataSet(entries,"月薪");

        dataSet.setColors(new int[]{R.color.color_green,R.color.color_violet,
                R.color.color_blue,R.color.color_orange},getApplicationContext());//设置颜色
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.setHoleRadius(0f);//设置内圆半径为0
        chart.setTransparentCircleRadius(0f);//设置半透明半径为0
        dataSet.setDrawValues(false);//不显示数值
        chart.setEntryLabelTextSize(16f);//文本大小
    }
}
