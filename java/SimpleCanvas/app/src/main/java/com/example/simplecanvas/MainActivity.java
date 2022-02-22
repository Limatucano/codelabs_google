package com.example.simplecanvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.example.simplecanvas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Canvas mCanvas;
    private ActivityMainBinding binding;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();
    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mColorBackground = ResourcesCompat.getColor(getResources(),R.color.colorBackground,null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),R.color.colorRectangle,null);
        mColorAccent = ResourcesCompat.getColor(getResources(),R.color.colorAccent,null);

        mPaint.setColor(mColorBackground);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(),R.color.black,null));
        mPaintText.setTextSize(70);

        binding.myImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawSomething(v,false);
            }
        });

        binding.myImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                drawSomething(v,true);
                return true;
            }
        });
    }
    private void drawSomething(View view, Boolean toReset){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;

        if(toReset){
            mOffset = OFFSET;
        }
        if(mOffset == OFFSET){
            mBitmap = Bitmap.createBitmap(vWidth,vHeight,Bitmap.Config.ARGB_8888);
            binding.myImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);
            mCanvas.drawText(getString(R.string.keep_tapping),100,100,mPaintText);
            mOffset += OFFSET;
        }else{
            if(mOffset < halfWidth && mOffset < halfHeight){
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mRect.set(mOffset,mOffset,vWidth,vHeight - mOffset);
                mCanvas.drawRect(mRect,mPaint);
                mOffset += OFFSET;
            }else{
                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, mPaint);
                String text = getString(R.string.press_to_reset);
                // Get bounding box for text to calculate where to draw it.
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
                // Calculate x and y for text so it's centered.
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);
            }
        }
        view.invalidate();
    }

}