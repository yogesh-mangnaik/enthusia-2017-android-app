package org.enthusia;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class NexaBoldTextView extends android.support.v7.widget.AppCompatTextView {
    public NexaBoldTextView(Context context) {
        super(context);
        setFont();
    }
    public NexaBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public NexaBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/nexabold.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}