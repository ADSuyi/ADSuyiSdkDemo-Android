package cn.admobiletop.adsuyidemo.activity.ad.feed;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import cn.admobiletop.adsuyi.ad.ADSuyiNativeAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeExpressAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiNativeAdListener;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyi.util.ADSuyiViewUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;

/**
 * @author : 草莓
 * @date : 2021/11/01
 * @description : 将信息流模板广告放到RelativeLayout进行展示案例
 */
public class NativeExpressActivity extends AppCompatActivity {

    private Button btnLoadAndShowAd;
    private Button btnLoadAd;
    private Button btnShowAd;
    private RelativeLayout rlExpressAd;

    private ADSuyiNativeAd adSuyiNativeAd;
    private ADSuyiNativeAdInfo adSuyiNativeAdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_native_express_ad);

        initView();
        initListener();

    }

    private void initView() {
        btnLoadAndShowAd = findViewById(R.id.btnLoadAndShowAd);
        btnLoadAd = findViewById(R.id.btnLoadAd);
        btnShowAd = findViewById(R.id.btnShowAd);
        rlExpressAd = findViewById(R.id.rlExpressAd);
    }

    private void initListener() {
        btnLoadAndShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAndShowAd();
            }
        });
        btnLoadAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd(false);
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
     * 加载成功并展示
     */
    private void loadAndShowAd() {
        loadAd(true);
    }

    /**
     * 加载广告
     *
     * @param isLoadSuccessShows 是否加载信息流广告成功后立刻展示广告
     */
    private void loadAd(boolean isLoadSuccessShows) {
        releaseAd();

        // 模板广告容器宽度
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        // 创建信息流广告实例
        adSuyiNativeAd = new ADSuyiNativeAd(this);
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置整个广告视图预期宽高，单位为px，高度如果小于等于0则高度自适应
                .adSize(new ADSuyiAdSize(widthPixels, 0))
                // 设置信息流广告适配播放是否静音，默认静音，目前优量汇、百度、汇量、快手、天目支持修改
                .nativeAdPlayWithMute(ADSuyiDemoConstant.NATIVE_AD_PLAY_WITH_MUTE)
                .build();
        // 设置一些额外参数，有些平台的广告可能需要传入一些额外参数
        adSuyiNativeAd.setLocalExtraParams(extraParams);

        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        adSuyiNativeAd.setOnlySupportPlatform(ADSuyiDemoConstant.NATIVE_AD_ONLY_SUPPORT_PLATFORM);
        // 设置广告监听
        adSuyiNativeAd.setListener(new ADSuyiNativeAdListener() {
            @Override
            public void onRenderFailed(ADSuyiNativeAdInfo adSuyiNativeAdInfo, ADSuyiError adSuyiError) {
                Log.d(ADSuyiDemoConstant.TAG, "onRenderFailed: " + adSuyiError.toString());
            }

            @Override
            public void onRenderSuccess(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                // 广告渲染成功，仅部分渠道支持
                Log.d(ADSuyiDemoConstant.TAG, "onRenderSuccess: " + adSuyiNativeAdInfo.hashCode());
            }

            @Override
            public void onAdReceive(List<ADSuyiNativeAdInfo> adInfoList) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive: " + adInfoList.size());
                if (adInfoList != null && adInfoList.size() > 0) {
                    Toast.makeText(NativeExpressActivity.this, "广告获取成功", Toast.LENGTH_SHORT).show();
                    adSuyiNativeAdInfo = adInfoList.get(0);

                    // 是否立刻展示广告
                    if (isLoadSuccessShows) {
                        showAd();
                    }
                }
            }

            @Override
            public void onAdExpose(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose: " + adSuyiNativeAdInfo.hashCode());
            }

            @Override
            public void onAdClick(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick: " + adSuyiNativeAdInfo.hashCode());
            }

            @Override
            public void onAdClose(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose: " + adSuyiNativeAdInfo.hashCode());
                closeAd();
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed: " + adSuyiError.toString());
                }
            }
        });

        adSuyiNativeAd.loadAd(ADSuyiDemoConstant.NATIVE_AD_POS_ID4, 1);
    }

    /**
     * 展示广告
     */
    private void showAd() {
        if (ADSuyiAdUtil.adInfoIsRelease(adSuyiNativeAdInfo)) {
            Toast.makeText(this, "广告已被释放", Toast.LENGTH_SHORT).show();
            Log.d(ADSuyiDemoConstant.TAG, "广告已被释放");
            return;
        }
        if (adSuyiNativeAdInfo == null) {
            Toast.makeText(this, "未获取到广告，请先请求广告", Toast.LENGTH_SHORT).show();
            Log.d(ADSuyiDemoConstant.TAG, "未获取到广告，请先请求广告");
            return;
        }

        ADSuyiNativeExpressAdInfo nativeExpressAdInfo;
        if (!adSuyiNativeAdInfo.isNativeExpress()) {
            Toast.makeText(this, "当前请求到广告非信息流模板广告，请使用信息流模板广告位", Toast.LENGTH_SHORT).show();
            Log.d(ADSuyiDemoConstant.TAG, "当前请求到广告非信息流模板广告，请使用信息流模板广告位");
            return;
        } else {
            // 将广告对象转换成模板广告
            nativeExpressAdInfo = (ADSuyiNativeExpressAdInfo)adSuyiNativeAdInfo;
        }

        // 当前是信息流模板广告，getNativeExpressAdView获取的是整个模板广告视图
        View nativeExpressAdView = nativeExpressAdInfo.getNativeExpressAdView(rlExpressAd);
        // 将广告视图添加到容器中的便捷方法
        ADSuyiViewUtil.addAdViewToAdContainer(
                rlExpressAd,
                nativeExpressAdView,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        // 渲染广告视图, 必须调用, 因为是模板广告, 所以传入ViewGroup和响应点击的控件可能并没有用
        // 务必在最后调用
        nativeExpressAdInfo.render(rlExpressAd);

    }

    /**
     * 关闭广告
     */
    private void closeAd() {
        if (rlExpressAd != null) {
            rlExpressAd.removeAllViews();
        }
        releaseAd();
    }

    /**
     * 释放广告
     */
    private void releaseAd() {
        if (adSuyiNativeAd != null) {
            adSuyiNativeAd.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseAd();
    }
}
