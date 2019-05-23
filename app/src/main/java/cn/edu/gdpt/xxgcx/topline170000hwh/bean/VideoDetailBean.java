package cn.edu.gdpt.xxgcx.topline170000hwh.bean;

import java.io.Serializable;

public class VideoDetailBean implements Serializable {
    /**
     * video_id : DA2D015D371417299C33DC5901307461
     * video_name : 01-jQuery初体验
     */
    private static final long serialVersionUID = 1L;
    private String video_id;
    private String video_name;

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }
}
