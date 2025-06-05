package cn.admobiletop.adsuyidemo.entity;

import cn.admobiletop.adsuyi.ad.data.ADSuyiDrawVodAdInfo;

/**
 * @author ciba
 * @description Draw视频信息流广告示例数据
 * @date 2020/4/1
 */
public class DrawVodAdSampleData {
    /**
     * 普通数据
     */
    private String normalData;
    /**
     * Draw视频信息流广告对象
     */
    private ADSuyiDrawVodAdInfo drawVodAdInfo;

    public DrawVodAdSampleData(String normalData) {
        this.normalData = normalData;
    }

    public DrawVodAdSampleData(ADSuyiDrawVodAdInfo drawVodAdInfo) {
        this.drawVodAdInfo = drawVodAdInfo;
    }

    public String getNormalData() {
        return normalData;
    }

    public ADSuyiDrawVodAdInfo getDrawVodAdInfo() {
        return drawVodAdInfo;
    }
}
