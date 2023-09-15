# ADSuyiSdk Android Sdk——接入文档 V3.7.7.07141



## 1. 概述
### 1.1 概述

尊敬的开发者朋友，欢迎您使用ADSuyi广告聚合SDK。通过本文档，您可以快速完成多平台广告SDK的集成。

**注意：本SDK仅支持中国大陆地区**；如需发布到Google Play，请勿引入本SDK及相关依赖文件。



### 1.2 ADSuyi广告聚合SDK 组成结构

ADSuyi广告聚合SDK主要由**ADSuyi核心SDK（简称ADSuyiSdk）**和一个或多个**三方平台适配器SDK（简称AdapterSdk）**组成，开发者可以自由的在后台配置中选择需要接入的三方广告平台，然后导入所对应的AdapterSdk，其中**艾狄墨搏平台的AdapterSdk是必须导入的**。



### <a name="platform_name"> 1.3 三方广告平台名称概述 </a>

| Name      | 平台名称     | 平台别称     |
| --------- | ------------ | ------------ |
| tianmu    | 天目         | 天目         |
| gdt       | 优量汇       | 广点通       |
| toutiao   | 头条         | 穿山甲       |
| baidu     | 百度         | 百青藤       |
| mintegral | 汇量         | Mobvsita     |
| ksad      | 快手         | 快手         |
| mimo      | 米盟         | 米盟         |
| hwpps     | 华为广告联盟 | 华为广告联盟 |
| inmobi    | Inmobi       | Inmobi       |
| gromore   | gromore      | gromore      |

### 1.4 ADSuyi必添包容量

| Name         | 大小 | 版本号       | MD5值                            |
| ------------ | ---- | ------------ | -------------------------------- |
| ADSuyi基础包 | 0.3M | V3.7.7.07141  | 8575e20f2b5e44f1512614406fa9af2e |
| OAID         | 1.1M | —            | —                                |

### 1.5 三方广告平台适配器+三方广告sdk总容量

| Name      | 容量  | 版本号             | MD5值                            |
| --------- | ----- | ------------------ | -------------------------------- |
| tianmu    | 1.80M | v2.0.7.07173       | 41e9794d26a3aafa5100861c3f728eee |
| gdt       | 1.92M | v4.532.1402.07241  | af175160dcb3937585265b23b803e068 |
| toutiao   | 7.00M | v5.4.1.6.07141     | a99e365188af68c537958adbf49f0d1f |
| baidu     | 1.80M | v9.31.07141        | 2651d6a5f4ed82406096fd8669b2497f |
| mintegral | 2.80M | v16.4.71.07171     | 909b8f76b99eb28e6620b73a4c0918d9 |
| ksad      | 4.20M | v3.3.49.07141      | 8626f9a53729a6d03c6abcb1e12e3930 |
| mimo      | 1.60M | v5.2.5.06251       | d96bc1a429f1b39a2271a33d89fd75db |
| hwpps     | 1.01M | v13.4.62.302.06251 | 43617828b5bbf34651ef5df68ca7d532 |
| inmobi    | 0.95M | v7.5.4.11152       | 8ecde00efc7e8af8b1fa2cbc7ebe3f89 |
| gromore   | —     | v4.2.0.2.05151     | be9089e3dcd84c218b7fa39ab1184b6e |


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
    <td><a href="#ad_banner">Banner广告</a></td>
    <td>Banner广告是横向贯穿整个可视页面的模板广告，需要将Banner广告视图添加到承载的广告容器中</td>
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
    <td><a href="#ad_full_screen_vod">全屏视频广告</a></td>
    <td>类似激励视频，与激励视频不同的是，全屏视频广告在观看一定时长后即可跳过广告，无需全部观看完成，有视频跳过回调，但是没有激励回调</td>
    <td>常出现在游戏的复活、任务等位置，或者网服类APP的一些增值服务场景</td>
  </tr>
  <tr>
    <td><a href="#ad_interstitial">插屏广告</a></td>
    <td>插屏广告是移动广告的一种常见形式，在应用流程中弹出，当应用展示插屏广告时，用户可以选择点击广告，访问其目标网址，也可以将其关闭并返回应用</td>
    <td>在应用执行流程的自然停顿点，适合投放这类广告</td>
  </tr>
  <tr>
    <td><a href="#ad_draw_vod">Draw视频广告</a></td>
    <td>类似小视频一样的视频广告</td>
    <td>类似小视频列表的场景</td>
  </tr>
  <tr>
    <td><a href="#inner_notice">浮窗广告</a></td>
    <td>类似通知栏样式展示的广告，只在应用中弹出，几乎不影响用户操作，用户可以上滑左右滑动移除广告</td>
    <td>任意场景</td>
  </tr>
</table>

## 3. Demo及SDK下载链接

