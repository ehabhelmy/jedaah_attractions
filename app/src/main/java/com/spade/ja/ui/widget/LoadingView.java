package com.spade.ja.ui.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.spade.ja.R;


/**
 * Created by ehab on 12/21/17.
 */

public class LoadingView extends View {
    private boolean mLoopAnimation;
    private int mBackGroundColor;
    private int mCircleColor;
    private float mCircleStroke;
    private float mStartAngle;
    private int mSweepAngle;
    private float mScale;
    private boolean aFirstAnimation, aSecondAnimation, aThirdAnimation;
    private RectF mCircleRect;
    private Paint mBackgroundPaint;
    private Paint mCirclePaint;
    private ValueAnimator mAnimator1;
    private ValueAnimator mAnimator2;
    private ValueAnimator mAnimator3;
    private Animator.AnimatorListener mAnimator1Listener;
    private Animator.AnimatorListener mAnimator2Listener;
    private Animator.AnimatorListener mAnimator3Listener;

    public LoadingView(Context context) {
        super(context);
        init(null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mScale = getResources().getDisplayMetrics().density;
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCircleRect = new RectF();
        aFirstAnimation = true;

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.VodafoneLoadingAnimationView);
            try {
                mLoopAnimation = typedArray.getBoolean(R.styleable.VodafoneLoadingAnimationView_vav_loopAnimation, true);
                mBackGroundColor = typedArray.getColor(R.styleable.VodafoneLoadingAnimationView_vav_backgroundColor, Color.TRANSPARENT);
                mCircleColor = typedArray.getColor(R.styleable.VodafoneLoadingAnimationView_vav_circleColor, Color.BLACK);
                mCircleStroke = typedArray.getDimension(R.styleable.VodafoneLoadingAnimationView_vav_stroke, 5 * mScale);
            } finally {
                typedArray.recycle();
            }
        } else {
            mBackGroundColor = Color.GRAY;
            mCircleColor = Color.BLACK;
            mCircleStroke = 5 * mScale;
            mLoopAnimation = true;
        }
        mBackgroundPaint.setColor(mBackGroundColor);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeWidth(mCircleStroke);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        // For debugging in design preview
        if (isInEditMode()) {
            mStartAngle = 0;
            mSweepAngle = 111;
        } else {
            mSweepAngle = 0;
        }

