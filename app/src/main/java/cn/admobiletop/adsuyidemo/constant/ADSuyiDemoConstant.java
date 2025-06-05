package cn.admobiletop.adsuyidemo.constant;

/**
 * @author ciba
 * @description 描述
 * @date 2020/4/7
 */
public class ADSuyiDemoConstant {
    public static final String APP_ID = "3801556";
    public static final String TAG = "ADSuyiDemoLog";
    /**
     * 开屏广告位ID
     */
    public static final String SPLASH_AD_POS_ID1 = "63a981ded04aa89442";
    public static String SPLASH_AD_POS_ID = SPLASH_AD_POS_ID1;
    /**
     * 开屏广告位仅支持的平台
     */
    public static String SPLASH_AD_ONLY_SUPPORT_PLATFORM = null;
    /**
     * 开屏自定义跳过按钮
     */
    public static boolean SPLASH_AD_CUSTOM_SKIP_VIEW = false;

    /**
     * Banner广告位ID, 640*100
     */
    public final static String BANNER_AD_POS_ID1 = "467b6588a2a28bff5b";
    /**
     * Banner广告位ID, 600*300
     */
    public final static String BANNER_AD_POS_ID2 = "8ca4d2887cdc6c37de";
    /**
     * Banner广告位ID, 600*150
     */
    public final static String BANNER_AD_POS_ID3 = "098c34f804fd2591e9";
    public static String BANNER_AD_POS_ID = BANNER_AD_POS_ID1;

    /**
     * Banner广告自刷新间隔时长，单位秒
     */
    public static int BANNER_AD_AUTO_REFRESH_INTERVAL = 30;
    /**
     * BANNER广告位仅支持的平台
     */
    public static String BANNER_AD_ONLY_SUPPORT_PLATFORM = null;
    /**
     * BANNER广告场景id
     */
    public static String BANNER_AD_SCENE_ID = "";

    /**
     * 信息流广告位ID，自渲染
     */
    public static final String NATIVE_AD_POS_ID1 = "c078638e143ac16f46";
    /**
     * 信息流广告位ID，模板自渲染混合
     */
    public static final String NATIVE_AD_POS_ID2 = "9f273c39a30ab43655";
    /**
     * 信息流广告位ID，只建了优量汇模板2.0
     */
    public static final String NATIVE_AD_POS_ID3 = "6552acfed554d68c78";
    /**
     * 信息流广告位ID，模板
     */
    public static final String NATIVE_AD_POS_ID4 = "fd3b78d0c93e75330a";
    public static String NATIVE_AD_POS_ID = NATIVE_AD_POS_ID1;
    /**
     * 信息流广告一次拉取广告数量
     */
    public static int NATIVE_AD_COUNT = 2;
    /**
     * 信息流广告位仅支持的平台
     */
    public static String NATIVE_AD_ONLY_SUPPORT_PLATFORM = null;
    public static boolean NATIVE_AD_PLAY_WITH_MUTE = true;
    /**
     * 信息流广告位场景id
     */
    public static String NATIVE_AD_SCENE_ID = "";

    /**
     * 激励视频广告位ID，竖版
     */
    public static final String REWARD_VOD_AD_POS_ID1 = "9ec7704ebcad7364ce";
    /**
     * 激励视频广告位ID，横版
     */
    public static final String REWARD_VOD_AD_POS_ID2 = "31167c54f5edc926f4";
    public static String REWARD_VOD_AD_POS_ID = REWARD_VOD_AD_POS_ID1;
    /**
     * 激励视频广告位仅支持的平台
     */
    public static String REWARD_VOD_AD_ONLY_SUPPORT_PLATFORM = null;
    public static boolean REWARD_AD_PLAY_WITH_MUTE = false;

    /**
     * 全屏视频广告位ID
     */
    public static final String FULL_SCREEN_VOD_AD_POS_ID1 = "6620c9299df9013cf5";
    public static String FULL_SCREEN_VOD_AD_POS_ID = FULL_SCREEN_VOD_AD_POS_ID1;
    /**
     * 全屏视频广告位仅支持的平台
     */
    public static String FULL_SCREEN_VOD_AD_ONLY_SUPPORT_PLATFORM = null;
    /**
     * 激励视频广告位场景id
     */
    public static String REWARD_VOD_AD_SCENE_ID = "";

    /**
     * 插屏广告位ID
     */
    public static final String INTERSTITIAL_AD_POS_ID1 = "7b4e3d4697e1c00908";
    public static String INTERSTITIAL_AD_POS_ID = INTERSTITIAL_AD_POS_ID1;
    /**
     * 插屏广告位仅支持的平台
     */
    public static String INTERSTITIAL_AD_ONLY_SUPPORT_PLATFORM = null;
    public static boolean INTERSTITIAL_AD_PLAY_WITH_MUTE = false;
    public static boolean INTERSTITIAL_AD_AUTO_CLOSE = false;

    /**
     * 插屏广告位场景id
     */
    public static String INTERSTITIAL_AD_SCENE_ID = "";

    /**
     * Draw视频信息流广告位ID
     */
    public static final String DRAW_VOD_AD_POS_ID1 = "4d6bee2ec7217adf86";
    public static String DRAW_VOD_AD_POS_ID = DRAW_VOD_AD_POS_ID1;
    /**
     * Draw视频信息流广告一次拉取广告数量
     */
    public static int DRAW_VOD_AD_COUNT = 1;
    /**
     * Draw视频信息流广告位仅支持的平台
     */
    public static String DRAW_VOD_AD_ONLY_SUPPORT_PLATFORM = null;

    /**
     * Dl激励广告位ID
     */
    public static String ADMOBILE_DL_AD_POS_ID = "ac966d36094a167687";

    /**
     * 内容联盟广告位场景id
     */
    public static String CONTENT_ALLIANCE_AD_SCENE_ID = "";
    /**
     * 内容联盟广告位ID
     */
    public static final String CONTENT_ALLIANCE_AD_POS_ID1 = "724469db49ff19761e";
    public static String CONTENT_ALLIANCE_AD_POS_ID = CONTENT_ALLIANCE_AD_POS_ID1;


    /**
     * 开屏广告全屏并去除状态了
     */
    public static int IMMERSIVE_AND_FULLSCREEN = 0;
    /**
     * 开屏广告全屏不去除状态栏
     */
    public static int FULL_SCREEN = 1;
    /**
     * 开屏广告半屏
     */
    public static int HALF_SCREEN = 2;
    /**
     * 加载并展示
     */
    public static int LOAD_AND_SHOW = 0;
    /**
     * 仅加载
     */
    public static int LOAD_ONLY = 1;

}
