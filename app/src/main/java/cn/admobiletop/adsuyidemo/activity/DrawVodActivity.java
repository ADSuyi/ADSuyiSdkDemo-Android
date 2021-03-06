package cn.admobiletop.adsuyidemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private static final String[] MOCK_IMAGE_URLS = {"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3866740925,837396992&fm=26&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586249535109&di=0b7de387c7b00bcb4b8564b9f8f0e230&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201306%2F06%2F20130606201616_4ZivY.jpeg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586249535108&di=d9a6b1312cfe8d48d3ff742dab50cb90&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F13%2F20170813181506_Q2YUw.jpeg"
            , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=357485033,2928965735&fm=15&gp=0.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586249580122&di=bc848ed60b06740e3fd84fcf69319a21&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253d580%2Fsign%3Daa5b70becd1b9d168ac79a69c3dfb4eb%2F4d1833fa828ba61e744e2a4e4234970a314e59d9.jpg"};
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
                // 设置广告预期宽高(目前仅头条平台需要，没有接入头条平台可不设置)，单位为px，宽高均不能小于等于0
                .adSize(new ADSuyiAdSize(width, height))
                .build();
        // 设置一些额外参数，有些平台的广告可能需要传入一些额外参数，如果有接入头条、快手平台，该参数必须设置
        drawVodAd.setLocalExtraParams(extraParams);

        // 设置仅支持的广告平台，设置了这个值，获取广告时只会去获取该平台的广告，null或空字符串为不限制，默认为null，方便调试使用，上线时建议不设置
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
