package cn.edu.gdpt.xxgcx.topline170000hwh.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.xxgcx.topline170000hwh.Fragment.AdBannerFragment;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.NewsBean;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<NewsBean> abl;
    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        abl = new ArrayList<NewsBean>();//广告数据集合类对象
    }
    public void setData(List<NewsBean> abl) {//设置数据更新界面
        this.abl = abl;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {//反馈回每个ViewPager视图
        Bundle args = new Bundle();
        if (abl.size() > 0)
            args.putSerializable("ad", abl.get(index % abl.size()));
        return AdBannerFragment.newInstance(args);
    }
    @Override
    public int getCount() {
        return abl == null ? 0 : abl.size();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;//防止刷新结果显示列表的时候出现缓存数据,重载这个函数,使之默认返回POSITION_NONE
    }
}
