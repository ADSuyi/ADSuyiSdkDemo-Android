package cn.admobiletop.adsuyidemo.activity.ad;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

import cn.admobiletop.adsuyi.ad.ADSuyiBannerAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiBannerAdListener;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.base.BaseAdActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.util.SPUtil;

/**
 * @author ciba
 * @description Banner广告示例
 * @date 2020/3/26
 */
public class BannerAdActivity extends BaseAdActivity {

    private FrameLayout flContainer;
    private ADSuyiBannerAd suyiBannerAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        flContainer = findViewById(R.id.flContainer);
        loadBannerAd();
    }

    private void loadBannerAd() {
        boolean issensor = SPUtil.getBoolean(this, "sensor");
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                .setAdShakeDisable(issensor)
                .build();

        // 创建Banner广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器（请保证容器不会拦截点击、触摸等事件）
        suyiBannerAd = new ADSuyiBannerAd(this, flContainer);
        // 设置自刷新时间范围为30～120秒，⚠️注意！！！如果设置了自刷新，初始化ADSuyiSDK时传入的content一定为Application的Content
        suyiBannerAd.setAutoRefreshInterval(ADSuyiDemoConstant.BANNER_AD_AUTO_REFRESH_INTERVAL);
        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        suyiBannerAd.setOnlySupportPlatform(ADSuyiDemoConstant.BANNER_AD_ONLY_SUPPORT_PLATFORM);
        // 如果横幅容器不是全屏可以设置额外参数
        suyiBannerAd.setLocalExtraParams(extraParams);
        // 设置Banner广告监听
        suyiBannerAd.setListener(new ADSuyiBannerAdListener() {
            @Override
            public void onAdReceive(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive...");
            }

            @Override
            public void onAdExpose(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose...");
            }

            @Override
            public void onAdClick(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick...");
            }

            @Override
            public void onAdClose(ADSuyiAdInfo adSuyiAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose...");
                if (flContainer != null) {
                    flContainer.removeAllViews();
                    // flContainer.setVisibility(View.GONE);
                }

                if (suyiBannerAd != null) {
                    suyiBannerAd.release();
                }
                if (adSuyiAdInfo != null) {
                    adSuyiAdInfo.release();
                }
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                // ADSuyiToastUtil.show(getApplicationContext(), "广告获取失败");
                if (adSuyiError != null) {
                    String failedJson = adSuyiError.toString();
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed..." + failedJson);
                }
            }
        });
        // banner广告场景id（场景id非必选字段，如果需要可到开发者后台创建）
        suyiBannerAd.setSceneId(ADSuyiDemoConstant.BANNER_AD_SCENE_ID);
        // 加载Banner广告，参数为广告位ID，同一个ADSuyiBannerAd只有一次loadAd有效
        suyiBannerAd.loadAd(ADSuyiDemoConstant.BANNER_AD_POS_ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (suyiBannerAd != null) {
            suyiBannerAd.release();
            suyiBannerAd = null;
        }
    }
}
