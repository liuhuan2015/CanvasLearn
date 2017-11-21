package com.liuh.canvaslearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huan on 2017/11/20 09:36.
 * 使用Canvas绘制图形
 * http://blog.csdn.net/aigestudio/article/details/42677973
 * Canvas是一个容器，如果把Canvas理解成画板，那么我们的“层”就像张张夹在画板上的透明的纸，
 * 而这些纸对应到Android则是一个个封装在Canvas中的Bitmap
 * canvas.save();在当前的Bitmap中进行操作,保存Canvas当前的状态
 * canvas.saveLayer(...);会将所有的操作存到一个新的Bitmap中而不影响当前Canvas的Bitmap
 * canvas.restore();恢复Canvas的状态到canvas.save()时的状态，一般和canvas.save()配对使用
 * canvas.restoreToCount(int saveCount);还原到某一个save节点
 *
 */

public class LayerView extends View {
    private Paint mPaint;

    private int mViewWidth, mViewHeight;

    public LayerView(Context context) {
        this(context, null);
    }

    public LayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制一个红色矩形
        mPaint.setColor(Color.RED);
        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);

        //保存并裁剪画布，填充绿色
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);//裁剪的标识位
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);

        //保存并旋转画布，绘制一个蓝色矩形
        int saveID2 = canvas.save(Canvas.MATRIX_SAVE_FLAG);//变换的标识位
        canvas.rotate(5);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
        canvas.restoreToCount(saveID1);
    }
}
