package com.benshanyang.toolslibrary.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.benshanyang.toolslibrary.R;
import com.benshanyang.toolslibrary.callback.TextWatchListener;
import com.benshanyang.toolslibrary.drawable.EditableTextViewDrawable;
import com.benshanyang.toolslibrary.utils.ResUtils;
import com.benshanyang.toolslibrary.utils.TextUtils;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * 类描述: 可以编辑的自定义TextView </br>
 * 时间: 2019/4/4 11:28
 *
 * @author YangKuan
 * @version 1.0.0
 * @since
 */
public class EditableTextView extends LinearLayout {

    private TextView textView;
    private EditText editText;

    @ColorInt
    private int topBorderColor = Color.TRANSPARENT;//设置上边框的颜色
    @ColorInt
    private int bottomBorderColor = Color.TRANSPARENT;//设置下边框的颜色
    @ColorInt
    private int backgroundColor = Color.TRANSPARENT;//背景色
    private int maxLength = Integer.MAX_VALUE;//最大输入长度
    private int defaultTextSize;//默认字体大小
    private int titleTextSize;//标题文字大小
    private int contentTextSize;//内容文字大小
    private float topBorderWidth = 0f;//上边框的粗细
    private float bottomBorderWidth = 0f;//下边框的粗细
    private float topBorderLeftSpace = 0f;//上边框距离左侧的距离
    private float topBorderRightSpace = 0f;//上边框距离右侧的距离
    private float bottomBorderLeftSpace = 0f;//下边框距离左侧的距离
    private float bottomBorderRightSpace = 0f;//下边框距离右侧的距离
    private float titleDrawablePadding = 0f;//标题和Icon的间距
    private float titleContentSpace = 0f;//标题和内容的间距
    private boolean editable = true;//输入框是否可以编辑
    private int contentTextId = -1;
    private int hintId = -1;
    private CharSequence titleText = "";//标题文字
    private CharSequence contentText = "";//内容文字
    private CharSequence hint = "";//提示文字
    private CharSequence digits = "";//输入框的过滤条件
    private Drawable titleIcon;//标题的Icon
    private ColorStateList titleTextColor;//标题文字颜色
    private ColorStateList contentTextColor;//内容文字颜色
    private ColorStateList textColorHint;//提示文字颜色
    private EditableTextViewDrawable drawable;//背景样式

    private InputFilter inputFilter;//过滤器
    private TextWatchListener textWatchListener;//输入监听
    private OnFocusChangeListener onFocusChangeListener;//输入框的焦点改变监听

    public EditableTextView(Context context) {
        super(context);
        initWidget(context);
        initSettings(context);
    }

