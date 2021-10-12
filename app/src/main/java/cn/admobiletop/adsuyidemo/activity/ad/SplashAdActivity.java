package cn.admobiletop.adsuyidemo.activity.ad;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.admobiletop.adsuyi.ADSuyiSdk;
import cn.admobiletop.adsuyi.ad.ADSuyiSplashAd;
import cn.admobiletop.adsuyi.ad.api.ADSuyiNetworkRequestInfo;
import cn.admobiletop.adsuyi.ad.api.BaiduSplashAdRequestInfo;
import cn.admobiletop.adsuyi.ad.api.GdtSplashAdRequestInfo;
import cn.admobiletop.adsuyi.ad.api.KsSplashAdRequestInfo;
import cn.admobiletop.adsuyi.ad.api.TTSplashAdRequestInfo;
import cn.admobiletop.adsuyi.ad.api.annotation.DownloadTipParam;
import cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiSplashAdListener;
import cn.admobiletop.adsuyi.config.ADSuyiInitConfig;
import cn.admobiletop.adsuyi.util.ADSuyiToastUtil;
import cn.admobiletop.adsuyidemo.ADSuyiApplication;
import cn.admobiletop.adsuyidemo.BuildConfig;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.MainActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.util.SPUtil;
import cn.admobiletop.adsuyidemo.widget.PrivacyPolicyDialog;

/**
 * @author ciba
 * @description 开屏广告示例，开屏广告容器请保证有屏幕高度的75%，建议开屏界面设置为全屏模式并禁止返回按钮
 * @date 2020/3/25
 */
public class SplashAdActivity extends AppCompatActivity {
    private static final String AGREE_PRIVACY_POLICY = "AGREE_PRIVACY_POLICY";
    /**
     * 根据实际情况申请
     */
    private static final String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE
            , Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.ACCESS_COARSE_LOCATION
            , Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int REQUEST_CODE = 7722;

