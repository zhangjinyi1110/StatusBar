package china.zjy.statusbarlibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

public class StatusBar extends LinearLayout implements Bar {

    private Activity activity;

    private View decorView;

    private StatusBarView statusBarView;

    private boolean lightStyle = false;//是否是亮色主题，即是否要设置成字体为黑色

    private boolean hideStatusBar = false;//是否隐藏状态栏

    private boolean hideHalfStatusBar = false;//是否隐藏状态栏但是现实状态栏的文字图标等

    StatusBar(Context context) {
        super(context);
        this.activity = (Activity) context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        decorView = activity.getWindow().getDecorView();
        ViewGroup contentView = decorView.findViewById(Window.ID_ANDROID_CONTENT);
        if (contentView.getChildCount() > 0) {
            View content = contentView.getChildAt(0);
            contentView.removeView(content);
            this.addView(content);
            contentView.addView(this, 0);
        }
        statusBarView = new StatusBarView(activity);
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(VERTICAL);
    }

    @Override
    public Bar setStatusBackgroundColor(@ColorInt int color) {
        hideStatusBar = false;
        hideHalfStatusBar = false;
        initStatusBar();
        statusBarView.setBackgroundColor(color);
        return this;
    }

    @Override
    public Bar setStatusBackgroundDrawable(Drawable drawable) {
        hideStatusBar = false;
        hideHalfStatusBar = false;
        initStatusBar();
        statusBarView.setBackground(drawable);
        return this;
    }

    @Override
    public Bar hideStatusBar() {
        hideStatusBar = true;
        hideHalfStatusBar = true;
        initStatusBar();
        return this;
    }

    @Override
    public Bar showStatusBar() {
        hideStatusBar = false;
        hideHalfStatusBar = false;
        initStatusBar();
        return this;
    }

    @Override
    public Bar hideHalfStatusBar() {
        hideStatusBar = false;
        hideHalfStatusBar = true;
        initStatusBar();
        return this;
    }

    @Override
    public Bar lightColor() {
        lightStyle = true;
        initStatusBar();
        return this;
    }

    @Override
    public Bar darkColor() {
        lightStyle = false;
        initStatusBar();
        return this;
    }

    private void initStatusBar() {
        statusBarView.setVisibility(hideHalfStatusBar ? GONE : VISIBLE);
        if (getChildAt(0) != statusBarView) {
            this.addView(statusBarView, 0);
        }
        int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (lightStyle && Build.VERSION_CODES.M <= Build.VERSION.SDK_INT) {
            visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        if (hideStatusBar) {
            visibility |= SYSTEM_UI_FLAG_FULLSCREEN;
        }
        decorView.setSystemUiVisibility(visibility);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

}