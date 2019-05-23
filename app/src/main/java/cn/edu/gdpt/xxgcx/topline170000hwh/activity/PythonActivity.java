package cn.edu.gdpt.xxgcx.topline170000hwh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;
import cn.edu.gdpt.xxgcx.topline170000hwh.adapter.PythonListAdapter;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.PythonBean;
import cn.edu.gdpt.xxgcx.topline170000hwh.utils.Constant;
import cn.edu.gdpt.xxgcx.topline170000hwh.utils.JsonParse;
import cn.edu.gdpt.xxgcx.topline170000hwh.view.WrapRecyclerView;

public class PythonActivity extends AppCompatActivity {
    private PullToRefreshView mPullToRefreshView;
    private WrapRecyclerView recycleView;
    private MHandler mHandler;
    private PythonListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        mHandler = new MHandler();

        initData();
        intiView();
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //使用Gson解析数据
                        List<PythonBean> pythonList = JsonParse.getInstance().
                                getPythonList(vlResult);
//                        Toast.makeText(getApplicationContext(),String.valueOf(pythonList.size()),Toast.LENGTH_LONG).show();
                        adapter.setData(pythonList);//适配器数据赋值
                    }
                    break;
            }
        }
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_PYTHON_URL).build();
        Call call = okHttpClient.newCall(request);
        //开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
//                Log.i("Python", res);
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

    private void intiView() {
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.
                OnRefreshListener(){
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        initData();
                    }
                }, 1000);
            }
        });

        recycleView = (WrapRecyclerView) findViewById(R.id.recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PythonListAdapter();
        recycleView.setAdapter(adapter);
    }
}
