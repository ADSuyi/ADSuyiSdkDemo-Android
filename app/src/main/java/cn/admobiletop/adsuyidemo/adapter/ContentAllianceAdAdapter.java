package cn.admobiletop.adsuyidemo.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.admobiletop.adsuyi.ad.data.ADSuyiContentAllianceAdInfo;
import cn.admobiletop.adsuyi.util.ADSuyiAdUtil;
import cn.admobiletop.adsuyi.util.ADSuyiViewUtil;
import cn.admobiletop.adsuyidemo.R;
import cn.admobiletop.adsuyidemo.entity.ContentAllianceAdSampleData;

/**
 * @author 草莓
 * @description 内容联盟广告Adapter
 * @date 2021/01/06
 */
public class ContentAllianceAdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 普通数据类型
     */
    private static final int ITEM_VIEW_TYPE_NORMAL_DATA = 0;
    /**
     * 内容联盟数据
     */
    private static final int ITEM_VIEW_TYPE_CONTENT_ALLIANCE_AD = 1;
    private final Context context;

    private List<ContentAllianceAdSampleData> dataList = new ArrayList<>();

    public ContentAllianceAdAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemViewType) {
        switch (itemViewType) {
            case ITEM_VIEW_TYPE_CONTENT_ALLIANCE_AD:
                return new ContentAllianceAdViewHolder(viewGroup);
            default:
                return new NormalDataViewHolder(viewGroup);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ContentAllianceAdSampleData sampleData = dataList.get(position);
        if (viewHolder instanceof NormalDataViewHolder) {
            ((NormalDataViewHolder) viewHolder).setData(sampleData.getNormalData());
        } else if (viewHolder instanceof ContentAllianceAdViewHolder) {
            ((ContentAllianceAdViewHolder) viewHolder).setData(sampleData.getContentAllianceAdInfo());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ADSuyiContentAllianceAdInfo nativeAdInfo = dataList.get(position).getContentAllianceAdInfo();
        if (nativeAdInfo == null) {
            return ITEM_VIEW_TYPE_NORMAL_DATA;
        } else {
            return ITEM_VIEW_TYPE_CONTENT_ALLIANCE_AD;
        }
    }

    /**
     * 刷新数据
     */
    public void clearData() {
        dataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    public void addData(List<ContentAllianceAdSampleData> nativeAdSampleDataList) {
        int startPosition = dataList.size();
        dataList.addAll(nativeAdSampleDataList);
        if (startPosition <= 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(startPosition + 1, dataList.size() - startPosition);
        }
    }

    /**
     * 普通数据ViewHolder
     */
    private static class NormalDataViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNormalData;

        NormalDataViewHolder(@NonNull ViewGroup viewGroup) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_native_ad_normal_data, viewGroup, false));
            tvNormalData = itemView.findViewById(R.id.tvNormalData);
        }

        public void setData(String normalData) {
            tvNormalData.setText(normalData);
        }
    }

    /**
     * 信息流模板广告ViewHolder
     */
    private static class ContentAllianceAdViewHolder extends RecyclerView.ViewHolder {

        ContentAllianceAdViewHolder(@NonNull ViewGroup viewGroup) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_native_ad_express_ad, viewGroup, false));
        }

        public void setData(ADSuyiContentAllianceAdInfo adSuyiContentAllianceAdInfo) {
            // 判断广告Info对象是否被释放（调用过ADSuyiNativeAd的release()或ADSuyiNativeAdInfo的release()会释放广告Info对象）
            // 释放后的广告Info对象不能再次使用
            if (!ADSuyiAdUtil.adInfoIsRelease(adSuyiContentAllianceAdInfo)) {
                // 当前是信息流模板广告，getNativeExpressAdView获取的是整个模板广告视图
                View nativeExpressAdView = adSuyiContentAllianceAdInfo.getContentAllianceAdView((ViewGroup) itemView);
                // 将广告视图添加到容器中的便捷方法
                ADSuyiViewUtil.addAdViewToAdContainer((ViewGroup) itemView, nativeExpressAdView);

                // 渲染广告视图, 必须调用, 因为是模板广告, 所以传入ViewGroup和响应点击的控件可能并没有用
                // 务必在最后调用
                adSuyiContentAllianceAdInfo.render((ViewGroup) itemView);
            }
        }
    }

}
