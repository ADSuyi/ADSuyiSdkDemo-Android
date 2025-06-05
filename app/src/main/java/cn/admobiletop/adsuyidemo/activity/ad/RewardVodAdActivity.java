package cn.admobiletop.adsuyidemo.activity.ad;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.admobiletop.adsuyi.ad.ADSuyiRewardVodAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiRewardVodAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiRewardVodAdListener;
import cn.admobiletop.adsuyi.util.ADSuyiToastUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.base.BaseAdActivity;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.util.SPUtil;

/**
 * @author ciba
 * @description 激励视频广告示例
 * @date 2020/3/27
 */
public class RewardVodAdActivity extends BaseAdActivity implements View.OnClickListener {
    private ADSuyiRewardVodAd rewardVodAd;
    private ADSuyiRewardVodAdInfo rewardVodAdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_vod);

        initListener();
        initAd();
    }

    private void initListener() {
        findViewById(R.id.btnLoadAd).setOnClickListener(this);
        findViewById(R.id.btnShowAd).setOnClickListener(this);
    }

    private void initAd() {
        boolean issensor = SPUtil.getBoolean(this, "sensor");
        // 创建激励视频广告实例
        rewardVodAd = new ADSuyiRewardVodAd(this);

//        ADSuyiRewardExtra adSuyiRewardExtra = new ADSuyiRewardExtra("用户id");
//        // 设置激励视频服务端验证的自定义信息
//        adSuyiRewardExtra.setCustomData("设置激励视频服务端验证的自定义信息");
//        // 设置激励视频服务端激励名称
//        adSuyiRewardExtra.setRewardName("激励名称");
//        // 设置激励视频服务端激励数量
//        adSuyiRewardExtra.setRewardAmount(1);
//        rewardVodAd.setLocalExtraParams(new ADSuyiExtraParams.Builder().rewardExtra(adSuyiRewardExtra).build());

        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
//                .rewardExtra(adSuyiRewardExtra)
                // 设置视频类广告是否静音
                .setVideoWithMute(ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE)
                .setAdShakeDisable(issensor)
                .build();

        rewardVodAd.setLocalExtraParams(extraParams);

        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        rewardVodAd.setOnlySupportPlatform(ADSuyiDemoConstant.REWARD_VOD_AD_ONLY_SUPPORT_PLATFORM);
        // 设置激励视频广告监听
        rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {

            @Override
            public void onAdReceive(ADSuyiRewardVodAdInfo rewardVodAdInfo) {
                // TODO 激励视频广告对象一次成功拉取的广告数据只允许展示一次
                RewardVodAdActivity.this.rewardVodAdInfo = rewardVodAdInfo;
                ADSuyiToastUtil.show(getApplicationContext(), "激励视频广告获取成功");
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive...");
            }

            @Override
            public void onVideoCache(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                // 部分渠道不会回调该方法，请在onAdReceive做广告展示处理
                Log.d(ADSuyiDemoConstant.TAG, "onVideoCache...");
            }

            @Override
            public void onVideoComplete(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onVideoComplete...");
            }

            @Override
            public void onVideoError(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo, ADSuyiError adSuyiError) {
                Log.d(ADSuyiDemoConstant.TAG, "onVideoError..." + adSuyiError.toString());
            }

            @Override
            public void onReward(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onReward...");
            }

            @Override
            public void onAdExpose(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose...");
            }

            @Override
            public void onAdClick(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick...");
            }

            @Override
            public void onAdClose(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose...");
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                // ADSuyiToastUtil.show(getApplicationContext(), "广告获取失败");
                if (adSuyiError != null) {
                    String failedJosn = adSuyiError.toString();
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed..." + failedJosn);
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
                if (rewardVodAdInfo == null) {
                    Toast.makeText(this, "无可用广告", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!rewardVodAdInfo.isReady()) {
                    Toast.makeText(this, "广告未准备好", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rewardVodAdInfo.hasExpired()) {
                    Toast.makeText(this, "广告已失效", Toast.LENGTH_SHORT).show();
                    return;
                }
                rewardVodAdInfo.showRewardVod(this);
                break;
            default:
                break;
        }
    }

    /**
     * 加载广告
     */
    private void loadAd() {
        if (rewardVodAdInfo != null) {
            rewardVodAdInfo.release();
            rewardVodAdInfo = null;
        }

        // 激励广告场景id（场景id非必选字段，如果需要可到开发者后台创建）
        rewardVodAd.setSceneId(ADSuyiDemoConstant.REWARD_VOD_AD_SCENE_ID);
        // 加载激励视频广告，参数为广告位ID
        rewardVodAd.loadAd(ADSuyiDemoConstant.REWARD_VOD_AD_POS_ID);
    }

}
