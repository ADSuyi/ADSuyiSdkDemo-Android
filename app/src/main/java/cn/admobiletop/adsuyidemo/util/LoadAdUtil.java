package cn.admobiletop.adsuyidemo.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import cn.admobiletop.adsuyi.ad.ADSuyiSplashAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiSplashAdListener;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;

public class LoadAdUtil {
    public static void loadSplashVPlusAd(Activity activity, FrameLayout flContainer) {
        flContainer.removeAllViews();
        // 请在先展示布局
        flContainer.setVisibility(View.VISIBLE);
        // 创建开屏广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器（请保证容器不会拦截点击、触摸等事件，高度不小于真实屏幕高度的75%，并且处于可见状态）
        ADSuyiSplashAd adSuyiSplashAd = new ADSuyiSplashAd(activity, flContainer);
        adSuyiSplashAd.setOnlySupportPlatform(ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM);
        // 设置开屏广告监听
        adSuyiSplashAd.setListener(new ADSuyiSplashAdListener() {

            @Override
            public void onADTick(long countdownSeconds) {
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
                flContainer.removeAllViews();
                flContainer.setVisibility(View.GONE);
                if (adSuyiSplashAd != null) {
                    adSuyiSplashAd.release();
                }
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    String failedJson = adSuyiError.toString();
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed----->" + failedJson);
                    // ADSuyiToastUtil.show(getApplicationContext(), "广告获取失败 : " + failedJson);
                }
                flContainer.removeAllViews();
                flContainer.setVisibility(View.GONE);
                if (adSuyiSplashAd != null) {
                    adSuyiSplashAd.release();
                }
            }
        });

        adSuyiSplashAd.loadAd("5380eccc543592625d");
    }
}
