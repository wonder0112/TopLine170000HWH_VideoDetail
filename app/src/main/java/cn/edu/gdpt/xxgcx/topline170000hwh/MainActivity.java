package cn.edu.gdpt.xxgcx.topline170000hwh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.xxgcx.topline170000hwh.Fragment.CountFragment;
import cn.edu.gdpt.xxgcx.topline170000hwh.Fragment.HomeFragment;
import cn.edu.gdpt.xxgcx.topline170000hwh.Fragment.MeFragment;
import cn.edu.gdpt.xxgcx.topline170000hwh.Fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private TextView tv_main_title;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment> alFragment = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
        initListener();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        radioGroup.check(R.id.rb_home);
                        tv_main_title.setText("首页");
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_count);
                        tv_main_title.setText("统计");
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_video);
                        tv_main_title.setText("视频");
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_me);
                        tv_main_title.setText("我");
                        break;
                }
            }
            @Override
            public void onPageScrolled(int i, float v, int i1) {           }
            @Override
            public void onPageScrollStateChanged(int i) {            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        viewPager.setCurrentItem(0, false);break;
                    case R.id.rb_count:
                        viewPager.setCurrentItem(1, false);break;
                    case R.id.rb_video:
                        viewPager.setCurrentItem(2, false); break;
                    case R.id.rb_me:
                        viewPager.setCurrentItem(3, false);break;
                }
            }
        });
    }
    private void initViewPager() {
        alFragment.add(new HomeFragment());
        alFragment.add(new CountFragment());
        alFragment.add(new VideoFragment());
        alFragment.add(new MeFragment());
        viewPager.setOffscreenPageLimit(3); //三个界面之间来回切换都不会重新加载数据。
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return alFragment.get(i);
            }
            @Override
            public int getCount() {
                return alFragment.size();
            }
        });
        viewPager.setCurrentItem(0);
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }
}
