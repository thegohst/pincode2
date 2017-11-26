package mlikamohamed.com.pincode;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.TypedValue;

import java.util.ArrayList;

/**
 * Created by Mohamed Abdhamid Mlika on 14/11/2017.
 * 360 medlink
 * mohamedmlikaa@gmail.com
 */
public class PinCode extends android.support.v7.widget.AppCompatEditText {
    private static final String TAG = PinCode.class.getSimpleName();

    private int itemCount = 4;
    Paint paint;
    Paint selectedRectPaint;
    Paint textPaint;
    int pad;
    private float underlineStrokeWidth;
    private ArrayList<Point> list;
    private CharSequence charecter;
    private OnTextChangeListener changeListener;
    private boolean hidePassword = true;
    private Bitmap bitmap;
    private int paddintLeft;
    private int width;
    private int cornerRound;
    private int imageResource;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private float textSize;

    public PinCode(Context context) {
        super(context);
        initFields();

        init();
        initDraw();
    }

    public PinCode(Context context, AttributeSet attributeset) {
        super(context, attributeset);
        initFields();
        getAttributeSet(attributeset, 2);
        init();
        initDraw();

    }


    public PinCode(Context context, AttributeSet attributeset, int defStyledAttrs) {
        super(context, attributeset, defStyledAttrs);

        initFields();
        getAttributeSet(attributeset, defStyledAttrs);
        init();
        initDraw();
    }

    private void getAttributeSet(AttributeSet attributeset, int defStyledAttrs) {
        TypedArray a = getContext().obtainStyledAttributes(attributeset, R.styleable.PinCode, 0, 0);


        int boxCount = a.getInt(R.styleable.PinCode_boxCount, -1);

        if (boxCount > 0) {
            itemCount = boxCount;
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(itemCount);
            this.setFilters(FilterArray);

        }

        int imageResoure = a.getResourceId(R.styleable.PinCode_imageResource, -1);

        if (imageResoure > 0) {

            this.imageResource = imageResoure;
            changeImageResource();

        }


        int textSize = a.getDimensionPixelSize(R.styleable.PinCode_textSize, -1);

        if (textSize > 0) {
            this.textSize = textSize;

        }

        int widthAndHeight = a.getDimensionPixelSize(R.styleable.PinCode_boxWidthAndHeight, -1);
        if (widthAndHeight > 0) {
            this.width = widthAndHeight;

        }
        a.recycle();
    }


