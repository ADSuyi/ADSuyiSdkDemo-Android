package cn.admobiletop.adsuyidemo.activity.ad.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.constant.ADSuyiDemoConstant;

/**
 * @Description:
 * @Author: 草莓
 * @CreateDate: 7/15/22 10:39 AM
 */
public class SplashAdSettingActivity extends AppCompatActivity {

    private RadioGroup rgDisplayStyle;
    private RadioGroup rgLoadType;
    private LinearLayout llHalfEnterLogoHeightSize;
    private EditText etLogoHeight;

    private int splashType = ADSuyiDemoConstant.HALF_SCREEN;

    private int loadType = ADSuyiDemoConstant.LOAD_AND_SHOW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_setting);

        rgDisplayStyle = findViewById(R.id.rgDisplayStyle);
        rgLoadType = findViewById(R.id.rgLoadType);
        llHalfEnterLogoHeightSize = findViewById(R.id.llHalfEnterLogoHeightSize);
        etLogoHeight = findViewById(R.id.etLogoHeight);

        rgDisplayStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbHalf) {
                    splashType = ADSuyiDemoConstant.HALF_SCREEN;
                    llHalfEnterLogoHeightSize.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rbFullScreen) {
                    splashType = ADSuyiDemoConstant.FULL_SCREEN;
                    llHalfEnterLogoHeightSize.setVisibility(View.GONE);
                } else if (checkedId == R.id.rbImmersiveAndFullScreen) {
                    splashType = ADSuyiDemoConstant.IMMERSIVE_AND_FULLSCREEN;
                    llHalfEnterLogoHeightSize.setVisibility(View.GONE);
                }
            }
        });

        rgLoadType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.loadAndShow) {
                    loadType = ADSuyiDemoConstant.LOAD_AND_SHOW;
                } else if (checkedId == R.id.loadOnly) {
                    loadType = ADSuyiDemoConstant.LOAD_ONLY;
                }
            }
        });

        findViewById(R.id.btnLoadSplashAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashAdSettingActivity.this, SplashAdActivity.class);
                String etLogoHeightString = etLogoHeight.getText().toString().trim();
                intent.putExtra("splashType", splashType);
                intent.putExtra("logoHeightPx", TextUtils.isEmpty(etLogoHeightString) ? 0 : Integer.valueOf(etLogoHeightString));
                intent.putExtra("loadType", loadType);
                startActivity(intent);
            }
        });

    }

}
