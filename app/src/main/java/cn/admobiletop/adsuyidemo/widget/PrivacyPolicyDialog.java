package cn.admobiletop.adsuyidemo.widget;

import android.app.Activity;
import android.app.Dialog;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.activity.other.NormalWebActivity;
import cn.admobiletop.adsuyidemo.util.StringUtil;

/**
 * @author parting_soul
 * @date 2018/11/1
 */
public class PrivacyPolicyDialog extends Dialog {

    private OnResultCallback mCallback;


    public PrivacyPolicyDialog(@NonNull Activity activity) {
        super(activity);
        setContentView(R.layout.dialog_privacy_policy);
        TextView tvContent = findViewById(R.id.tvContent);
        StringUtil.KeyWordClick privacyPolicySpan = new StringUtil.KeyWordClick("《隐私政策》", 0xffFEB34D, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/9/10 跳转隐私政策详情界面（仅为示例，请结合自己的业务需求）
                NormalWebActivity.jump(getContext(), "https://www.admobile.top/privacyPolicy.html");
            }
        });
        StringUtil.setCustomKeyWordClickSpan(tvContent, tvContent.getText().toString(), privacyPolicySpan);
        Button btnDisagree = findViewById(R.id.btnDisagree);
        Button btnAgree = findViewById(R.id.btnAgree);

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mCallback != null) {
                    mCallback.onConfirm();
                }
            }
        });

        btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mCallback != null) {
                    mCallback.onCancel();
                }
            }
        });
    }

    public void setOnResultCallback(OnResultCallback callback) {
        this.mCallback = callback;
    }

    public interface OnResultCallback {
        void onConfirm();

        void onCancel();
    }
}
