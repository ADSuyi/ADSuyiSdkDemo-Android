package cn.admobiletop.adsuyidemo.adapter.holder;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.admobiletop.adsuyi.ad.data.ADSuyiAdAppInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeFeedAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAppInfo;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiNativeVideoListener;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyi.util.ADSuyiViewUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;

/**
 * 信息流原生广告BaseViewHolder
 */
public class NativeFeedAdViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ivIcon;
    private final RelativeLayout rlAdContainer;
    private final ImageView ivAdTarget;
    private final TextView tvTitle;
    private final TextView tvDesc;
    private final ImageView ivClose;

    private final ImageView ivImage;
    private final FrameLayout flMediaContainer;

    public NativeFeedAdViewHolder(@NonNull ViewGroup viewGroup) {
        super(
                LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.item_native_ad_native_ad,
                        viewGroup,
                        false)
        );

        rlAdContainer = itemView.findViewById(R.id.rlAdContainer);
        ivIcon = itemView.findViewById(R.id.ivIcon);
        ivAdTarget = itemView.findViewById(R.id.ivAdTarget);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDesc = itemView.findViewById(R.id.tvDesc);
        ivClose = itemView.findViewById(R.id.ivClose);

        ivImage = itemView.findViewById(R.id.ivImage);
        flMediaContainer = itemView.findViewById(R.id.flMediaContainer);
    }

    public void setData(ADSuyiNativeFeedAdInfo nativeFeedAdInfo) {
        // 判断广告Info对象是否被释放（调用过ADSuyiNativeAd的release()或ADSuyiNativeAdInfo的release()会释放广告Info对象）
        // 释放后的广告Info对象不能再次使用
        if (!ADSuyiAdUtil.adInfoIsRelease(nativeFeedAdInfo)) {
            setVideoListener(nativeFeedAdInfo);

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

            // 下载类广告六要素
            if (nativeFeedAdInfo instanceof ADSuyiAdAppInfo) {
                ADSuyiAppInfo adSuyiAppInfo = ((ADSuyiAdAppInfo) nativeFeedAdInfo).getAppInfo();
                if (adSuyiAppInfo != null) {
                    // 应用名
                    String name = adSuyiAppInfo.getName();
                    // 开发者
                    String developer = adSuyiAppInfo.getDeveloper();
                    // 版本号
                    String version = adSuyiAppInfo.getVersion();
                    // 隐私地址
                    String privacyUrl = adSuyiAppInfo.getPrivacyUrl();
                    // 权限地址
                    String permissionsUrl = adSuyiAppInfo.getPermissionsUrl();
                    // 功能介绍
                    String descriptionUrl = adSuyiAppInfo.getDescriptionUrl();
                    // 应用大小
                    long size = adSuyiAppInfo.getSize();
                    // icp备案号
                    String icp = adSuyiAppInfo.getIcp();
                }
            }

            if (nativeFeedAdInfo.isVideo()) {
                // 当前信息流原生广告，获取的是多媒体视图（可能是视频、或者图片之类的），mediaView不为空时强烈建议进行展示
                View mediaView = nativeFeedAdInfo.getMediaView(flMediaContainer);
                // 将广告视图添加到容器中的便捷方法，mediaView为空会移除flMediaContainer的所有子View
                ADSuyiViewUtil.addAdViewToAdContainer(flMediaContainer, mediaView);
                flMediaContainer.setVisibility(View.VISIBLE);
                ivImage.setVisibility(View.GONE);
            } else {
                Glide.with(ivImage.getContext()).load(nativeFeedAdInfo.getImageUrl()).into(ivImage);
                flMediaContainer.setVisibility(View.GONE);
                ivImage.setVisibility(View.VISIBLE);
            }
            // 注册广告交互, 必须调用
            // 务必最后调用
            nativeFeedAdInfo.registerViewForInteraction(
                    rlAdContainer,
                    rlAdContainer
            );
        }
    }

    /**
     * 设置视频监听，无需求可不设置，视频监听回调因平台差异会有所不一，如：某些平台可能没有完成回调等
     */
    private static void setVideoListener(ADSuyiNativeAdInfo nativeAdInfo) {
        if (nativeAdInfo.isVideo()) {
            // 设置视频监听，监听回调因三方平台SDK差异有所差异，无需要可不设置
            nativeAdInfo.setVideoListener(new ADSuyiNativeVideoListener() {
                @Override
                public void onVideoLoad(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                    Log.d(ADSuyiDemoConstant.TAG, "onVideoLoad.... ");
                }

                @Override
                public void onVideoStart(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                    Log.d(ADSuyiDemoConstant.TAG, "onVideoStart.... ");
                }

                @Override
                public void onVideoPause(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                    Log.d(ADSuyiDemoConstant.TAG, "onVideoPause.... ");
                }

                @Override
                public void onVideoComplete(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                    Log.d(ADSuyiDemoConstant.TAG, "onVideoComplete.... ");
                }

                @Override
                public void onVideoError(ADSuyiNativeAdInfo adSuyiNativeAdInfo, ADSuyiError adSuyiError) {
                    Log.d(ADSuyiDemoConstant.TAG, "onVideoError.... " + adSuyiError.toString());
                }
            });
        }
    }
}
