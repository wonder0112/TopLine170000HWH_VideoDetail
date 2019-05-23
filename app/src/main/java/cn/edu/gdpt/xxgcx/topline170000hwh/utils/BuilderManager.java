package cn.edu.gdpt.xxgcx.topline170000hwh.utils;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;

public class BuilderManager {//单例模式
    private BuilderManager() {//私有化构造方法
    }
    private static BuilderManager ourInstance = new BuilderManager();//单例对象
    public static BuilderManager getInstance() {
        return ourInstance;
    }
    private static int[] imageResources = new int[]{ //9个菜单的随机选择的图片
            R.drawable.bat,            R.drawable.bear,
            R.drawable.bee,            R.drawable.butterfly,
            R.drawable.cat,            R.drawable.deer,
            R.drawable.dolphin,        R.drawable.eagle,
            R.drawable.horse,          R.drawable.elephant,
            R.drawable.owl,            R.drawable.peacock,
            R.drawable.pig,            R.drawable.rat,
            R.drawable.snake,          R.drawable.squirrel
    };
    private static int[] textResources = new int[]{ //9个菜单中的文本
            R.string.android,      R.string.java,
            R.string.python,       R.string.php,
            R.string.c,            R.string.ios,
            R.string.fore_end,     R.string.ui,
            R.string.network
    };

    private static int imageResourceIndex = 0;//初始化序号为0
    private static int textResourceIndex = 0;//初始化序号为0
    public static int getImageResource() {//返回图片
        if (imageResourceIndex >= imageResources.length)
            imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }
    public static int getTextResource() {//返回文本
        if (textResourceIndex >= textResources.length)
            textResourceIndex = 0;
        return textResources[textResourceIndex++];
    }
}