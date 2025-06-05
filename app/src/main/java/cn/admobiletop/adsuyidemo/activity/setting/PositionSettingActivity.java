package cn.admobiletop.adsuyidemo.activity.setting;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.admobiletop.adsuyi.ad.data.ADSuyiAdType;
import cn.admobiletop.adsuyi.util.ADSuyiToastUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;

/**
 * @author ciba
 * @description 设置界面
 * @date 2020/4/7
 */
public class PositionSettingActivity extends AppCompatActivity {
    private static final String AD_TYPE = "AD_TYPE";
    private static final String POS_ID_LIST = "POS_ID_LIST";
    private EditText etPosId;
    private TextView tvCount;
    private EditText etCount;
    private TextView tvAutoRefreshInterval;
    private EditText etAutoRefreshInterval;
    private SwitchCompat cbNativeMute;
    private String adType;
    private EditText etOnlySupportPlatform;
    private List<String> posIdList;
    private SwitchCompat cbOnlySupportPlatform;
    private TextView tvScene;
    private EditText etScene;
    private SwitchCompat cbCustomSkipView;
    private HashMap<String, String> platformMap = new HashMap<>();

    public static void start(Context context, String adType, ArrayList<String> posIdList) {
        Intent intent = new Intent(context, PositionSettingActivity.class);
        intent.putExtra(AD_TYPE, adType);
        intent.putStringArrayListExtra(POS_ID_LIST, posIdList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_setting);
        initPlatformData();
        initView();
        initListener();
        initData();
    }

    private void initPlatformData() {
        platformMap.put("所有(null或空字符串)", "");
        platformMap.put("天目(tianmu)", "tianmu");
        platformMap.put("优量汇/广点通(gdt)", "gdt");
        platformMap.put("穿山甲/头条(toutiao)", "toutiao");
        platformMap.put("百度/百青藤(baidu)", "baidu");
        platformMap.put("快手(ksad)", "ksad");
    }