> [ADSuyiSdkDemo-演示APK下载地址](https://doc.admobile.top/file/ADSuyiSdkDemo.apk)
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
        // 如果添加了汇量广告，需要添加汇量的远程仓库依赖
        maven { url "https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea" }
        // 如果添加了华为联盟广告，需要添加华为联盟的远程仓库依赖
        maven { url 'https://developer.huawei.com/repo/' }
        // 如果添加了gromore广告，需要添加gromore的远程仓库依赖
        maven { url "https://artifact.bytedance.com/repository/pangle" }
    }
}
```



#### 5.1.2 添加ADSuyiSdk和需要的AdapterSdk

将广告所需要的依赖集成进去，AdapterSdk可根据接入平台情况进行选择接入。

```java
dependencies {
    // support支持库，如果是AndroidX请使用对应的支持库
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support:design:28.0.0'

    // ADSuyiSdk、common和OAID库是必须导入的
    implementation 'cn.admobiletop.adsuyi.ad:core:3.7.7.07141'

    // OAID库1.0.25华为渠道请参考文档5.2
    implementation(name: 'oaid_sdk_1.0.25', ext: 'aar')
    // OAID1.0.25版本适配器不支持其它版本，ADSuyi获取oaid使用
    implementation 'cn.admobiletop.adsuyi.ad:oaid:1.0.25.08024'

    // 天目AdapterSdk，必选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:tianmu:2.0.7.07173'

    // 优量汇（广点通）AdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:gdt:4.532.1402.07241'

    // 头条AdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:toutiao:5.4.1.6.07141'

    // 百度增强版AdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:baidu-enhanced:9.31.07141'

    // 汇量AdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:mintegral:16.4.71.07171'

    // 快手AdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:ksadbase:3.3.49.07141'

    // InmobiAdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:inmobi:7.5.4.11152'
    implementation 'com.squareup.picasso:picasso:2.5.2'

    // 米盟AdapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:mimo:5.2.5.06251'
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'

    // 华为广告联盟AdadapterSdk，可选的
    implementation 'cn.admobiletop.adsuyi.ad.adapter:hwpps:13.4.62.302.06251'

    // gromoreAdapterSdk，可选的。如果要使用gromore的其他渠道，请联系开发者。
    implementation 'cn.admobiletop.adsuyi.ad.adapter:gromore:4.2.0.2.05151'
    implementation "by.gm_mediation.com:gdt-adapter:4.520.1390.1" //广点通 adapter
    implementation "by.gm_mediation.com:pangle-adapter:5.3.0.5.0" //穿山甲 adapter
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

2. 使用**AdapterSdk**的**without**集成方式，该方式没有集成三方广告SDK和配置，开发者可自行集成三方广告SDK，但是需要注意，
<p style="color:red;">我们的AdapterSdk是基于三方广告SDK某个版本开发的，如果自行集成三方广告SDK，需要承担三方广告SDK版本不一致可能引起的兼容性和其他不可预知问题； </p>

   ```java
   // 优量汇AdapterSdk的without集成示例，其中x.x.x.x为AdapterSdk版本号
   implementation 'cn.admobiletop.adsuyi.ad.adapter:gdt-without:x.x.x.x'
   ```

> 如果接入汇量，需要加入第三方依赖库https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea

> 如果接入华为联盟，需要加入第三方依赖库https://developer.huawei.com/repo/

> **由于头条(穿山甲)渠道支持了Android R，引入了Android R的 \<queries\> 标签,需要对gradle版本进行限制，限制范围为：3.3.3、 3.4.3、 3.5.4、3.6.4、4.0.1 ，开发者根据自身情况酌情升级**

>  **如对接华为广告联盟，激励视频要提前预加载，并且播放完成后需要预加载下一个激励视频，华为渠道点击事件无法统计；banner广告使用场景是程序页面的顶部或者底部。**

3. 激励、全屏视频、插屏等广告对象一次成功拉取的广告数据只允许展示一次，还需展示请再次加载广告。

4. 关于项目使用autosize后出现广告样式出现异常问题处理方案，请参考master-screen-adapter分支中的BannerActivity，并将适配单位改为pt。

5. Mintegral（汇量）渠道与微信sdk冲突解决办法
    由于Mintegral渠道16.1.7版本支持小程序跳转功能，若媒体已导入opensdk，会导致冲突，可通过以下方式移除Mintegral适配器中的opensdk
    ```java
    ('cn.admobiletop.adsuyi.ad.adapter:mintegral:16.4.71.07171') {
            exclude group: "com.tencent.mm.opensdk", module: "wechat-sdk-android"
        }
    ```

### 5.2 OAID1.0.25版本支持

**Android10之后IMEI等数据无法获取，这对广告投放将产生一定影响，所以移动安全联盟(MSA)提出OAID来代替IMEI参与广告投放决策，OAID的支持会在一定程度上影响广告收益；**

**OAID是必须集成项，没有集成将会抛出异常提醒开发者**，OAID集成并不繁琐，SDK中已经进行了OAID1.0.25的封装适配，只需以下几步即可完成OAID的支持；

1. 导入安全联盟的OAID支持库 **oaid_sdk_1.0.25.aar**，可在Demo的libs目录下找到，**对接其它版本请勿参考该教程；**<br>

2. 将Demo中assets文件夹下的**supplierconfig.json**文件复制到自己的assets目录下并按照**supplierconfig.json**文件中的说明进行OAID的 **AppId** 配置，**supplierconfig.json**文件名不可修改。需要设置 appid 的部分需要去对应厂商的应用商店的应用信息中查看；

3. 添加以下混淆配置；

   ```java
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
   ```
   
4. 修改AndroidManifest.xml，**OAID SDK minSdkVersion为21，如果应用的minSdkVersion小于21，则添加：**
    ```java
    // 如果导入后有冲突可以不添加，suyi中已经添加过了
    <uses-sdk tools:overrideLibrary="com.bun.miitmdid"/>
    ```

    **PS：目前华为应用市场会提示OAID1.0.25中存在网易（飞鱼）的sdk，这是由于OAID1.0.25中的包路径和网易（飞鱼）sdk的包路径相同，只能通过升级OAID进行解决。<br>
    OAID 1.0.25以上版本需要到[《移动智能终端补充设备标识体系统一调用SDK》](http://www.msa-alliance.cn/col.jsp?id=120)申请APP专属密钥才能正常使用，需要开发者自行获取OAID，并参考<a href="#custom_controller"> 5.7 向SDK传入设备标识 </a> ，将获取到的OAID字符串传给广告SDK，保证广告SDK参数成功接收到。**<br><br>
    **注意：使用其它版本oaid请移除以下依赖，避免造成崩溃：**
    ```java
    // OAID库1.0.25
    implementation(name: 'oaid_sdk_1.0.25', ext: 'aar')
    // OAID1.0.25版本适配器不支持其它版本，ADSuyi获取oaid使用
    implementation 'cn.admobiletop.adsuyi.ad:oaid:1.0.25.08024'
    ```

5. 使用其它版本参考案例
[Oaid高版本参考案例](https://gitee.com/admobile/oaid-docking-case.git)

### 5.3 权限申请

  使用SDK时可能需要以下权限，为了保证使用广告的正确，请在6.0及以上的手机中使用SDK前及时申请。

```java
<!-- 广告必须的权限，允许网络访问 -->
<uses-permission android:name="android.permission.INTERNET" />
<!-- 广告必须的权限，允许安装未知来源权限（如下载类广告下载完成后唤起安卓） -->
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
<!-- 广告必须的权限，地理位置权限，获取位置信息，用于广告投放。精准广告投放及反作弊 -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

<!-- 如果有视频相关的广告播放请务必添加，屏幕保持唤醒不锁屏（部分渠道未添加该权限时会出现视频类广告黑屏）-->
<uses-permission android:name="android.permission.WAKE_LOCK" />

<!-- 如果接入了优量汇渠道，必须加入以下权限，不然会导致优量汇填充失败 -->
<!-- 允许应用获取 MAC 地址。广告投放及广告监测归因、反作弊 -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!-- 允许应用检测网络状态，SDK 会根据网络状态选择是否发送数据 -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- 影响广告填充，强烈建议的权限，获取设备信息，允许应用获取手机状态（包括手机号码、IMEI、IMSI权限等）。广告投放及广告监测归因、反作弊 -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />

<!-- 为了提高广告收益，建议设置的权限，写入权限，用于下载类广告数据写入 -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 为了提高广告收益，建议设置的权限，读取权限，用于下载类广告数据读取（如判断是否已下载过该APK，避免重复下载）-->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

<!-- 为了提高广告收益，建议设置的权限，获取粗略位置信息。精准广告投放及反作弊 -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```



### 5.4 兼容配置

#### 5.4.1 FileProvider配置

1. 适配Anroid7.0以及以上，请在AndroidManifest中添加如下代码：

* 如果支持库是support

  ```java
  <provider
  	  android:name="android.support.v4.content.FileProvider"
      android:authorities="${applicationId}.fileprovider"
      android:exported="false"
      android:grantUriPermissions="true">
      <meta-data
      		android:name="android.support.FILE_PROVIDER_PATHS"
          android:resource="@xml/adsuyi_file_paths" />
  </provider>
  ```

* 如果支持库为androidx

  ```java
  <provider
  	  android:name="androidx.core.content.FileProvider"
      android:authorities="${applicationId}.fileprovider"
      android:exported="false"
      android:grantUriPermissions="true">
      <meta-data
      		android:name="android.support.FILE_PROVIDER_PATHS"
          android:resource="@xml/adsuyi_file_paths" />
  </provider>
  ```



2. 在res/xml目录下(如果xml目录不存在需要手动创建)，新建xml文件adsuyi_file_paths，在该文件中加入如下配置，如果存在相同android:authorities的provider，请将paths标签中的路劲配置到自己的xml文件中：

  ```java
  <?xml version="1.0" encoding="utf-8"?>
  <paths xmlns:android="http://schemas.android.com/apk/res/android">
      <external-path name="external_path" path="." />
      <external-files-path name="external_files_path" path="." />
  </paths>
  ```

  <p style="color:red;">PS  :  为了适配下载和安装相关功能，在工程中引用的包 com.android.support:support-v4:x.x.x请使用26.0.0及以上版本。 </p>



#### 5.4.2 网络配置

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



#### 5.4.3 混淆配置

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

# 优量汇广告平台混淆
-keep class com.qq.e.** {public protected *;}
-keep class MTT.ThirdAppInfoNew {*;}
-keep class com.tencent.** {*;}

# 如果使用了tbs版本的sdk需要进行以下配置
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

# 头条广告平台混淆
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

# imobi广告平台混淆
-dontwarn com.inmobi.**
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.bun.**
-dontwarn com.iab.**
-keepattributes SourceFile,LineNumberTable
-keep class com.inmobi.** { *; }
-keep class com.squareup.picasso.** {*;}
-keep class com.integralads.avid.library.** {*;}
-keep class com.iab.** {*;}

# mintegral广告平台混淆
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mbridge.** {*; }
-keep interface com.mbridge.** {*; }
-keep class android.support.v4.** { *; }
-dontwarn com.mbridge.**
-keep class **.R$* { public static final int mbridge*; }

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

# 米盟混淆
-keep class com.miui.zeus.mimo.sdk.* { *; }
-keep class com.miui.analytics.** { *; }
-keep class com.xiaomi.analytics.* { public protected *; }
-keep class * extends android.os.IInterface{*; }
# gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * { @com.google.gson.annotations.SerializedName <fields>; }
# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule { <init>(...);}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {**[] $VALUES;public *;}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {*** rewind();}

# 华为PPS混淆
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

# CookbookAdapter混淆
-keep class cn.admobiletop.cookbook.**{*;}

# ADSyid混淆
-keep class adsuyi.com.** { *; }
-keep interface adsuyi.com.** { *; }

```

### 5.5 隐私信息控制开关

**为了保证您的App顺利通过检测，结合当前监管关注重点，请务必将ADSuyiSdk的初始化放在用户同意隐私政策之后。**

**如合规有更高要求，可以使用以下方法进行控制，但会严重降低广告收益，可根据实际需求进行设置，或联系我发运营人员获取建议。**
同时ADSuyiSDK初始化时开放以下接口，确保imei等设备标识不被读取（目前部分三方广告平台支持）：
```java
//【慎改】是否同意隐私政策，将禁用一切设备信息读起严重影响收益
.agreePrivacyStrategy(true)
// 是否可获取定位数据
.isCanUseLocation(true)
// 是否可获取设备信息
.isCanUsePhoneState(true)
// 是否可读取设备安装列表
.isCanReadInstallList(true)
// 是否可读取设备外部读写权限
.isCanUseReadWriteExternal(true)
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
                     * @return 极光地理位置参数JUnionLocationProvider
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
                     * 开发者可以传入vaid ，若不传或为空值，则不使用oaid信息
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
| pauseFloatingAd() | 暂停浮窗广告投放。 |
| restartFloatingAd() | 恢复浮窗广告投放。 |

**ADSuyiInitConfig**

cn.admobiletop.adsuyi.config.ADSuyiInitConfig

| 方法名         | 介绍 |
| ------------ | ---- |
| appId(String appId) | 设置AppId，必填参数。|
| debug(boolean debug) | 设置是否是Debug模式。参数说明：debug（true：开启，false：关闭， 默认：false）开发阶段以及提交测试阶段可设置为true，方便异常排查。|
| isCanUseLocation(boolean isCanUseLocation) | 设置SDK是否可以使用定位信息。参数说明：isCanUseLocation（true：开启，false：关闭， 默认：true）。|
| isCanUsePhoneState(boolean isCanUsePhoneState) | 设置SDK是否可以使用IMEI等设备信息。参数说明：isCanUsePhoneState（true：开启，false：关闭， 默认：true）。|
| isCanUseWifiState(boolean isCanUseWifiState) | 设置SDK是否可以使用WIFI信息。参数说明：isCanUseWifiState（true：开启，false：关闭， 默认：true）。|
| agreePrivacyStrategy(boolean agreePrivacyStrategy) | 是否同意隐私政策。参数说明：agreePrivacyStrategy（true：开启，false：关闭， 默认：true）。|
| openFloatingAd(boolean isOpen) | 是否开启浮窗广告。参数说明：isOpen（true：开启，false：关闭， 默认：true）。|
| floatingAdBlockList(boolean defaultBlockList, String... blockList) | 设置阻止展示浮窗广告的界面。参数说明：defaultBlockList（是否开启默认阻止的界面（激励视频播放界面等））、blockList（自定义需要阻止的界面，activity路径）。|
| filterThirdQuestion(boolean filterThirdQuestion) | 设置是否过滤第三方平台的问题广告（例如: 已知某个广告平台在某些机型的Banner广告可能存在问题，如果开启过滤，则在该机型将不再去获取该平台的Banner广告）。参数说明：filterThirdQuestion（true：开启，false：关闭， 默认：true）。|
| setCustomDeviceInfoController(CustomDeviceInfoController controller) | 自定义设备信息。可选参数。<a href="#custom_controller"> 请参考5.7 向SDK传入设备标识 </a>|

**CustomDeviceInfoController**

cn.admobiletop.adsuyi.config.CustomDeviceInfoController

| 方法名         | 介绍 |
| ------------ | ---- |
| getImei() | 当isCanUsePhoneState=false时，可传入imei信息，使用您传入的imei信息。|
| getAndroidId() | 当isCanUsePhoneState=false时，可传入AndroidId信息，使用您传入的AndroidId信息。|
| getLocation() | 当isCanUseLocation=false时，可传入地理位置信息，使用您传入的地理位置信息。|
| getMacAddress() | 当isCanUseWifiState=false时，可传入Mac地址信息，使用您传入的Mac地址信息。|
| getOaid() | 开发者可以传入oaid ，若不传或为空值，则不使用oaid信息。|
| getVaid() | 开发者可以传入vaid ，若不传或为空值，则不使用oaid信息。|

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
| loadAd(String posId, ADSuyiNetworkRequestInfo requestInfo) | 请求打底广告并展示，目前支持优量汇、头条、百度、快手。参数说明：posId（广告位ID）、requestInfo（打底广告对象，接口地址[Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/README-ADSuyiNetworkRequestInfo.md)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/README-ADSuyiNetworkRequestInfo.md)）。|
| showSplash() | 展示广告。使用loadOnly方法去加载广告时，可在onAdReceive回调后去展示广告。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| adSize(ADSuyiAdSize adSize) | 设置开屏视图宽高。参数说明：adSize（设置整个广告视图预期宽高(目前仅头条平台需要，没有接入头条可不设置)，单位为px，如果不设置头条开屏广告视图将会以9 : 16的比例进行填充，小屏幕手机可能会出现素材被压缩的情况，大屏幕设备可能出现留白）。|


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



### <a name="ad_banner">6.3 Banner横幅广告示例</a>

Banner横幅广告建议放置在 **固定位置**，而非ListView、RecyclerView、ViewPager等控件中充当Item，Banner广告支持多种尺寸比例，可在后台创建广告位时配置，Banner广告的宽度将会撑满容器，高度自适应，建议Banner广告容器高度也为自适应。

#### 6.3.1 横幅广告主要 API

**ADSuyiBannerAd**

cn.admobiletop.adsuyi.ad.ADSuyiBannerAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiBannerAd(Activity activity, ViewGroup container) | 构造方法。参数说明：activity（当前页面activity对象）、container（展示广告视图的父容器）。|
| ADSuyiBannerAd(Fragment fragment, ViewGroup container) | 构造方法。参数说明：fragment（当前页面fragment对象）、container（展示广告视图的父容器）。|
| setAutoRefreshInterval(long seconds) | 设置自刷新时间间隔。参数说明：seconds（0为不自动刷新（部分平台无效，如百度），其他取值范围为[30,120]，单位秒）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
| setListener(ADSuyiBannerAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setSceneId(String sceneId) | 设置广告场景id，用于区分同一个广告位在不同场景下使用的数据。参数说明：sceneId（场景ID）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| release() | 释放广告。|

**ADSuyiBannerAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiBannerAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiAdInfo adInfo) | 广告展示回调。|
| onAdClick(ADSuyiAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiAdInfo adInfo) | 广告关闭回调。|
| onAdFailed(ADSuyiError error) | 广告失败回调。参数说明：error（广告错误信息）。|



#### 6.3.2 横幅广告加载并展示

```java
// 创建Banner广告实例，第一个参数可以是Activity或Fragment，第二个参数是广告容器（请保证容器不会拦截点击、触摸等事件）
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
        // 广告关闭回调
   	}

    @Override
    public void onAdFailed(ADSuyiError adSuyiError) {
        // 广告获取失败回调...
    }
});

// 加载Banner广告，参数为广告位ID，同一个ADSuyiBannerAd只有一次loadAd有效
bannerAd.loadAd(String posId);
```

>Banner广告示例 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/BannerAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/BannerAdActivity.java)
>



### <a name="ad_native">6.4 信息流广告示例</a>

信息流广告，具备自渲染和模板两种广告样式：自渲染是SDK将返回广告标题、描述、Icon、图片、多媒体视图等信息，开发者可通过自行拼装渲染成喜欢的样式；模板样式则是返回拼装好的广告视图，开发者只需将视图添加到相应容器即可，模板样式的容器高度建议是自适应。
**请务必确保自渲染类型广告渲染时包含广告创意素材（至少包含一张图片）、平台logo、广告标识、关闭按钮；模板广告不得被遮挡。**
**注意，信息流广告点击关闭时，开发者需要在onAdClose回调中将广告容器隐藏或移除，避免如头条渠道点击关闭后视图依旧存在问题**

#### 6.4.1 信息流广告主要 API

**ADSuyiNativeAd**

cn.admobiletop.adsuyi.ad.ADSuyiNativeAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiNativeAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiNativeAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
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
| onRenderFailed(ADSuyiNativeAdInfo adInfo, ADSuyiError error) | 广告失败回调。参数说明：error（广告错误信息）。|

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

<p style="color:red;">当isNativeExpress返回true时，可强转为ADSuyiNativeExpressAdInfo类，否则转为ADSuyiNativeExpressAdInfo类</p>

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
| registerViewForInteraction(ViewGroup container, View... actionViews) | void | 注册广告视图。参数说明：container（广告容器，若对接优量汇自渲染，此处一定要传入com.qq.e.ads.nativ.widget.NativeAdContainer布局，否则优量汇渠道无法曝光）、<br>actionViews（可点击的布局）|
| registerCloseView(View close) | void | 注册关闭按钮。参数说明：close（点击关闭的view，不注册将不会回调onAdClose事件）|

#### 6.4.2 信息流广告加载并展示

#### 6.4.2.1 信息流广告加载

```java
// 创建信息流广告实例
ADSuyiNativeAd nativeAd = new ADSuyiNativeAd(Activity activity);
int widthPixels = getResources().getDisplayMetrics().widthPixels;
// 创建额外参数实例
ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
    // 设置整个广告视图预期宽高(目前仅头条，艾狄墨搏平台需要，没有接入头条、艾狄墨搏可不设置)，单位为px，高度如果小于等于0则高度自适应
    .adSize(new ADSuyiAdSize(widthPixels, 0))
   	.build();
// 设置一些额外参数，有些平台的广告可能需要传入一些额外参数，如果有接入头条、Inmobi平台，该参数必须设置
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
    // 注意：广点通只会响应View...actionViews的点击事件，且这些View都应该是com.qq.e.ads.nativ.widget.NativeAdContainer的子View
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
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
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

**ADSuyiRewardVodAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiRewardVodAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiRewardVodAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiRewardVodAdInfo adInfo) | 广告曝光回调。|
| onAdClick(ADSuyiRewardVodAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiRewardVodAdInfo adInfo) | 广告关闭回调。|
| onReward(ADSuyiRewardVodAdInfo adInfo) | 广告奖励回调。|
| onVideoCache(ADSuyiRewardVodAdInfo adInfo) | 广告缓存成功回调。|
| onVideoComplete(ADSuyiRewardVodAdInfo adInfo) | 广告播放完毕回调。|
| onVideoError(ADSuyiRewardVodAdInfo adInfo, ADSuyiError error) | 视频播放错误回调。|
| onAdFailed(ADSuyiError error) | 广告获取失败回调。|

**ADSuyiRewardVodAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiRewardVodAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| showRewardVod(Activity activity) | 展示广告。参数说明：activity（当前页面activity对象）。|

#### 6.5.2 激励视频广告加载并展示

```java
// 创建激励视频广告实例
ADSuyiRewardVodAd rewardVodAd = new ADSuyiRewardVodAd(Activity activity);

// 设置激励视频广告监听
rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {

    @Override
    public void onAdReceive(ADSuyiRewardVodAdInfo adInfo) {
        // 广告获取成功回调...
        // 全屏视频广告对象一次成功拉取的广告数据只允许展示一次
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



### <a name="ad_full_screen_vod">6.6 全屏视频广告示例</a>

全屏视频广告是类似激励视频样式的广告形式，与激励视频不同之处在于全屏视频广告播放一定时间时间后即可跳过，同时全屏视频广告拥有跳过回调不具备奖励回调。

#### 6.6.1 全屏视频广告主要 API

**ADSuyiFullScreenVodAd**

cn.admobiletop.adsuyi.ad.ADSuyiFullScreenVodAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiFullScreenVodAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiFullScreenVodAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
| setListener(ADSuyiFullScreenVodAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setSceneId(String sceneId) | 设置广告场景id，用于区分同一个广告位在不同场景下使用的数据。参数说明：sceneId（场景ID）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| release() | 释放广告。|

**ADSuyiFullScreenVodAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiFullScreenVodAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiFullScreenVodAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiFullScreenVodAdInfo adInfo) | 广告曝光回调。|
| onAdClick(ADSuyiFullScreenVodAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiFullScreenVodAdInfo adInfo) | 广告关闭回调。|
| onVideoCache(ADSuyiFullScreenVodAdInfo adInfo) | 广告缓存成功回调。|
| onVideoComplete(ADSuyiFullScreenVodAdInfo adInfo) | 广告播放完毕回调。|
| onVideoError(ADSuyiFullScreenVodAdInfo adInfo, ADSuyiError error) | 视频播放错误回调。|
| onAdFailed(ADSuyiError error) | 广告获取失败回调。|

**ADSuyiFullScreenVodAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiFullScreenVodAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| showFullScreenVod(Activity activity) | 展示广告。参数说明：activity（当前页面activity对象）。|

#### 6.6.2 全屏视频广告加载并展示

```java
ADSuyiFullScreenVodAd fullScreenVodAd = new ADSuyiFullScreenVodAd(this);

// 设置全屏视频监听
fullScreenVodAd.setListener(new ADSuyiFullScreenVodAdListener() {

    @Override
    public void onAdReceive(ADSuyiFullScreenVodAdInfo adInfo) {
        // 广告获取成功回调...
        // 全屏视频广告对象一次成功拉取的广告数据只允许展示一次
        // 广告展示
        adInfo.showFullScreenVod(Activity activity);
    }

    @Override
    public void onVideoCache(ADSuyiFullScreenVodAdInfo adInfo) {
        // 广告视频缓存成功回调...
    }

    @Override
    public void onVideoComplete(ADSuyiFullScreenVodAdInfo adInfo) {
        // 广告观看完成回调...
   	}

    @Override
    public void onVideoError(ADSuyiFullScreenVodAdInfo adInfo, ADSuyiError adSuyiError) {
        // 广告播放错误回调...
    }

    @Override
    public void onAdExpose(ADSuyiFullScreenVodAdInfo adInfo) {
        // 广告展示回调，有展示回调不一定是有效曝光，如网络等情况导致上报失败
    }

    @Override
    public void onAdClick(ADSuyiFullScreenVodAdInfo adInfo) {
        // 广告点击回调，有点击回调不一定是有效点击，如网络等情况导致上报失败
    }

    @Override
    public void onAdClose(ADSuyiFullScreenVodAdInfo adInfo) {
        // 广告点击关闭回调
    }

    @Override
    public void onAdFailed(ADSuyiError error) {
        // 广告获取失败回调...
   	}
});

// 加载全屏视频广告
fullScreenVodAd.loadAd(String posId);
```

<p style="color:red;">注意广告对象的获取是异步的，请在onAdReceive回调后展示广告 </p>


> 全屏视频广告示例详情 [Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/FullScreenVodAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/FullScreenVodAdActivity.java)
>



### <a name="ad_interstitial">6.7 插屏广告示例</a>

插屏广告是移动广告的一种常见形式，在应用流程中弹出，当应用展示插屏广告时，用户可以选择点击广告，也可以将其关闭并返回应用。

#### 6.7.1 插屏广告主要 API

**ADSuyiInterstitialAd**

cn.admobiletop.adsuyi.ad.ADSuyiInterstitialAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiInterstitialAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiInterstitialAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
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

**ADSuyiInterstitialAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiInterstitialAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(ADSuyiInterstitialAdInfo adInfo) | 广告加载成功回调。|
| onAdExpose(ADSuyiInterstitialAdInfo adInfo) | 广告曝光回调。|
| onAdClick(ADSuyiInterstitialAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiInterstitialAdInfo adInfo) | 广告关闭回调。|
| onAdReady(ADSuyiInterstitialAdInfo adInfo) | 广告准备完毕回调。|
| onAdFailed(ADSuyiError error) | 广告获取失败回调。|

**ADSuyiInterstitialAdInfo**

cn.admobiletop.adsuyi.ad.data.ADSuyiInterstitialAdInfo

| 方法名         | 介绍 |
| ------------ | ---- |
| showInterstitial(Activity activity) | 展示广告。参数说明：activity（当前页面activity对象）。|

#### 6.7.2 插屏广告加载并展示

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

<p style="color:red;">注意广告对象的获取是异步的，请在onAdReceive或onAdReady回调后展示广告 </p>

> 插屏广告示例详情[Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/interstitial/InterstitialAdActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/interstitial/InterstitialAdActivity.java)
>



### <a name="ad_draw_vod">6.8 DrawVod广告示例</a>

类似抖音、快手小视频一样的沉浸式视频广告类型

#### 6.8.1 DrawVod广告主要 API

**ADSuyiDrawVodAd**

cn.admobiletop.adsuyi.ad.ADSuyiDrawVodAd

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiDrawVodAd(Activity activity) | 构造方法。参数说明：activity（当前页面activity对象）。|
| ADSuyiDrawVodAd(Fragment fragment) | 构造方法。参数说明：fragment（当前页面fragment对象）。|
| setLocalExtraParams(ADSuyiExtraParams extraParams) | 设置额外参数。参数说明：extraParams（广告额外参数）。|
| setOnlySupportPlatform(String platform) | 设置广告定向，仅请求某一渠道。参数说明：platform（<a href="#platform_name">渠道名</a>）。|
| setListener(ADSuyiDrawVodAdListener listener) | 设置广告相关状态。参数说明：listener（广告状态监听器）。|
| setVideoListener(ADSuyiDrawVodVideoListener listener) | 设置广告视频相关状态。参数说明：listener（广告状态监听器）。|
| loadAd(String posId) | 请求广告并展示。参数说明：posId（广告位ID）。|
| release() | 释放广告。|

**ADSuyiExtraParams**

cn.admobiletop.adsuyi.ad.entity.ADSuyiExtraParams

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiExtraParams.Builder().build() | 构造方法。|
| adSize(ADSuyiAdSize adSize) | 设置开屏视图宽高。参数说明：adSize（设置整个广告视图预期宽高）。|


**ADSuyiAdSize**

cn.admobiletop.adsuyi.ad.entity.ADSuyiAdSize

| 方法名         | 介绍 |
| ------------ | ---- |
| ADSuyiAdSize(int width, int height) | 构造方法。参数说明：<br>width（容器宽度，单位：px）请传入实际宽度、<br>height（容器高度，单位：px）请传入实际高度。|


**ADSuyiDrawVodAdListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiDrawVodAdListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdReceive(List\<ADSuyiDrawVodAdInfo> adInfoList) | 广告加载成功回调。|
| onAdExpose(ADSuyiDrawVodAdInfo adInfo) | 广告展示回调。|
| onAdClick(ADSuyiDrawVodAdInfo adInfo) | 广告点击回调。|
| onAdClose(ADSuyiDrawVodAdInfo adInfo) | 广告关闭回调。|
| onRenderFailed(ADSuyiDrawVodAdInfo adInfo, ADSuyiError error) | 广告渲染失败回调。|
| onAdFailed(ADSuyiError error) | 广告失败回调。参数说明：error（广告错误信息）。|

**ADSuyiDrawVodVideoListener**

cn.admobiletop.adsuyi.ad.listener.ADSuyiDrawVodVideoListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onVideoLoad(ADSuyiNativeAdInfo nativeAdInfo) | 视频加载中回调。|
| onVideoStart(ADSuyiNativeAdInfo nativeAdInfo) | 视频播放回调。|
| onVideoPause(ADSuyiNativeAdInfo nativeAdInfo) | 视频暂停回调。|
| onVideoComplete(ADSuyiNativeAdInfo nativeAdInfo) | 视频播放完毕回调。|
| onVideoError(ADSuyiNativeAdInfo nativeAdInfo) | 视频异常回调。|

#### 6.8.2 DrawVod广告加载并展示

```java
ADSuyiDrawVodAd drawVodAd = new ADSuyiDrawVodAd(Activity activity);
int width = getResources().getDisplayMetrics().widthPixels;
int height = width * 16 / 9;

// 创建额外参数实例
ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
		// 设置广告预期宽高(目前仅头条平台需要，没有接入头条平台可不设置)，单位为px，宽高均不能小于等于0
    .adSize(new ADSuyiAdSize(width, height))
    .build();
// 设置一些额外参数，有些平台的广告可能需要传入一些额外参数，如果有接入头条平台，该参数必须设置
drawVodAd.setLocalExtraParams(extraParams);

// 设置DrawVod广告监听
drawVodAd.setListener(new ADSuyiDrawVodAdListener() {
		@Override
    public void onRenderFailed(ADSuyiDrawVodAdInfo adInfo, ADSuyiError error) {
        // 广告渲染失败，可在此处移除广告视图
   	}

    @Override
    public void onAdReceive(List\<ADSuyiDrawVodAdInfo\> adInfoList) {
        // 广告加载成功回调
   	}

    @Override
    public void onAdExpose(ADSuyiDrawVodAdInfo adInfo) {
        // 广告展示回调
    }

    @Override
    public void onAdClick(ADSuyiDrawVodAdInfo adInfo) {
    	// 广告点击回调
    }

    @Override
    public void onAdClose(ADSuyiDrawVodAdInfo adInfo) {
    	// 广告关闭回调
   	}

   	@Override
    public void onAdFailed(ADSuyiError error) {
    	// 广告失败回调
    }
);

// 请求广告数据，参数一广告位ID，参数二请求数量[1,3]
drawVodAd.loadAd(String posId, int count);
```

```java
// 判断广告Info对象是否被释放（调用过ADSuyiDrawVodAd的release()或ADSuyiDrawVodAdInfo的release()会释放广告Info对象）
// 释放后的广告Info对象不能再次使用
if (!ADSuyiAdUtil.adInfoIsRelease(drawVodAdInfo)) {
    // 当前是Draw视频信息流模板广告，getMediaView获取的是模板广告视图
    View mediaView = drawVodAdInfo.getMediaView(rlAdContainer);
    // 将广告视图添加到容器中的便捷方法
    ADSuyiViewUtil.addAdViewToAdContainer(rlAdContainer, mediaView);
    // 注册或者渲染广告视图, 必须调用
    drawVodAdInfo.render(rlAdContainer);
}
```

> DrawVod广告示例详情[Gitee地址](https://gitee.com/admobile/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/DrawVodActivity.java)、[Github地址](https://github.com/ADSuyi/ADSuyiSdkDemo-Android/blob/master/app/src/main/java/cn/admobiletop/adsuyidemo/activity/ad/DrawVodActivity.java)
>



### <a name="inner_notice">6.9 浮窗广告</a>

类似通知栏样式展示的广告，只在应用中弹出，几乎不影响用户操作，用户可以上滑左右滑动移除广告。**浮窗广告无需自行对接，只需要联系我们后台开通即可。同时我们提供一些接口方法，可以自行控制浮窗广告开启/关闭，暂停/恢复，界面过滤等。**

#### 6.9.1 浮窗广告主要 API

**ADSuyiInnerNoticeManager**

cn.admobiletop.adsuyi.ad.inner.ADSuyiInnerNoticeManager

| 方法名         | 介绍 |
| ------------ | ---- |
| loadInnerNoticeAd(Activity activity, ADSuyiInnerNoticeListener listener) | 构造方法。参数说明：activity（当前页面activity对象）、listener（广告状态监听器）。|

**ADSuyiInnerNoticeListener**

cn.admobiletop.adsuyi.ad.inner.ADSuyiInnerNoticeListener

| 方法名         | 介绍 |
| ------------ | ---- |
| onAdFailed(String msg) | 浮窗广告获取失败回调。|
| onAdSuccess() | 浮窗广告展示成功回调。|
| onAdDelay() | 浮窗广告距离下一次可展示剩余时长。|

#### 6.9.2 浮窗广告加载并展示

```java
 // 初始化ADSuyi广告SDK
ADSuyiSdk.getInstance().init(this, new ADSuyiInitConfig.Builder()
		// 设置APPID
    .appId(ADSuyiDemoConstant.APP_ID)
    // 可以通过设置该值手动关闭或开启浮窗广告，默认开启（服务端没有配置开启也不会有浮窗广告）
    .openFloatingAd(true:开启，false:关闭)
    // 如果开了浮窗广告，可设置不展示浮窗广告的界面，第一个参数为是否开启默认不展示的页面（例如:激励视频播放页面），第二可变参数为自定义不展示的页面
    .floatingAdBlockList(false, "cn.admobiletop.adsuyidemo.activity.ad.ADSuyiInitAndLoadSplashAdActivity")
    .build());
```

**浮窗广告的暂停和恢复**

```java
// 可通过调用此方法暂停浮窗广告投放
ADSuyiSdk.getInstance().pauseFloatingAd();

// 可通过调用此方法恢复浮窗广告投放
ADSuyiSdk.getInstance().restartFloatingAd();
```

**开发者也可自行控制浮窗广告的展示**

1.首先将openFloatingAd设置为false
```java
 // 初始化ADSuyi广告SDK
ADSuyiSdk.getInstance().init(this, new ADSuyiInitConfig.Builder()
		// 设置APPID
    .appId(ADSuyiDemoConstant.APP_ID)
    // 可以通过设置该值手动关闭或开启浮窗广告，默认开启（服务端没有配置开启也不会有浮窗广告）
    .openFloatingAd(false)
    // 如果开了浮窗广告，可设置不展示浮窗广告的界面，第一个参数为是否开启默认不展示的页面（例如:激励视频播放页面），第二可变参数为自定义不展示的页面
    .floatingAdBlockList(false, "cn.admobiletop.adsuyidemo.activity.ad.ADSuyiInitAndLoadSplashAdActivity")
    .build());
```

2.获取浮窗广告
```java
// 获取并展示广告
ADSuyiInnerNoticeManager.getInstance().loadInnerNoticeAd(InnerNoticeActivity.this, new ADSuyiInnerNoticeListener() {
    @Override
    public void onAdFailed(String msg) {
        // 浮窗广告获取失败回调
    }

    @Override
    public void onAdSuccess() {
        // 浮窗广告展示成功回调
    }

    @Override
    public void onAdDelay(int i) {
        // 浮窗广告距离下一次可展示剩余时长
    }
});
```

### 6.10 备注

具体的接入代码和流程，请参考Demo



## 7. 常见问题和错误调试

> [常见问题和错误调试及错误码](https://doc.admobile.top/ssp/2jieru/3-ADSuyiSDK_And_Error_code.html#%E9%94%99%E8%AF%AF%E7%A0%81)

如果以上地址无法跳转，请访问[备用地址](http://doc.admobile.top/ssp/)，下拉找到**Android集成常见问题**



## 8.商务合作

邮箱 : yuxingcao@admobile.top