package cn.admobiletop.adsuyidemo.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.admobiletop.adsuyidemo.R;

/**
 * @author ciba
 * @description 信息流广告Fragment示例
 * @date 2020/4/20
 */
public class OtherFragment extends BaseFragment  {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_other_page, null);
        initView(inflate);
        return inflate;
    }

    @Override
    public String getTitle() {
        return "其他页面";
    }

    private void initView(View inflate) {
        Button btnFinish = inflate.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }


}
