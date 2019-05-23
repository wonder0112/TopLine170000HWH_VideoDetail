package cn.edu.gdpt.xxgcx.topline170000hwh.bean;

import java.io.Serializable;

public class NewsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id; //新闻Id
    //若type为1（黑马新闻）显示一张图片的布局，为2（黑马推荐）显示三张图片的布局, 为3时广告图片
    private int type;
    private String newsName;      //新闻名称
    private String newsTypeName; //新闻类型，是黑马新闻还是黑马推荐
    private String img1;           //新闻图片1
    private String img2;      //新闻图片2
    private String img3;     //新闻图片3
    private String newsUrl; //新闻链接

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsTypeName() {
        return newsTypeName;
    }

    public void setNewsTypeName(String newsTypeName) {
        this.newsTypeName = newsTypeName;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
