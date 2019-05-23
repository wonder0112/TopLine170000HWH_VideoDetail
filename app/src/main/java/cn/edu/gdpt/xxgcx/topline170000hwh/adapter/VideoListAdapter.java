package cn.edu.gdpt.xxgcx.topline170000hwh.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;
import cn.edu.gdpt.xxgcx.topline170000hwh.activity.VideoDetailActivity;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.VideoBean;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.VideoDetailBean;

import static android.support.constraint.Constraints.TAG;

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VideoBean> videoList;
    private Context context;

    public VideoListAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<VideoBean> videoList){
        this.videoList=videoList;
    }
    @Override
    public int getItemCount() {
        return videoList == null ? 0 : videoList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.
                layout.video_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img_round);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final VideoBean bean = videoList.get(i);
        Glide
                .with(context)
                .load(bean.getImg())
                .error(R.mipmap.ic_launcher)
                .into(((ViewHolder) viewHolder).iv_img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoDetailActivity.class);
                intent.putExtra("intro", bean.getIntro());
                intent.putExtra("videoDetailList", (Serializable) bean.getVideoDetailList());
                context.startActivity(intent);
            }
        });
    }
}
