package china.zjy.statusbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import china.zjy.statusbarlibrary.BarHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                BarHelper.with(this).setStatusBackgroundColor(Color.parseColor("#333333"));
                break;
            case R.id.btn2:
                BarHelper.with(this).setStatusBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
                break;
            case R.id.btn4:
                BarHelper.with(this).hideStatusBar();
                break;
            case R.id.btn5:
                BarHelper.with(this).hideHalfStatusBar();
                break;
            case R.id.btn6:
                BarHelper.with(this).lightColor();
                break;
            case R.id.btn7:
                BarHelper.with(this).darkColor();
                break;
            case R.id.btn9:
                BarHelper.with(this).showStatusBar();
                break;
        }
    }
}
