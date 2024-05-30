package cn.admobiletop.adsuyidemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Landscape_Activity;
import com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity;

import cn.admobiletop.adsuyi.ADSuyiSdk;
import cn.admobiletop.adsuyi.config.ADSuyiInitConfig;
import cn.admobiletop.adsuyi.listener.ADSuyiInitListener;
import cn.admobiletop.adsuyi.util.SuyiPackageStrategy;
import cn.admobiletop.adsuyidemo.activity.ad.ADSuyiInitAndLoadSplashAdActivity;
import cn.admobiletop.adsuyidemo.activity.setting.SettingActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.manager.ADSuyiInterstitialManager;
import cn.admobiletop.adsuyidemo.util.SPUtil;

/**
 * @author ciba
 * @description 描述
 * @date 2020/3/25
 */
public class ADSuyiApplication extends Application {
    public static final String AGREE_PRIVACY_POLICY = "AGREE_PRIVACY_POLICY";
    public static Context context;
    /**
     * 检查是否需要再次打开开屏界面的间隔时长。
     * 注意，由于activity的启动大概是70毫秒，请不要设置低于1000毫秒，不然会出现卡开屏页的bug
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
        setOnlySupportPlatform();
        context = this;

        // 据悉，工信部将在2020年8月底前上线运行全国APP技术检测平台管理系统，2020年12月10日前完成覆盖40万款主流App的合规检测工作。
        // 为了保证您的App顺利通过检测，结合当前监管关注重点，请务必将ADSuyiSdk的初始化放在用户同意隐私政策之后。

        // 如果有接开屏广告，可以设置应用进入后台一段时间后回到应用再次开启开屏界面，增加开屏广告收益（仅供参考，无需要可不设置）
        openSplashActivityAgain();
    }

    private void openSplashActivityAgain() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
                if (ADSuyiDemoConstant.INTERSTITIAL_AD_AUTO_CLOSE) {
                    Log.d(ADSuyiDemoConstant.TAG, "ActivityLifecycleCallbacks:"+activity.getComponentName().getClassName());

                    ADSuyiInterstitialManager.getInstance().addInterstitialList(activity);
                }
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
                && !(activity instanceof ADSuyiInitAndLoadSplashAdActivity)) {
//            activity.startActivity(new Intent(activity, ADSuyiInitAndLoadSplashAdActivity.class));
        }
        preMillis = millis;
    }

    /**
     * 设置仅仅支持平台
     */
    private void setOnlySupportPlatform() {
        String onlySupportPlatform = SPUtil.getString(this, SettingActivity.KEY_ONLY_SUPPORT_PLATFORM, null);
        ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
        ADSuyiDemoConstant.BANNER_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
        ADSuyiDemoConstant.NATIVE_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
        ADSuyiDemoConstant.REWARD_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
        ADSuyiDemoConstant.INTERSTITIAL_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
    }

    @Override
    public String getPackageName() {
        return SuyiPackageStrategy.getSuyiPackageName(this);
    }

    /**
     * 初始化广告SDK并且跳转开屏界面
     */
    public static void initAd() {

        boolean isOpenFloatingAd = SPUtil.getBoolean(ADSuyiApplication.context, SettingActivity.KEY_OPEN_FLOATING_AD, true);

        // 初始化ADSuyi广告SDK
        ADSuyiSdk.getInstance().init(ADSuyiApplication.context, new ADSuyiInitConfig.Builder()
                        // 设置APPID
                        .appId(ADSuyiDemoConstant.APP_ID)
                        // 是否开启Debug，开启会有详细的日志信息打印，如果用上ADSuyiToastUtil工具还会弹出toast提示。
                        // TODO 注意上线后请置为false
                        .debug(BuildConfig.DEBUG)
                        //【慎改】是否同意隐私政策，将禁用一切设备信息读起严重影响收益
                        .agreePrivacyStrategy(true)
                        // 是否可获取定位数据
                        .isCanUseLocation(true)
                        // 是否可获取设备信息
                        .isCanUsePhoneState(true)
                        // 是否可读取设备安装列表
                        .isCanReadInstallList(true)
                        // 是否可读取设备外部读写权限
                        .isCanUseReadWriteExternal(true)
                        // 是否开启浮窗（默认true。true：浮窗广告交由ADSuyiSdk控制，false：媒体自行拉取广告并展示广告）
                        .openFloatingAd(false)
                        // 是否过滤第三方平台的问题广告（例如: 已知某个广告平台在某些机型的Banner广告可能存在问题，如果开启过滤，则在该机型将不再去获取该平台的Banner广告）
                        .filterThirdQuestion(true)
                        // 如果开了浮窗广告，可设置不展示浮窗广告的界面，第一个参数为是否开启默认不展示的页面（例如:激励视频播放页面），第二可变参数为自定义不展示的页面
                        .floatingAdBlockList(false,
                                // 主动设置不展示浮窗广告的Activity路径
                                "cn.admobiletop.adsuyidemo.activity.ad.ADSuyiInitAndLoadSplashAdActivity",
                                "cn.admobiletop.adsuyidemo.activity.ad.splash.SplashAdActivity",
                                "cn.admobiletop.adsuyidemo.activity.ad.splash.SplashAdSettingActivity"
                        )
                        .build(),
                new ADSuyiInitListener() {
                    @Override
                    public void onSuccess() {
                        // 初始化成功
                    }

                    @Override
                    public void onFailed(String error) {
                        // 初始化失败
                    }
                });

        // 可通过调用此方法暂停浮窗广告投放
        ADSuyiSdk.getInstance().pauseFloatingAd();
    }
}
