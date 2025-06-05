package cn.admobiletop.adsuyidemo.activity.ad;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.admobiletop.adsuyidemo.ADSuyiApplication;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.MainActivity;
import cn.admobiletop.adsuyidemo.activity.other.NormalWebActivity;
import cn.admobiletop.adsuyidemo.util.SPUtil;
import cn.admobiletop.adsuyidemo.util.StringUtil;

public class PrivacyActivity extends AppCompatActivity {

    private TextView tv_content;
    private TextView tv_disagree;
    private TextView tv_agree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_privacy);

        initView();
        initListener();
        initData();

        // 获取是否已经同意过隐私政策
        boolean agreePrivacyPolicy = SPUtil.getBoolean(this, ADSuyiApplication.AGREE_PRIVACY_POLICY);
        if (agreePrivacyPolicy) {
            ADSuyiApplication.initAd();
            jumpMain();
        }
    }

    private void initView() {
        tv_content = findViewById(R.id.tv_content);
        tv_disagree = findViewById(R.id.tv_disagree);
        tv_agree = findViewById(R.id.tv_agree);
    }

    private void initListener() {
        tv_disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpMain();
            }
        });

        tv_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 用户同意之后SP进行记录
                SPUtil.putBoolean(getApplicationContext(), ADSuyiApplication.AGREE_PRIVACY_POLICY, true);
                ADSuyiApplication.initAd();
                jumpMain();
            }
        });
    }

    private void initData() {
        StringUtil.KeyWordClick privacyPolicySpan = new StringUtil.KeyWordClick("《隐私政策》", 0xff1b78c8, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/9/10 跳转隐私政策详情界面（仅为示例，请结合自己的业务需求）
                NormalWebActivity.jump(PrivacyActivity.this, "https://www.admobile.top/privacyPolicy.html");
            }
        });
        StringUtil.KeyWordClick privacyPolicySpan2 = new StringUtil.KeyWordClick("《用户协议》", 0xff1b78c8, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/9/10 跳转隐私政策详情界面（仅为示例，请结合自己的业务需求）
                NormalWebActivity.jump(PrivacyActivity.this, "https://doc.admobile.top/ssp/pages/contract/");
            }
        });
        StringUtil.KeyWordClick privacyPolicySpan3 = new StringUtil.KeyWordClick("《第三方SDK使用列表》", 0xff1b78c8, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/9/10 跳转隐私政策详情界面（仅为示例，请结合自己的业务需求）
                NormalWebActivity.jump(PrivacyActivity.this, "https://doc.admobile.top/ssp/pages/sfsdkth/");
            }
        });
        StringUtil.setCustomKeyWordClickSpan(tv_content, tv_content.getText().toString(), privacyPolicySpan, privacyPolicySpan2, privacyPolicySpan3);
    }

    /**
     * 跳转到主界面
     */
    private void jumpMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
