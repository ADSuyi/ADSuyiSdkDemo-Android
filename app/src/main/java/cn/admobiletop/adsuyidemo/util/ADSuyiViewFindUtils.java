package cn.admobiletop.adsuyidemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author songzi
 * @date 2022/2/21
 */
public class ADSuyiViewFindUtils {


    public static View tryGetTheFrontView(Activity targetActivity) {
        try {
            WindowManager windowManager = targetActivity.getWindowManager();
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                Field mWindowManagerField = Class.forName("android.view.WindowManagerImpl$CompatModeWrapper").getDeclaredField("mWindowManager");
                mWindowManagerField.setAccessible(true);
                Field mViewsField = Class.forName("android.view.WindowManagerImpl").getDeclaredField("mViews");
                mViewsField.setAccessible(true);
                List<View> views = Arrays.asList((View[]) mViewsField.get(mWindowManagerField.get(windowManager)));
                for (int i = views.size() - 1; i >= 0; i--) {
                    View targetView = getTargetDecorView(targetActivity, views.get(i));
                    if (targetView != null) {
                        return targetView;
                    }
                }
            }
            Field mGlobalField = ADSuyiClassFindUtils.getDeclaredField(ADSuyiClassFindUtils.forName("android.view.WindowManagerImpl"), "mGlobal");
            mGlobalField.setAccessible(true);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                Field mViewsField = Class.forName("android.view.WindowManagerGlobal").getDeclaredField("mViews");
                mViewsField.setAccessible(true);
                List<View> views = (List<View>) mViewsField.get(mGlobalField.get(windowManager));
                for (int i = views.size() - 1; i >= 0; i--) {
                    View targetView = getTargetDecorView(targetActivity, views.get(i));
                    if (targetView != null) {
                        return targetView;
                    }
                }
            } else {
                Field mRootsField = ADSuyiClassFindUtils.getDeclaredField(ADSuyiClassFindUtils.forName("android.view.WindowManagerGlobal"), "mRoots");
                mRootsField.setAccessible(true);
                List viewRootImpls;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    viewRootImpls = (List) mRootsField.get(mGlobalField.get(windowManager));
                } else {
                    viewRootImpls = Arrays.asList((Object[]) mRootsField.get(mGlobalField.get(windowManager)));
                }
                for (int i = viewRootImpls.size() - 1; i >= 0; i--) {
                    Class clazz = ADSuyiClassFindUtils.forName("android.view.ViewRootImpl");
                    Object object = viewRootImpls.get(i);
                    Field mWindowAttributesField = ADSuyiClassFindUtils.getDeclaredField(clazz, "mWindowAttributes");
                    mWindowAttributesField.setAccessible(true);
                    Field mViewField = ADSuyiClassFindUtils.getDeclaredField(clazz, "mView");
                    mViewField.setAccessible(true);
                    View decorView = (View) mViewField.get(object);
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) mWindowAttributesField.get(object);
                    if (layoutParams.getTitle().toString().contains(targetActivity.getClass().getName())
                            || getTargetDecorView(targetActivity, decorView) != null) {
                        return decorView;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            //Accessing hidden field Landroid/view/WindowManagerImpl;->mGlobal:Landroid/view/WindowManagerGlobal; (light greylist, reflection)
            //Accessing hidden field Landroid/view/WindowManagerGlobal;->mRoots:Ljava/util/ArrayList; (light greylist, reflection)
            //Accessing hidden field Landroid/view/ViewRootImpl;->mWindowAttributes:Landroid/view/WindowManager$LayoutParams; (dark greylist, reflection)
            //Accessing hidden field Landroid/view/ViewRootImpl;->mView:Landroid/view/View; (light greylist, reflection)
        }
        return targetActivity.getWindow().peekDecorView();
    }

    private static View getTargetDecorView(Activity targetActivity, View decorView) {
        View targetView = null;
        Context context = decorView.getContext();
        if (context == targetActivity) {
            targetView = decorView;
        } else {
            while (context instanceof ContextWrapper && !(context instanceof Activity)) {
                Context baseContext = ((ContextWrapper) context).getBaseContext();
                if (baseContext == null) {
                    break;
                }
                if (baseContext == targetActivity) {
                    targetView = decorView;
                    break;
                }
                context = baseContext;
            }
        }
        return targetView;
    }


}
