package cn.admobiletop.adsuyidemo.activity.ad.splash;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.admobiletop.adsuyi.ad.ADSuyiSplashAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiSplashAdListener;
import cn.admobiletop.adsuyi.util.ADSuyiToastUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.util.SPUtil;
import cn.admobiletop.adsuyidemo.util.UIUtils;

/**
 * @author ciba
 * @description 开屏广告示例，开屏广告容器请保证有屏幕高度的75%，建议开屏界面设置为全屏模式并禁止返回按钮
 * @date 2020/3/25
 */
public class SplashAdActivity extends AppCompatActivity {

    private ADSuyiSplashAd adSuyiSplashAd;

    private TextView tvSkip;
    private FrameLayout flContainer;
    private RelativeLayout rlLogoContainer;
    private Button btnLoadAd;
    private Button btnShowAd;

    /**
     * 广告位
     */
    private String posId;
    /**
     * 展示样式
     */
    private int splashType = ADSuyiDemoConstant.HALF_SCREEN;
    /**
     * 加载类型
     */
    private int loadType = ADSuyiDemoConstant.LOAD_AND_SHOW;
    /**
     * logo高度
     */
    private int logoHeightPx;
    /**
     * 是否沉浸状态栏
     */
    private boolean isImmersive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_ad);

        initData();
        initView();
        initListener();
        initAd();
    }

    /**
     * 初始化页面数据
     */
    private void initData() {
        posId = getIntent().getStringExtra("POSID");
        if (TextUtils.isEmpty(posId)) {
            posId = ADSuyiDemoConstant.SPLASH_AD_POS_ID1;
        }

        splashType = getIntent().getIntExtra("splashType", ADSuyiDemoConstant.HALF_SCREEN);

        loadType = getIntent().getIntExtra("loadType", ADSuyiDemoConstant.LOAD_AND_SHOW);

        logoHeightPx = getIntent().getIntExtra("logoHeightPx", 0);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        if (splashType == ADSuyiDemoConstant.IMMERSIVE_AND_FULLSCREEN) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        flContainer = findViewById(R.id.flContainer);
        tvSkip = findViewById(R.id.tvSkip);
        rlLogoContainer = findViewById(R.id.rlLogoContainer);
        btnLoadAd = findViewById(R.id.btnLoadAd);
        btnShowAd = findViewById(R.id.btnShowAd);

        if (splashType == ADSuyiDemoConstant.IMMERSIVE_AND_FULLSCREEN) {
            isImmersive = true;
            rlLogoContainer.setVisibility(View.GONE);
        } else if (splashType == ADSuyiDemoConstant.FULL_SCREEN) {
            isImmersive = false;
            rlLogoContainer.setVisibility(View.GONE);
        } else if (splashType == ADSuyiDemoConstant.HALF_SCREEN) {
            isImmersive = false;
            ViewGroup.LayoutParams layoutParams = rlLogoContainer.getLayoutParams();
            layoutParams.height = logoHeightPx;
            rlLogoContainer.setLayoutParams(layoutParams);
            rlLogoContainer.setVisibility(View.VISIBLE);
        }

        if (loadType == ADSuyiDemoConstant.LOAD_ONLY) {
            btnLoadAd.setVisibility(View.VISIBLE);
            btnShowAd.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        btnLoadAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd();
            }
        });
        btnShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAd();
            }
        });
    }

    /**
     * 初始化广告
     */
    private void initAd() {
        // 创建开屏广告实例，第一个参数可以是Activity或Fragment
        adSuyiSplashAd = new ADSuyiSplashAd(this);

        int widthPixels = UIUtils.getScreenWidthInPx(this);
        int heightPixels = UIUtils.getScreenHeightInPx(this);

        boolean issensor = SPUtil.getBoolean(this, "sensor");

        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置整个广告视图预期宽高(目前仅穿山甲（头条）平台需要，没有接入头条可不设置)，单位为px，如果不设置穿山甲（头条）开屏广告视图将会以满屏进行填充
                .adSize(new ADSuyiAdSize(widthPixels, heightPixels - logoHeightPx))
                .setAdShakeDisable(issensor)
                .build();
        // 如果开屏容器不是全屏可以设置额外参数
        adSuyiSplashAd.setLocalExtraParams(extraParams);
        // 设置是否是沉浸式，如果为true，跳过按钮距离顶部的高度会加上状态栏高度
        adSuyiSplashAd.setImmersive(isImmersive);

        if (ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW) {
            // 设置自定义跳过按钮，自定义跳过按钮倒计时时长，默认是5秒，范围3000~5000，建议不修改
            adSuyiSplashAd.setSkipView(tvSkip, 5000);
        }
        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        adSuyiSplashAd.setOnlySupportPlatform(ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM);
        // 设置开屏广告监听
        adSuyiSplashAd.setListener(new ADSuyiSplashAdListener() {

            @Override
            public void onADTick(long millisUntilFinished) {
                Log.d(ADSuyiDemoConstant.TAG, "广告剩余倒计时时长回调：" + millisUntilFinished);
                tvSkip.setText(millisUntilFinished + "s自动跳转");
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
                if (btnLoadAd != null) {
                    btnLoadAd.setText("加载广告");
                }
                if (btnShowAd != null) {
                    btnShowAd.setText("加载成功请展示广告");
                }

                Log.d(ADSuyiDemoConstant.TAG, "广告获取成功回调... ");

                if (loadType == ADSuyiDemoConstant.LOAD_AND_SHOW) {
                    showAd();
                }

            }

            @Override
            public void onAdExpose(ADSuyiAdInfo adSuyiAdInfo) {
                if (ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW) {
                    tvSkip.setAlpha(1f);
                }
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
                    ADSuyiToastUtil.show(getApplicationContext(), "广告获取失败 : " + failedJson);
                }
                jumpMain();
            }
        });

        if (loadType == ADSuyiDemoConstant.LOAD_AND_SHOW) {
            loadAd();
        }
    }

    private void loadAd() {
        if (btnLoadAd != null) {
            btnLoadAd.setText("加载中...");
        }
        if (adSuyiSplashAd != null) {
            // 仅加载开屏广告，参数为广告位ID，同一个ADSuyiSplashAd只有一次loadAd有效，请在onAdReceive回调中展示广告
            adSuyiSplashAd.loadOnly(ADSuyiDemoConstant.SPLASH_AD_POS_ID);
        }
    }

    /**
     * 展示广告
     */
    private void showAd() {
        if (adSuyiSplashAd != null && flContainer != null) {
            // 展示广告，第一个参数是广告容器（请保证容器不会拦截点击、触摸等事件，高度不小于真实屏幕高度的75%，并且处于可见状态）
            adSuyiSplashAd.showSplash(flContainer);
        }
    }

    /**
     * 跳转到主界面
     */
    private void jumpMain() {
        finish();
    }

    @Override
    public void onBackPressed() {
        // 取消返回事件，增加开屏曝光率
    }

}
