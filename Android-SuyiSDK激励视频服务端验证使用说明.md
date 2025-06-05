## SuyiSDK 激励视频服务端验证使用说明

### 支持平台

- 优量汇
- 穿山甲
- 快手
- 百度
- 汇量



### 演示代码

> 由于不同平台，入参和激励回调返回参数有差异，请按照文档说明进行调用。设置服务端验证回调URL以及校验过程省略，请参考具体平台官方文档

注意，每个渠道都有些许不同，需要按文档进行对接，否则可能出现无服务端回调的情况，如：快手

####1.优量汇平台使用模板

#### 优量汇服务端验证说明

当选择需要服务端验证时，开发者需要按照以上接口进行开发，并在开发者平台上填写回调 URL。服务端回调是指在用户看完视频达到奖励条件时，优量汇服务端会向开发者服务端发送一个验证请求，同时客户端会给出 onReward 回调，开发者根据回调进行奖励发放，**因为奖励回调和服务端验证请求是同时发送的，开发者后台收到验证请求可能会有延迟或网络原因上的失败，开发者需要平衡用户体验与奖励验证。**

```java
   /**
     * 优量汇平台激励视频服务验证，支持透传的参数为用户ID(String)和自定义数据(String)
     * 参数透传对应优量汇SDK ServerSideVerificationOptions.setCustomData(String customData) 
     * 和 ServerSideVerificationOptions.setUserId(String userId)
     */
    private void initAd() {
        // 创建激励视频广告实例
        rewardVodAd = new ADSuyiRewardVodAd(this);
      
        //对应优量汇SDK -> ServerSideVerificationOptions.setUserId(String userId)
        ADSuyiRewardExtra adSuyiRewardExtra = new ADSuyiRewardExtra("设置自定义用户ID");
      
        //对应优量汇SDK -> ServerSideVerificationOptions.setCustomData(String customData) 
        adSuyiRewardExtra.setCustomData("设置自定义额外数据");
      
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                .rewardExtra(adSuyiRewardExtra)
                // 设置视频类广告是否静音
                .setVideoWithMute(ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE)
                .build();
        rewardVodAd.setLocalExtraParams(extraParams);
        // 设置激励视频广告监听
        rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {
            ...
            
            @Override
            public void onReward(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                //优量汇平台获取服务端验证返回参数方法

                String platformFlag = "gdt"; //优量汇平台标识
                if (platformFlag.equals(adSuyiRewardVodAdInfo.getPlatform())) {
                    Map<String, Object> rewardMap = adSuyiRewardVodAdInfo.getRewardMap();
                    if (rewardMap != null) {
                        //获取服务端验证的唯一 ID
                        //注意优量汇奖励回调和服务端验证请求是同时发送的，如果需要根据TRANS_ID验证结果需要处理延迟同步问题
                        if(rewardMap.get(ServerSideVerificationOptions.TRANS_ID) != null){
                          String transId = (String)rewardMap.get(ServerSideVerificationOptions.TRANS_ID)
                        }
                        
                    }
                }
            }
            
            ...
        });
    }
```



#### 2.穿山甲平台使用方法

```java
		/**
     * 穿山甲平台激励视频服务验证，支持透传的参数为用户ID(String)和自定义数据(String)
     * 参数透传对应穿山甲SDK AdSlot.setUserID() 和 AdSlot.setMediaExtra()
     */
    private void initAd() {
        // 创建激励视频广告实例
        rewardVodAd = new ADSuyiRewardVodAd(this);
        
        //对应穿山甲SDK -> AdSlot.setUserID()
        ADSuyiRewardExtra adSuyiRewardExtra = new ADSuyiRewardExtra("设置自定义用户ID");
      
        //对应穿山甲SDK -> AdSlot.setMediaExtra()
        adSuyiRewardExtra.setCustomData("设置自定义附带参数");
      
        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                .rewardExtra(adSuyiRewardExtra)
                // 设置视频类广告是否静音
                .setVideoWithMute(ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE)
                .build();
        rewardVodAd.setLocalExtraParams(extraParams);
        // 设置激励视频广告监听
        rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {
            ...

            @Override
            public void onReward(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                //穿山甲平台获取服务端验证返回参数方法，参数以穿山甲SDK4400版本新增onRewardArrived回调参数为准
                // onReward 回调只是视频播放状态的一个结果或者是开发者返回的结果，不代表此次播放是否计费等广告业务指标。

                String platformFlag = "toutiao"; //穿山甲平台标识
                if (platformFlag.equals(adSuyiRewardVodAdInfo.getPlatform())) {
                    Map<String, Object> rewardMap = adSuyiRewardVodAdInfo.getRewardMap();
                    if (rewardMap != null) {
                        //奖励是否有效，返回值是开发者服务端验证的结果
                        boolean isRewardValid = (boolean) rewardMap.get("isRewardValid");

                        // 奖励类型 包括：
                        // 基础奖励 int REWARD_TYPE_DEFAULT = 0、
                        // 进阶奖励-互动 int REWARD_TYPE_INTERACT = 1、
                        // 进阶奖励-超过30s的视频播放完成 int REWARD_TYPE_VIDEO_COMPLETE = 2。
                        if(rewardMap.get("rewardType") != null){
                          int rewardType = (int) rewardMap.get("rewardType");
                        }
                        
                        //奖励的额外参数，包括：
                        // errorCode： 服务器验证错误码、
                        // errorMsg：服务器验证错误信息、
                        // RewardName： 奖励名称、
                        // RewardAmount：奖励数量、
                        // RewardProPose：建议奖励数量。
                       if(rewardMap.get("extraInfo") != null){
                         Bundle extraInfo = (Bundle) rewardMap.get("extraInfo");
                       }
                    }
                }
            }

            ...
        });
    }
```

