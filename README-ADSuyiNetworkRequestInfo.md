#### 开屏打底对象 API

/**
 * 加载开屏保底广告，可选的
 * 功能说明：App在首次启动时，需要先请求获取广告位配置文件后，然后再去请求开屏广告，也就是首次加载开屏广告时需要两次串行网络请求，因此很容易因超时导致开屏广告展示失败。
 * 解决方案：为避免开屏超时问题，开放此设置给开发者，开发者可以根据实际需求选择一家广告平台，通过API接口将必需参数传递给Suyi聚合SDK。（该设置只能指定一家广告平台，并且首次启动时只会请求该平台的广告，但App首次开屏广告将不受ADmobile后台控制，包括下载提示，广告位关闭。）
 * 该设置仅会在首次加载开屏广告时，SDK会使用开发者传入的参数进行广告请求，同时获取后台配置文件的广告配置信息缓存到本地（首次请求广告平台广告和获取配置信息时并发进行），后续的开屏广告将按照缓存缓存的后台广告位配置顺序进行开屏广告请求。
 * 支持穿山甲、优量汇、快手、百度
 */

**GdtSplashAdRequestInfo**

cn.admobiletop.adsuyi.ad.api.GdtSplashAdRequestInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| GdtSplashAdRequestInfo(String appId, String networkSlotId, String networkAdPosListID, @DownloadTipParam int downloadTip) | 优量汇打底构造方法。参数说明：appId（渠道AppId）、networkSlotId（开屏广告源ID）、networkAdPosListID（Suyi开屏广告源AdPosList ID）、downloadTip（下载提示模式））。|

**TTSplashAdRequestInfo**

cn.admobiletop.adsuyi.ad.api.TTSplashAdRequestInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| TTSplashAdRequestInfo(String appId, String networkSlotId, String networkAdPosListID, @DownloadTipParam int downloadTip, @RenderTypeParam int renderType) | 优量汇打底构造方法。参数说明：appId（渠道AppId）、networkSlotId（开屏广告源ID）、networkAdPosListID（Suyi开屏广告源AdPosList ID）、downloadTip（下载提示模式））renderType（渲染方式）。|

**BaiduSplashAdRequestInfo**

cn.admobiletop.adsuyi.ad.api.BaiduSplashAdRequestInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| BaiduSplashAdRequestInfo(String appId, String networkSlotId, String networkAdPosListID, @DownloadTipParam int downloadTip) | 优量汇打底构造方法。参数说明：appId（渠道AppId）、networkSlotId（开屏广告源ID）、networkAdPosListID（Suyi开屏广告源AdPosList ID）、downloadTip（下载提示模式））。|

**KsSplashAdRequestInfo**

cn.admobiletop.adsuyi.ad.api.KsSplashAdRequestInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| KsSplashAdRequestInfo(String appId, String networkSlotId, String networkAdPosListID, @DownloadTipParam int downloadTip) | 优量汇打底构造方法。参数说明：appId（渠道AppId）、networkSlotId（开屏广告源ID）、networkAdPosListID（Suyi开屏广告源AdPosList ID）、downloadTip（下载提示模式））。|