    private void initFields() {
        paddintLeft = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics());
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 38, getContext().getResources().getDisplayMetrics());
        underlineStrokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getContext().getResources().getDisplayMetrics());
        pad = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getContext().getResources().getDisplayMetrics());
        cornerRound = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getContext().getResources().getDisplayMetrics());
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18F, getContext().getResources().getDisplayMetrics());

        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(itemCount);
        this.setFilters(FilterArray);

        setImageResource(R.drawable.icon_google);

    }

    private void init() {


        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(underlineStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setPathEffect(new CornerPathEffect(underlineStrokeWidth));


        selectedRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedRectPaint.setColor(Color.BLACK);
        selectedRectPaint.setStrokeWidth(underlineStrokeWidth);
        selectedRectPaint.setStyle(Paint.Style.STROKE);
        selectedRectPaint.setStrokeCap(Paint.Cap.ROUND);
        selectedRectPaint.setDither(true);
        selectedRectPaint.setStrokeJoin(Paint.Join.ROUND);
        selectedRectPaint.setPathEffect(new CornerPathEffect(underlineStrokeWidth));


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCursorVisible(false);
        super.setTextColor(Color.TRANSPARENT);


    }

    private void initDraw() {

        list = new ArrayList<>();
        Point p;
        int left = pad;
        int right;

        for (int i = 0; i < itemCount; i++) {
            p = new Point();

            p.setLeft(left);
            p.setTop(pad);
            right = left + width + pad;
            p.setRight(right);
            p.setBottom(width + 2 * pad);
            left = right + paddintLeft;

            list.add(p);

        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
        drawText(canvas);
    }

    private void drawRect(Canvas canvas) {
        RectF rect;

        int i = 0;


        for (Point p : list) {
            rect = new RectF(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
            if (charecter != null && charecter.length() >= i && isFocused()) {

                canvas.drawRoundRect(rect, cornerRound, cornerRound, selectedRectPaint);

            } else {


                canvas.drawRoundRect(rect, cornerRound, cornerRound, paint);

            }
            i++;
        }
    }

    private void drawText(Canvas canvas) {


        Rect rect = new Rect();
        if (!hidePassword) {
            textPaint.getTextBounds("8", 0, 1, rect);
        } else {

            textPaint.getTextBounds("88", 0, 2, rect);

        }

        Point point;
        for (int i = 0; i < charecter.length(); i++) {

            point = list.get(i);
            if (hidePassword) {


                canvas.drawBitmap(bitmap, (point.getRight() - point.getLeft() - imageWidth) / 2 + point.getLeft(),
                        (point.bottom - point.getTop() - imageHeight) / 2 + point.getTop(), textPaint);

            } else {

                canvas.drawText(String.valueOf(charecter.charAt(i)), (point.getRight() - point.getLeft()) / 2
                        + list.get(i).getLeft(), (point.getBottom() - list.get(i).getTop()) / 2 + rect.height() / 2 + pad, textPaint);
            }
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = ((this.width + 2 * pad + paddintLeft) * itemCount) + 2 * pad;
        int height = this.width + itemCount * pad;

        width = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        height = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(width, height);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);


        this.charecter = text;
        try {
            if (changeListener != null) {

                changeListener.onTextChnaged(text.toString());

                if (text.length() == itemCount) {

                    changeListener.onAllBoxFilled(text.toString());
                }
            }
        } catch (Exception ignore) {
        }

        invalidate();

    }

    public void setTextChangeListener(OnTextChangeListener listener) {
        this.changeListener = listener;

    }

    public void showText() {

        hidePassword = false;
        invalidate();
    }

    public void hideText() {
        hidePassword = true;
        invalidate();


    }

    public boolean isTextVisible() {

        return !hidePassword;
    }

    public void setMarginBetweenBoxDp(int marginBetweenBoxDp) {

        setMarginBetweenBox((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                marginBetweenBoxDp, getContext().getResources().getDisplayMetrics()));

    }

    public void setMarginBetweenBox(int marginBetweenBox) {
        this.paddintLeft = marginBetweenBox;
        initDraw();
        invalidate();
    }

    public void setBoxCount(int itemCount) {

        this.itemCount = itemCount;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(itemCount);
        this.setFilters(FilterArray);
        initDraw();
        invalidate();
    }

    @Override
    public void setMaxLines(int maxLines) {

    }

    @Override
    public void setMaxEms(int maxEms) {
        super.setMaxEms(maxEms);
    }

    @Override
    public void setTextSize(float textSize) {
        textPaint.setTextSize(textSize);
        invalidate();
    }


    public void setHighlightedBoxColor(@ColorInt int color) {

        this.selectedRectPaint.setColor(color);
    }

    public void setBoxColor(@ColorInt int color) {
        paint.setColor(color);
        invalidate();


    }

    public void setStrkoeWidthDP(float widthDP) {

        setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthDP,
                getContext().getResources().getDisplayMetrics()));

    }

    public void setStrokeWidth(float width) {
        this.underlineStrokeWidth = width;
        paint.setStrokeWidth(underlineStrokeWidth);
        paint.setPathEffect(new CornerPathEffect(underlineStrokeWidth));
        selectedRectPaint.setStrokeWidth(underlineStrokeWidth);
        selectedRectPaint.setPathEffect(new CornerPathEffect(underlineStrokeWidth));

    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(Color.TRANSPARENT);
        textPaint.setColor(color);
        invalidate();
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(Color.TRANSPARENT);
    }

    public void setTextSizeDP(float textSize) {
        setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, textSize, getContext().getResources().getDisplayMetrics()));

    }


    public void setBoxWidthAndHeight(int widthAndHeight) {
        this.width = widthAndHeight;
        initDraw();
        changeImageResource();
        invalidate();
    }

    public void setBoxWidthAndHeightDP(int widthAndHeight) {
        this.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthAndHeight, getContext().getResources().getDisplayMetrics());
        initDraw();
        changeImageResource();
        invalidate();

    }


    public interface OnTextChangeListener {

        void onTextChnaged(String text);

        void onAllBoxFilled(String text);

    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    public void setImageResource(@DrawableRes int imageResource) {
        this.imageResource = imageResource;
        changeImageResource();
        invalidate();

    }

    public void setImageResource(@DrawableRes int imageResource, int width, int height) {
        this.imageWidth = width;
        this.imageHeight = height;
        this.imageResource = imageResource;
        changeImageResource();
        invalidate();

    }


    /**
     * set the picture
     *
     * @param imageResource imagereource
     * @param widthDP       the width of the picture the value will be converted to DP , the width
     *                      of picture should be smalled than the width of the box
     * @param heightDP      the height of the picture the value will be converted to DP , the height
     *                      should be smaller than the box height
     */
    public void setImageResourceDP(@DrawableRes int imageResource, int widthDP, int heightDP) {
        this.imageWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthDP, getContext().getResources().getDisplayMetrics());
        this.imageHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightDP, getContext().getResources().getDisplayMetrics());
        this.imageResource = imageResource;
        changeImageResource();

    }

    private void changeImageResource() {
        Bitmap bit = BitmapFactory.decodeResource(getResources(), imageResource);

        if (imageWidth == 0 || imageWidth > width) {
            imageWidth = width - 2 * pad;

        }
        if (imageHeight == 0 || imageHeight > width) {
            imageHeight = width - 2 * pad;

        }
        bitmap = Bitmap.createScaledBitmap(bit, imageWidth, imageHeight, true);


    }


    private class Point {
        private int left;
        private int right;
        private int top;
        private int bottom;
        private char charecter;

        public Point() {
        }

        public Point(int left, int right, int top, int bottom, char charecter) {
            this.left = left;
            this.right = right;
            this.bottom = bottom;
            this.charecter = charecter;

        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public char getCharecter() {
            return charecter;
        }

        public void setCharecter(char charecter) {
            this.charecter = charecter;
        }
    }
}