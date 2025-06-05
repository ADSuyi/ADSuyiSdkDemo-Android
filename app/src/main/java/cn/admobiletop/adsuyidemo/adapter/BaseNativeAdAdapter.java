package cn.admobiletop.adsuyidemo.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo;

public abstract class BaseNativeAdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public abstract void removeData(ADSuyiNativeAdInfo adSuyiNativeAdInfo);

    public abstract void clearData();

    public abstract void addData(List<Object> datas);
}
