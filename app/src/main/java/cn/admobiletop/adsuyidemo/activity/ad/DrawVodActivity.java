package cn.admobiletop.adsuyidemo.activity.ad;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import cn.admobiletop.adsuyi.ad.ADSuyiDrawVodAd;
import cn.admobiletop.adsuyi.ad.data.ADSuyiDrawVodAdInfo;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize;
import cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams;
import cn.admobiletop.adsuyi.ad.error.ADSuyiError;
import cn.admobiletop.adsuyi.ad.listener.ADSuyiDrawVodAdListener;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.adapter.DrawVodAdAdapter;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;
import cn.admobiletop.adsuyidemo.entity.DrawVodAdSampleData;
import cn.admobiletop.adsuyidemo.widget.MySmartRefreshLayout;
import cn.admobiletop.adsuyidemo.widget.ViewPagerLayoutManager;

/**
 * @author ciba
 * @description ：Draw视频信息流广告示例
 * @date 2020/4/7
 */
public class DrawVodActivity extends AppCompatActivity implements OnRefreshLoadMoreListener {
    private MySmartRefreshLayout refreshLayout;
    private ADSuyiDrawVodAd drawVodAd;
    private DrawVodAdAdapter drawVodAdAdapter;
    private int refreshType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_vod);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        refreshLayout = findViewById(R.id.refreshLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL));
        drawVodAdAdapter = new DrawVodAdAdapter(this);
        recyclerView.setAdapter(drawVodAdAdapter);
    }

    private void initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(this);
    }

    private void initData() {
        drawVodAd = new ADSuyiDrawVodAd(this);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                // 设置广告预期宽高，单位为px，宽高均不能小于等于0
                .adSize(new ADSuyiAdSize(width, height))
                .build();
        // 设置一些额外参数，有些平台的广告可能需要传入一些额外参数
        drawVodAd.setLocalExtraParams(extraParams);

        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        drawVodAd.setOnlySupportPlatform(ADSuyiDemoConstant.DRAW_VOD_AD_ONLY_SUPPORT_PLATFORM);
        // 设置DrawVod广告监听
        drawVodAd.setListener(new ADSuyiDrawVodAdListener() {
            @Override
            public void onRenderFailed(ADSuyiDrawVodAdInfo adSuyiDrawVodAdInfo, ADSuyiError adSuyiError) {
                Log.d(ADSuyiDemoConstant.TAG, "onRenderFailed: " + adSuyiError.toString());
                // 广告渲染失败，释放并移除ADSuyiDrawVodAdInfo
                drawVodAdAdapter.removeData(adSuyiDrawVodAdInfo);
            }

            @Override
            public void onAdReceive(List<ADSuyiDrawVodAdInfo> adInfoList) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdReceive: " + adInfoList.size());
                List<DrawVodAdSampleData> drawVodAdSampleDataList = new ArrayList<>();
                for (int i = 0; i < adInfoList.size(); i++) {
                    drawVodAdSampleDataList.add(new DrawVodAdSampleData(adInfoList.get(i)));
                }
                drawVodAdAdapter.addData(drawVodAdSampleDataList);
                refreshLayout.finish(refreshType, true, false);
                refreshLayout.autoRefresh();
            }

            @Override
            public void onAdExpose(ADSuyiDrawVodAdInfo adSuyiDrawVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdExpose: " + adSuyiDrawVodAdInfo.hashCode());
            }

            @Override
            public void onAdClick(ADSuyiDrawVodAdInfo adSuyiDrawVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClick: " + adSuyiDrawVodAdInfo.hashCode());
            }

            @Override
            public void onAdClose(ADSuyiDrawVodAdInfo adSuyiDrawVodAdInfo) {
                Log.d(ADSuyiDemoConstant.TAG, "onAdClose: " + adSuyiDrawVodAdInfo.hashCode());
                // 广告关闭，释放并移除ADSuyiDrawVodAdInfo
                drawVodAdAdapter.removeData(adSuyiDrawVodAdInfo);
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    String failedJson = adSuyiError.toString();
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed : " + failedJson);
                }
                refreshLayout.finish(refreshType, false, false);
                refreshLayout.autoRefresh();
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshType = MySmartRefreshLayout.TYPE_FRESH;
        drawVodAdAdapter.clearData();
        loadData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshType = MySmartRefreshLayout.TYPE_LOAD_MORE;
        loadData();
    }

    private void loadData() {
        List<DrawVodAdSampleData> normalDataList = mockNormalDataRequest();
        drawVodAdAdapter.addData(normalDataList);

        // 请求广告数据，参数一广告位ID，参数二请求数量[1,3]
        drawVodAd.loadAd(ADSuyiDemoConstant.DRAW_VOD_AD_POS_ID, ADSuyiDemoConstant.DRAW_VOD_AD_COUNT);
    }

    /**
     * 模拟普通数据请求
     *
     * @return : 普通数据列表
     */
    private List<DrawVodAdSampleData> mockNormalDataRequest() {
        List<DrawVodAdSampleData> normalDataList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            normalDataList.add(new DrawVodAdSampleData("Normal Data Page"));
        }
        return normalDataList;
    }
}
