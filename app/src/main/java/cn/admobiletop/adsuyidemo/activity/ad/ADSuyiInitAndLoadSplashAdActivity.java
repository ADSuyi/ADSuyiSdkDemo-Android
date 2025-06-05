package cn.admobiletop.adsuyidemo.activity.ad;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import cn.admobiletop.adsuyi.ADSuyiSdk;
import cn.admobiletop.adsuyi.ad.ADSuyiSplashAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiSplashAdListener;
import cn.admobiletop.adsuyi.config.ADSuyiInitConfig;
import cn.admobiletop.adsuyi.listener.ADSuyiInitListener;
import cn.admobiletop.adsuyidemo.ADSuyiApplication;
import cn.admobiletop.adsuyidemo.BuildConfig;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.MainActivity;
import cn.admobiletop.adsuyidemo.activity.setting.SettingActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.util.SPUtil;
import cn.admobiletop.adsuyidemo.util.UIUtils;
import cn.admobiletop.adsuyidemo.widget.PrivacyPolicyDialog;

/**
 * @author ciba
 * @description 初始化ADSuyi并加载开屏广告示例。
 *              开屏广告容器请保证有屏幕高度的75%，建议开屏界面设置为全屏模式并禁止返回按钮
 * @date 2020/3/25
 */
public class ADSuyiInitAndLoadSplashAdActivity extends AppCompatActivity {
    private static final String AGREE_PRIVACY_POLICY = "AGREE_PRIVACY_POLICY";
    /**
     * 根据实际情况申请
     */
    private static final String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION
            , Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int REQUEST_CODE = 7722;

    private List<String> permissionList = new ArrayList<>();
    private ADSuyiSplashAd adSuyiSplashAd;
    private FrameLayout flContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adsuyi_init_and_load_splash_ad);
        flContainer = findViewById(R.id.flContainer);

        // 据悉，工信部将在2020年8月底前上线运行全国APP技术检测平台管理系统，2020年12月10日前完成覆盖40万款主流App的合规检测工作。
        // 为了保证您的App顺利通过检测，结合当前监管关注重点，请务必将ADSuyiSdk的初始化放在用户同意隐私政策之后。
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

        boolean isOpenFloatingAd = SPUtil.getBoolean(this, SettingActivity.KEY_OPEN_FLOATING_AD, true);

        // 初始化ADSuyi广告SDK
        ADSuyiSdk.getInstance().init(ADSuyiApplication.context, new ADSuyiInitConfig.Builder()
                        // 设置APPID
                        .appId(ADSuyiDemoConstant.APP_ID)
                        // 是否开启Debug，开启会有详细的日志信息打印，如果用上ADSuyiToastUtil工具还会弹出toast提示。
                        // TODO 注意上线后请置为false
                        .debug(BuildConfig.DEBUG)
                        //【慎改】是否同意隐私政策，将禁用一切设备信息读起严重影响收益
                        .agreePrivacyStrategy(false)
                        // 是否可获取定位数据
                        .isCanUseLocation(false)
                        // 是否可获取设备信息
                        .isCanUsePhoneState(false)
                        // 是否可读取设备安装列表
                        .isCanReadInstallList(false)
                        // 是否可读取设备外部读写权限
                        .isCanUseReadWriteExternal(false)
                        // 是否过滤第三方平台的问题广告（例如: 已知某个广告平台在某些机型的Banner广告可能存在问题，如果开启过滤，则在该机型将不再去获取该平台的Banner广告）
                        .filterThirdQuestion(true)
                        .build(),
                        adSuyiInitListener);

        initSplashAd();
    }

    private ADSuyiInitListener adSuyiInitListener = new ADSuyiInitListener() {
        @Override
        public void onSuccess() {
            // ADSuyi初始化成功
        }

        @Override
        public void onFailed(String error) {
            // ADSuyi初始化失败
        }
    };

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

        int widthPixels = UIUtils.getScreenWidthInPx(this);
        int heightPixels = UIUtils.getScreenHeightInPx(this);

        boolean issensor = SPUtil.getBoolean(this, "sensor");

        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置整个广告视图预期宽高(目前仅穿山甲（头条）平台需要)，单位为px，如果不设置穿山甲（头条）开屏广告视图将会以满屏填充
                .adSize(new ADSuyiAdSize(widthPixels, heightPixels))
                .setAdShakeDisable(issensor)
                .build();

        // 创建开屏广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器（请保证容器不会拦截点击、触摸等事件，高度不小于真实屏幕高度的75%，并且处于可见状态）
        adSuyiSplashAd = new ADSuyiSplashAd(this, flContainer);
        // 如果开屏容器不是全屏可以设置额外参数
        adSuyiSplashAd.setLocalExtraParams(extraParams);
        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        adSuyiSplashAd.setOnlySupportPlatform(ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM);
        // 设置开屏广告监听
        adSuyiSplashAd.setListener(new ADSuyiSplashAdListener() {

            @Override
            public void onADTick(long countdownSeconds) {
                // 如果没有设置自定义跳过按钮不会回调该方法
                Log.d(ADSuyiDemoConstant.TAG, "倒计时剩余时长（单位秒）" + countdownSeconds);
            }

            @Override
            public void onReward(ADSuyiAdInfo adSuyiAdInfo) {
                // 目前仅仅优量汇渠道会被使用
                Log.d(ADSuyiDemoConstant.TAG, "广告奖励回调... ");
            }

            @Override
            public void onAdSkip(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告跳过回调，不一定准确，埋点数据仅供参考... ");
            }

            @Override
            public void onAdReceive(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "广告获取成功回调... ");
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
        adSuyiSplashAd.loadAd(ADSuyiDemoConstant.SPLASH_AD_POS_ID);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adSuyiInitListener = null;
    }
}
