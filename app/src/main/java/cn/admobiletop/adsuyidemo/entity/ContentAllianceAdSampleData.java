package cn.admobiletop.adsuyidemo.entity;

import cn.admobiletop.adsuyi.ad.data.ADSuyiContentAllianceAdInfo;

/**
 * @author 草莓
 * @description 信息流广告示例数据
 * @date 2020/4/1
 */
public class ContentAllianceAdSampleData {
    /**
     * 普通数据
     */
    private String normalData;
    /**
     * 信息流广告对象
     */
    private ADSuyiContentAllianceAdInfo contentAllianceAdInfo;

    public ContentAllianceAdSampleData(String normalData) {
        this.normalData = normalData;
    }

    public ContentAllianceAdSampleData(ADSuyiContentAllianceAdInfo contentAllianceAdInfo) {
        this.contentAllianceAdInfo = contentAllianceAdInfo;
    }

    public String getNormalData() {
        return normalData;
    }

    public ADSuyiContentAllianceAdInfo getContentAllianceAdInfo() {
        return contentAllianceAdInfo;
    }
}
