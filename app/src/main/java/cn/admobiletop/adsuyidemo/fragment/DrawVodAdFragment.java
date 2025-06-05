package cn.admobiletop.adsuyidemo.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * @description Draw视频信息流广告Fragment示例
 * @date 2020/4/20
 */
public class DrawVodAdFragment extends BaseFragment implements OnRefreshLoadMoreListener {
    private static final String[] MOCK_IMAGE_URLS = {"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3866740925,837396992&fm=26&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586249535109&di=0b7de387c7b00bcb4b8564b9f8f0e230&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201306%2F06%2F20130606201616_4ZivY.jpeg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586249535108&di=d9a6b1312cfe8d48d3ff742dab50cb90&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F13%2F20170813181506_Q2YUw.jpeg"
            , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=357485033,2928965735&fm=15&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586249580122&di=bc848ed60b06740e3fd84fcf69319a21&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253d580%2Fsign%3Daa5b70becd1b9d168ac79a69c3dfb4eb%2F4d1833fa828ba61e744e2a4e4234970a314e59d9.jpg"};
    private MySmartRefreshLayout refreshLayout;
    private DrawVodAdAdapter drawVodAdAdapter;
    private ADSuyiDrawVodAd drawVodAd;
    private int refreshType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_draw_vod, null);

        initView(inflate);
        initListener();
        initData();

        return inflate;
    }

    @Override
    public String getTitle() {
        return "DrawVod";
    }

    private void initView(View inflate) {
        refreshLayout = inflate.findViewById(R.id.refreshLayout);

        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new ViewPagerLayoutManager(inflate.getContext(), LinearLayoutManager.VERTICAL));
        drawVodAdAdapter = new DrawVodAdAdapter(inflate.getContext());
        recyclerView.setAdapter(drawVodAdAdapter);
    }

    private void initListener() {
        refreshLayout.setOnRefreshLoadMoreListener(this);
    }

    private void initData() {
        drawVodAd = new ADSuyiDrawVodAd(this);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = width * 16 / 9;
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                .adSize(new ADSuyiAdSize(width, height))
                .build();
        drawVodAd.setLocalExtraParams(extraParams);
        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
        // 注：仅debug模式为true时生效。
        drawVodAd.setOnlySupportPlatform(ADSuyiDemoConstant.DRAW_VOD_AD_ONLY_SUPPORT_PLATFORM);

        drawVodAd.setListener(new ADSuyiDrawVodAdListener() {
            @Override
            public void onRenderFailed(ADSuyiDrawVodAdInfo adSuyiDrawVodAdInfo, ADSuyiError adSuyiError) {
                Log.d(ADSuyiDemoConstant.TAG, "onRenderFailed: " + adSuyiError.toString());
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
                drawVodAdAdapter.removeData(adSuyiDrawVodAdInfo);
            }

            @Override
            public void onAdFailed(ADSuyiError adSuyiError) {
                if (adSuyiError != null) {
                    String failedJson = adSuyiError.toString();
                    Log.d(ADSuyiDemoConstant.TAG, "onAdFailed : " + failedJson);
                }
                refreshLayout.finish(refreshType, false, false);
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
        for (int i = 0; i < 5; i++) {
            normalDataList.add(new DrawVodAdSampleData(MOCK_IMAGE_URLS[i % MOCK_IMAGE_URLS.length]));
        }
        return normalDataList;
    }
}
