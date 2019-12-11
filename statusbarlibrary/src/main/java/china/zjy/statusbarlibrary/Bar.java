package china.zjy.statusbarlibrary;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;

public interface Bar {

    //设置状态栏背景颜色
    Bar setStatusBackgroundColor(@ColorInt int color);

    //设置状态栏背景图片
    Bar setStatusBackgroundDrawable(Drawable drawable);

    //隐藏状态栏
    Bar hideStatusBar();

    //显示状态栏
    Bar showStatusBar();

    //隐藏状态栏但显示文字图标
    Bar hideHalfStatusBar();

    //亮色主题时字体为黑色
    Bar lightColor();

    //暗色主题时字体为白色
    Bar darkColor();

}
