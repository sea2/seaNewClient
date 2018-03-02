package com.lhy.mvp.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.lhy.mvp.R;


/**
 * Created by 171842474@qq.com
 */
@SuppressLint("AppCompatCustomView")
public class EditTextWithDel extends EditText {
    private Drawable clearImg;
    private Context context;
    private int delSrc;

    public EditTextWithDel(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithDel);
        delSrc = ta.getResourceId(R.styleable.EditTextWithDel_delsrc, R.drawable.del_s);
        ta.recycle();

        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithDel);
        delSrc = ta.getResourceId(R.styleable.EditTextWithDel_delsrc, R.drawable.del_s);
        ta.recycle();
        init();
    }


    private void init() {
        setSingleLine();
        boolean isEnable = isEnabled();
        if (isEnable) {
            clearImg = ContextCompat.getDrawable(context, delSrc);

            clearImg.setBounds(0, 0, clearImg.getIntrinsicWidth(), clearImg.getIntrinsicHeight());

            addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    setDrawable();
                }
            });

            setDrawable();
        }
    }

    //设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, clearImg, null);
//            setSelection(length());
        }
        requestLayout();
        postInvalidate();
    }

    //设置删除图片
    public void setDrawable(Drawable drawable) {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            //            setSelection(length());
        }
        requestLayout();
        postInvalidate();
    }

 /*   // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clearImg != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY)) setText("");
        }
        return super.onTouchEvent(event);
    }
*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 判断触碰是否结束
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 判断所触碰的位置是否为清除的按钮
            if (event.getX() > (getWidth() - getTotalPaddingRight())
                    && event.getX() < (getWidth() - getPaddingRight())) {
                // 将editText里面的内容清除
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            setDrawable();
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}