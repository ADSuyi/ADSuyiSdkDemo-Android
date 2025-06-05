package cn.admobiletop.adsuyidemo.entity;

/**
 * @author songzi
 * @date 2021/4/29
 */
public class AdSettingData {


    private int type;
    private String title;

    public AdSettingData(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
