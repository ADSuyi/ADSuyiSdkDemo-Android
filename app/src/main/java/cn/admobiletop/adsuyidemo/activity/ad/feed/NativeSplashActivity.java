package cn.admobiletop.adsuyidemo.activity.ad.feed;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qq.e.ads.nativ.widget.NativeAdContainer;

import java.util.List;

import cn.admobiletop.adsuyi.ad.ADSuyiNativeAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeExpressAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeFeedAdInfo;
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
 * @description : 将信息流广告作为开屏广告使用
 *                样式需要自行调整，demo只做参考
 */
public class NativeSplashActivity extends AppCompatActivity {

    private NativeAdContainer nativeAdContainer;
    private ImageView ivIcon;
    private RelativeLayout rlAdContainer;
    private ImageView ivAdTarget;
    private FrameLayout flContainer;
    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvSkip;

    private FrameLayout flContainer2;

    private ADSuyiNativeAd adSuyiNativeAd;
    private ADSuyiNativeAdInfo adSuyiNativeAdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_native_splash_ad);

        initView();
        initListener();
        loadAd();

    }

    private void initView() {
        nativeAdContainer = findViewById(R.id.nativeAdContainer);
        rlAdContainer = findViewById(R.id.rlAdContainer);
        flContainer = findViewById(R.id.flContainer);
        ivIcon = findViewById(R.id.ivIcon);
        ivAdTarget = findViewById(R.id.ivAdTarget);
        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.tvDesc);
        tvSkip = findViewById(R.id.tvSkip);

        flContainer2 = findViewById(R.id.rlAdContainer2);
    }

    private void initListener() {
    }

    /**
     * 加载广告
     *
     */
    private void loadAd() {
        releaseAd();

        // 模板广告容器宽度
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        // 创建信息流广告实例
        adSuyiNativeAd = new ADSuyiNativeAd(this);
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置整个广告视图预期宽高(目前仅优量汇、头条、艾狄墨搏平台需要，没有接入优量汇、头条、艾狄墨搏可不设置)，单位为px，高度如果小于等于0则高度自适应
                .adSize(new ADSuyiAdSize(widthPixels, 0))
                // 设置广告视图中MediaView的预期宽高(目前仅Inmobi平台需要,Inmobi的MediaView高度为自适应，没有接入Inmobi平台可不设置)，单位为px
                .nativeAdMediaViewSize(new ADSuyiAdSize((int) (widthPixels - 24 * getResources().getDisplayMetrics().density)))
                // 设置信息流广告适配播放是否静音，默认静音，目前优量汇、百度、汇量、快手、Admobile支持修改
                .nativeAdPlayWithMute(ADSuyiDemoConstant.NATIVE_AD_PLAY_WITH_MUTE)
                .build();
        // 设置一些额外参数，有些平台的广告可能需要传入一些额外参数，如果有接入头条、Inmobi平台，如果包含这些平台该参数必须设置
        adSuyiNativeAd.setLocalExtraParams(extraParams);
        adSuyiNativeAd.setTimeout(5000);

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
            public void onAdReceive(List<ADSuyiNativeAdInfo> adInfoList) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive: " + adInfoList.size());
                if (adInfoList != null && adInfoList.size() > 0) {
                    Toast.makeText(NativeSplashActivity.this, "广告获取成功", Toast.LENGTH_SHORT).show();
                    adSuyiNativeAdInfo = adInfoList.get(0);

                    showAd();
                }
            }

            @Override
            public void onAdExpose(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose: " + adSuyiNativeAdInfo.hashCode());
                tvSkip.setText("跳过5s");
                tvSkip.setAlpha(1);
                timer.start();
            }

            @Override
            public void onAdClick(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick: " + adSuyiNativeAdInfo.hashCode());
            }

            @Override
            public void onAdClose(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose: " + adSuyiNativeAdInfo.hashCode());
                finish();
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed: " + adSuyiError.toString());
                }
                finish();
            }
        });

        adSuyiNativeAd.loadAd(ADSuyiDemoConstant.NATIVE_AD_POS_ID, 1);
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

        if (adSuyiNativeAdInfo.isNativeExpress()) {
            showNativeExpressAd();
        } else {
            showNativeFeedAd();
        }
    }

    /**
     * 展示信息流模板广告
     */
    private void showNativeExpressAd() {
        flContainer2.setVisibility(View.VISIBLE);

        ADSuyiNativeExpressAdInfo nativeExpressAdInfo = (ADSuyiNativeExpressAdInfo) adSuyiNativeAdInfo;
        // 当前是信息流模板广告，getNativeExpressAdView获取的是整个模板广告视图
        View nativeExpressAdView = nativeExpressAdInfo.getNativeExpressAdView(flContainer2);
        // 将广告视图添加到容器中的便捷方法
        ADSuyiViewUtil.addAdViewToAdContainer(flContainer2, nativeExpressAdView);

        // 渲染广告视图, 必须调用, 因为是模板广告, 所以传入ViewGroup和响应点击的控件可能并没有用
        // 务必在最后调用
        nativeExpressAdInfo.render(flContainer2);
    }

    /**
     * 展示信息流自渲染广告
     */
    private void showNativeFeedAd() {
        nativeAdContainer.setVisibility(View.VISIBLE);

        ADSuyiNativeFeedAdInfo nativeFeedAdInfo = (ADSuyiNativeFeedAdInfo) adSuyiNativeAdInfo;

        if (ivIcon != null) {
            // 广告icon
            Glide.with(ivIcon).load(nativeFeedAdInfo.getIconUrl()).into(ivIcon);
        }
        if (tvTitle != null) {
            // 广告标题
            tvTitle.setText(nativeFeedAdInfo.getTitle());
        }
        if (tvDesc != null) {
            // 广告详情
            tvDesc.setText(nativeFeedAdInfo.getDesc());
        }
        // 广告平台logo图标
        ivAdTarget.setImageResource(nativeFeedAdInfo.getPlatformIcon());
        // 注册关闭按钮，将关闭按钮点击事件交于SDK托管，以便于回调onAdClose
        nativeFeedAdInfo.registerCloseView(tvSkip);

        if (nativeFeedAdInfo.hasMediaView()) {
            // 当前信息流原生广告，获取的是多媒体视图（可能是视频、或者图片之类的），mediaView不为空时强烈建议进行展示
            View mediaView = nativeFeedAdInfo.getMediaView(flContainer);
            // 将广告视图添加到容器中的便捷方法，mediaView为空会移除flMediaContainer的所有子View
            ADSuyiViewUtil.addAdViewToAdContainer(flContainer, mediaView);
        } else {
            ImageView imageView = new ImageView(flContainer.getContext());
            Glide.with(imageView.getContext()).load(nativeFeedAdInfo.getImageUrl()).into(imageView);
            ADSuyiViewUtil.addAdViewToAdContainer(flContainer, imageView );
        }

        // 注册广告交互, 必须调用
        // 注意：优量汇只会响应View...actionViews的点击事件，且这些View都应该是com.qq.e.ads.nativ.widget.NativeAdContainer的子View
        // 务必最后调用
        nativeFeedAdInfo.registerViewForInteraction(nativeAdContainer, rlAdContainer);
    }

    CountDownTimer timer = new CountDownTimer(5000, 1000) {
        public void onTick(long millisUntilFinished) {
            if (tvSkip != null) {
                tvSkip.setText("跳过" + (int)(millisUntilFinished / 1000) + "s");
            }
        }

        public void onFinish() {
            finish();
        }
    };

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
        timer.cancel();
        timer = null;
    }
}
