package cn.edu.gdpt.xxgcx.topline170000hwh;
import android.app.Application;
import android.widget.Toast;
import com.bokecc.sdk.mobile.drm.DRMServer;
public class TopLineApplication extends Application {
    private DRMServer drmServer;//视频播放时用到的服务
    @Override
    public void onCreate() {
        startDRMServer();
        super.onCreate();
    }
    //启动DRMServer
    public void startDRMServer() {
        if (drmServer == null) {
            drmServer = new DRMServer();
            drmServer.setRequestRetryCount(10);
        }
        try {
            drmServer.start();
            setDrmServerPort(drmServer.getPort());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "启动解密服务失败，请检查网络限制情况",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onTerminate() {
        if (drmServer != null) {
            drmServer.stop();
        }
        super.onTerminate();
    }
    private int drmServerPort;
    public int getDrmServerPort() {
        return drmServerPort;
    }
    public void setDrmServerPort(int drmServerPort) {
        this.drmServerPort = drmServerPort;
    }
    public DRMServer getDRMServer() {
        return drmServer;
    }
}
