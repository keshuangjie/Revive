package com.jimmy.revive.sample.animate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jimmy.revive.R;

/**
 * Created by Jimmy on 2017/12/28.
 */
public class VideoLayerView extends FrameLayout {

    private static final long DOUBLE_CLICK_INTERVAL = 300; // 双击命中间隔
    private static final long SINGLE_CLICK_DELAY = 400; // 单击延迟间隔
    private static final int LOVE_MOVE = -400;
    private static final int LOVE_SIZE = 200;

    private long lastClickTime; // 上一次点击时间
    private long currentClickTime; // 最新点击时间
    private int clickCount; // 点击次数

    public void setListener(OnVideoClickListener listener) {
        this.listener = listener;
    }

    private OnVideoClickListener listener;

    public VideoLayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastClickTime = currentClickTime;
                currentClickTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                clickCount++;
                if (!checkDoubleClick(event)) {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clickHandler(event);
                        }
                    }, SINGLE_CLICK_DELAY);
                }
                break;

        }
        return super.onTouchEvent(event);
    }

    private void clickHandler(MotionEvent event) {
        if (!checkDoubleClick(event)) {
            listener.onClick();
            clickCount = 0;
        }
    }

    private boolean checkDoubleClick(MotionEvent event) {
        if (lastClickTime != 0 && (currentClickTime - lastClickTime) <= DOUBLE_CLICK_INTERVAL) {
            if (clickCount >= 2) {
                listener.onDoubleClick(event);
                onDoubleClick(event);
                clickCount = 0;
            }
            return true;
        }
        return false;
    }

    private void onDoubleClick(MotionEvent event) {
        final ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.test_animate_yanhua);
        LayoutParams lp = new LayoutParams(LOVE_SIZE, LOVE_SIZE);
        lp.leftMargin = (int) event.getX() - LOVE_SIZE / 2;
        lp.topMargin = (int) event.getY() - LOVE_SIZE / 2;
        addView(imageView, 0, lp);
        zoomOut(imageView, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(imageView);
            }
        });
    }

    private void zoomOut(View view, AnimatorListenerAdapter listenerAdapter) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, LOVE_MOVE);
        AnimatorSet set = new AnimatorSet();
        set.play(translationYAnimator).with(animator);
        set.setDuration(1000);
        if (listenerAdapter != null) {
            set.addListener(listenerAdapter);
        }
        set.start();
    }

    public interface OnVideoClickListener {
        void onClick();

        void onDoubleClick(MotionEvent event);
    }
}