#### 3.快手平台使用方法

```java
		/**
     * 快手平台激励视频服务验证，支持透传的参数为setRewardCallbackExtraData(Map)
     * 参数透传对应快手SDK builder.rewardCallbackExtraData(rewardCallbackExtraData);
     */
    private void initAd() {
        // 创建激励视频广告实例
        rewardVodAd = new ADSuyiRewardVodAd(this);
        //快手平台构造函数参数不使用，可以传空字符串
        ADSuyiRewardExtra adSuyiRewardExtra = new ADSuyiRewardExtra("");

        // 激励视频服务端回调的参数设置
        Map<String, String> rewardCallbackExtraData = new HashMap<>();
        // 开发者系统中的⽤户id，会在请求客户的回调url中带上，注意"thirdUserId为固定写法
        rewardCallbackExtraData.put("thirdUserId", "设置自定义用户ID");
        // 开发者⾃定义的附加参数，会在请求客户的回调url中带上，注意"extraData"为固定写法
        rewardCallbackExtraData.put("extraData", "设置自定义附带参数");
      
        //对应快手SDK -> builder.rewardCallbackExtraData(rewardCallbackExtraData);
        adSuyiRewardExtra.setRewardCallbackExtraData(rewardCallbackExtraData);

        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                .rewardExtra(adSuyiRewardExtra)
                // 设置视频类广告是否静音
                .setVideoWithMute(ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE)
                .build();
        rewardVodAd.setLocalExtraParams(extraParams);
        // 设置激励视频广告监听
        rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {
            ...

            @Override
            public void onReward(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                //注意快手平台无回调参数返回
            }
            ...
        });
    }
```

#### 4.百度平台使用方法

#### 百度服务端奖励验证接入说明

服务端奖励验证用于帮助媒体判定是否提供奖励给观看广告的用户，当用户成功看完广告时，会向媒体在union平台中配置的验证接口发送验证请求，并携带广告和媒体自定义参数信息，由媒体来判断此次观看是否给用户发放奖励。百青藤服务端不会主动干预奖励的成功与失败。

```java
/**
     * 百度平台激励视频服务验证，支持透传的参数为自定义用户ID(String)和自定义参数(String)
     * 参数透传对应百度SDK RewardVideoAd.setUserId() 和 RewardVideoAd.setExtraInfo()
     */
    private void initAd() {
        // 创建激励视频广告实例
        rewardVodAd = new ADSuyiRewardVodAd(this);

        //对应百度SDK -> RewardVideoAd.setUserId()
        ADSuyiRewardExtra adSuyiRewardExtra = new ADSuyiRewardExtra("设置自定义用户ID");

        //对应百度SDK -> RewardVideoAd.setExtraInfo()
        adSuyiRewardExtra.setCustomData("设置自定义参数");

        // 创建额外参数实例
        ADSuyiExtraParams extraParams = new ADSuyiExtraParams.Builder()
                .rewardExtra(adSuyiRewardExtra)
                // 设置视频类广告是否静音
                .setVideoWithMute(ADSuyiDemoConstant.REWARD_AD_PLAY_WITH_MUTE)
                .build();
        rewardVodAd.setLocalExtraParams(extraParams);
        // 设置激励视频广告监听
        rewardVodAd.setListener(new ADSuyiRewardVodAdListener() {
            ...

            @Override
            public void onReward(ADSuyiRewardVodAdInfo adSuyiRewardVodAdInfo) {
                String platformFlag = "baidu"; //百度平台标识
                if (platformFlag.equals(adSuyiRewardVodAdInfo.getPlatform())) {
                    Map<String, Object> rewardMap = adSuyiRewardVodAdInfo.getRewardMap();
                    if (rewardMap != null) {
                        //奖励是否有效，返回值是开发者服务端验证的结果
                        if(rewardMap.get("rewardVerify") != null){
                          boolean rewardVerify = (boolean) rewardMap.get("rewardVerify");
                        }
                        
                    }
                }
            }

            ...
        });
    }
```