    private List<String> permissionList = new ArrayList<>();
    private ADSuyiSplashAd adSuyiSplashAd;
    private FrameLayout flContainer;
    private TextView skipView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_ad);
        flContainer = findViewById(R.id.flContainer);
        skipView = findViewById(R.id.tvSkip);

        // 据悉，工信部将在2020年8月底前上线运行全国APP技术检测平台管理系统，2020年12月10日前完成覆盖40万款主流App的合规检测工作。
        // 为了保证您的App顺利通过检测，结合当前监管关注重点，我们可以将ADSuyiSdk的初始化放在用户同意隐私政策之后。
        checkPrivacyPolicy();
    }

    /**
     * 检查隐私政策
     */
    private void checkPrivacyPolicy() {
        // 获取是否已经同意过隐私政策
        boolean agreePrivacyPolicy = SPUtil.getBoolean(this, AGREE_PRIVACY_POLICY);
        if (agreePrivacyPolicy) {
            // 如果同意了则直接初始化广告SDK并加载开屏广告
            initADSuyiSdkAndLoadSplashAd();
        } else {
            // 否则展示隐私政策弹框
            showPrivacyPolicyDialog();
        }
    }

    /**
     * 展示隐私政策弹框
     */
    private void showPrivacyPolicyDialog() {
        PrivacyPolicyDialog privacyPolicyDialog = new PrivacyPolicyDialog(this);
        privacyPolicyDialog.setOnResultCallback(new PrivacyPolicyDialog.OnResultCallback() {
            @Override
            public void onConfirm() {
                // 用户同意之后SP进行记录
                SPUtil.putBoolean(getApplicationContext(), AGREE_PRIVACY_POLICY, true);
                // 初始化广告SDK并加载开屏广告
                initADSuyiSdkAndLoadSplashAd();
            }

            @Override
            public void onCancel() {
                // 用户不同意不进行ADSuyiSdk的初始化（将导致广告获取失败）
                jumpMain();
            }
        });
        privacyPolicyDialog.setCancelable(false);
        privacyPolicyDialog.setCanceledOnTouchOutside(false);
        privacyPolicyDialog.show();
    }

    /**
     * 初始化广告SDK并且跳转开屏界面
     */
    private void initADSuyiSdkAndLoadSplashAd() {
        // 初始化ADSuyi广告SDK
        ADSuyiSdk.getInstance().init(ADSuyiApplication.context, new ADSuyiInitConfig.Builder()
                // 设置APPID
                .appId(ADSuyiDemoConstant.APP_ID)
                // 是否开启Debug，开启会有详细的日志信息打印，如果用上ADSuyiToastUtil工具还会弹出toast提示。
                // TODO 注意上线后请置为false
                .debug(BuildConfig.DEBUG)
                // 是否同意隐私政策
                .agreePrivacyStrategy(true)
                // 是否同意使用oaid
                .isCanUseOaid(true)
                // 是否可读取wifi状态
                .isCanUseWifiState(true)
                // 是否可获取定位数据
                .isCanUseLocation(true)
                // 是否可获取设备信息
                .isCanUsePhoneState(true)
                // 是否过滤第三方平台的问题广告（例如: 已知某个广告平台在某些机型的Banner广告可能存在问题，如果开启过滤，则在该机型将不再去获取该平台的Banner广告）
                .filterThirdQuestion(true)
                // 如果开了浮窗广告，可设置不展示浮窗广告的界面，第一个参数为是否开启默认不展示的页面（例如:激励视频播放页面），第二可变参数为自定义不展示的页面
                .floatingAdBlockList(false, "cn.admobiletop.adsuyidemo.activity.ad.SplashAdActivity")
                // 注意：如果使用oaid1.0.26版本，需要在assets中放置密钥，并将密钥传入ADSuyi（suyi内部初始化oaid需要使用）
                // 密钥需要到移动安全联盟申请（非oaid1.0.26版本无需使用该接口）
                .setOaidCertPath("cn.admobiletop.adsuyidemo.cert.pem")
                .build());

        initSplashAd();
    }

    private void initSplashAd() {
        // 6.0及以上获取没有申请的权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : PERMISSIONS) {
                int checkSelfPermission = ContextCompat.checkSelfPermission(this, permission);
                if (PackageManager.PERMISSION_GRANTED == checkSelfPermission) {
                    continue;
                }
                permissionList.add(permission);
            }
        }

        // 创建开屏广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器（请保证容器不会拦截点击、触摸等事件，高度不小于真实屏幕高度的75%，并且处于可见状态）
        adSuyiSplashAd = new ADSuyiSplashAd(this, flContainer);
        // 底部logo容器高度，请根据实际情况进行计算，这里的值是随便写的
        int logoHeight = 136;
        // 屏幕宽度px
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        // 屏幕高度px
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置整个广告视图预期宽高(目前仅头条平台需要，没有接入头条可不设置)，单位为px，如果不设置头条开屏广告视图将会以9 : 16的比例进行填充，小屏幕手机可能会出现素材被压缩的情况
                .adSize(new ADSuyiAdSize(widthPixels, heightPixels - logoHeight))
                .build();
        // 如果开屏容器不是全屏可以设置额外参数
        adSuyiSplashAd.setLocalExtraParams(extraParams);
        // 设置是否是沉浸式，如果为true，跳过按钮距离顶部的高度会加上状态栏高度
        adSuyiSplashAd.setImmersive(false);
        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        adSuyiSplashAd.setOnlySupportPlatform(ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM);
        if (ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW) {
            // 设置自定义跳过按钮和倒计时时长（非必传，倒计时时长范围[3000,5000]建议不要传入倒计时时长） 目前不支持广点通、讯飞、快手、华为广告联盟、云码、爱奇艺，平台自定义跳过按钮
            adSuyiSplashAd.setSkipView(skipView);
        }
        // 设置开屏广告监听
        adSuyiSplashAd.setListener(new ADSuyiSplashAdListener() {

            @Override
            public void onADTick(long countdownSeconds) {
                // 如果没有设置自定义跳过按钮不会回调该方法
                Log.d(ADSuyiDemoConstant.TAG, "倒计时剩余时长（单位秒）" + countdownSeconds);
                if (ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW && skipView != null) {
                    skipView.setText("跳过" + countdownSeconds + "s");
                }
            }

            @Override
            public void onAdSkip(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告跳过回调，不一定准确，埋点数据仅供参考... ");
            }

            @Override
            public void onAdReceive(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告获取成功回调... ");
                if (ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW && skipView != null) {
                    skipView.setVisibility(View.VISIBLE);
                    skipView.setAlpha(1);
                }
            }

            @Override
            public void onAdExpose(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败");
            }

            @Override
            public void onAdClick(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败");
            }

            @Override
            public void onAdClose(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告关闭回调，需要在此进行页面跳转");
                jumpMain();
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    String failedJson = adSuyiError.toString();
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed----->" + failedJson);
                    // ADSuyiToastUtil.show(getApplicationContext(), "广告获取失败 : " + failedJson);
                }
                jumpMain();
            }
        });

        if (!permissionList.isEmpty()) {
            // 存在未申请的权限则先申请
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[0]), REQUEST_CODE);
        } else {
            // 加载开屏广告，参数为广告位ID，同一个ADSuyiSplashAd只有一次loadAd有效
//            adSuyiSplashAd.loadAd(ADSuyiDemoConstant.SPLASH_AD_POS_ID);
            loadSplashAd();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (REQUEST_CODE == requestCode) {
            // 加载开屏广告，参数为广告位ID，同一个ADSuyiSplashAd只有一次loadAd有效
            loadSplashAd();
        }
    }

    private void loadSplashAd() {
        // 加载开屏广告，参数为广告位ID，同一个ADSuyiSplashAd只有一次loadAd有效
//        adSuyiSplashAd.loadAd(ADSuyiDemoConstant.SPLASH_AD_POS_ID);
        adSuyiSplashAd.loadAd(ADSuyiDemoConstant.SPLASH_AD_POS_ID,
                getSplashInfo("e866cfb0", "2058622", "891", DownloadTipParam.DOWNLOAD_TIP_ALL));
    }

    /**
     * 设置开屏保底广告 目前支持广点通、头条、百度、快手保底，保底是为了加速用户第一次获取开屏广告时的速度。
     * demo中演示百度平台，有其它平台需求的可以查看文档或在对接群中咨询
     *
     * @param platformAppId suyi开屏广告源应用ID
     * @param platformPosId suyi开屏广告源ID
     * @param adPosListId suyi开屏广告源AdPosList ID
     * @param downloadTip 下载提示 DOWNLOAD_TIP_NOTHING不提示 DOWNLOAD_TIP_MOBILE_TRAFFIC移动网络提示 DOWNLOAD_TIP_ALL 全提示
     * @return
     */
    private ADSuyiNetworkRequestInfo getSplashInfo(String platformAppId, String platformPosId, String adPosListId, int downloadTip) {
        return new KsSplashAdRequestInfo(platformAppId, platformPosId, adPosListId, downloadTip);
    }

    @Override
    public void onBackPressed() {
        // 取消返回事件，增加开屏曝光率
    }

    /**
     * 跳转到主界面
     */
    private void jumpMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
