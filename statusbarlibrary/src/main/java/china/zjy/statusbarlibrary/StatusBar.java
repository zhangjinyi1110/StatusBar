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

final class StatusBar extends LinearLayout implements Bar {

    private final int STATUS_VISIBILITY = 0;//显示状态栏,显示状态栏文字图标
    private final int STATUS_INVISIBILITY = 1;//隐藏状态栏,显示状态栏文字图标
    private final int STATUS_GONE = 2;//隐藏状态栏,隐藏状态栏文字图标

    private Activity activity;

    private View decorView;

    private StatusBarView statusBarView;

    private boolean lightStyle = false;//是否是亮色主题，即是否要设置成字体为黑色

    private int barStatus = STATUS_VISIBILITY;

    private int visibility;

    StatusBar(Context context) {
        super(context);
        this.activity = (Activity) context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initLollipop();
        }
        decorView.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0 && barStatus == STATUS_GONE) {
                    hideHalfStatusBar();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideStatusBar();
                        }
                    }, 2000);
                }
            }
        });
    }

    /**
     * api 21 以上
     */
    private void initLollipop() {
        decorView = activity.getWindow().getDecorView();
        ViewGroup contentView = decorView.findViewById(Window.ID_ANDROID_CONTENT);
        if (contentView.getChildCount() > 0) {
            View content = contentView.getChildAt(0);
            if (!(content instanceof StatusBar)) {
                contentView.removeView(content);
                this.addView(content);
                contentView.addView(this, 0);
            }
            if (!(getChildAt(0) instanceof StatusBarView)) {
                statusBarView = new StatusBarView(activity);
                this.addView(statusBarView, 0);
                showStatusBar();
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(VERTICAL);
    }

    @Override
    public Bar setStatusBackgroundColor(@ColorInt int color) {
        statusBarView.setBackgroundColor(color);
        return this;
    }

    @Override
    public Bar setStatusBackgroundDrawable(Drawable drawable) {
        statusBarView.setBackground(drawable);
        return this;
    }

    @Override
    public Bar hideStatusBar() {
        barStatus = STATUS_GONE;
        statusBarView.setVisibility(GONE);
        visibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        switchColor();
        return this;
    }

    @Override
    public Bar showStatusBar() {
        barStatus = STATUS_VISIBILITY;
        statusBarView.setVisibility(VISIBLE);
        visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        switchColor();
        return this;
    }

    @Override
    public Bar hideHalfStatusBar() {
        barStatus = STATUS_INVISIBILITY;
        statusBarView.setVisibility(GONE);
        visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        switchColor();
        return this;
    }

    @Override
    public Bar lightColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            lightStyle = true;
            visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        decorView.setSystemUiVisibility(visibility);
        return this;
    }

    @Override
    public Bar darkColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            lightStyle = false;
            visibility = (visibility & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        decorView.setSystemUiVisibility(visibility);
        return this;
    }

    private void switchColor() {
        if (lightStyle) {
            lightColor();
        } else {
            darkColor();
        }
    }

}