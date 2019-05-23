package cn.edu.gdpt.xxgcx.topline170000hwh.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.gdpt.xxgcx.topline170000hwh.bean.NewsBean;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.PythonBean;
import cn.edu.gdpt.xxgcx.topline170000hwh.bean.VideoBean;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse() {
    }
    public static JsonParse getInstance() {
        if (instance == null) {
            instance = new JsonParse();
        }
        return instance;
    }
    public List<NewsBean> getAdList(String json) {
        Gson gson = new Gson();//使用gson库解析JSON数据

        Type listType = new TypeToken<List<NewsBean>>() {//创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        }.getType();
        List<NewsBean> adList = gson.fromJson(json, listType); //把获取到的信息集合存到adList中
        return adList;
    }
    public List<NewsBean> getNewsList(String json) {
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<NewsBean>>() {
        }.getType();
        //把获取到的信息集合存到newsList中
        List<NewsBean> newsList = gson.fromJson(json, listType);
        return newsList;
    }
    public List<PythonBean> getPythonList(String json) {
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<PythonBean>>() {}.getType();
        //把获取到的信息集合存到pythonList中
        List<PythonBean> pythonList = gson.fromJson(json, listType);
        return pythonList;
    }
    public List<VideoBean> getVideoList(String json) {
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<VideoBean>>() {
        }.getType();
        //把获取到的信息集合存到videoList中
        List<VideoBean> videoList = gson.fromJson(json, listType);
        return videoList;
    }
}
