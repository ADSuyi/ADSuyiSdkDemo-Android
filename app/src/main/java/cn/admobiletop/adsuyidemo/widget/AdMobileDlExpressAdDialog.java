package cn.admobiletop.adsuyidemo.widget;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeExpressAdInfo;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyi.util.ADSuyiViewUtil;
import cn.admobiletop.adsuyidemo.R;

/**
 * @author 草莓
 * @description admobileDl 模板广告弹出框
 * @date 2020/10/21
 */
public class AdMobileDlExpressAdDialog extends Dialog {

    public AdMobileDlExpressAdDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_admobile_dl_ad);
        // 设置点击外部不关闭dialog
        setCanceledOnTouchOutside(false);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    public void render(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
        if(adSuyiNativeAdInfo instanceof ADSuyiNativeExpressAdInfo) {
            ADSuyiNativeExpressAdInfo nativeExpressAdInfo = (ADSuyiNativeExpressAdInfo) adSuyiNativeAdInfo;
            if (nativeExpressAdInfo == null) {
                Log.d("AdMobileDlAdDialog", "ADSuyiNativeExpressAdInfo is null");
                return;
            }
            // 判断广告Info对象是否被释放（调用过ADSuyiNativeAd的release()或ADSuyiNativeAdInfo的release()会释放广告Info对象）
            // 释放后的广告Info对象不能再次使用
            if (ADSuyiAdUtil.adInfoIsRelease(nativeExpressAdInfo)) {
                Log.d("AdMobileDlAdDialog", "dl广告对象已被释放");
                return;
            }
            RelativeLayout relativeLayout = findViewById(R.id.rlAdContainer);
            // 当前是信息流模板广告，getNativeExpressAdView获取的是整个模板广告视图
            View nativeExpressAdView = nativeExpressAdInfo.getNativeExpressAdView(relativeLayout);
            // 将广告视图添加到容器中的便捷方法
            ADSuyiViewUtil.addAdViewToAdContainer(relativeLayout, nativeExpressAdView);

            // 渲染广告视图, 必须调用, 因为是模板广告, 所以传入ViewGroup和响应点击的控件可能并没有用
            // 务必在最后调用
            nativeExpressAdInfo.render(relativeLayout);
            show();
        } else {
            Log.d("AdMobileDlAdDialog", "dl广告对象类型异常");
        }
    }
}
