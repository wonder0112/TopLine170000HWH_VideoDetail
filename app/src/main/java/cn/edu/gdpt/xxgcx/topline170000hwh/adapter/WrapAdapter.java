package cn.edu.gdpt.xxgcx.topline170000hwh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WrapAdapter<T extends RecyclerView.Adapter> extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final T mRealAdapter;//当前适配器
    private static final int BASE_HEADER_VIEW_TYPE = -1 << 10;//头部位置标志
    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>();//头部视图集合类
    public class FixedViewInfo { //头部数据结构
        public View view;
        public int viewType;
    }
    public WrapAdapter(T adapter) {//构造方法，将列表适配器赋值给当前适配器
        super();
        mRealAdapter = adapter;
    }
    public void addHeaderView(View view) {//添加头部视图
        if (null == view) {
            throw new IllegalArgumentException("the view to add must not be null!");
        }
        final FixedViewInfo info = new FixedViewInfo();
        info.view = view;
        info.viewType = BASE_HEADER_VIEW_TYPE + mHeaderViewInfos.size();
        mHeaderViewInfos.add(info);
        notifyDataSetChanged();
    }
    private boolean isHeader(int viewType) {//判断是否为头部
        return viewType >= BASE_HEADER_VIEW_TYPE
                && viewType < (BASE_HEADER_VIEW_TYPE + mHeaderViewInfos.size());
    }
    private boolean isHeaderPosition(int position) {//判断是否为头部位置
        return position < mHeaderViewInfos.size();
    }
    @NonNull
    @Override//加载视图
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (isHeader(viewType)) {
            int whichHeader = Math.abs(viewType - BASE_HEADER_VIEW_TYPE);
            View headerView = mHeaderViewInfos.get(whichHeader).view;
            return new RecyclerView.ViewHolder(headerView){};
        } else {
            return mRealAdapter.onCreateViewHolder(viewGroup, viewType);
        }
    }

    @Override//绑定视图数据
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (position < mHeaderViewInfos.size()) {
        } else if (position < mHeaderViewInfos.size() + mRealAdapter.getItemCount())
        {
            mRealAdapter.onBindViewHolder(viewHolder,
                    position - mHeaderViewInfos.size());
        }
    }
    @Override
    public int getItemCount() {
        return mHeaderViewInfos.size() + mRealAdapter.getItemCount();
    }
    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return mHeaderViewInfos.get(position).viewType;
        } else {
            return mRealAdapter.getItemViewType(position - mHeaderViewInfos.size());
        }
    }
}
