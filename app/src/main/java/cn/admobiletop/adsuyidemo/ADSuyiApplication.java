package cn.admobiletop.adsuyidemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.tencent.bugly.crashreport.CrashReport;

import cn.admobiletop.adsuyi.ADSuyiSdk;
import cn.admobiletop.adsuyi.config.ADSuyiInitConfig;
import cn.admobiletop.adsuyidemo.activity.SplashAdActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * @author ciba
 * @description 描述
 * @date 2020/3/25
 */
public class ADSuyiApplication extends Application {
    public static Context context;
    /**
     * 检查是否需要再次打开开屏界面的间隔时长。
     * 180 * 1000 为 3分钟间隔时长，可自行修改时长
     */
    private static final long OPEN_SPLASH_ACTIVITY_INTERVAL_TIME = 180 * 1000;
    private long preMillis;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.PT);
        // 添加bugly初始化（该初始化与广告SDK无关，广告SDK中不包含bugly相关内容，仅供Demo错误信息收集使用）
        CrashReport.initCrashReport(getApplicationContext(), "6d9d9f24ee", true);

        // 据悉，工信部将在2020年8月底前上线运行全国APP技术检测平台管理系统，2020年12月10日前完成覆盖40万款主流App的合规检测工作。
        // 为了保证您的App顺利通过检测，结合当前监管关注重点，我们可以将ADSuyiSdk的初始化放在用户同意隐私政策之后。
        // 初始化ADSuyi广告SDK
//        ADSuyiSdk.getInstance().init(this, new ADSuyiInitConfig.Builder()
//                // 设置APPID

//                .appId(ADSuyiDemoConstant.APP_ID)
//                // 是否开启Debug，开启会有详细的日志信息打印
//                .debug(true)
//                // 是否过滤第三方平台的问题广告（例如: 已知某个广告平台在某些机型的Banner广告可能存在问题，如果开启过滤，则在该机型将不再去获取该平台的Banner广告）
//                .filterThirdQuestion(true)
//                // 如果开了浮窗广告，可设置不展示浮窗广告的界面，第一个参数为是否开启默认不展示的页面（例如:激励视频播放页面），第二可变参数为自定义不展示的页面
//                .floatingAdBlockList(false, "cn.admobiletop.adsuyidemo.activity.SplashAdActivity")
//                .build());

        // 如果有接开屏广告，可以设置应用进入后台一段时间后回到应用再次开启开屏界面，增加开屏广告收益（仅供参考，无需要可不设置）
        openSplashActivityAgain();
    }

    private void openSplashActivityAgain() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                checkNeedOpenSplashActivity(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                preMillis = System.currentTimeMillis();
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    /**
     * 检查是否需要再次打开开屏界面
     */
    private void checkNeedOpenSplashActivity(Activity activity) {
        long millis = System.currentTimeMillis();
        if (preMillis > 0
                && millis - preMillis > OPEN_SPLASH_ACTIVITY_INTERVAL_TIME
                && !(activity instanceof SplashAdActivity)) {
            activity.startActivity(new Intent(activity, SplashAdActivity.class));
        }
        preMillis = millis;
    }
}
