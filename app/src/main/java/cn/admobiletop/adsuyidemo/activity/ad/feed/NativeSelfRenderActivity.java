package cn.admobiletop.adsuyidemo.activity.ad.feed;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.List;

import cn.admobiletop.adsuyi.ad.ADSuyiNativeAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeFeedAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiNativeAdListener;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;

/**
 * @author : 草莓
 * @date : 2022/06/14
 * @description : 将信息流自渲染广告直接进行展示案例
 */
public class NativeSelfRenderActivity extends AppCompatActivity {

    private Button btnLoadAndShowAd;
    private Button btnLoadAd;
    private Button btnShowAd;

    /**
     * 自渲染相关布局
     */
    private ImageView ivIcon;
    private FrameLayout nativeAdContainer;
    private RelativeLayout rlAdContainer;
    private FrameLayout flContent;
    private ImageView ivAdTarget;
    private TextView tvTitle;
    private TextView tvDesc;
    private ImageView ivClose;

    private ADSuyiNativeAd adSuyiNativeAd;
    private ADSuyiNativeAdInfo adSuyiNativeAdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_native_self_render_ad);

        initView();
        initListener();

    }

    private void initView() {
        btnLoadAndShowAd = findViewById(R.id.btnLoadAndShowAd);
        btnLoadAd = findViewById(R.id.btnLoadAd);
        btnShowAd = findViewById(R.id.btnShowAd);

        nativeAdContainer = findViewById(R.id.nativeAdContainer);
        rlAdContainer = findViewById(R.id.rlAdContainer);
        flContent = findViewById(R.id.flContent);
        ivIcon = findViewById(R.id.ivIcon);
        ivAdTarget = findViewById(R.id.ivAdTarget);
        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.tvDesc);
        ivClose = findViewById(R.id.ivClose);
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
        // 设置一些额外参数，有些平台的广告可能需要传入一些额外参数，如果有接入头条、Inmobi平台，如果包含这些平台该参数必须设置
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
                    Toast.makeText(NativeSelfRenderActivity.this, "广告获取成功", Toast.LENGTH_SHORT).show();
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

        adSuyiNativeAd.loadAd(ADSuyiDemoConstant.NATIVE_AD_POS_ID1, 1);
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

        ADSuyiNativeFeedAdInfo nativeFeedAdInfo;
        if (adSuyiNativeAdInfo.isNativeExpress()) {
            Toast.makeText(this, "当前请求到广告非信息流自渲染广告，请使用信息流自渲染广告位", Toast.LENGTH_SHORT).show();
            Log.d(ADSuyiDemoConstant.TAG, "当前请求到广告非信息流自渲染广告，请使用信息流自渲染广告位");
            return;
        } else {
            // 将广告对象转换成自渲染广告
            nativeFeedAdInfo = (ADSuyiNativeFeedAdInfo)adSuyiNativeAdInfo;
        }

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
        nativeFeedAdInfo.registerCloseView(ivClose);

        flContent.removeAllViews();
        nativeAdContainer.setVisibility(View.VISIBLE);
        if (nativeFeedAdInfo.hasMediaView()) {
            View mediaView = nativeFeedAdInfo.getMediaView(flContent);
            flContent.addView(mediaView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            ImageView ivImage = new ImageView(flContent.getContext());
            Glide.with(ivImage).load(nativeFeedAdInfo.getImageUrl()).into(ivImage);
            flContent.addView(ivImage, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        // 注册广告交互, 必须调用
        // 务必最后调用
        nativeFeedAdInfo.registerViewForInteraction(
                nativeAdContainer,
                rlAdContainer
        );
    }

    /**
     * 关闭广告
     */
    private void closeAd() {
        nativeAdContainer.setVisibility(View.GONE);
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
