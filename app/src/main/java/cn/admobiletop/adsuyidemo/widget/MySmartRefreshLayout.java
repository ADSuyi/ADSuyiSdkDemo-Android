package cn.admobiletop.adsuyidemo.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author ciba
 * @date 2020/4/01
 * @description
 */

public class MySmartRefreshLayout extends SmartRefreshLayout {
    public static final int TYPE_FRESH = 0;
    public static final int TYPE_LOAD_MORE = 1;

    public MySmartRefreshLayout(Context context) {
        this(context, null);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //显示下拉高度/手指真实下拉高度=阻尼效果
        setDragRate(0.5f);
        //是否启用越界回弹
        setEnableOverScrollBounce(true);
        //回弹动画时长（毫秒）
        setReboundDuration(300);
        //是否启用越界拖动（仿苹果效果）
        setEnableOverScrollDrag(true);
        // 设置默认的刷新布局
        setRefreshHeader(new ClassicsHeader(getContext()));
        // 设置默认的加载更多布局
        setRefreshFooter(new ClassicsFooter(getContext()));
        //是否在刷新的时候禁止列表的操作
        setDisableContentWhenRefresh(false);
        //是否在加载的时候禁止列表的操作
        setDisableContentWhenLoading(false);
    }

    /**
     * 结束刷新或加载更多
     *
     * @param type    ：当前获取数据的类型 0 - 刷新 1 - 加载更多
     * @param success ：获取数据是否成功
     */
    public void finish(int type, boolean success, boolean noMoreData) {
        if (TYPE_FRESH == type) {
            finishRefresh(success);
        } else if (TYPE_LOAD_MORE == type) {
            finishLoadMore(0, success, noMoreData);
        }
    }
}
