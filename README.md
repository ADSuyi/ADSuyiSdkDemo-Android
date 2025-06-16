# Suyi聚合广告SDK ——接入文档 V4.0.0.05062

# Suyi聚合广告SDK  介绍


```
SDK名称: Suyi聚合广告SDK
开发者: 杭州艾狄墨搏信息服务有限公司
更新日期: 2025-05-28
功能介绍: Suyi聚合广告SDK 集成了多种广告类型和主流广告平台，提供广告数据统计功能，帮助开发者轻松实现应用内广告的接入和管理，提升应用盈利和用户体验。
```


[SDK 下载地址](https://doc.admobile.top/AndroidSDK/SuyiSDK.zip)

[查看更新日志](https://doc.admobile.top/ssp/4changelog/1-androidchangelog.html)

[查看接入文档](https://doc.admobile.top/ssp/pages/suyisdkand/)

[隐私政策](https://www.admobile.top/privacyPolicy.html)

[合规指引](https://doc.admobile.top/ssp/1Start/7_compliance_guide.html)

[用户协议](https://doc.admobile.top/ssp/pages/contract/)

## 1. 概述
### 1.1 概述

尊敬的开发者朋友，欢迎您使用Suyi聚合广告SDK 。通过本文档，您可以快速完成多平台广告SDK的集成。

**注意：本SDK仅支持中国大陆地区**；如需发布到Google Play，请勿引入本SDK及相关依赖文件。



### 1.2 Suyi聚合广告SDK  组成结构

Suyi聚合广告SDK 主要由**ADSuyi核心SDK（简称ADSuyiSdk）**和一个或多个**三方平台适配器SDK（简称AdapterSdk）**组成，开发者可以自由的在后台配置中选择需要接入的三方广告平台，然后导入所对应的AdapterSdk，其中**艾狄墨搏平台的AdapterSdk是必须导入的**。



### <a name="platform_name"> 1.3 三方广告平台名称概述 </a>

| Name    | 平台名称     | 平台别称   | 
| ------- | ------------ | ---------- |
| tianmu  | 天目         | 天目       |
| gdt     | 优量汇       | 广点通     |
| toutiao | 穿山甲        | 头条     |
| baidu   | 百度         | 百青藤     |
| ksad    | 快手         | 快手       |
| hwpps   | 华为广告 | 华为广告联盟 |
| jadyun  | 京东广告         | 京媒       | 
| iqy     | 爱奇艺      | 爱奇艺    |
| gromore | gromore      | gromore    |

### 1.4 ADSuyi必添包容量

| Name         | 大小   | 版本号         |
| ------------ | ----  | ------------- |
| ADSuyi基础包  | 0.43M | V4.0.0.05062  |
| OAID         | 1.10M | V1.0.25       |
| OAID适配器    | 0.01M | V1.0.25.12122 |

### 1.5 三方广告平台适配器+三方广告sdk总容量

| Name      | 容量   | 版本号              | 备注 |
| --------- | ----- | ------------------ |------ |
| tianmu    | 2.14M | v3.0.0.05151       | |
| gdt       | 2.01M | v4.640.1510.05133  | |
| toutiao   | 8.22M | v6.8.1.7.05132     | |
| baidu     | 1.98M | v9.391.05141       | 仅支持AndroidX |
| ksad      | 5.08M | v3.3.76.5.05141      | |
| hwpps     | 1.01M | v13.4.76.300.01031 | |
| jadyun    | 1.41M | v2.6.28.05141       | 仅支持AndroidX |
| iqy       | 1.00M | v1.15.100.53.05141      | |
| gromore   | —     | v6.6.0.7.01084     | |


## 2. 支持的广告类型

<table>
  <tr>
    <th style="width:150px">类型</th>
    <th>简介</th>
    <th>适用场景</th>
  </tr>
  <tr>
    <td><a href="#ad_splash">开屏广告</a></td>
    <td>开屏广告以APP启动作为曝光时机的模板广告，需要将开屏广告视图添加到承载的广告容器中，提供5s可感知广告展示</td>
    <td>APP启动界面常会使用开屏广告</td>
  </tr>
  <tr>
    <td><a href="#ad_banner">横幅广告</a></td>
    <td>横幅广告是横向贯穿整个可视页面的模板广告，需要将横幅广告视图添加到承载的广告容器中</td>
    <td>任意界面的固定位置，不建议放在RecyclerView、List这种滚动布局中当item</td>
  </tr>
  <tr>
    <td><a href="#ad_native">信息流广告</a></td>
    <td>信息流广告集合原生自渲染广告和模板广告两种，可以通过后台配置和SDK相关方法判断进行不同的渲染，以满足不同的样式需求</td>
    <td>信息流列表，轮播控件，固定位置都是较为适合</td>
  </tr>
  <tr>
    <td><a href="#ad_reward_vod">激励视频广告</a></td>
    <td>将短视频融入到APP场景当中，用户观看短视频广告后可以给予一些应用内奖励</td>
    <td>常出现在游戏的复活、任务等位置，或者网服类APP的一些增值服务场景</td>
  </tr>
  <tr>
    <td><a href="#ad_interstitial">插屏广告</a></td>
    <td>插屏广告是移动广告的一种常见形式，在应用流程中弹出，当应用展示插屏广告时，用户可以选择点击广告，访问其目标网址，也可以将其关闭并返回应用</td>
    <td>在应用执行流程的自然停顿点，适合投放这类广告</td>
  </tr>
</table>


## 3. Demo及SDK下载链接

> [ADSuyiSdkDemo-演示APK下载地址](https://doc.admobile.top/file/ADSuyiSdkDemo-V202412162.apk)
>
> [SDK版本更新日志](https://doc.admobile.top/ssp/4changelog/1-androidchangelog.html)



## 4. SDK版本说明

### 4.1 ADSuyiSdk版本号说明

版本号格式为3.0.0.xxxxn，其中xxxx代表日期，最后一位n为版本扩展号；



### 4.2 AdapterSdk版本号说明

版本号格式为 y.y.xxxxn，y.y代表三方SDK版本号（可能两位、也可能三位、四位...），其中xxxx代表日期，最后一位n为版本扩展号；



### 4.3 AdapterSdk和ADSuyiSdk版本对应说明

AdapterSdk会指定支持的ADSuyiSdk版本，**如果导入的AdapterSdk和ADSuyiSdk版本不对应会抛出异常提醒开发者使用相对应的版本**；



## 5. SDK接入流程

### 5.1 添加SDK到工程中

接入环境：**Android Studio**。



#### 5.1.1 添加仓库地址

首先需要在项目的build.gradle文件中引入如下配置：

```java
allprojects {
    repositories {
        ...
        google()
        jcenter()
        mavenCentral()
        // 添加ADSuyi相关仓库依赖
        maven { url "https://maven.admobile.top/repository/maven-releases/" }
    }
}
```



#### 5.1.2 添加ADSuyiSdk和需要的AdapterSdk

将广告所需要的依赖集成进去，AdapterSdk可根据接入平台情况进行选择接入。

```java
dependencies {
    // androidx支持库，如果是support请使用对应的支持库
    implementation 'androidx.appcompat:appcompat:1.0.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.material:material:1.0.0'

    // ADSuyiSdk核心库必须导入
    implementation 'cn.admobiletop.adsuyi.ad:core:4.0.0.05062'

    // OAID1.0.25版本
    implementation(name: 'oaid_sdk_1.0.25', ext: 'aar')
    // OAID1.0.25版本适配器，对接高版本请参考文档5.2
    implementation 'cn.admobiletop.adsuyi.ad:oaid:1.0.25.12122'

    // 天目适配器，必选
    implementation 'cn.admobiletop.adsuyi.ad.adapter:tianmu:3.0.0.05151'

    // 优量汇（广点通）适配器，可选
    implementation 'cn.admobiletop.adsuyi.ad.adapter:gdt:4.640.1510.05133'

    // 穿山甲（头条）适配器，可选
    implementation 'cn.admobiletop.adsuyi.ad.adapter:toutiao:6.8.1.7.05132'

    // 百度适配器，可选。仅支持AndroidX版本
    implementation 'cn.admobiletop.adsuyi.ad.adapter:baidu-androidx:9.391.05141'

    // 快手适配器，可选
    implementation 'cn.admobiletop.adsuyi.ad.adapter:ksadbase:3.3.76.5.05141'

    // 华为适配器，可选
    implementation 'cn.admobiletop.adsuyi.ad.adapter:hwpps:13.4.76.300.01031'

    // 京媒适配器，可选。仅支持AndroidX版本
    implementation 'cn.admobiletop.adsuyi.ad.adapter:jadyun-androidx:2.6.28.05141'

    // 爱奇艺适配器，可选
    implementation 'cn.admobiletop.adsuyi.ad.adapter:iqy:1.15.100.53.05141'

    // gromoreAdapterSdk，可选的。如使用gromore的其他渠道，请联系开发者。
    // gromore已与穿山甲合并，此处无需重复导入穿山甲。
    implementation 'cn.admobiletop.adsuyi.ad.adapter:gromore-without:6.6.0.7.01084'
    implementation "com.pangle.cn:mediation-gdt-adapter:4.575.1445.1" //广点通 adapter
    // 有gromore其他渠道需求，请联系开发者。

}
```



#### 5.1.3 注意事项

* 支持主流架构，x86架构暂不支持

  ```java
  ndk {
  	// 设置支持的SO库架构，暂不支持x86
  	abiFilters 'armeabi-v7a', 'arm64-v8a'
  }
  ```

* **AdapterSdk默认已经集成了三方的广告SDK**，如果因为项目中也使用了相同的三方广告SDK而发生冲突，可通过以下方法尝试避免或解决；

1. 移除己方使用的三方广告SDK和相关配置；

> **由于穿山甲(头条)渠道支持了Android R，引入了Android R的 \<queries\> 标签,需要对gradle版本进行限制，限制范围为：3.3.3、 3.4.3、 3.5.4、3.6.4、4.0.1 ，开发者根据自身情况酌情升级**

>  **如对接华为广告联盟，激励视频要提前预加载，并且播放完成后需要预加载下一个激励视频，华为渠道点击事件无法统计；横幅广告使用场景是程序页面的顶部或者底部。**

2. 激励、插屏等广告对象一次成功拉取的广告数据只允许展示一次，还需展示请再次加载广告。

3. 关于项目使用autosize后出现广告样式出现异常问题处理方案，请参考master-screen-adapter分支中的BannerActivity，并将适配单位改为pt。

### 5.2 OAID支持

#### 5.2.1 适配OAID1.0.25

低版本OAID对接参考案例，低版本无需向[MSA](https://www.msa-alliance.cn/col.jsp?id=120)申请密钥，由于版本较低，新机型可能无法获取。

```java
// OAID1.0.25版本
implementation(name: 'oaid_sdk_1.0.25', ext: 'aar')
// OAID1.0.25版本适配器，对接高版本请参考文档5.2
implementation 'cn.admobiletop.adsuyi.ad:oaid:1.0.25.12122'
```

#### 5.2.2 使用高版本OAID

[MSA](https://www.msa-alliance.cn/col.jsp?id=120)官方地址

[高版本OAID对接参考案例](https://gitee.com/admobile/oaid-docking-case.git)

### 5.3 权限申请

使用SDK时可能需要以下权限，为了保证使用广告的正确，请在6.0及以上的手机中使用SDK前及时申请。

```java
<!-- 广告必须的权限，允许网络访问 -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```



### 5.4 兼容配置

#### 5.4.1 网络配置

需要在 AndroidManifest.xml 添加依赖声明**uses-library android:name="org.apache.http.legacy" android:required="false"**， 且 application标签中添加 **android:usesCleartextTraffic="true"**，适配网络http请求，否则 SDK可能无法正常工作，接入代码示例如下：

```java
<application
    android:name=".MyApplication"
    ...
    android:usesCleartextTraffic="true">

    <uses-library
        android:name="org.apache.http.legacy"
        android:required="false" />
    ...
</application>
```



#### 5.4.2 混淆配置

如果打包时开启了混淆配置，请按需添加以下混淆内容，并保证广告资源文件不被混淆

```java
-ignorewarnings
# v4、v7（如果是Support支持库需添加）
-keep class android.support.v4.**{public *;}
-keep class android.support.v7.**{public *;}

# AndroidX (如果是AndroidX支持库需添加)
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep class * extends androidx.**

# 资源文件混淆配置
-keep class **.R$* { *; }
-keep public class **.R$*{
   public static final int *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}

# ADSuyiSdk混淆
-dontwarn cn.admobiletop.adsuyi.**
-keep class adsuyi.com.** { *; }
-keep interface adsuyi.com.** { *; }
-dontwarn org.apache.commons.**
-keep class cn.admobiletop.adsuyi.**{public *;}
-keep class cn.admobiletop.materialutil.**{public *;}
-keep class com.android.**{*;}
-keep class com.ciba.**{ *; }
-keep class org.apache.**{*;}

# okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

# OAID混淆
-keep class com.bun.miitmdid.core.** {*;}
-keep class XI.CA.XI.**{*;}
-keep class XI.K0.XI.**{*;}
-keep class XI.XI.K0.**{*;}
-keep class XI.vs.K0.**{*;}
-keep class XI.xo.XI.XI.**{*;}
-keep class com.asus.msa.SupplementaryDID.**{*;}
-keep class com.asus.msa.sdid.**{*;}
-keep class com.bun.lib.**{*;}
-keep class com.bun.miitmdid.**{*;}
-keep class com.huawei.hms.ads.identifier.**{*;}
-keep class com.samsung.android.deviceidservice.**{*;}
-keep class com.zui.opendeviceidlibrary.**{*;}
-keep class org.json.**{*;}
-keep public class com.netease.nis.sdkwrapper.Utils {public <methods>;}

# 广点通（优量汇）广告平台混淆
-keep class com.qq.e.** {public protected *;}
-keep class MTT.ThirdAppInfoNew {*;}
-keep class com.tencent.** {*;}
-keep class com.tencent.smtt.** { *; }
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**

# 百度广告SDK混淆
-keepclassmembers class * extends android.app.Activity { public void *(android.view.View);}
-keepclassmembers enum * {
 public static **[] values();
 public static ** valueOf(java.lang.String);
}
-keep class com.baidu.mobads.** { *; }
-keep class com.baidu.mobad.** { *; }

# 穿山甲（头条）广告平台混淆
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
-keep class com.pgl.sys.ces.* {*;}
-keep class com.bytedance.embed_dr.** {*;}
-keep class com.bytedance.embedapplog.** {*;}
-keep class com.bytedance.frameworks.** { *; }
-keep class ms.bd.c.Pgl.**{*;}
-keep class com.bytedance.mobsec.metasec.ml.**{*;}
-keep class com.ss.android.**{*;}
-keep class com.bykv.vk.** {*;}

# 快手广告平台混淆
-keep class org.chromium.** { *; }
-keep class aegon.chrome.** { *; }
-keep class com.kwai.**{ *; }
-keep class com.kwad.**{ *; }
-dontwarn com.kwai.**
-dontwarn com.kwad.**
-dontwarn com.ksad.**
-dontwarn aegon.chrome.**
-keep class com.kwad.sdk.** { *;}
-keep class com.ksad.download.** { *;}
-keep class com.kwai.filedownloader.** { *;}
-keepclasseswithmembernames class * { native <methods>;}

# 快手广告平台混淆
-keep class org.chromium.** { *; }
-keep class aegon.chrome.** { *; }
-keep class com.kwai.**{ *; }
-keep class com.kwad.**{ *; }
-dontwarn com.kwai.**
-dontwarn com.kwad.**
-dontwarn com.ksad.**
-dontwarn aegon.chrome.**

# 华为联盟
-keep class com.huawei.openalliance.ad.** { *; }
-dontwarn com.huawei.openalliance.ad.activity.PPSActivity
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** { **[] $VALUES; public *; }
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder

# gromore
# 请参考gromore-proguard-rules.pro文件

# 天目
-keep class com.tianmu.**{ *; }
-keep class tianmu.com.** { *; }
-keep interface tianmu.com.** { *; }

# 京媒混淆
-keep class com.jd.ad.sdk.** { *; }

# ADSyid混淆
-keep class adsuyi.com.** { *; }
-keep interface adsuyi.com.** { *; }

# 爱奇艺混淆
-keep class com.mcto.sspsdk.** { *; }


```

### 5.5 隐私信息控制开关

**为了保证您的App顺利通过检测，结合当前监管关注重点，请务必将ADSuyiSdk的初始化放在用户同意隐私政策之后。**

**如合规有更高要求，可以使用以下方法进行控制，但会严重降低广告收益，可根据实际需求进行设置，或联系我发运营人员获取建议。**
同时ADSuyiSDK初始化时开放以下接口，确保imei等设备标识不被读取（目前部分三方广告平台支持）：
```java
//【慎改】是否同意隐私政策，将禁用一切设备信息读起严重影响收益
.agreePrivacyStrategy(false)
// 是否可获取定位数据
.isCanUseLocation(false)
// 是否可获取设备信息
.isCanUsePhoneState(false)
// 是否可读取设备安装列表
.isCanReadInstallList(false)
// 是否可读取设备外部读写权限
.isCanUseReadWriteExternal(false)
// 是否可读取WIFI信息
.isCanUseWifiState(false)
// 是否可使用OAID
.isCanUseOaid(false)
// 是否允许使用传感器
.isCanUseSensor(false)
```
另外还可从根源上解决设备标识被读取等问题，可对配置清单中的权限增加tools:node="remove"配置；
如下：
```java
<!-- 影响广告填充，强烈建议的权限 -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" tools:node="remove" />
<!-- 为了提高广告收益，建议设置的权限 -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" tools:node="remove" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" tools:node="remove" />
```

以上操作会对广告填充造成影响，请斟酌使用。


### 5.6 个性化开关

ADSuyi的个性化开关可统一控制第三方广告SDK的个性化开关接口，目前支持天目、优量汇、穿山甲、百度、快手、汇量、米盟、华为；

```java
// 设置个性化开关。true为开启、false为关闭，请在初始化ADSuyi后进行控制。
ADSuyiSdk.setPersonalizedAdEnabled(boolean personalized);
```

```java
// 获取当前个性化开关设置状态，true为开启、false为关闭。
ADSuyiSdk.getInstance().getPersonalizedAdEnabled()
```

### <a name="custom_controller"> 5.7 向SDK传入设备标识 </a>

统一由可选参数 ： CustomDeviceInfoController 进行设置

- 新增可选参数设置

```java
ADSuyiSdk.getInstance().init(
        this,
        new ADSuyiInitConfig.Builder()
                ...
                // 是否可读取wifi状态
                .isCanUseWifiState(false)
                // 是否可获取定位数据
                .isCanUseLocation(false)
                // 是否可获取设备信息
                .isCanUsePhoneState(false)
                .setCustomDeviceInfoController(new CustomDeviceInfoController() {
                    /**
                     * 当isCanUsePhoneState=false时，可传入imei信息，使用您传入的imei信息
                     * @return imei信息
                     */
                    @Override
                    public String getImei() {
                        return super.getImei();
                    }

                    /**
                     * 当isCanUsePhoneState=false时，可传入AndroidId信息，使用您传入的AndroidId信息
                     * @return androidid信息
                     */
                    @Override
                    public String getAndroidId() {
                        return super.getAndroidId();
                    }

                    /**
                     * 当isCanUseLocation=false时，可传入地理位置信息，使用您传入的地理位置信息
                     * @return 地理位置
                     */
                    @Override
                    public Location getLocation() {
                        return super.getLocation();
                    }

                    /**
                     * 当isCanUseWifiState=false时，可传入Mac地址信息，使用您传入的Mac地址信息
                     * @return Mac地址
                     */
                    @Override
                    public String getMacAddress() {
                        return super.getMacAddress();
                    }

                    /**
                     * 开发者可以传入oaid ，若不传或为空值，则不使用oaid信息
                     * @return oaid
                     */
                    @Override
                    public String getOaid() {
                        return super.getOaid();
                    }

                    /**
                     * 开发者可以传入vaid ，若不传或为空值，则不使用vaid信息
                     * @return vaid
                     */
                    @Override
                    public String getVaid() {
                        return super.getVaid();
                    }
                })
                ...
                .build()
);
```

## 6. 示例代码

具体的广告SDK接口和接口说明请参考Demo


### 6.1 SDK初始化

在隐私同意后进行SDK的初始化(详情请参考Demo SplashAdActivity.java类)

#### 6.1.1 初始化主要 API

**ADSuyiSdk**

cn.admobiletop.adsuyi.ADSuyiSdk

| 方法名         | 介绍 |
| ------------ | ---- |
| init(Context context, ADSuyiInitConfig config) | 构造方法。参数说明：context（初始化SDK请传入Application的上下文对象）、config（初始化配置信息）。|
| init(Context context, ADSuyiInitConfig config, ADSuyiInitListener listener) | 构造方法。参数说明：context（初始化SDK请传入Application的上下文对象）、config（初始化配置信息）、listener（初始化状态监听）。|
| setPersonalizedAdEnabled(boolean enablePersonalized) | 设置个性化推荐开关。参数说明：enablePersonalized（true：开启，false：关闭， 默认：true）。|
| getPersonalizedAdEnabled() | 获取个性化推荐状态，true：开启，false：关闭。 |

**ADSuyiInitConfig**

cn.admobiletop.adsuyi.config.ADSuyiInitConfig

| 方法名         | 介绍 |
| ------------ | ---- |
| appId(String appId) | 设置AppId，必填参数。|
| debug(boolean debug) | 设置是否是Debug模式。参数说明：debug（true：开启，false：关闭， 默认：true）开发阶段以及提交测试阶段可设置为true，方便异常排查。|
| agreePrivacyStrategy(boolean agreePrivacyStrategy) | 是否同意隐私政策。参数说明：agreePrivacyStrategy（true：开启，false：关闭， 默认：true）。|
| isCanUseSensor(boolean isCanUseSensor) | 设置SDK是否可以使用传感器信息。参数说明：isCanUseSensor（true：开启，false：关闭， 默认：false，建议媒体升级后调用该方法开启。目前仅支持控制优量汇、快手、天目，其中优量汇和天目可控制所有广告类型，快手仅支持控制开屏，其他渠道需要到对应后台控制或提交工单关闭，关闭后将影响广告点击率）。|
| isCanUseLocation(boolean isCanUseLocation) | 设置SDK是否可以使用定位信息。参数说明：isCanUseLocation（true：开启，false：关闭， 默认：true）。|
| isCanUsePhoneState(boolean isCanUsePhoneState) | 设置SDK是否可以使用IMEI等设备信息。参数说明：isCanUsePhoneState（true：开启，false：关闭， 默认：true）。|
| isCanUseWifiState(boolean isCanUseWifiState) | 设置SDK是否可以使用WIFI信息。参数说明：isCanUseWifiState（true：开启，false：关闭， 默认：true）。|
| isCanUseReadWriteExternal(boolean isCanUseReadWriteExternal) | 是否允许读写权限。参数说明：isCanUseReadWriteExternal（true：开启，false：关闭， 默认：true）。|
| filterThirdQuestion(boolean filterThirdQuestion) | 设置是否过滤第三方平台的问题广告（例如: 已知某个广告平台在某些机型的横幅广告可能存在问题，如果开启过滤，则在该机型将不再去获取该平台的横幅广告）。参数说明：filterThirdQuestion（true：开启，false：关闭， 默认：true）。|
| setCustomDeviceInfoController(CustomDeviceInfoController controller) | 自定义设备信息。可选参数。<a href="#custom_controller"> 请参考5.7 向SDK传入设备标识 </a>|
| setWXAppId(String wxAppId) | 设置微信AppId。可选参数。参数说明：wxAppId（微信AppId）|
| lazyInitThirdAd(boolean lazyInitThirdAd) | 延迟初始化三方广告，开启后将在请求广告时进行三方广告初始化。参数说明：lazyInitThirdAd（true：开启，false：关闭， 默认：false）。|

**CustomDeviceInfoController**

cn.admobiletop.adsuyi.config.CustomDeviceInfoController

| 方法名         | 介绍 |
| ------------ | ---- |
| getImei() | 当isCanUsePhoneState=false时，可传入imei信息，使用您传入的imei信息。|
| getAndroidId() | 当isCanUsePhoneState=false时，可传入AndroidId信息，使用您传入的AndroidId信息。|
| getLocation() | 当isCanUseLocation=false时，可传入地理位置信息，使用您传入的地理位置信息。|
| getMacAddress() | 当isCanUseWifiState=false时，可传入Mac地址信息，使用您传入的Mac地址信息。|
| getOaid() | 开发者可以传入oaid ，若不传或为空值，则不使用oaid信息。|
| getVaid() | 开发者可以传入vaid ，若不传或为空值，则不使用vaid信息。|

#### 6.1.2 初始化接入示例

```java
// 初始化ADSuyi广告SDK
ADSuyiSdk.getInstance().init(Context context, new ADSuyiInitConfig.Builder()
    // 设置APPID，必须的
    .appId(String appId)
    // 是否开启Debug，开启会有详细的日志信息打印，如果用上ADSuyiToastUtil工具还会弹出toast提示。
    // 注意上线后请置为false
    .debug(boolean debug)
    ...
    .build());
```

<p style="color:red;">PS ：AppId通过后台配置生成，初始化必须在主线程中进行，SDK暂不支持多进程。 </p>



### <a name="ad_splash">6.2 开屏广告示例</a>

开屏广告建议在闪屏页进行展示，开屏广告的宽度和高度取决于容器的宽高，都是会撑满广告容器；**开屏广告的高度必须大于等于屏幕高度（手机屏幕完整高度，包括状态栏之类）的75%**，否则可能会影响收益计费（优量汇的开屏甚至会影响跳过按钮的回调）。

#### 6.2.1 开屏广告主要 API

**ADSuyiSplashAd**

cn.admobiletop.adsuyi.ad.ADSuyiSplashAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiSplashAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiSplashAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| ADSuyiSplashAd(Activity activity, ViewGroup container) | 构造方法。参数说明：activity（当前页面activity对象）、container（展示广告视图的父容器）。|
| ADSuyiSplashAd(Fragment fragment, ViewGroup container) | 构造方法。参数说明：fragment（当前页面fragment对象）、container（展示广告视图的父容器）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setImmersive(boolean isImmersive) | 设置沉浸效果。参数说明：isImmersive（true：沉浸，false：不沉浸， 目前仅影响默认跳过按钮位置）。|
| setSkipView(View skipView) | 设置跳过按钮。参数说明：skipView（自定义跳过按钮）。|
| setSkipView(View skipView, long countDownTime) | 设置跳过按钮。参数说明：skipView（自定义跳过按钮）、countDownTime（[3000ms~5000ms]，默认5000ms）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
| setListener(ADSuyiSplashAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| loadOnly(String posId) | 仅请求广告不展示。参数说明：posId（广告位ID）。|
| loadAd(String posId, ADSuyiNetworkRequestInfo requestInfo) | 请求打底广告并展示，目前支持优量汇、穿山甲、百度、快手。参数说明：posId（广告位ID）、requestInfo（打底广告对象，接口地址[Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/README-ADSuyiNetworkRequestInfo.md)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/README-ADSuyiNetworkRequestInfo.md)）。|
| showSplash() | 展示广告。使用loadOnly方法去加载广告时，可在onAdReceive回调后去展示广告。|
| showSplash(ViewGroup container) | 展示广告。参数说明：container（广告展示父容器）。通过ADSuyiSplashAd(Activity activity)、ADSuyiSplashAd(Fragment fragment)构造广告对象，并使用loadOnly方法加载广告时，可在onAdReceive回调后调用该方法去展示广告。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| adSize(ADSuyiAdSize adSize) | 设置开屏视图宽高。参数说明：adSize（设置整个广告视图预期宽高(目前仅穿山甲（头条）平台需要，没有接入穿山甲（头条）可不设置)，单位为px，如果不设置穿山甲（头条）开屏广告视图将会以满屏幕进行填充）。|
| setAdShakeDisable(boolean adShakeDisable) | 设置传感器禁用。参数说明：adShakeDisable（true：禁用、false：可用，默认：false）。|


**ADSuyiAdSize**

cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiAdSize(int width, int height) | 构造方法。参数说明：<br>width（容器宽度，单位：px）请传入实际宽度、<br>height（容器高度，单位：px）请传入实际高度。|


**ADSuyiSplashAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiSplashAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onADTick(long millisUntilFinished) | 广告倒计时剩余时长回调。参数说明：millisUntilFinished（剩余时间，单位：秒））。|
| onAdReceive(ADSuyiAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiAdInfo adInfo) | 广告展示回调。|
| onAdClick(ADSuyiAdInfo adInfo) | 广告点击回调。|
| onAdSkip(ADSuyiAdInfo adInfo) | 广告跳过回调，用户点击跳过按钮时触发。|
| onAdClose(ADSuyiAdInfo adInfo) | 广告关闭回调，用户点击跳过按钮、触发落地页后返回开屏页、倒计时结束，则触发。|
| onReward(ADSuyiAdInfo adInfo) | 广告奖励回调，目前仅优量汇渠道有效。|
| onAdFailed(ADSuyiError error) | 广告失败回调。参数说明：error（广告错误信息）。|

**ADSuyiAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| getPlatform() | 获取三方广告平台名称，返回String类型。|
| getECPM() | 获取ECPM，返回Double类型（单位：元）。|
| getEcpmPrecision() | ECPM类型，返回String类型（accurate：精准、platform_assignment：平台指定、estimate：估算）。|


#### 6.2.2 开屏广告加载并展示

```java
// 创建开屏广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器
ADSuyiSplashAd splashAd = new ADSuyiSplashAd(Activity activity, ViewGroup container);

// 创建额外参数实例
ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
        .adSize(new ADSuyiAdSize(int width, int height))
        .build();
// 如果开屏容器不是全屏可以设置额外参数
splashAd.setLocalExtraParams(extraParams);

// 设置开屏广告监听
splashAd.setListener(new ADSuyiSplashAdListener() {
  	@Override
    public void onADTick(long countdownSeconds) {
        // 如果没有设置自定义跳过按钮不会回调该方法（单位为秒）
    }
    @Override
    public void onReward(ADSuyiAdInfo adInfo) {
        // 广告奖励回调，目前仅仅优量汇渠道会被使用
    }
    @Override
    public void onAdSkip(ADSuyiAdInfo adInfo) {
        // 广告跳过回调，不一定准确，埋点数据仅供参考...
    }
    @Override
    public void onAdReceive(ADSuyiAdInfo adInfo) {
        // 广告获取成功回调...
    }

    @Override
    public void onAdExpose(ADSuyiAdInfo adInfo) {
        // 广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败
    }

    @Override
    public void onAdClick(ADSuyiAdInfo adInfo) {
        // 广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败
    }

    @Override
    public void onAdClose(ADSuyiAdInfo adInfo) {
        // 广告关闭回调，需要在此进行页面跳转
    }

    @Override
    public void onAdFailed(ADSuyiError error) {
        // 广告失败回调，需要在此进行页面跳转
    }
});

// 加载开屏广告
splashAd.loadAd(String posId);
```

> 开屏广告示例 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/splash/SplashAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/splash/SplashAdActivity.java)
>

#### 6.2.3 开屏广告加载与展示分离

##### 仅加载开屏广告
```java
// 创建广告对象的逻辑与6.2.2的案例相同，不同点在loadAd
...
// 仅加载开屏广告
splashAd.loadOnly(String posId);
```

##### 展示开屏广告
```java
// 需要开发者在onAdReceive回调之后再展示开屏广告
...
public void onAdReceive(ADSuyiAdInfo adInfo) {
    // 广告获取成功回调...
    // 展示开屏广告
    splashAd.showSplash();
}
...
```

> 开屏广告加载与展示分离示例 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/splash/SplashAdLoadShowSeparationActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/splash/SplashAdLoadShowSeparationActivity.java)
>


### <a name="ad_banner">6.3 横幅广告示例</a>

横幅广告建议放置在 **固定位置**，而非ListView、RecyclerView、ViewPager等控件中充当Item，横幅广告支持多种尺寸比例，可在后台创建广告位时配置，横幅广告的宽度将会撑满容器，高度自适应，建议横幅广告容器高度也为自适应。

#### 6.3.1 横幅广告主要 API

**ADSuyiBannerAd**

cn.admobiletop.adsuyi.ad.ADSuyiBannerAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiBannerAd(Activity activity, ViewGroup container) | 构造方法。参数说明：activity（当前页面activity对象）、container（展示广告视图的父容器）。|
| ADSuyiBannerAd(Fragment fragment, ViewGroup container) | 构造方法。参数说明：fragment（当前页面fragment对象）、container（展示广告视图的父容器）。|
| ~~setAutoRefreshInterval(long seconds)~~ | 设置自刷新时间间隔。参数说明：seconds（0为不自动刷新（部分平台无效，如百度），其他取值范围为[30,120]，单位秒）。**3.9.6.07121版本废弃，可在SSP后台控制**。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。注：仅debug模式为true时生效。|
| setListener(ADSuyiBannerAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setSceneId(String sceneId) | 设置广告场景id，用于区分同一个广告位在不同场景下使用的数据。参数说明：sceneId（场景ID）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| adSize(ADSuyiAdSize adSize) | 设置开屏视图宽高。参数说明：adSize（设置整个广告视图预期宽高，单位为px）。|
| setAdShakeDisable(boolean adShakeDisable) | 设置传感器禁用。参数说明：adShakeDisable（true：禁用、false：可用，默认：false）。|

**ADSuyiBannerAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiBannerAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiAdInfo adInfo) | 广告展示回调。|
| onAdClick(ADSuyiAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiAdInfo adInfo) | 广告关闭回调，开发者需要在此回调中对广告父视图进行隐藏或移除子视图，并对广告对象进行释放，避免自刷新逻辑持续进行|
| onAdFailed(ADSuyiError error) | 广告失败回调。参数说明：error（广告错误信息）。|

**ADSuyiAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| getPlatform() | 获取三方广告平台名称，返回String类型。|
| getECPM() | 获取ECPM，返回Double类型（单位：元）。|
| getEcpmPrecision() | ECPM类型，返回String类型（accurate：精准、platform_assignment：平台指定、estimate：估算）。|

#### 6.3.2 横幅广告加载并展示

```java
// 创建横幅广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器（请保证容器不会拦截点击、触摸等事件）
ADSuyiBannerAd bannerAd = new ADSuyiBannerAd(Activity activity, ViewGroup container);

// 设置Banner广告监听
bannerAd.setListener(new ADSuyiBannerAdListener() {
    @Override
    public void onAdReceive(ADSuyiAdInfo adSuyiAdInfo) {
        // 广告获取成功回调...
    }

    @Override
    public void onAdExpose(ADSuyiAdInfo adSuyiAdInfo) {
        // 广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败
    }

    @Override
    public void onAdClick(ADSuyiAdInfo adSuyiAdInfo) {
        // 广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败
    }

    @Override
    public void onAdClose(ADSuyiAdInfo adSuyiAdInfo) {
        // 广告关闭回调，开发者需要在此回调中对广告父视图进行隐藏或移除子视图，
        // 并对广告对象进行释放，避免自刷新逻辑持续进行
   	}

    @Override
    public void onAdFailed(ADSuyiError adSuyiError) {
        // 广告获取失败回调...
    }
});

// 加载横幅广告，参数为广告位ID，同一个ADSuyiBannerAd只有一次loadAd有效
bannerAd.loadAd(String posId);
```

>横幅广告示例 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/BannerAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/BannerAdActivity.java)
>



### <a name="ad_native">6.4 信息流广告示例</a>

信息流广告，具备自渲染和模板两种广告样式：自渲染是SDK将返回广告标题、描述、Icon、图片、多媒体视图等信息，开发者可通过自行拼装渲染成喜欢的样式；模板样式则是返回拼装好的广告视图，开发者只需将视图添加到相应容器即可，模板样式的容器高度建议是自适应。
**请务必确保自渲染类型广告渲染时包含广告创意素材（至少包含一张图片）、平台logo、广告标识、关闭按钮；模板广告不得被遮挡。**
**注意，信息流广告点击关闭时，开发者需要在onAdClose回调中将广告容器隐藏或移除，避免如穿山甲（头条）渠道点击关闭后视图依旧存在问题**

#### 6.4.1 信息流广告主要 API

**ADSuyiNativeAd**

cn.admobiletop.adsuyi.ad.ADSuyiNativeAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiNativeAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiNativeAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。注：仅debug模式为true时生效。|
| setListener(ADSuyiNativeAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setVideoListener(ADSuyiNativeVideoListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setSceneId(String sceneId) | 设置广告场景id，用于区分同一个广告位在不同场景下使用的数据。参数说明：sceneId（场景ID）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| loadAd(String posId, int count) | 请求广告并展示。参数说明：posId（广告位ID）、count（广告数量，1～3条）。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| adSize(ADSuyiAdSize adSize) | 设置整个广告视图预期宽高。参数说明：adSize（广告容器宽高，建议传入宽度为容器实际宽度，高度传入0（自适应高度））。|
| nativeAdMediaViewSize(ADSuyiAdSize adSize) | 设置广告视图中MediaView的预期宽高。参数说明：adSize（广告视频宽高，目前仅Inmobi平台需要，Inmobi的MediaView高度为自适应，没有接入Inmobi平台可不设置）。|
| nativeStyle(ADSuyiAdNativeStyle adNativeStyle) | 设置模板广告内外边距参数。参数说明：adNativeStyle（模板广告样式，目前仅天目平台需要）。|
| nativeAdPlayWithMute(boolean isMute) | 视频静音设置。参数说明：isMute（true：静音、false：不静音，默认：true）。|
| setAdShakeDisable(boolean adShakeDisable) | 设置传感器禁用。参数说明：adShakeDisable（true：禁用、false：可用，默认：false）。|

**ADSuyiAdNativeStyle**

cn.admobiletop.adsuyi.ad.entity.ADSuyiAdNativeStyle

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiAdNativeStyle(int padding) | 构造方法。参数说明：padding（容器内边距）。|
| ADSuyiAdNativeStyle(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) | 构造方法。参数说明：paddingLeft（容器左边距）、paddingTop（容器上边距）、paddingRight（容器右边距）、paddingBottom（容器下边距）。|
| setTitleSize(int titleSize) | 设置标题大小。参数说明：titleSize（标题大小，单位：sp）。|
| setDescSize(int descSize) | 副标题大小。参数说明：descSize（副标题大小，单位：sp）。|

**ADSuyiNativeAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiNativeAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(List\<ADSuyiNativeAdInfo> adInfos) | 广告加载成功回调。|
| onAdExpose(ADSuyiNativeAdInfo adInfo) | 广告展示回调。|
| onAdClick(ADSuyiNativeAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiNativeAdInfo adInfo) | 广告关闭回调，在此回调中移除页面中的视图。|
| onAdFailed(ADSuyiError error) | 广告失败回调。参数说明：error（广告错误信息）。|
| onRenderFailed(ADSuyiNativeAdInfo adInfo, ADSuyiError error) | 广告渲染失败回调。参数说明：error（广告错误信息）。|
| onRenderSuccess(ADSuyiNativeAdInfo adInfo) | 广告渲染成功回调。|

**ADSuyiNativeVideoListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiNativeVideoListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onVideoLoad(ADSuyiNativeAdInfo nativeAdInfo) | 视频加载中回调。|
| onVideoStart(ADSuyiNativeAdInfo nativeAdInfo) | 视频播放回调。|
| onVideoPause(ADSuyiNativeAdInfo nativeAdInfo) | 视频暂停回调。|
| onVideoComplete(ADSuyiNativeAdInfo nativeAdInfo) | 视频播放完毕回调。|
| onVideoError(ADSuyiNativeAdInfo nativeAdInfo) | 视频异常回调。|

**信息流广告父对象ADSuyiNativeAdInfo**

<p style="color:red;">信息流模板和自渲染均继承自该类</p>

cn.admobiletop.adsuyi.ad.data.ADSuyiNativeAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| isNativeExpress() | 广告类型，返回boolean类型，true模板类型，false自渲染类型。|
| getPlatform() | 获取三方广告平台名称，返回String类型。|
| getECPM() | 获取ECPM，返回Double类型（单位：元）。|
| getEcpmPrecision() | ECPM类型，返回String类型（accurate：精准、platform_assignment：平台指定、estimate：估算）。|

<p style="color:red;">当isNativeExpress返回true时，可强转为ADSuyiNativeExpressAdInfo类，否则转为ADSuyiNativeFeedAdInfo类</p>

**模板广告对象ADSuyiNativeExpressAdInfo继承自ADSuyiNativeAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiNativeExpressAdInfo

| 方法名         | 类型 | 介绍 |
| ------------ | ---- | ---- |
| getNativeExpressAdView() | View | 获取的是整个模板广告视图。|
| render(ViewGroup container) | void | 渲染视图，调用该方法才能响应曝光、点击等操作，影响广告收益。参数说明：container（承载广告的容器，不能为空）|

**自渲染广告对象ADSuyiNativeFeedAdInfo继承自ADSuyiNativeAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiNativeFeedAdInfo

| 方法名         | 类型 | 介绍 |
| ------------ | ---- | ---- |
| getTitle() | String | 获取广告标题，可能为空。|
| getDesc() | String | 获取广告描述，可能为空。|
| getActionType() | int | 获取广告交互类型，未知：-1，应用内打开落地页：0，浏览器打开落地页：1，下载类型：2，拨打电话：3。|
| getCtaText() | String | 广告交互按钮文案，可能为空。|
| getIconUrl() | String | 广告图标地址，可能为空。|
| getImageUrl() | String | 图片地址，可能为空。。|
| getImageUrlList() | List\<String\> | 广告图片集合，可能为空。|
| hasMediaView() | boolean | 判断是否包含多媒体广告视图。|
| getMediaView() | View | 获取的是多媒体广告视图。|
| getPlatformIcon() | int | 获取广告平台角标，资源文件地址。|
| registerViewForInteraction(ViewGroup container, View... actionViews) | void | 注册广告视图。参数说明：container（广告容器）、<br>actionViews（可点击的布局）|
| registerCloseView(View close) | void | 注册关闭按钮。参数说明：close（点击关闭的view，不注册将不会回调onAdClose事件）|
| getAppInfo() | ADSuyiAdAppInfo | 下载类应用六要素信息，可能为空。 |

**自渲染广告ADSuyiAdAppInfo六要素，需要先通过（nativeFeedAdInfo instanceof ADSuyiAdAppInfo）方法判断广告对象是否支持六要素返回，
然后通过强转获取六要素对象((ADSuyiAdAppInfo) nativeFeedAdInfo).getAppInfo()**

cn.admobiletop.adsuyi.ad.data.ADSuyiAdAppInfo

| 方法名         | 类型 | 介绍 |
| ------------ | ---- | ---- |
| getName() | String | 应用名，可能为空。|
| getDeveloper() | String | 开发者，可能为空。|
| getVersion() | String |版本号，可能为空。|
| getPrivacyUrl() | String | 隐私地址，可能为空。|
| getPermissionsUrl() | String | 权限地址，可能为空。|
| getDescriptionUrl() | String | 功能介绍，可能为空。|
| getSize() | long | 应用大小，可能为空。|
| getIcp() | String | icp备案号，可能为空。|

#### 6.4.2 信息流广告加载并展示

#### 6.4.2.1 信息流广告加载

```java
// 创建信息流广告实例
ADSuyiNativeAd nativeAd = new ADSuyiNativeAd(Activity activity);
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
// 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
        // 设置整个广告视图预期宽高，单位为px，高度如果小于等于0则高度自适应
        .adSize(new ADSuyiAdSize(widthPixels, 0))
        .build();
// 设置额外参数
        nativeAd.setLocalExtraParams(extraParams);

// 设置广告监听
        nativeAd.setListener(new ADSuyiNativeAdListener() {
@Override
public void onRenderFailed(ADSuyiNativeAdInfo adInfo, ADSuyiError error) {
        // 广告渲染失败，可在此回调中移除视图和释放广告对象
        }

@Override
public void onAdReceive(List<ADSuyiNativeAdInfo> adInfos) {
        // 广告获取成功回调...
        }

@Override
public void onAdExpose(ADSuyiNativeAdInfo adInfo) {
        // 广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败
        }

@Override
public void onAdClick(ADSuyiNativeAdInfo adInfo) {
        // 广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败
        }

@Override
public void onAdClose(ADSuyiNativeAdInfo adInfo) {
        // 广告关闭回调，可在此回调中移除视图和释放广告对象
        }

@Override
public void onAdFailed(ADSuyiError error) {
        // 广告获取失败回调...
        }
        });

// 请求广告数据，参数一广告位ID，参数二请求数量[1,3]
        nativeAd.loadAd(String posId, int count);
```

#### 6.4.2.2 信息流广告展示-模板

```java
// 判断广告Info对象是否被释放（调用过ADSuyiNativeAd的release()或ADSuyiNativeAdInfo的release()会释放广告Info对象）
// 释放后的广告Info对象不能再次使用
if (!ADSuyiAdUtil.adInfoIsRelease(nativeExpressAdInfo)) {
        // 当前是信息流模板广告，getNativeExpressAdView获取的是整个模板广告视图
        View nativeExpressAdView = nativeExpressAdInfo.getNativeExpressAdView((ViewGroup) itemView);
        // 将广告视图添加到容器中的便捷方法
        ADSuyiViewUtil.addAdViewToAdContainer((ViewGroup) itemView, nativeExpressAdView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // 渲染广告视图, 必须调用, 因为是模板广告, 所以传入ViewGroup和响应点击的控件可能并没有用
        // 务必在最后调用
        nativeExpressAdInfo.render((ViewGroup) itemView);
        }
```

#### 6.4.2.3 信息流广告展示-自渲染

```java
// 判断广告Info对象是否被释放（调用过ADSuyiNativeAd的release()或ADSuyiNativeAdInfo的release()会释放广告Info对象）
// 释放后的广告Info对象不能再次使用
if (!ADSuyiAdUtil.adInfoIsRelease(nativeFeedAdInfo)) {
        NativeAdAdapter.setVideoListener(nativeFeedAdInfo);

        // 交由子类实现加载图片还是MediaView
        setImageOrMediaData(context, nativeFeedAdInfo);

        Glide.with(context).load(nativeFeedAdInfo.getIconUrl()).into(ivIcon);
        ivAdTarget.setImageResource(nativeFeedAdInfo.getPlatformIcon());
        tvTitle.setText(nativeFeedAdInfo.getTitle());
        tvDesc.setText(nativeFeedAdInfo.getDesc());
        tvAdType.setText(nativeFeedAdInfo.getCtaText());

        // 注册广告交互, 必须调用
        // 注意：部分渠道只会响应View...actionViews的点击事件，如快手的视频组件，不传则无法点击
        nativeFeedAdInfo.registerViewForInteraction((ViewGroup) itemView, rlAdContainer, tvAdType);

        // 注册关闭按钮，将关闭按钮点击事件交于SDK托管，以便于回调onAdClose
        // 务必最后调用
        nativeFeedAdInfo.registerCloseView(ivClose);
        }
```



> 信息流广告示例 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/other/NativeAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/other/NativeAdActivity.java)
>



### <a name="ad_reward_vod">6.5 激励视频广告示例</a>

将短视频融入到APP场景当中，用户观看短视频广告后可以给予一些应用内奖励。

#### 6.5.1 激励视频广告主要 API

**ADSuyiRewardVodAd**

cn.admobiletop.adsuyi.ad.ADSuyiRewardVodAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiRewardVodAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiRewardVodAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。注：仅debug模式为true时生效。|
| setListener(ADSuyiRewardVodAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setSceneId(String sceneId) | 设置广告场景id，用于区分同一个广告位在不同场景下使用的数据。参数说明：sceneId（场景ID）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| rewardExtra(ADSuyiRewardExtra extra) | 设置服务端奖励验证额外参数。参数说明：extra（服务端奖励验证额外参数请参考：[Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/Android-SuyiSDK激励视频服务端验证使用说明.md)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/Android-SuyiSDK激励视频服务端验证使用说明.md)）。|
| setVideoWithMute(boolean isMute) | 视频静音设置。参数说明：isMute（true：静音、false：不静音，默认：true）。|
| setAdShakeDisable(boolean adShakeDisable) | 设置传感器禁用。参数说明：adShakeDisable（true：禁用、false：可用，默认：false）。|

**ADSuyiRewardVodAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiRewardVodAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiRewardVodAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiRewardVodAdInfo adInfo) | 广告曝光回调。|
| onAdClick(ADSuyiRewardVodAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiRewardVodAdInfo adInfo) | 广告关闭回调。|
| onReward(ADSuyiRewardVodAdInfo adInfo) | 广告奖励回调。|
| onVideoCache(ADSuyiRewardVodAdInfo adInfo) | 广告缓存成功回调。部分渠道不会回调该方法，请在onAdReceive做广告展示处理|
| onVideoComplete(ADSuyiRewardVodAdInfo adInfo) | 广告播放完毕回调。|
| onVideoError(ADSuyiRewardVodAdInfo adInfo, ADSuyiError error) | 视频播放错误回调。|
| onAdFailed(ADSuyiError error) | 广告获取失败回调。|

**ADSuyiRewardVodAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiRewardVodAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| showRewardVod(Activity activity) | 展示广告。参数说明：activity（当前页面activity对象）。|
| getPlatform() | 获取三方广告平台名称，返回String类型。|
| getECPM() | 获取ECPM，返回Double类型（单位：元）。|
| getEcpmPrecision() | ECPM类型，返回String类型（accurate：精准、platform_assignment：平台指定、estimate：估算）。|

#### 6.5.2 激励视频广告加载并展示

```java
// 创建激励视频广告实例
ADSuyiRewardVodAd rewardVodAd = new ADSuyiRewardVodAd(Activity activity);

// 设置激励视频广告监听
        rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {

@Override
public void onAdReceive(ADSuyiRewardVodAdInfo adInfo) {
        // 广告获取成功回调...
        // 激励视频广告对象一次成功拉取的广告数据只允许展示一次
        // 广告展示
        adInfo.showRewardVod(Activity activity)
        }

@Override
public void onVideoCache(ADSuyiRewardVodAdInfo adInfo) {
        // 广告视频缓存成功回调...
        // 部分渠道存在激励展示类广告，不会回调该方法，建议在onAdReceive做广告展示处理
        }

@Override
public void onVideoComplete(ADSuyiRewardVodAdInfo adInfo) {
        // 广告观看完成回调...
        }

@Override
public void onVideoError(ADSuyiRewardVodAdInfo adInfo, ADSuyiError error) {
        // 广告播放错误回调...
        }

@Override
public void onReward(ADSuyiRewardVodAdInfo adInfo) {
        // 广告激励发放回调...
        }

@Override
public void onAdExpose(ADSuyiRewardVodAdInfo adInfo) {
        // 广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败
        }

@Override
public void onAdClick(ADSuyiRewardVodAdInfo adInfo) {
        // 广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败
        }

@Override
public void onAdClose(ADSuyiRewardVodAdInfo adInfo) {
        // 广告关闭回调
        }

@Override
public void onAdFailed(ADSuyiError error) {
        // 广告获取失败回调...
        }
        });

// 加载激励视频广告，参数为广告位ID
        rewardVodAd.loadAd(String posId);
```

<p style="color:red;">注意广告对象的获取是异步的，请在onAdReceive回调后展示广告 </p>


> 激励视频广告示例详情 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/RewardVodAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/RewardVodAdActivity.java)
>


### <a name="ad_interstitial">6.6 插屏广告示例</a>

插屏广告是移动广告的一种常见形式，在应用流程中弹出，当应用展示插屏广告时，用户可以选择点击广告，也可以将其关闭并返回应用。

#### 6.6.1 插屏广告主要 API

**ADSuyiInterstitialAd**

cn.admobiletop.adsuyi.ad.ADSuyiInterstitialAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiInterstitialAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiInterstitialAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。注：仅debug模式为true时生效。|
| setListener(ADSuyiInterstitialAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setSceneId(String sceneId) | 设置广告场景id，用于区分同一个广告位在不同场景下使用的数据。参数说明：sceneId（场景ID）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| setVideoWithMute(boolean isMute) | 视频静音设置。参数说明：isMute（true：静音、false：不静音，默认：true）。|
| setAdShakeDisable(boolean adShakeDisable) | 设置传感器禁用。参数说明：adShakeDisable（true：禁用、false：可用，默认：false）。|

**ADSuyiInterstitialAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiInterstitialAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiInterstitialAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiInterstitialAdInfo adInfo) | 广告曝光回调。|
| onAdClick(ADSuyiInterstitialAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiInterstitialAdInfo adInfo) | 广告关闭回调。|
| onAdReady(ADSuyiInterstitialAdInfo adInfo) | 广告准备完毕回调。部分渠道不会回调该方法，请在onAdReceive做广告展示处理|
| onAdFailed(ADSuyiError error) | 广告获取失败回调。|

**ADSuyiInterstitialAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiInterstitialAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| showInterstitial(Activity activity) | 展示广告。参数说明：activity（当前页面activity对象）。|
| getPlatform() | 获取三方广告平台名称，返回String类型。|
| getECPM() | 获取ECPM，返回Double类型（单位：元）。|
| getEcpmPrecision() | ECPM类型，返回String类型（accurate：精准、platform_assignment：平台指定、estimate：估算）。|

#### 6.6.2 插屏广告加载并展示

```java
ADSuyiInterstitialAd interstitialAd = new ADSuyiInterstitialAd(Activity activity);

// 设置插屏广告监听
        interstitialAd.setListener(new ADSuyiInterstitialAdListener() {

@Override
public void onAdReceive(ADSuyiInterstitialAdInfo adInfo) {
        // 广告获取成功回调...
        // 插屏广告对象一次成功拉取的广告数据只允许展示一次
        // 展示广告
        adInfo.showInterstitial(Activity activity);
        }

@Override
public void onAdReady(ADSuyiInterstitialAdInfo adInfo) {
        // 部分渠道不会回调该方法，请在onAdReceive做广告展示处理
        // 广告准备完毕回调...
        }

@Override
public void onAdExpose(ADSuyiInterstitialAdInfo adInfo) {
        // 广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败
        }

@Override
public void onAdClick(ADSuyiInterstitialAdInfo adInfo) {
        // 广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败
        }

@Override
public void onAdClose(ADSuyiInterstitialAdInfo adInfo) {
        // 广告点击关闭回调
        }

@Override
public void onAdFailed(ADSuyiError error) {
        // 广告获取失败回调...
        }
        });

// 加载插屏广告
        interstitialAd.loadAd(String posId);
```

<p style="color:red;">注意广告对象的获取是异步的，请在onAdReceive回调后展示广告 </p>

> 插屏广告示例详情[Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/interstitial/InterstitialAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/interstitial/InterstitialAdActivity.java)
>

### 6.7 备注

具体的接入代码和流程，请参考Demo



## 7. 常见问题和错误调试

> [常见问题和错误调试及错误码](https://doc.admobile.top/ssp/2jieru/3-ADSuyiSDK_And_Error_code.html#%E9%94%99%E8%AF%AF%E7%A0%81)

如果以上地址无法跳转，请访问[备用地址](http://doc.admobile.top/ssp/)，下拉找到**Android集成常见问题**



## 8.商务合作

邮箱 : yuxingcao@admobile.top