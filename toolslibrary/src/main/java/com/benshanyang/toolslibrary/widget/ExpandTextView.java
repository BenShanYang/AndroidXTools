package com.benshanyang.toolslibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.benshanyang.toolslibrary.R;

/**
 * @ClassName: ExpandTextView
 * @Description: 可展开折叠的文字展示控件
 * @Author: YangKuan
 * @Date: 2020/11/11 14:54
 */
public class ExpandTextView extends FrameLayout {

    private int initWidth = 0;// TextView可展示宽度
    private TextTooLong textTooLong;//文字是否超过规定的最大行数监听器
    private ExpandStateListener expandStateListener;//文字展开收起监听器

    private TextView textView = null;
    private String originText = "";//文字内容
    private float textSize = -1;//文字大小
    @ColorInt
    private int textColor = 0xFF333333;//文字颜色
    private int maxLines = Integer.MAX_VALUE;// TextView最大行数
    private float lineSpacingExtra = -1;//行间距
    private String iconFontAssetsPath = "expand_icon_font/ic_expand_iconfont.ttf";//文字图标库的路径

    //展开文字
    private String expandText = "";
    //展开文字颜色
    @ColorInt
    private int expandTextColor = 0xFF333333;
    //展开文字字号
    private float expandTextSize = -1;
    //展开文字是否带有下划线
    private boolean expandUnderline = false;
    //展开小图标
    private String expandIcon = "";
    //展开小图标位置
    private int expandIconLocation = 0;
    //折叠文字
    private String foldText = "";
    //折叠文字颜色
    @ColorInt
    private int foldTextColor = 0xFF333333;
    //折叠文字字号
    private float foldTextSize = -1;
    //折叠文字是否带有下划线
    private boolean foldUnderline = false;
    //折叠小图标
    private String foldIcon = "";
    //折叠文字图标位置
    private int foldIconLocation = 0;

    private String TEXT_EXPAND = "展开";
    private String TEXT_CLOSE = "收起";

    public ExpandTextView(Context context) {
        super(context);
        initView(context);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }

