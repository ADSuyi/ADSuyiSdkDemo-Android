package cn.admobiletop.adsuyidemo.util;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ciba
 * @description 描述
 * @date 2018/10/29
 */
public class StringUtil {
    public static void setCustomKeyWordClickSpan(TextView tvContent, String text, KeyWordClick...keyWordClicks) {
        if (text == null || keyWordClicks == null || tvContent == null) {
            return;
        }
        SpannableString spannableString = new SpannableString(text);
        for (KeyWordClick keyWordClick : keyWordClicks) {
            if (keyWordClick.getKeyWord() == null) {
                continue;
            }
            Pattern p = Pattern.compile(keyWordClick.getKeyWord());
            Matcher m = p.matcher(spannableString);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                spannableString.setSpan(new CustomKeyWordClickSpan(keyWordClick.getHighLightColor(), keyWordClick.getClickListener()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        tvContent.setClickable(true);
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvContent.setText(spannableString);
    }

    public static class KeyWordClick{
        private final String mKeyWord;
        private final int mHighLightColor;
        private final View.OnClickListener mClickListener;

        public KeyWordClick(String mKeyWord, int mHighLightColor, View.OnClickListener mClickListener) {
            this.mKeyWord = mKeyWord;
            this.mHighLightColor = mHighLightColor;
            this.mClickListener = mClickListener;
        }

        String getKeyWord() {
            return mKeyWord;
        }

        int getHighLightColor() {
            return mHighLightColor;
        }

        View.OnClickListener getClickListener() {
            return mClickListener;
        }
    }

    private static class CustomKeyWordClickSpan extends ClickableSpan {
        private final int mHighLightColor;
        private boolean mUnderLine = false;
        private View.OnClickListener mClickListener;

        CustomKeyWordClickSpan(@ColorInt int highLightColor
                , @NonNull View.OnClickListener listener) {
            this.mHighLightColor = highLightColor;
            this.mClickListener = listener;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (mClickListener != null) {
                mClickListener.onClick(widget);
            }
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(mHighLightColor);
            ds.setUnderlineText(mUnderLine);
        }
    }
}
