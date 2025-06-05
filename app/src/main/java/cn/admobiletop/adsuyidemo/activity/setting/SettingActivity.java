package cn.admobiletop.adsuyidemo.activity.setting;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.base.BaseAdActivity;
import cn.admobiletop.adsuyidemo.adapter.SettingAdapter;
import cn.admobiletop.adsuyidemo.entity.AdSettingData;

public class SettingActivity extends BaseAdActivity {
    public static final String KEY_ONLY_SUPPORT_PLATFORM = "KEY_ONLY_SUPPORT_PLATFORM";
    public static final String KEY_OPEN_FLOATING_AD = "KEY_OPEN_FLOATING_AD";

    private RecyclerView mRv;
    private SettingAdapter mAdapter;
    private List<AdSettingData> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();
    }



    private void initView() {
        mRv = findViewById(R.id.recycler);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SettingAdapter(this);
        mRv.setAdapter(mAdapter);
    }

    private void initData() {
        AdSettingData titlePlatData = new AdSettingData(SettingAdapter.ITEM_VIEW_TYPE_TITLE_DATA,"广告平台设置-所有广告位都生效");
        mData.add(titlePlatData);
        AdSettingData platformData = new AdSettingData(SettingAdapter.ITEM_VIEW_TYPE_PLATFORM_DATA,"选择广告平台");
        mData.add(platformData);
        AdSettingData titleAdData = new AdSettingData(SettingAdapter.ITEM_VIEW_TYPE_TITLE_DATA,"广告位设置");
        mData.add(titleAdData);

        String[] adTypeItems = getResources().getStringArray(R.array.ad_type_items);
        List<String> TypeItems = Arrays.asList(adTypeItems);
        for (String typeItem : TypeItems) {
            AdSettingData TypeData = new AdSettingData(SettingAdapter.ITEM_VIEW_TYPE_AD_DATA, typeItem);
            mData.add(TypeData);
        }
        mAdapter.setDataList(mData);
        mAdapter.notifyDataSetChanged();
    }
}