    public EditableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWidget(context);
        initAttrs(context, attrs);
        initSettings(context);
    }

    public EditableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWidget(context);
        initAttrs(context, attrs);
        initSettings(context);
    }

    private void initWidget(Context context) {
        defaultTextSize = sp2px(14f);
        titleTextSize = defaultTextSize;//标题文字大小
        contentTextSize = defaultTextSize;//内容文字大小

        FrameLayout frameLayout = new FrameLayout(context);
        textView = new TextView(context);
        editText = new EditText(context);
        frameLayout.addView(editText, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL));

        textView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        editText.setPadding(2, 0, 2, 0);
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);

        LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        LayoutParams editParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        textView.setLayoutParams(textParams);
        frameLayout.setLayoutParams(editParams);
        addView(textView);
        addView(frameLayout);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditableTextView, 0, 0);
        if (typedArray != null) {
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.EditableTextView_titleIcon) {
                    //标题的Icon
                    titleIcon = typedArray.getDrawable(attr);
                } else if (attr == R.styleable.EditableTextView_titleDrawablePadding) {
                    //标题和Icon的间距
                    titleDrawablePadding = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_titleTextSize) {
                    //标题文字字号
                    titleTextSize = typedArray.getDimensionPixelSize(attr, defaultTextSize);
                } else if (attr == R.styleable.EditableTextView_titleTextColor) {
                    //标题文字颜色
                    titleTextColor = typedArray.getColorStateList(attr);
                } else if (attr == R.styleable.EditableTextView_titleText) {
                    //标题文字
                    titleText = typedArray.getText(attr);
                } else if (attr == R.styleable.EditableTextView_contentTextSize) {
                    //内容文字字号
                    contentTextSize = typedArray.getDimensionPixelSize(attr, defaultTextSize);
                } else if (attr == R.styleable.EditableTextView_contentTextColor) {
                    //内容文字颜色
                    contentTextColor = typedArray.getColorStateList(attr);
                } else if (attr == R.styleable.EditableTextView_contentText) {
                    //内容文字
                    contentText = typedArray.getText(attr);
                    contentTextId = typedArray.getResourceId(attr, Resources.ID_NULL);
                } else if (attr == R.styleable.EditableTextView_hint) {
                    //提示文字
                    hint = typedArray.getText(attr);
                    hintId = typedArray.getResourceId(attr, Resources.ID_NULL);
                } else if (attr == R.styleable.EditableTextView_textColorHint) {
                    //提示文字颜色
                    textColorHint = typedArray.getColorStateList(attr);
                } else if (attr == R.styleable.EditableTextView_titleContentSpace) {
                    //标题和内容之间的间隙
                    titleContentSpace = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_digits) {
                    //输入框的过滤条件 只能是正则表达式
                    digits = typedArray.getText(attr);
                } else if (attr == R.styleable.EditableTextView_maxLength) {
                    //最大输入的长度
                    maxLength = typedArray.getInteger(attr, Integer.MAX_VALUE);
                } else if (attr == R.styleable.EditableTextView_backgroundColor) {
                    //背景色
                    backgroundColor = typedArray.getColor(attr, Color.TRANSPARENT);
                } else if (attr == R.styleable.EditableTextView_topBorderColor) {
                    //上边框颜色
                    topBorderColor = typedArray.getColor(attr, Color.TRANSPARENT);
                } else if (attr == R.styleable.EditableTextView_bottomBorderColor) {
                    //下边框颜色
                    bottomBorderColor = typedArray.getColor(attr, Color.TRANSPARENT);
                } else if (attr == R.styleable.EditableTextView_topBorderWidth) {
                    //上边框的粗细
                    topBorderWidth = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_bottomBorderWidth) {
                    //下边框的粗细
                    bottomBorderWidth = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_topBorderLeftSpace) {
                    //上边框距离最左边的距离
                    topBorderLeftSpace = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_topBorderRightSpace) {
                    //上边框距离最右边的距离
                    topBorderRightSpace = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_bottomBorderLeftSpace) {
                    //下边框距离最左边的距离
                    bottomBorderLeftSpace = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_bottomBorderRightSpace) {
                    //下边框距离最右边的距离
                    bottomBorderRightSpace = typedArray.getDimension(attr, 0f);
                } else if (attr == R.styleable.EditableTextView_editable) {
                    //输入框是否可以边界
                    editable = typedArray.getBoolean(attr, true);
                }
            }
            typedArray.recycle();
        }
    }

    private void initSettings(Context context) {
        inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (!TextUtils.isEmpty(charSequence)) {
                    String inputStr = charSequence.toString();
                    if (TextUtils.isEmpty(digits)) {
                        //如果没有匹配条件就不去匹配
                        return null;
                    } else if (inputStr.matches(digits.toString())) {
                        //设置了匹配条件 且符合匹配条件
                        return null;
                    } else {
                        //设置了匹配条件但不符合匹配条件
                        return "";
                    }
                } else {
                    //输入的为空则过滤掉
                    return "";
                }
            }
        };

        //标题的设置
        if (textView != null) {
            if (titleTextColor != null) {
                textView.setTextColor(titleTextColor);
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            TextUtils.setDrawable(textView, titleText, titleIcon, (int) titleDrawablePadding, com.benshanyang.toolslibrary.constant.Gravity.LEFT);
        }

        if (editText != null) {
            //设置过滤器
            if (contentTextColor != null) {
                editText.setTextColor(contentTextColor);
            }
            if (textColorHint != null) {
                editText.setHintTextColor(textColorHint);
            }

            if (!TextUtils.isEmpty(hint)) {
                editText.setHint(hint);
            }
            if (hintId != Resources.ID_NULL) {
                editText.setHint(hintId);
            }
            if (!TextUtils.isEmpty(contentText)) {
                editText.setText(contentText);
            }
            if (contentTextId != Resources.ID_NULL) {
                editText.setText(contentTextId);
            }
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
            editText.setEnabled(editable);
            ((FrameLayout.LayoutParams) editText.getLayoutParams()).leftMargin = (int) titleContentSpace;

            editText.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(maxLength)});

            editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //焦点改变的监听回调接口
                    if (onFocusChangeListener != null) {
                        onFocusChangeListener.onFocusChange(v, hasFocus);
                    }
                }
            });

            //输入内容改变时的监听
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (textWatchListener != null) {
                        textWatchListener.beforeTextChanged(s, start, count, after);
                    }
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (textWatchListener != null) {
                        textWatchListener.onTextChanged(s, start, before, count);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (textWatchListener != null) {
                        textWatchListener.afterTextChanged(s);
                    }
                }
            });
        }

        //设置背景和边框
        drawable = new EditableTextViewDrawable();
        drawable.setTopBorderWidth(topBorderWidth);
        drawable.setBottomBorderWidth(bottomBorderWidth);
        drawable.setTopBorderLeftSpace(topBorderLeftSpace);
        drawable.setTopBorderRightSpace(topBorderRightSpace);
        drawable.setBottomBorderLeftSpace(bottomBorderLeftSpace);
        drawable.setBottomBorderRightSpace(bottomBorderRightSpace);
        drawable.setBackgroundColor(backgroundColor);
        drawable.setBottomBorderColor(bottomBorderColor);
        drawable.setTopBorderColor(topBorderColor);
        setBackground(drawable);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();//获取控件的宽度
        int height = getMeasuredHeight();//获取控件的高度
        if (drawable != null) {
            drawable.setWidth(width);
            drawable.setHeight(height);
        }
    }

    /**
     * 设置输入监听
     *
     * @param textWatchListener 输入监听回调接口
     */
    public void addTextWatchListener(TextWatchListener textWatchListener) {
        this.textWatchListener = textWatchListener;
    }

    /**
     * 焦点改变监听
     *
     * @param onFocusChangeListener 焦点改变的回调接口
     */
    public void setClearEditTextFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    /**
     * 设置上边框的颜色
     *
     * @param topBorderColor 上边框的颜色色值
     */
    public void setTopBorderColor(int topBorderColor) {
        this.topBorderColor = topBorderColor;
        if (drawable != null) {
            drawable.setTopBorderColor(topBorderColor);
        }
    }

    /**
     * 设置下边框的颜色
     *
     * @param bottomBorderColor 下边框的颜色色值
     */
    public void setBottomBorderColor(int bottomBorderColor) {
        this.bottomBorderColor = bottomBorderColor;
        if (drawable != null) {
            drawable.setBottomBorderColor(bottomBorderColor);
        }
    }

    /**
     * 设置背景色
     *
     * @param backgroundColor 背景色值
     */
    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        if (drawable != null) {
            drawable.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置最大文字输入长度
     *
     * @param maxLength 最大输入字数
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        editText.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(maxLength)});
    }

    /**
     * 设置上边框的宽度(粗细)
     *
     * @param topBorderWidth 宽度值
     */
    public void setTopBorderWidth(float topBorderWidth) {
        this.topBorderWidth = topBorderWidth;
        if (drawable != null) {
            drawable.setTopBorderWidth(topBorderWidth);
        }
    }

    /**
     * 设置下边框的宽度(粗细)
     *
     * @param bottomBorderWidth 宽度值
     */
    public void setBottomBorderWidth(float bottomBorderWidth) {
        this.bottomBorderWidth = bottomBorderWidth;
        if (drawable != null) {
            drawable.setBottomBorderWidth(bottomBorderWidth);
        }
    }

    /**
     * 设置上边框距离最左边的距离
     *
     * @param topBorderLeftSpace 距离值
     */
    public void setTopBorderLeftSpace(float topBorderLeftSpace) {
        this.topBorderLeftSpace = topBorderLeftSpace;
        if (drawable != null) {
            drawable.setTopBorderLeftSpace(topBorderLeftSpace);
        }
    }

    /**
     * 设置上边框距离最右边的距离
     *
     * @param topBorderRightSpace 距离值
     */
    public void setTopBorderRightSpace(float topBorderRightSpace) {
        this.topBorderRightSpace = topBorderRightSpace;
        if (drawable != null) {
            drawable.setTopBorderRightSpace(topBorderRightSpace);
        }
    }

    /**
     * 设置下边框距离最左边的距离
     *
     * @param bottomBorderLeftSpace 距离值
     */
    public void setBottomBorderLeftSpace(float bottomBorderLeftSpace) {
        this.bottomBorderLeftSpace = bottomBorderLeftSpace;
        if (drawable != null) {
            drawable.setBottomBorderLeftSpace(bottomBorderLeftSpace);
        }
    }

    /**
     * 设置下边框距离最右边的距离
     *
     * @param bottomBorderRightSpace 距离值
     */
    public void setBottomBorderRightSpace(float bottomBorderRightSpace) {
        this.bottomBorderRightSpace = bottomBorderRightSpace;
        if (drawable != null) {
            drawable.setBottomBorderRightSpace(bottomBorderRightSpace);
        }
    }

    /**
     * 设置标题的Icon和DrawablePadding
     *
     * @param resId           图片的资源Id
     * @param drawablePadding padding值
     */
    public void setTitleIcon(@DrawableRes int resId, int drawablePadding) {
        this.titleIcon = ResUtils.getDrawable(getContext(), resId);
        if (textView != null) {
            TextUtils.setDrawable(textView, resId, drawablePadding, com.benshanyang.toolslibrary.constant.Gravity.LEFT);
        }
    }

    /**
     * 设置标题的Icon和DrawablePadding
     *
     * @param drawable        图片的资源
     * @param drawablePadding padding值
     */
    public void setTitleIcon(Drawable drawable, int drawablePadding) {
        this.titleIcon = drawable;
        if (textView != null) {
            TextUtils.setDrawable(textView, drawable, drawablePadding, com.benshanyang.toolslibrary.constant.Gravity.LEFT);
        }
    }

    /**
     * 设置标题的文字字号
     *
     * @param titleTextSize 字号
     */
    public void setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
        if (textView != null) {
            textView.setTextSize(COMPLEX_UNIT_PX, titleTextSize);
        }
    }

    /**
     * 设置内容文字的字号
     *
     * @param contentTextSize 内容文字的字号
     */
    public void setContentTextSize(int contentTextSize) {
        this.contentTextSize = contentTextSize;
        if (editText != null) {
            editText.setTextSize(COMPLEX_UNIT_PX, contentTextSize);
        }
    }

    /**
     * 设置标题和内容的距离
     *
     * @param titleContentSpace 距离值
     */
    public void setTitleContentSpace(float titleContentSpace) {
        this.titleContentSpace = titleContentSpace;
        if (editText != null) {
            ((FrameLayout.LayoutParams) editText.getLayoutParams()).leftMargin = (int) titleContentSpace;
        }
    }

    /**
     * 设置是否可编辑的状态
     *
     * @param editable true可编辑,false不可编辑
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
        if (editText != null) {
            editText.setEnabled(editable);
        }
    }

    /**
     * 设置标题文字
     *
     * @param titleText 标题文字
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        if (textView != null) {
            textView.setText(titleText);
        }
    }

    /**
     * 设置内容文字
     *
     * @param contentText 内容文字
     */
    public void setContentText(String contentText) {
        this.contentText = contentText;
        if (editText != null) {
            editText.setText(contentText);
        }
    }

    /**
     * 设置提示文字
     *
     * @param hint 提示文字
     */
    public void setHint(String hint) {
        this.hint = hint;
        if (editText != null) {
            editText.setHint(hint);
        }
    }

    /**
     * 设置过滤条件
     *
     * @param digits 过滤条件字符串,必须未正则表达式
     */
    public void setDigits(String digits) {
        this.digits = digits;
    }

    /**
     * 设置标题文字颜色
     *
     * @param titleTextColor 标题文字颜色
     */
    public void setTitleTextColor(@ColorInt int titleTextColor) {
        if (textView != null) {
            textView.setTextColor(titleTextColor);
        }
    }

    /**
     * 设置标题文字颜色
     *
     * @param titleTextColor 标题文字颜色
     */
    public void setTitleTextColor(ColorStateList titleTextColor) {
        if (textView != null && titleTextColor != null) {
            this.titleTextColor = titleTextColor;
            textView.setTextColor(titleTextColor);
        }
    }

    /**
     * 设置内容文字颜色
     *
     * @param contentTextColor 内容文字颜色
     */
    public void setContentTextColor(@ColorInt int contentTextColor) {
        if (editText != null) {
            editText.setTextColor(contentTextColor);
        }
    }

    /**
     * 设置内容文字颜色
     *
     * @param contentTextColor 内容文字颜色
     */
    public void setContentTextColor(ColorStateList contentTextColor) {
        if (editText != null && contentTextColor != null) {
            this.contentTextColor = contentTextColor;
            editText.setTextColor(contentTextColor);
        }
    }

    /**
     * 设置提示文字颜色
     *
     * @param textColorHint 提示文字颜色
     */
    public void setTextColorHint(@ColorInt int textColorHint) {
        if (editText != null) {
            editText.setHintTextColor(textColorHint);
        }
    }

    /**
     * 设置提示文字颜色
     *
     * @param textColorHint 提示文字颜色
     */
    public void setTextColorHint(ColorStateList textColorHint) {
        this.textColorHint = textColorHint;
        if (editText != null && textColorHint != null) {
            editText.setHintTextColor(textColorHint);
        }
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
