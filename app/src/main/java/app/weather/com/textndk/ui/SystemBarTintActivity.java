//package app.weather.com.textndk.ui;
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.readystatesoftware.systembartint.SystemBarTintManager;
//
//import app.weather.com.textndk.R;
//
///**
// * Created by mengxj on 2018/5/10.
// */
//
//public class SystemBarTintActivity extends AppCompatActivity {
//    public SystemBarTintActivity() {
//    }
//
//    @SuppressLint("WrongConstant")
//    protected void onCreate(Bundle savedInstanceState) {
//        this.setRequestedOrientation(1);
//        super.onCreate(savedInstanceState);
//    }
//
//    @TargetApi(19)
//    private void setTranslucentStatus(boolean on) {
//        Window win = this.getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        int bits = 67108864;
//        if(on) {
//            winParams.flags |= 67108864;
//        } else {
//            winParams.flags &= -67108865;
//        }
//
//        win.setAttributes(winParams);
//    }
//
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        this.initSystemBar();
//    }
//
//    protected void initSystemBar() {
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(this.getStatusbarColor());
//        if(Build.VERSION.SDK_INT >= 19 && this.getTopBarView() != null) {
//            this.setTranslucentStatus(true);
//        }
//
//        Window win = this.getWindow();
//        @SuppressLint("ResourceType") ViewGroup contentView = (ViewGroup)win.getDecorView().findViewById(16908290);
//        ViewGroup rootView = (ViewGroup)contentView.getChildAt(0);
//        if(Build.VERSION.SDK_INT >= 14) {
//            rootView.setFitsSystemWindows(true);
//        }
//
//    }
//
//    protected View getTopBarView() {
//        return null;
//    }
//
//    protected int getStatusbarColor() {
//        return R.color.status_bar_color;
//    }
//}
//
