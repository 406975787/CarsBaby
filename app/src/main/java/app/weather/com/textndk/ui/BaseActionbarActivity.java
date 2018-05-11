//package app.weather.com.textndk.ui;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.lang.reflect.Field;
//
//import app.weather.com.textndk.R;
//
///**
// * Created by mengxj on 2018/5/10.
// */
//
//public class BaseActionbarActivity   extends  SystemBarTintActivity {
//    protected Context mContext;
//    private Toolbar mToolbar;
//    private LayoutInflater mInflater;
//    private TextView mTitleTextView;
//
//    public BaseActionbarActivity() {
//    }
//
//    public Toolbar getToolbar() {
//        return this.mToolbar;
//    }
//
//    protected void setCustomTitle(int titleId) {
//        this.setCustomTitle(this.getString(titleId));
//    }
//
//    protected void setCustomTitle(String title) {
//        if(this.mTitleTextView != null && !TextUtils.isEmpty(title)) {
//            this.mTitleTextView.setText(title);
//        }
//
//    }
//
//    public void setContentView(int layoutResID) {
//        super.setContentView(layoutResID);
//        this.addToolBarAtTop();
//        this.initToolBar();
//        this.initSystemBar();
//    }
//
//    protected View getTopBarView() {
//        return this.mToolbar;
//    }
//
//    private void addToolBarAtTop() {
//        View toolbarView = LayoutInflater.from(this).inflate(R.layout.top_bar, (ViewGroup)null);
//        Window win = this.getWindow();
//        ViewGroup contentView = (ViewGroup)win.getDecorView().findViewById(16908290);
//        ViewGroup rootView = (ViewGroup)contentView.getChildAt(0);
//        if(rootView instanceof RelativeLayout) {
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -2);
//            lp.addRule(10);
//            rootView.addView(toolbarView, lp);
//        } else {
//            ((ViewGroup)contentView.getChildAt(0)).addView(toolbarView, 0);
//        }
//
//    }
//
//    protected void initToolBar() {
//        this.mToolbar = (Toolbar)this.findViewById(R.id.toolbar);
//        if(this.mToolbar != null) {
//            this.setSupportActionBar(this.mToolbar);
//            this.mToolbar.setNavigationIcon(R.drawable.home_as_up_indicator_bg);
//        }
//
//        this.mTitleTextView = (TextView)this.findViewById(R.id.title);
//        if(this.mTitleTextView != null) {
//            this.mTitleTextView.setText(this.getTitle());
//            this.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }
//
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.mContext = this;
//    }
//
//    private void initOverflowMenu() {
//        try {
//            ViewConfiguration mconfig = ViewConfiguration.get(this);
//            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
//            if(menuKeyField != null) {
//                menuKeyField.setAccessible(true);
//                menuKeyField.setBoolean(mconfig, false);
//            }
//        } catch (Exception var3) {
//            ;
//        }
//
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case 16908332:
//                this.finish();
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//}