    private String getPlatformKey(Map<String, String> map, String value) {
        if (value == null) {
            value = "";
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return "";
    }

    private void initView() {
        etPosId = findViewById(R.id.etPosId);
        tvCount = findViewById(R.id.tvCount);
        etCount = findViewById(R.id.etCount);

        etOnlySupportPlatform = findViewById(R.id.etOnlySupportPlatform);
        cbOnlySupportPlatform = findViewById(R.id.cbOnlySupportPlatform);

        tvAutoRefreshInterval = findViewById(R.id.tvAutoRefreshInterval);
        etAutoRefreshInterval = findViewById(R.id.etAutoRefreshInterval);

        cbNativeMute = findViewById(R.id.cbNativeMute);

        tvScene = findViewById(R.id.tvScene);
        etScene = findViewById(R.id.etScene);

        cbCustomSkipView = findViewById(R.id.cbCustomSkipView);
    }

    private void initListener() {
        findViewById(R.id.btnDefine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        etOnlySupportPlatform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlatformSelectDialog();
            }
        });
        etPosId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPosIdSelectDialog();
            }
        });
        etCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdCountSelectDialog();
            }
        });
    }

    private void showAdCountSelectDialog() {
        new AlertDialog.Builder(this)
                .setItems(R.array.ad_count, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etCount.setText(getResources().getStringArray(R.array.ad_count)[which]);
                    }
                })
                .create()
                .show();
    }

    private void showPosIdSelectDialog() {
        String[] posIds = new String[posIdList.size()];
        posIdList.toArray(posIds);

        new AlertDialog.Builder(this)
                .setItems(posIds, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etPosId.setText(posIdList.get(which));
                    }
                })
                .create()
                .show();
    }

    private void showPlatformSelectDialog() {
        new AlertDialog.Builder(this)
                .setItems(R.array.platforms_zh, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etOnlySupportPlatform.setText(getResources().getStringArray(R.array.platforms_zh)[which]);
                    }
                })
                .create()
                .show();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        adType = getIntent().getStringExtra(AD_TYPE);
        adType = adType == null ? "" : adType;
        posIdList = getIntent().getStringArrayListExtra(POS_ID_LIST);
        posIdList = posIdList == null ? new ArrayList<String>() : posIdList;

        switch (adType) {
            case ADSuyiAdType.TYPE_SPLASH:
                etPosId.setText(ADSuyiDemoConstant.SPLASH_AD_POS_ID);
                String platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                cbCustomSkipView.setVisibility(View.VISIBLE);
                cbCustomSkipView.setChecked(ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW);
                break;
            case ADSuyiAdType.TYPE_BANNER:
                etPosId.setText(ADSuyiDemoConstant.BANNER_AD_POS_ID);
                platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.BANNER_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                etAutoRefreshInterval.setText(String.valueOf(ADSuyiDemoConstant.BANNER_AD_AUTO_REFRESH_INTERVAL));
                tvAutoRefreshInterval.setVisibility(View.VISIBLE);
                etAutoRefreshInterval.setVisibility(View.VISIBLE);
                tvScene.setVisibility(View.VISIBLE);
                etScene.setVisibility(View.VISIBLE);
                etScene.setText(String.valueOf(ADSuyiDemoConstant.BANNER_AD_SCENE_ID));
                break;
            case ADSuyiAdType.TYPE_FLOW:
                etPosId.setText(ADSuyiDemoConstant.NATIVE_AD_POS_ID);
                platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.BANNER_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                etCount.setText(String.valueOf(ADSuyiDemoConstant.NATIVE_AD_COUNT));
                etCount.setVisibility(View.VISIBLE);
                tvCount.setVisibility(View.VISIBLE);
                cbNativeMute.setVisibility(View.VISIBLE);
                cbNativeMute.setChecked(ADSuyiDemoConstant.NATIVE_AD_PLAY_WITH_MUTE);
                tvScene.setVisibility(View.VISIBLE);
                etScene.setVisibility(View.VISIBLE);
                etScene.setText(String.valueOf(ADSuyiDemoConstant.NATIVE_AD_SCENE_ID));
                break;
            case ADSuyiAdType.TYPE_REWARD_VOD:
                etPosId.setText(ADSuyiDemoConstant.REWARD_VOD_AD_POS_ID);
                platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.REWARD_VOD_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                tvScene.setVisibility(View.VISIBLE);
                etScene.setVisibility(View.VISIBLE);
                etScene.setText(String.valueOf(ADSuyiDemoConstant.REWARD_VOD_AD_SCENE_ID));
                cbNativeMute.setVisibility(View.VISIBLE);
                cbNativeMute.setChecked(ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE);
                break;
            case ADSuyiAdType.TYPE_FULLSCREEN_VOD:
                etPosId.setText(ADSuyiDemoConstant.FULL_SCREEN_VOD_AD_POS_ID);
                platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.FULL_SCREEN_VOD_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                break;
            case ADSuyiAdType.TYPE_INTERSTITIAL:
                etPosId.setText(ADSuyiDemoConstant.INTERSTITIAL_AD_POS_ID);
                platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.INTERSTITIAL_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                tvScene.setVisibility(View.VISIBLE);
                etScene.setVisibility(View.VISIBLE);
                etScene.setText(String.valueOf(ADSuyiDemoConstant.INTERSTITIAL_AD_SCENE_ID));
                cbNativeMute.setVisibility(View.VISIBLE);
                cbNativeMute.setChecked(ADSuyiDemoConstant.INTERSTITIAL_AD_PLAY_WITH_MUTE);
                break;
            case ADSuyiAdType.TYPE_DRAW_VOD:
                etPosId.setText(ADSuyiDemoConstant.DRAW_VOD_AD_POS_ID);
                etCount.setText(String.valueOf(ADSuyiDemoConstant.NATIVE_AD_COUNT));
                platformKey = getPlatformKey(platformMap, ADSuyiDemoConstant.DRAW_VOD_AD_ONLY_SUPPORT_PLATFORM);
                etOnlySupportPlatform.setText(platformKey);
                etCount.setVisibility(View.VISIBLE);
                tvCount.setVisibility(View.VISIBLE);
                break;
            default:
                ADSuyiToastUtil.show(this, "非法广告类型");
                finish();
                break;
        }
    }

    private void updateData() {
        String posId = etPosId.getText().toString().trim();
        String platformKey = etOnlySupportPlatform.getText().toString().trim();
        String onlySupportPlatform = platformMap.get(platformKey);
        switch (adType) {
            case ADSuyiAdType.TYPE_SPLASH:
                ADSuyiDemoConstant.SPLASH_AD_POS_ID = posId;
                ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                ADSuyiDemoConstant.SPLASH_AD_CUSTOM_SKIP_VIEW = cbCustomSkipView.isChecked();
                break;
            case ADSuyiAdType.TYPE_BANNER:
                ADSuyiDemoConstant.BANNER_AD_POS_ID = posId;
                ADSuyiDemoConstant.BANNER_AD_AUTO_REFRESH_INTERVAL = getAutoRefreshInterval();
                ADSuyiDemoConstant.BANNER_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                ADSuyiDemoConstant.BANNER_AD_SCENE_ID = getSceneId();
                break;
            case ADSuyiAdType.TYPE_FLOW:
                ADSuyiDemoConstant.NATIVE_AD_POS_ID = posId;
                ADSuyiDemoConstant.NATIVE_AD_COUNT = getAdCount();
                ADSuyiDemoConstant.NATIVE_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                ADSuyiDemoConstant.NATIVE_AD_PLAY_WITH_MUTE = cbNativeMute.isChecked();
                ADSuyiDemoConstant.NATIVE_AD_SCENE_ID = getSceneId();
                break;
            case ADSuyiAdType.TYPE_REWARD_VOD:
                ADSuyiDemoConstant.REWARD_VOD_AD_POS_ID = posId;
                ADSuyiDemoConstant.REWARD_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE = cbNativeMute.isChecked();
                ADSuyiDemoConstant.REWARD_VOD_AD_SCENE_ID = getSceneId();
                break;
            case ADSuyiAdType.TYPE_FULLSCREEN_VOD:
                ADSuyiDemoConstant.FULL_SCREEN_VOD_AD_POS_ID = posId;
                ADSuyiDemoConstant.FULL_SCREEN_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                break;
            case ADSuyiAdType.TYPE_INTERSTITIAL:
                ADSuyiDemoConstant.INTERSTITIAL_AD_POS_ID = posId;
                ADSuyiDemoConstant.INTERSTITIAL_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                ADSuyiDemoConstant.INTERSTITIAL_AD_PLAY_WITH_MUTE = cbNativeMute.isChecked();
                ADSuyiDemoConstant.INTERSTITIAL_AD_SCENE_ID = getSceneId();
                break;
            case ADSuyiAdType.TYPE_DRAW_VOD:
                ADSuyiDemoConstant.DRAW_VOD_AD_POS_ID = posId;
                ADSuyiDemoConstant.NATIVE_AD_COUNT = getAdCount();
                ADSuyiDemoConstant.DRAW_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
                break;
            default:
                break;
        }
        if (cbOnlySupportPlatform.isChecked()) {
            ADSuyiDemoConstant.SPLASH_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
            ADSuyiDemoConstant.BANNER_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
            ADSuyiDemoConstant.NATIVE_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
            ADSuyiDemoConstant.REWARD_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
            ADSuyiDemoConstant.FULL_SCREEN_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
            ADSuyiDemoConstant.INTERSTITIAL_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
            ADSuyiDemoConstant.DRAW_VOD_AD_ONLY_SUPPORT_PLATFORM = onlySupportPlatform;
        }

        ADSuyiToastUtil.show(this, "修改成功");
        finish();
    }

    private int getAutoRefreshInterval() {
        String autoRefreshIntervalStr = etAutoRefreshInterval.getText().toString().trim();
        try {
            int autoRefreshInterval = Integer.parseInt(autoRefreshIntervalStr);
            return autoRefreshInterval <= 0 ? 0 : autoRefreshInterval < 30 ? 30 : autoRefreshInterval > 120 ? 120 : autoRefreshInterval;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getAdCount() {
        String countStr = etCount.getText().toString().trim();
        try {
            int count = Integer.parseInt(countStr);
            return count <= 0 ? 1 : count > 3 ? 3 : count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    private String getSceneId() {
        String sceneStr = etScene.getText().toString().trim();
        return sceneStr;
    }
}
