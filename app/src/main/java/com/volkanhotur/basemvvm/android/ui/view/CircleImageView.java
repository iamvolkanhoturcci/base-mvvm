package com.volkanhotur.basemvvm.android.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.volkanhotur.basemvvm.R;

import static android.widget.ImageView.ScaleType.CENTER_CROP;
import static android.widget.ImageView.ScaleType.CENTER_INSIDE;

/**
 * @author volkanhotur
 */

public class CircleImageView extends AppCompatImageView {

    private static final float DEFAULT_BORDER_WIDTH = 4;
    private static final float DEFAULT_SHADOW_RADIUS = 8.0f;

    private float borderWidth;
    private int canvasSize;
    private float shadowRadius;
    private int shadowColor = Color.BLACK;
    private ShadowGravity shadowGravity = ShadowGravity.BOTTOM;
    private ColorFilter colorFilter;

    private Bitmap image;
    private Drawable drawable;
    private Paint paint;
    private Paint paintBorder;
    private Paint paintBackground;

    public CircleImageView(final Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setAntiAlias(true);

        paintBackground = new Paint();
        paintBackground.setAntiAlias(true);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyleAttr, 0);

        if (attributes.getBoolean(R.styleable.CircleImageView_border, true)) {
            float defaultBorderSize = DEFAULT_BORDER_WIDTH * getContext().getResources().getDisplayMetrics().density;
            setBorderWidth(attributes.getDimension(R.styleable.CircleImageView_border_width, defaultBorderSize));
            setBorderColor(attributes.getColor(R.styleable.CircleImageView_border_color, Color.WHITE));
        }

        setBackgroundColor(attributes.getColor(R.styleable.CircleImageView_background_color, Color.WHITE));

        if (attributes.getBoolean(R.styleable.CircleImageView_shadow, false)) {
            shadowRadius = DEFAULT_SHADOW_RADIUS;
            drawShadow(attributes.getFloat(R.styleable.CircleImageView_shadow_radius, shadowRadius),
                    attributes.getColor(R.styleable.CircleImageView_shadow_color, shadowColor));
            int shadowGravityIntValue = attributes.getInteger(R.styleable.CircleImageView_shadow_gravity, ShadowGravity.BOTTOM.getValue());
            shadowGravity = ShadowGravity.fromValue(shadowGravityIntValue);
        }

        attributes.recycle();
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        requestLayout();
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null) {
            paintBorder.setColor(borderColor);
        }
        invalidate();
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        if (paintBackground != null) {
            paintBackground.setColor(backgroundColor);
        }
        invalidate();
    }

    public void addShadow() {
        if (shadowRadius == 0) {
            shadowRadius = DEFAULT_SHADOW_RADIUS;
        }
        drawShadow(shadowRadius, shadowColor);
        invalidate();
    }

    public void setShadowRadius(float shadowRadius) {
        drawShadow(shadowRadius, shadowColor);
        invalidate();
    }

    public void setShadowColor(int shadowColor) {
        drawShadow(shadowRadius, shadowColor);
        invalidate();
    }

    public void setShadowGravity(ShadowGravity shadowGravity) {
        this.shadowGravity = shadowGravity;
        invalidate();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.colorFilter == colorFilter) {
            return;
        }
        this.colorFilter = colorFilter;
        drawable = null;
        invalidate();
    }

    @Override
    public ScaleType getScaleType() {
        ScaleType currentScaleType = super.getScaleType();
        return currentScaleType != CENTER_INSIDE ? CENTER_CROP : currentScaleType;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != CENTER_CROP && scaleType != CENTER_INSIDE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported. " +
                    "Just ScaleType.CENTER_CROP & ScaleType.CENTER_INSIDE are available for this library.", scaleType));
        } else {
            super.setScaleType(scaleType);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        loadBitmap();

        if (image == null) {
            return;
        }

        if (!isInEditMode()) {
            canvasSize = Math.min(getWidth(), getHeight());
        }

        // circleCenter is the x or y of the view's center
        // radius is the radius in pixels of the circle to be drawn
        // paint contains the shader that will texture the shape
        int circleCenter = (int) (canvasSize - (borderWidth * 2)) / 2;
        float margeWithShadowRadius = shadowRadius * 2;

        // Draw Border
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - margeWithShadowRadius, paintBorder);
        // Draw Circle background
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - margeWithShadowRadius, paintBackground);
        // Draw Circle ImageView
        canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - margeWithShadowRadius, paint);
    }

    private void loadBitmap() {
        if (drawable == getDrawable()) {
            return;
        }

        drawable = getDrawable();
        image = drawableToBitmap(drawable);
        updateShader();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        canvasSize = Math.min(width, height);
        if (image != null) {
            updateShader();
        }
    }

    private void drawShadow(float shadowRadius, int shadowColor) {
        this.shadowRadius = shadowRadius;
        this.shadowColor = shadowColor;
        setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);

        float dx;
        float dy;

        switch (shadowGravity) {
            case TOP:
                dx = 0.0f;
                dy = -shadowRadius / 2;
                break;
            case BOTTOM:
                dx = 0.0f;
                dy = shadowRadius / 2;
                break;
            case START:
                dx = -shadowRadius / 2;
                dy = 0.0f;
                break;
            case END:
                dx = shadowRadius / 2;
                dy = 0.0f;
                break;

            case CENTER:

            default:
                    dx = 0.0f;
                    dy = 0.0f;
                    break;
        }

        paintBorder.setShadowLayer(shadowRadius, dx, dy, shadowColor);
    }

    private void updateShader() {
        if (image == null) {
            return;
        }

        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        float scale = 0;
        float dx = 0;
        float dy = 0;

        switch (getScaleType()) {
            case CENTER_CROP:
                if (image.getWidth() * getHeight() > getWidth() * image.getHeight()) {
                    scale = getHeight() / (float) image.getHeight();
                    dx = (getWidth() - image.getWidth() * scale) * 0.5f;
                } else {
                    scale = getWidth() / (float) image.getWidth();
                    dy = (getHeight() - image.getHeight() * scale) * 0.5f;
                }
                break;
            case CENTER_INSIDE:
                if (image.getWidth() * getHeight() < getWidth() * image.getHeight()) {
                    scale = getHeight() / (float) image.getHeight();
                    dx = (getWidth() - image.getWidth() * scale) * 0.5f;
                } else {
                    scale = getWidth() / (float) image.getWidth();
                    dy = (getHeight() - image.getHeight() * scale) * 0.5f;
                }
                break;

                default:
                    break;
        }

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postTranslate(dx, dy);
        shader.setLocalMatrix(matrix);

        paint.setShader(shader);

        paint.setColorFilter(colorFilter);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else {
            result = canvasSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else {
            result = canvasSize;
        }

        return result + 2;
    }

    public enum ShadowGravity {
        CENTER,
        TOP,
        BOTTOM,
        START,
        END;

        public int getValue() {
            switch (this) {
                case TOP:
                    return 2;
                case BOTTOM:
                    return 3;
                case START:
                    return 4;
                case END:
                    return 5;
                case CENTER:
                default:
                        return 1;
            }
        }

        public static ShadowGravity fromValue(int value) {
            switch (value) {
                case 2:
                    return TOP;
                case 3:
                    return BOTTOM;
                case 4:
                    return START;
                case 5:
                    return END;
                case 1:
                default:
                        return CENTER;
            }
        }

    }
}
