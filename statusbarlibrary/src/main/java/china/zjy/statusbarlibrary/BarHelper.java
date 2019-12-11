package china.zjy.statusbarlibrary;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class BarHelper {

    public static Bar with(Activity activity) {
        Window window = activity.getWindow();
        View d = window.getDecorView();
        ViewGroup contentView = d.findViewById(Window.ID_ANDROID_CONTENT);
        if (contentView.getChildCount() > 0) {
            View content = contentView.getChildAt(0);
            if (content instanceof Bar) {
                return (Bar) content;
            }
        }
        return new StatusBar(activity);
    }

    public static Bar with(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new NullPointerException("The fragment parent activity is null");
        }
        return with(activity);
    }

}
