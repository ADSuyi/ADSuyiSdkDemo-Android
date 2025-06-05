package cn.admobiletop.adsuyidemo.widget;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeFeedAdInfo;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyidemo.R;

/**
 * @author 草莓
 * @description admobileDl自渲染广告弹出框
 * @date 2020/10/21
 */
public class AdMobileDlFeedAdDialog extends Dialog {

    private RelativeLayout rlAdContainer;
    private ImageView ivImage;
    private ImageView ivClose;
    private ImageView ivAdTarget;

    public AdMobileDlFeedAdDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_admobile_dl_ad2);
        rlAdContainer = findViewById(R.id.rlAdContainer);
        ivImage = findViewById(R.id.ivImage);
        ivClose = findViewById(R.id.ivClose);
        ivAdTarget = findViewById(R.id.ivAdTarget);
        // 设置点击外部不关闭dialog
        setCanceledOnTouchOutside(false);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    public void render(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
        if(adSuyiNativeAdInfo instanceof ADSuyiNativeFeedAdInfo) {
            ADSuyiNativeFeedAdInfo nativeFeedAdInfo = (ADSuyiNativeFeedAdInfo) adSuyiNativeAdInfo;
            if (nativeFeedAdInfo == null) {
                Log.d("AdMobileDlAdDialog", "ADSuyiNativeFeedAdInfo is null");
                return;
            }
            if (nativeFeedAdInfo.hasMediaView()) {
                Log.d("AdMobileDlAdDialog", "dl广告没有MediaView");
                return;
            }
            // 判断广告Info对象是否被释放（调用过ADSuyiNativeAd的release()或ADSuyiNativeAdInfo的release()会释放广告Info对象）
            // 释放后的广告Info对象不能再次使用
            if (ADSuyiAdUtil.adInfoIsRelease(nativeFeedAdInfo)) {
                Log.d("AdMobileDlAdDialog", "dl广告对象已被释放");
                return;
            }
            Glide.with(ivImage).load(nativeFeedAdInfo.getImageUrl()).into(ivImage);
            ivAdTarget.setImageResource(nativeFeedAdInfo.getPlatformIcon());
            // 注册关闭按钮，将关闭按钮点击事件交于SDK托管，以便于回调onAdClose
            nativeFeedAdInfo.registerCloseView(ivClose);
            // 注册广告交互, 必须调用
            // 务必最后调用
            nativeFeedAdInfo.registerViewForInteraction(rlAdContainer, rlAdContainer);
            show();
        } else {
            Log.d("AdMobileDlAdDialog", "dl广告对象类型异常");
        }
    }
}
