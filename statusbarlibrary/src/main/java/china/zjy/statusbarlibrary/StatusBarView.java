package china.zjy.statusbarlibrary;

import android.content.Context;
import android.view.View;

public class StatusBarView extends View {

    private int height;

    public StatusBarView(Context context) {
        super(context);
        int id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (id > 0) {
            height = getResources().getDimensionPixelSize(id);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }
}