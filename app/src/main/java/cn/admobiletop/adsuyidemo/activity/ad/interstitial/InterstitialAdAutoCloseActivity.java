package cn.admobiletop.adsuyidemo.activity.ad.interstitial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import cn.admobiletop.adsuyi.ad.ADSuyiInterstitialAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiInterstitialAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiInterstitialAdListener;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyi.util.ADSuyiToastUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.base.BaseAdActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.manager.ADSuyiInterstitialManager;

/**
 * @author ciba
 * @description 插屏广告示例
 * @date 2020/3/27
 */
public class InterstitialAdAutoCloseActivity extends BaseAdActivity implements View.OnClickListener {
    private ADSuyiInterstitialAd interstitialAd;
    private ADSuyiInterstitialAdInfo interstitialAdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_ad);

        initListener();
        initAd();
    }

    private void initListener() {
        Button btnLoadAd = findViewById(R.id.btnLoadAd);
        Button btnShowAd = findViewById(R.id.btnShowAd);
        CheckBox cbAutoClose = findViewById(R.id.cbAutoClose);

        btnLoadAd.setText("获取插屏广告");
        btnShowAd.setText("展示插屏广告");

        btnLoadAd.setOnClickListener(this);
        btnShowAd.setOnClickListener(this);

        cbAutoClose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ADSuyiDemoConstant.INTERSTITIAL_AD_AUTO_CLOSE = b;
            }
        });
    }

    private void initAd() {
        interstitialAd = new ADSuyiInterstitialAd(this);
        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        interstitialAd.setOnlySupportPlatform(ADSuyiDemoConstant.INTERSTITIAL_AD_ONLY_SUPPORT_PLATFORM);
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置视频类广告是否静音
                .setVideoWithMute(ADSuyiDemoConstant.INTERSTITIAL_AD_PLAY_WITH_MUTE)
                .build();
        interstitialAd.setLocalExtraParams(extraParams);
        // 设置插屏广告监听
        interstitialAd.setListener(new ADSuyiInterstitialAdListener() {
            @Override
            public void onAdReady(ADSuyiInterstitialAdInfo interstitialAdInfo) {
                // 目前汇量和Inmobi走了该回调之后才准备好
                Log.d(ADSuyiDemoConstant.TAG, "onAdReady...");
            }

            @Override
            public void onAdReceive(ADSuyiInterstitialAdInfo interstitialAdInfo) {
                // TODO 插屏广告对象一次成功拉取的广告数据只允许展示一次
                InterstitialAdAutoCloseActivity.this.interstitialAdInfo = interstitialAdInfo;
                ADSuyiToastUtil.show(getApplicationContext(), "插屏广告获取成功");
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive...");
            }

            @Override
            public void onAdExpose(ADSuyiInterstitialAdInfo interstitialAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose...");

                if (ADSuyiDemoConstant.INTERSTITIAL_AD_AUTO_CLOSE) {
                    ADSuyiInterstitialManager.getInstance().addJumpView(interstitialAdInfo, InterstitialAdAutoCloseActivity.this);
                }
            }

            @Override
            public void onAdClick(ADSuyiInterstitialAdInfo interstitialAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick...");
            }

            @Override
            public void onAdClose(ADSuyiInterstitialAdInfo interstitialAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose...");
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoadAd:
                loadAd();
                break;
            case R.id.btnShowAd:
                ADSuyiAdUtil.showInterstitialAdConvenient(this, interstitialAdInfo);
                break;
            default:
                break;
        }
    }

    /**
     * 加载广告
     */
    private void loadAd() {
        if (interstitialAdInfo != null) {
            interstitialAdInfo.release();
            interstitialAdInfo = null;
        }
        // 插屏广告场景id（场景id非必选字段，如果需要可到开发者后台创建）
        interstitialAd.setSceneId(ADSuyiDemoConstant.INTERSTITIAL_AD_SCENE_ID);
        // 加载插屏广告，参数为广告位ID
        interstitialAd.loadAd(ADSuyiDemoConstant.INTERSTITIAL_AD_POS_ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ADSuyiDemoConstant.INTERSTITIAL_AD_AUTO_CLOSE = false;
    }
}
