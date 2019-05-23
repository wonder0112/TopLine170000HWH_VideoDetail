package cn.edu.gdpt.xxgcx.topline170000hwh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import cn.edu.gdpt.xxgcx.topline170000hwh.R;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.VideoDetailBean;

public class VideoDetailListAdapter extends BaseAdapter {
    private Context mContext;
    private List<VideoDetailBean> vdbl;
    public VideoDetailListAdapter(Context context) {
        this.mContext = context;
    }
       public void setData(List<VideoDetailBean> vdbl) {
        this.vdbl = vdbl;
        notifyDataSetChanged();
    }
        @Override
    public int getCount() {
        return vdbl == null ? 0 : vdbl.size();
    }
    @Override
    public VideoDetailBean getItem(int position) {
        return vdbl == null ? null : vdbl.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.video_detail_item, null);
            vh.title = (TextView) convertView.findViewById(R.id.tv_video_name);
            vh.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final VideoDetailBean bean = getItem(position);
        if (bean != null) {//赋值
            vh.title.setText(bean.getVideo_name());
            vh.iv_icon.setImageResource(R.drawable.iv_video_icon);
        }
        return convertView;
    }
    class ViewHolder {
        public TextView title;
        public ImageView iv_icon;
    }
}