        startLoading();
        invalidate();
    }

    private void startLoading() {
        if (mLoopAnimation) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    setFillAmountWithAnimation1(mSweepAngle);
                }
            }, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackgroundColor(mBackGroundColor);
        mCircleRect.set(0 + mCirclePaint.getStrokeWidth(), 0 + mCirclePaint.getStrokeWidth(),
                getWidth() - mCirclePaint.getStrokeWidth(), getWidth() - mCirclePaint.getStrokeWidth());

        if (aFirstAnimation) {
            canvas.drawArc(mCircleRect, mStartAngle, mSweepAngle, false, mCirclePaint);
            //Log.d("TAGG 1", "start " + mStartAngle + "sweep " + mSweepAngle);
        }
        if (aSecondAnimation) {
            canvas.drawArc(mCircleRect, mStartAngle, 360 - mStartAngle, false, mCirclePaint);
            //Log.d("TAGG 2", "start " + mStartAngle + "sweep " + (360 - mStartAngle));
        }
        if (aThirdAnimation) {
            canvas.drawArc(mCircleRect, mStartAngle, mSweepAngle, false, mCirclePaint);
            //Log.d("TAGG 3", "start " + mStartAngle + "sweep " + mSweepAngle);
        }
        super.onDraw(canvas);
    }

    public void startAnimation() {
        mLoopAnimation = true;
        postInvalidate();
    }

    public void moveArcPoints(float fillPercentage) {
        mStartAngle = (int) (330 * fillPercentage);
        mSweepAngle = 30;
        postInvalidate();
    }

    public void reverseStartPoint(float fillPercentage) {
        mStartAngle = (int) (360 * fillPercentage);
        postInvalidate();
    }

    public void setFillAmount(float fillPercentage) {
        mSweepAngle = (int) (360 * fillPercentage);
        postInvalidate();
    }

    public void setFillAmountWithAnimation1(final float fillAmount) {
        mAnimator1 = ValueAnimator.ofInt(0);
        mAnimator1.setDuration(1000);
        mAnimator1.setStartDelay(100);
        mAnimator1.setInterpolator(new AccelerateInterpolator());
        mAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setFillAmount((1f - fillAmount) * animation.getAnimatedFraction());
            }
        });
        mAnimator1Listener = new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animator) {
            }

            @Override public void onAnimationCancel(Animator animator) {
            }

            @Override public void onAnimationRepeat(Animator animator) {
            }

            @Override public void onAnimationEnd(Animator animator) {
                if (aFirstAnimation) {
                    aFirstAnimation = false;
                    aSecondAnimation = true;
                    aThirdAnimation = false;
                    setFillAmountWithAnimation2(0);
                }
            }
        };
        mAnimator1.addListener(mAnimator1Listener);
        mAnimator1.start();
    }

    public void setFillAmountWithAnimation2(final float fillAmount) {
        mAnimator2 = ValueAnimator.ofInt(0);
        mAnimator2.setDuration(1000);
        mAnimator2.setStartDelay(100);
        mAnimator2.setInterpolator(new AccelerateInterpolator());
        mAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                reverseStartPoint((1f - fillAmount) * animation.getAnimatedFraction());
            }
        });
        mAnimator2Listener = new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animator) {
            }

            @Override public void onAnimationCancel(Animator animator) {
            }

            @Override public void onAnimationRepeat(Animator animator) {
            }

            @Override public void onAnimationEnd(Animator animator) {
                if (aSecondAnimation) {
                    aFirstAnimation = false;
                    aSecondAnimation = false;
                    aThirdAnimation = true;
                    mSweepAngle = 0;
                    mStartAngle = 0;
                    setFillAmountWithAnimation3(0);
                }
            }
        };
        mAnimator2.addListener(mAnimator2Listener);
        mAnimator2.start();
    }

    public void setFillAmountWithAnimation3(final float fillAmount) {
        mAnimator3 = ValueAnimator.ofInt(0);
        mAnimator3.setDuration(1000);
        mAnimator3.setStartDelay(100);
        mAnimator3.setInterpolator(new AccelerateInterpolator());
        mAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float calc = 1f - (1f - fillAmount) * animation.getAnimatedFraction();
                moveArcPoints(calc);
            }
        });
        mAnimator3Listener = new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animator) {
            }

            @Override public void onAnimationCancel(Animator animator) {
            }

            @Override public void onAnimationRepeat(Animator animator) {
            }

            @Override public void onAnimationEnd(Animator animator) {
                if (aThirdAnimation) {
                    aFirstAnimation = true;
                    aSecondAnimation = false;
                    aThirdAnimation = false;
                    setFillAmountWithAnimation1(0);
                }
            }
        };
        mAnimator3.addListener(mAnimator3Listener);
        mAnimator3.start();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        int size;
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);

        if (mode == View.MeasureSpec.EXACTLY || mode == View.MeasureSpec.AT_MOST) {
            size = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(widthMeasureSpec), mode);
        } else {
            size = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }
        setMeasuredDimension(size, size);
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAnimator1 != null) {
            mAnimator1.removeAllListeners();
            mAnimator1.removeAllUpdateListeners();
        }
        if (mAnimator2 != null) {
            mAnimator2.removeAllListeners();
            mAnimator2.removeAllUpdateListeners();
        }
        if (mAnimator3 != null) {
            mAnimator3.removeAllListeners();
            mAnimator3.removeAllUpdateListeners();
        }
    }
}
