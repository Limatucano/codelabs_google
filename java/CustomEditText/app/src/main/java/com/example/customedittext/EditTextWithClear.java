package com.example.customedittext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextWithClear extends AppCompatEditText {
    Drawable mClearButtonImage;

    public EditTextWithClear(@NonNull Context context) {
        super(context);
        Log.d("TESTE", "construtor 1");
        init(context);
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("TESTE", "construtor 2");
        init(context);
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("TESTE", "construtor 3");
        init(context);
    }

    private void init(Context context){
        //mClearButtonImage = context.getResources().getDrawable(R.drawable.clear_opaque);
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(),R.drawable.clear_opaque,null);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TESTE", "mudando");
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("TESTE", "mudou");
            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)) {

                    float clearButtonStart; // Used for LTR languages
                    float clearButtonEnd;  // Used for RTL languages
                    boolean isClearButtonClicked = false;
                    // Detect the touch in RTL or LTR layout direction.
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        // If RTL, get the end of the button on the left side.
                        clearButtonEnd = mClearButtonImage.getIntrinsicWidth() + getPaddingStart();
                        // If the touch occurred before the end of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        // Layout is LTR.
                        // Get the start of the button on the right side.
                        clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                        // If the touch occurred after the start of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }
                    // Check for actions if the button is tapped.
                    if (isClearButtonClicked) {
                        // Check for ACTION_DOWN (always occurs before ACTION_UP).
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            // Switch to the black version of clear button.
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.clear, null);
                            showClearButton();
                        }
                        // Check for ACTION_UP.
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            // Switch to the opaque version of clear button.
                            mClearButtonImage =
                                    ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.clear_opaque, null);
                            // Clear the text and hide the clear button.
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
    }

    private void showClearButton(){
        mClearButtonImage.setBounds(0,0,mClearButtonImage.getIntrinsicWidth(),mClearButtonImage.getIntrinsicHeight());
        setCompoundDrawables(null,null,null,null);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,mClearButtonImage,null);
    }

    private void hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
    }
}
