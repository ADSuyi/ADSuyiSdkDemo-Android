## 万维错误码

| Error                  | Message                | 说明                                                         |
| :--------------------- | :--------------------- | :----------------------------------------------------------- |
| NOT_INITIALIZED        | SDK 尚未初始化         | 请开发者在调用 SDK 相关接口时务必先进行 SDK 初始化           |
| INITIALIZE_FAILED      | 初始化失败             | 请根据文档检查相关配置是否正确                               |
| INVALID_ARGUMENT       | 参数错误               | 检查调用初始化接口或播放接口时传入的参数以及参数格式是否正确 |
| VIDEO_PLAYER_ERROR     | 播放失败               | 请检查 Manifest 配置,请检查 SDK 是否能初始化成功             |
| INIT_SANITY_CHECK_FAIL | 参数错误               | 参数错误                                                     |
| AD_BLOCKER_DETECTED    | 广告插件拦截           | 请检查设备是否存在广告拦截插件阻止了 SDK 初始化              |
| FILE_IO_ERROR          | SDK 无法读取或写入文件 | 请查看文件读写权限                                           |
| DEVICE_ID_ERROR        | 未知的设备标识符       | 请确定设备是否合法                                           |
| SHOW_ERROR             | 播放广告错误           | 在没有成功请求到广告数据（比如未初始化或初始化失败）时就调用广告播放接口 则会通知此错误信息 |
| INTERNAL_ERROR         | SDK 内部发生异常       | 请联系我们                                                   |
| CAMPAIGN_NO_FILL       | 请求广告失败           | 当前设备和 APP 所在区域等综合情况未符合我们的广告条件（注：此情况下 SDK 会每隔十分钟重试请求广告） |