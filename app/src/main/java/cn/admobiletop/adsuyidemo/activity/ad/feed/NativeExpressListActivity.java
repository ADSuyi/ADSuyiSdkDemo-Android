package cn.admobiletop.adsuyidemo.activity.ad.feed;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import cn.admobiletop.adsuyi.ad.ADSuyiNativeAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiNativeAdListener;
import cn.admobiletop.adsuyi.util.ADSuyiDisplayUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.base.BaseAdActivity;
import cn.admobiletop.adsuyidemo.adapter.NativeAdAdapter;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.widget.MySmartRefreshLayout;

public class NativeExpressListActivity extends BaseAdActivity implements OnRefreshLoadMoreListener {
    private MySmartRefreshLayout refreshLayout;
    private NativeAdAdapter nativeAdAdapter;
    private ADSuyiNativeAd adSuyiNativeAd;
    private List<Object> tempDataList = new ArrayList<>();
    private int refreshType;
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);
        initView();
        initListener();
        initAd();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout = findViewById(R.id.refreshLayout);

        nativeAdAdapter = new NativeAdAdapter();
        recyclerView.setAdapter(nativeAdAdapter);
    }

    private void initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(this);
    }

    private void initAd() {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        // 创建信息流广告实例
        adSuyiNativeAd = new ADSuyiNativeAd(this);

        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置整个广告视图预期宽高(目前仅头条平台需要，没有接入头条可不设置)，单位为px，高度如果小于等于0则高度自适应
                .adSize(new ADSuyiAdSize(widthPixels, 0))
                // 设置广告视图中MediaView的预期宽高(目前仅Inmobi平台需要,Inmobi的MediaView高度为自适应，没有接入Inmobi平台可不设置)，单位为px
                .nativeAdMediaViewSize(new ADSuyiAdSize((int) (widthPixels - 24 * getResources().getDisplayMetrics().density)))
//                .nativeStyle(adNativeStyle)
                // 设置信息流广告适配播放是否静音，默认静音，目前广点通、百度、汇量、快手、Admobile支持修改
                .nativeAdPlayWithMute(ADSuyiDemoConstant.NATIVE_AD_PLAY_WITH_MUTE)
                .build();
        // 设置一些额外参数，有些平台的广告可能需要传入一些额外参数，如果有接入头条、Inmobi平台，如果包含这些平台该参数必须设置
        adSuyiNativeAd.setLocalExtraParams(extraParams);

        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        adSuyiNativeAd.setOnlySupportPlatform(ADSuyiDemoConstant.NATIVE_AD_ONLY_SUPPORT_PLATFORM);
        // 设置广告监听
        adSuyiNativeAd.setListener(new ADSuyiNativeAdListener() {
            @Override
            public void onRenderFailed(ADSuyiNativeAdInfo adSuyiNativeAdInfo, ADSuyiError adSuyiError) {
                Log.d(ADSuyiDemoConstant.TAG, "onRenderFailed: " + adSuyiError.toString());
                // 广告渲染失败，释放和移除ADSuyiNativeAdInfo
                nativeAdAdapter.removeData(adSuyiNativeAdInfo);
            }

            @Override
            public void onRenderSuccess(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                // 广告渲染成功，仅部分渠道支持
                Log.d(ADSuyiDemoConstant.TAG, "onRenderSuccess: " + adSuyiNativeAdInfo.hashCode());
            }

            @Override
            public void onAdReceive(List<ADSuyiNativeAdInfo> adInfoList) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive: " + adInfoList.size());
                for (int i = 0; i < adInfoList.size(); i++) {
                    Log.d(
                            ADSuyiDemoConstant.TAG,
                            "platform : " + adInfoList.get(i).getPlatform() +
                                    " price : " +  adInfoList.get(i).getECPM() +
                                    " getEcpmPrecision : " +  adInfoList.get(i).getEcpmPrecision()
                    );

                    int index = i * 5;
                    ADSuyiNativeAdInfo nativeAdInfo = adInfoList.get(i);
                    if (index >= tempDataList.size()) {
                        tempDataList.add(nativeAdInfo);
                    } else {
                        tempDataList.add(index, nativeAdInfo);
                    }
                    Log.d(ADSuyiDemoConstant.TAG, "onAdReceive hash code: " + adInfoList.get(i).hashCode());
                }
                nativeAdAdapter.addData(tempDataList);
                refreshLayout.finish(refreshType, true, false);
            }

            @Override
            public void onAdExpose(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose: " + adSuyiNativeAdInfo.hashCode());
                setTvMessage("onAdExpose..." + adSuyiNativeAdInfo.toString());
            }

            @Override
            public void onAdClick(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick: " + adSuyiNativeAdInfo.hashCode());
                setTvMessage("onAdClick..." + adSuyiNativeAdInfo.toString());
            }

            @Override
            public void onAdClose(ADSuyiNativeAdInfo adSuyiNativeAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose: " + adSuyiNativeAdInfo.hashCode());
                // 广告被关闭，释放和移除ADSuyiNativeAdInfo
                nativeAdAdapter.removeData(adSuyiNativeAdInfo);
                setTvMessage("onAdClose..." + adSuyiNativeAdInfo.toString());
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed: " + adSuyiError.toString());
                }
                nativeAdAdapter.addData(tempDataList);
                refreshLayout.finish(refreshType, false, false);
                setTvMessage("onAdFailed...");
            }
        });

        // 触发刷新
        refreshLayout.autoRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshType = MySmartRefreshLayout.TYPE_LOAD_MORE;
        loadData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshType = MySmartRefreshLayout.TYPE_FRESH;
        nativeAdAdapter.clearData();
        loadData();
    }

    /**
     * 加载数据和广告
     */
    private void loadData() {
        tempDataList.clear();
        mockNormalDataRequest();
        // 信息流广告场景id（场景id非必选字段，如果需要可到开发者后台创建）
        adSuyiNativeAd.setSceneId(ADSuyiDemoConstant.NATIVE_AD_SCENE_ID);
        // 请求广告数据，参数一广告位ID，参数二请求数量[1,3]
        adSuyiNativeAd.loadAd(ADSuyiDemoConstant.NATIVE_AD_POS_ID4, ADSuyiDemoConstant.NATIVE_AD_COUNT);
    }

    /**
     * 模拟普通数据请求
     */
    private void mockNormalDataRequest() {
        for (int i = 0; i < 20; i++) {
            tempDataList.add("模拟的普通数据 : " + (nativeAdAdapter == null ? 0 : nativeAdAdapter.getItemCount() + i));
        }
    }

    public void setTvMessage(String msg) {
        stringBuffer.append(msg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initAd();
    }
}