    private void initView(Context context) {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView = new TextView(context);
        textView.setLayoutParams(params);
        addView(textView, params);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView, 0, 0);
        if (typedArray != null) {
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.ExpandTextView_android_text) {
                    //文字
                    originText = typedArray.getString(attr);
                } else if (attr == R.styleable.ExpandTextView_android_textColor) {
                    //文字颜色
                    textColor = typedArray.getColor(attr, 0xFF333333);
                } else if (attr == R.styleable.ExpandTextView_android_textSize) {
                    //文字大小
                    textSize = typedArray.getDimension(attr, -1);
                } else if (attr == R.styleable.ExpandTextView_android_maxLines) {
                    //最大行数
                    maxLines = typedArray.getInt(attr, Integer.MAX_VALUE);
                } else if (attr == R.styleable.ExpandTextView_android_lineSpacingExtra) {
                    //行间距
                    lineSpacingExtra = typedArray.getDimension(attr, -1);
                } else if (attr == R.styleable.ExpandTextView_iconFontPath) {
                    //展开收起图标库路径
                    iconFontAssetsPath = typedArray.getString(attr);
                } else if (attr == R.styleable.ExpandTextView_expandText) {
                    //展开文字
                    expandText = typedArray.getString(attr);
                } else if (attr == R.styleable.ExpandTextView_expandTextColor) {
                    //展开文字颜色
                    expandTextColor = typedArray.getColor(attr, 0xFF333333);
                } else if (attr == R.styleable.ExpandTextView_expandTextSize) {
                    //展开文字字号
                    expandTextSize = typedArray.getDimension(attr, -1);
                } else if (attr == R.styleable.ExpandTextView_expandTextUnderline) {
                    //展开文字是否带有下划线
                    expandUnderline = typedArray.getBoolean(attr, false);
                } else if (attr == R.styleable.ExpandTextView_expandIcon) {
                    //展开小图标
                    expandIcon = typedArray.getString(attr);
                } else if (attr == R.styleable.ExpandTextView_expandIconLocation) {
                    //展开小图标位置
                    expandIconLocation = typedArray.getInt(attr, 0);
                } else if (attr == R.styleable.ExpandTextView_foldText) {
                    //折叠文字
                    foldText = typedArray.getString(attr);
                } else if (attr == R.styleable.ExpandTextView_foldTextColor) {
                    //折叠文字颜色
                    foldTextColor = typedArray.getColor(attr, 0xFF333333);
                } else if (attr == R.styleable.ExpandTextView_foldTextSize) {
                    //折叠文字字号
                    foldTextSize = typedArray.getDimension(attr, -1);
                } else if (attr == R.styleable.ExpandTextView_foldTextUnderline) {
                    //折叠文字是否带有下划线
                    foldUnderline = typedArray.getBoolean(attr, false);
                } else if (attr == R.styleable.ExpandTextView_foldIcon) {
                    //折叠小图标
                    foldIcon = typedArray.getString(attr);
                } else if (attr == R.styleable.ExpandTextView_foldIconLocation) {
                    //折叠文字图标位置
                    foldIconLocation = typedArray.getInt(attr, 0);
                }
            }
            typedArray.recycle();
        }

        if (textSize != -1) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        textView.setTextColor(textColor);
        /*
        参数add：增加的间距数值，对应android:lineSpacingExtra参数。
        参数mult：增加的间距倍数，对应android:lineSpacingMultiplier参数。
        最终结果：原行间距 lineHeight*mult+add
         */
        if (lineSpacingExtra != -1) {
            textView.setLineSpacing(lineSpacingExtra, 1f);
        }

        if (expandIconLocation == 0) {
            //图标在前边
            TEXT_EXPAND = (TextUtils.isEmpty(expandIcon) ? "" : expandIcon) + (TextUtils.isEmpty(expandText) ? "展开" : expandText);
        } else if (expandIconLocation == 1) {
            //图标在后边
            TEXT_EXPAND = (TextUtils.isEmpty(expandText) ? "展开" : expandText) + (TextUtils.isEmpty(expandIcon) ? "" : expandIcon);
        }

        if (foldIconLocation == 0) {
            //图标在前边
            TEXT_CLOSE = (TextUtils.isEmpty(foldIcon) ? "" : foldIcon) + (TextUtils.isEmpty(foldText) ? "收起" : foldText);
        } else if (foldIconLocation == 1) {
            //图标在后边
            TEXT_CLOSE = (TextUtils.isEmpty(foldText) ? "收起" : foldText) + (TextUtils.isEmpty(foldIcon) ? "" : foldIcon);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = initWidth(widthMeasureSpec);
        if (initWidth != width) {
            initWidth = width;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setText(CharSequence text) {
        if (text != null && !TextUtils.equals(text, originText)) {
            originText = text.toString();
            post(new Runnable() {
                @Override
                public void run() {
                    if (initWidth != 0) {
                        textView.setMaxLines(maxLines);
                        setCloseText();
                    }
                }
            });
        }
    }

    /**
     * 判断文字是否超过最大行数限制
     *
     * @param maxLines 最大行数
     * @return 返回值为-1说明字符串没有超过最大限制，反之则超过最大行数限制
     */
    private int computeTextNum(int maxLines) {
        int subIndex = -1;// true 不需要展开收起功能， false 需要展开收起功能
        String workingText = new StringBuilder(originText).toString();
        if (maxLines != -1) {
            Layout layout = createWorkingLayout(workingText);
            if (layout.getLineCount() > maxLines) {
                //获取一行显示字符个数，然后截取字符串数
                // 收起状态原始文本截取展示的部分
                workingText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim();
                String showText = originText.substring(0, layout.getLineEnd(maxLines)).trim() + "...   " + TEXT_EXPAND;
                Layout layout2 = createWorkingLayout(showText);
                // 对workingText进行-1截取，直到展示行数==最大行数，并且添加 SPAN_CLOSE 后刚好占满最后一行
                while (layout2.getLineCount() > maxLines) {
                    int lastSpace = workingText.length() - 1;
                    if (lastSpace == -1) {
                        break;
                    }
                    subIndex = lastSpace;
                    workingText = workingText.substring(0, lastSpace);
                    layout2 = createWorkingLayout(workingText + "...   " + TEXT_EXPAND);
                }
            }
        }
        return subIndex;
    }

    private void setCloseText() {
        int subIndex = computeTextNum(maxLines);
        boolean isShowAll = subIndex != -1;

        String workingText = new StringBuilder(originText).toString();
        if (isShowAll) {
            workingText = workingText.substring(0, subIndex);
            workingText = workingText + "... ";
        }

        if (isShowAll) {
            //文字超过指定行数
            String content = workingText + TEXT_EXPAND;
            SpannableString SPAN_CLOSE = new SpannableString(content);
            SPAN_CLOSE.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint textPaint) {
                    //设置展开文字样式
                    if (expandTextColor != -1) {
                        textPaint.setColor(expandTextColor);
                    }
                    if (expandTextSize != -1) {
                        textPaint.setTextSize(dp2px(expandTextSize));
                    }
                    textPaint.setUnderlineText(expandUnderline);
                    if (!TextUtils.isEmpty(iconFontAssetsPath)) {
                        textPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), iconFontAssetsPath));
                    }
                }

                @Override
                public void onClick(@NonNull View widget) {
                    textView.setMaxLines(Integer.MAX_VALUE);
                    setExpandText(originText);

                    //展开
                    if (expandStateListener != null) {
                        expandStateListener.expandText(true);
                    }
                }
            }, workingText.length(), content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            textView.setText(SPAN_CLOSE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            //注意，设置了ClickableSpan后，点击将会触发TextView在该区域的高亮效果，即一个背景变化，我们手动去除
            textView.setHighlightColor(Color.TRANSPARENT);

        } else {
            //文字没有超过指定行数
            textView.setText(workingText);
        }

        if (textTooLong != null) {
            textTooLong.tooLong(isShowAll);
        }
    }

    private void setExpandText(String text) {
        String content;
        String textStr;

        Layout layout1 = createWorkingLayout(text);
        Layout layout2 = createWorkingLayout(text + TEXT_CLOSE);

        // 展示全部原始内容时 如果 TEXT_CLOSE 需要换行才能显示完整，则直接将TEXT_CLOSE展示在下一行
        if (layout2.getLineCount() > layout1.getLineCount()) {
            textStr = text + "\n";
        } else {
            textStr = text + " ";
        }
        content = textStr + TEXT_CLOSE;

        SpannableString SPAN_EXPAND = new SpannableString(content);
        SPAN_EXPAND.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint textPaint) {
                //收起文字样式
                if (foldTextColor != -1) {
                    textPaint.setColor(foldTextColor);
                }
                if (foldTextSize != -1) {
                    textPaint.setTextSize(dp2px(foldTextSize));
                }
                textPaint.setUnderlineText(foldUnderline);
                if (!TextUtils.isEmpty(iconFontAssetsPath)) {
                    textPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), iconFontAssetsPath));
                }
            }

            @Override
            public void onClick(@NonNull View widget) {
                textView.setMaxLines(maxLines);
                setCloseText();

                //收起
                if (expandStateListener != null) {
                    expandStateListener.expandText(false);
                }
            }
        }, textStr.length(), content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        textView.setText(SPAN_EXPAND);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //注意，设置了ClickableSpan后，点击将会触发TextView在该区域的高亮效果，即一个背景变化，我们手动去除
        textView.setHighlightColor(Color.TRANSPARENT);
    }

    //返回textview的显示区域的layout，该textview的layout并不会显示出来，只是用其宽度来比较要显示的文字是否过长
    @SuppressLint("ObsoleteSdkInt")
    private Layout createWorkingLayout(@NonNull String workingText) {
        //int width = getContext().getResources().getDisplayMetrics().widthPixels;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return new StaticLayout(workingText, textView.getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                    Layout.Alignment.ALIGN_NORMAL, textView.getLineSpacingMultiplier(), textView.getLineSpacingExtra(), false);
        } else {
            return new StaticLayout(workingText, textView.getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        }
    }

    /**
     * 获取测量大小
     */
    private int initWidth(int measureSpec) {
        int result;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == View.MeasureSpec.EXACTLY) {
            //match_parent或确切值
            //确切大小,所以将得到的尺寸给view
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            //wrap_content
            //如果View的宽高模式为AT_MOST （包裹内容），最终宽高也是填充父控件
            result = Math.max(0, specSize);
        } else {
            result = 0;
        }
        return result;
    }

    /**
     * 设置文字超过最大长度监听器
     *
     * @param textTooLong
     */
    public void addTextTooLong(TextTooLong textTooLong) {
        this.textTooLong = textTooLong;
    }

    /**
     * 设置文字展开收起监听
     *
     * @param expandStateListener
     */
    public void addExpandStateListener(ExpandStateListener expandStateListener) {
        this.expandStateListener = expandStateListener;
    }

    /**
     * 文字是否超过规定的最大行数监听器
     */
    public interface TextTooLong {
        /**
         * 文字是否太长
         *
         * @param tooLong true文字过长超过最大行数 false文字没有超过指定行数
         */
        void tooLong(boolean tooLong);
    }

    /**
     * 文字收起展开监听
     */
    public interface ExpandStateListener {
        /**
         * 文字是否展开
         *
         * @param expand true展开 false收起
         */
        void expandText(boolean expand);
    }

    /**
     * 设置文字大小
     *
     * @param dipValue
     * @return
     */
    private int dp2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private float sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * fontScale + 0.5f;
    }

}
