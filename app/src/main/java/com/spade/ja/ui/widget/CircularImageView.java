package com.spade.ja.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.spade.ja.R;


/**
 * Created by Maya Gardeva on 10/11/15.
 */

public class CircularImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = CircularImageView.class.getSimpleName();

    private boolean isSelected;
    private int canvasSize;

    private BitmapShader shader;
    private Bitmap image;
    private Paint paint;
    private int borderWidth;
    private boolean hasBorder;
    private Paint paintSelectorBorder;
    private Paint paintBorder;
    private ColorFilter backgroundFilter;

    public CircularImageView(Context context) {
        this(context, null, R.styleable.CircularImageViewStyle_circularImageViewDefault);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.styleable.CircularImageViewStyle_circularImageViewDefault);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        paintSelectorBorder = new Paint();
        paintSelectorBorder.setAntiAlias(true);

        int defaultBorderSize = (int) (2 * context.getResources().getDisplayMetrics().density + 0.5f);

        paintBorder = new Paint();
        paintBorder.setAntiAlias(true);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setStrokeWidth(defaultBorderSize);

        // Load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, defStyle, 0);

        // Check for extra features being enabled
        if (attrs != null) {
            int backgroundColor = attributes.getColor(R.styleable.CircularImageView_civ_backgroundColor, getResources().getColor(R.color.transparent));
            setBackgroundFilter(backgroundColor);
            hasBorder = attributes.getBoolean(R.styleable.CircularImageView_civ_border, false);

            // Set border properties, if enabled
            if (hasBorder) {
                setBorderWidth(attributes.getDimensionPixelOffset(R.styleable.CircularImageView_civ_borderWidth, defaultBorderSize));
                setBorderColor(attributes.getColor(R.styleable.CircularImageView_civ_borderColor, Color.WHITE));
            }
        }

        attributes.recycle();
    }


    public void setBackgroundFilter(int backgroundColor) {
        this.backgroundFilter = new PorterDuffColorFilter(backgroundColor, PorterDuff.Mode.SRC_ATOP);
        this.invalidate();
    }

    public void setImageColorFilter(String colorString) {
        paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor(colorString), PorterDuff.Mode.SRC_ATOP));
        this.invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        // Don't draw anything without an image
        if (image == null) {
            return;
        }

        // Nothing to draw (Empty bounds)
        if (image.getHeight() == 0 || image.getWidth() == 0) {
            return;
        }

        // Update shader if canvas size has changed
        int oldCanvasSize = canvasSize;
        canvasSize = getWidth() < getHeight() ? getWidth() : getHeight();
        if (oldCanvasSize != canvasSize) {
            updateBitmapShader();
        }

        // Apply shader to paint
        paint.setShader(shader);

        // Keep track of selectorStroke/border width
        int outerWidth = 0;

        // Get the exact X/Y axis of the view
        int center = canvasSize / 2;

        paintSelectorBorder.setColorFilter(backgroundFilter);
        canvas.drawCircle(center + outerWidth, center + outerWidth, ((canvasSize - (outerWidth * 2)) / 2) +
                outerWidth - 4.0f, paintSelectorBorder);

        canvas.drawCircle(center + outerWidth, center + outerWidth, ((canvasSize - (outerWidth * 2)) / 2) +
                outerWidth - 4.0f, paint);

        if (hasBorder) {
            outerWidth = borderWidth;
            center = (canvasSize - (outerWidth * 2)) / 2;
            // paint.setColorFilter(null);
            canvas.drawCircle(center + outerWidth, center + outerWidth, ((canvasSize - (outerWidth * 2)) / 2) + outerWidth - 4.0f, paintBorder);
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);

        // Extract a Bitmap out of the drawable & set it as the main shader
        image = drawableToBitmap(getDrawable());
        if (canvasSize > 0) {
            updateBitmapShader();
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);

        // Extract a Bitmap out of the drawable & set it as the main shader
        image = drawableToBitmap(getDrawable());
        if (canvasSize > 0) {
            updateBitmapShader();
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);

        // Extract a Bitmap out of the drawable & set it as the main shader
        image = drawableToBitmap(getDrawable());
        if (canvasSize > 0) {
            updateBitmapShader();
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);

        // Extract a Bitmap out of the drawable & set it as the main shader
        image = bm;
        if (canvasSize > 0) {
            updateBitmapShader();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // The parent has determined an exact size for the child.
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // The parent has not imposed any constraint on the child.
            result = canvasSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = canvasSize;
        }

        return (result + 2);
    }

    /**
     * Convert a drawable object into a Bitmap.
     *
     * @param drawable Drawable to extract a Bitmap from.
     * @return A Bitmap created from the drawable parameter.
     */
    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) { // Don't do anything without a proper drawable
            return null;
        } else if (drawable instanceof BitmapDrawable) {  // Use the getBitmap() method instead if BitmapDrawable
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();

        if (!(intrinsicWidth > 0 && intrinsicHeight > 0)) {
            return null;
        }

        try {
            // Create Bitmap object out of the drawable
            Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Simply return null of failed bitmap creations
            Log.e(TAG, "Encountered OutOfMemoryError while generating bitmap!");
            return null;
        }
    }

    /**
     * Re-initializes the shader texture used to fill in
     * the Circle upon drawing.
     */
    public void updateBitmapShader() {
        if (image == null) {
            return;
        }

        shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        if (canvasSize != image.getWidth() || canvasSize != image.getHeight()) {
            Matrix matrix = new Matrix();
            float scale = (float) canvasSize / (float) image.getWidth();
            matrix.setScale(scale, scale);
            shader.setLocalMatrix(matrix);
        }
    }

    /**
     * @return Whether or not this view is currently
     * in its selected state.
     */
    public boolean isSelected() {
        return this.isSelected;
    }

    @Override public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null) {
            paintBorder.setColor(borderColor);
        }
        this.invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        if (paintBorder != null) {
            paintBorder.setStrokeWidth(borderWidth);
        }
        invalidate();
    }
}