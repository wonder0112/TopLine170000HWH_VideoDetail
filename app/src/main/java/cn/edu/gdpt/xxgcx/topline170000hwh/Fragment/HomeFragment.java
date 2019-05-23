package cn.edu.gdpt.xxgcx.topline170000hwh.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;
import cn.edu.gdpt.xxgcx.topline170000hwh.activity.PythonActivity;
import cn.edu.gdpt.xxgcx.topline170000hwh.adapter.AdBannerAdapter;
import cn.edu.gdpt.xxgcx.topline170000hwh.adapter.HomeListAdapter;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.NewsBean;
import cn.edu.gdpt.xxgcx.topline170000hwh.utils.Constant;
import cn.edu.gdpt.xxgcx.topline170000hwh.utils.JsonParse;
import cn.edu.gdpt.xxgcx.topline170000hwh.utils.UtilsHelper;
import cn.edu.gdpt.xxgcx.topline170000hwh.view.WrapRecyclerView;

/**
 * A simple {@link Fragment} subclass.

 */
public class HomeFragment extends Fragment {
    private PullToRefreshView mPullToRefreshView;
    private WrapRecyclerView recycleView;
    private HomeListAdapter adapter;
    private View adBannerLay;//广告部分外框
    private ViewPager adPager;//广告翻页控件
    private AdBannerAdapter ada;//翻页控件适配器
    private OkHttpClient okHttpClient;//网络访问客户端
    private MHandler mHandler;
    public HomeFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        okHttpClient = new OkHttpClient();
        mHandler = new MHandler();
        getADData();
        getNewsData();
        View view = initView(inflater, container);
        return view;
    }
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        String adResult = (String) msg.obj;
                        List<NewsBean> adl = JsonParse.getInstance().
                                getAdList(adResult);//Json解析
                        if (adl != null) {
                            if (adl.size() > 0) {
                                ada.setData(adl);
                            }
                        }
                    }
                    break;
                case 2:
                    if (msg.obj != null) {
                        String newsResult = (String) msg.obj;
                        List<NewsBean> nbl = JsonParse.getInstance().
                                getNewsList(newsResult);
                        if (nbl != null) {
                            if (nbl.size() > 0) {
                                adapter.setData(nbl);
                            }
                        }
                    }
                    break;
            }
        }
    }
    private void getADData() {//读取广告Json数据
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_AD_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {//开启异步线程访问网络
            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                //Log.i("AD",res);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Request arg0, IOException arg1) {
            }
        });
    }
    private void getNewsData() {//读取新闻Json数据
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_NEWS_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {//开启异步线程访问网络
            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = 2;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Request arg0, IOException arg1) {
            }
        });
    }
    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        recycleView = (WrapRecyclerView) view.findViewById(R.id.recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));//布局方式
        adapter = new HomeListAdapter(getActivity());//列表数据适配器
        recycleView.setAdapter(adapter);
        View headView = inflater.inflate(R.layout.head_view, container, false);
        recycleView.addHeaderView(headView);//添加头部
        adBannerLay = headView.findViewById(R.id.adbanner_layout);
        adPager = (ViewPager) headView.findViewById(R.id.slidingAdvertBanner);
        ada = new AdBannerAdapter(getActivity().getSupportFragmentManager());
        adPager.setAdapter(ada);
        resetSize();

        LinearLayout ll_python = (LinearLayout) headView.findViewById(R.id.ll_python);
        ll_python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PythonActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void resetSize() {
        int sw = UtilsHelper.getScreenWidth(getActivity());//屏幕宽带
        int adLheight = sw / 2; //广告条高度
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();//广告部分外框
        adlp.width = sw;//设置宽度
        adlp.height = adLheight;//设置高度
        adBannerLay.setLayoutParams(adlp);
    }
}
