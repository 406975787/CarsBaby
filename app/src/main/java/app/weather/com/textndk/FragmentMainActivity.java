package app.weather.com.textndk;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.daren.common.widget.NoDetachedTabFragmentHost;
import com.facebook.drawee.backends.pipeline.Fresco;

import app.weather.com.textndk.view.AppBarStateChangeListener;
import app.weather.com.textndk.view.BWMMainFragment;
import app.weather.com.textndk.view.BeazMainFragment;
import app.weather.com.textndk.view.aaFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {


    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(android.R.id.tabhost)
    NoDetachedTabFragmentHost mTabHost;
    @BindView(R.id.layout)
    CoordinatorLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Fresco.initialize(this);
        setContentView(R.layout.activity_fragment_main_bg);

        ButterKnife.bind(this);

        mTabHost = (NoDetachedTabFragmentHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        setupTabs();

        mTabHost.setOnTabChangedListener(this);

//        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        final CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        mCollapsingToolbarLayout.setTitle("宝马时尚中国");
//        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT);//设置收缩后Toolbar上字体的颜色

        appbarlayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {
                Log.e("www", "mCollapsingToolbarLayout===>>" + state.name());
                Log.e("www", "int i===>>" + i);

                if (state == State.EXPANDED) {
                    //展开状态
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                } else {
                    //中间状态
                }
            }
        });
    }


    private void setupTabs() {


        // 添加tab名称和图标
        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.title_bwm))
                        .setIndicator(getTabItemView(R.drawable.tab_bwm_im,
                                R.string.title_bwm)),
                BWMMainFragment.class, null);


        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.title_benz))
                        .setIndicator(getTabItemView(R.drawable.tab_benz_im,
                                R.string.title_benz)),
                BeazMainFragment.class, null);


        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.title_audi))
                        .setIndicator(getTabItemView(R.drawable.tab_audi_im,
                                R.string.title_audi)),
                aaFragment.class, null);


        mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.title_mch))
                        .setIndicator(getTabItemView(R.drawable.tab_koenigsegg_im,
                                R.string.title_mch)),
                aaFragment.class, null);


        Intent intent = getIntent();
        if (intent != null) {
            mTabHost.setCurrentTab(intent.getIntExtra("index", 0));
        } else {
            mTabHost.setCurrentTab(0);
        }

    }


    /**
     * get tab item view
     *
     * @param iconRes
     * @param stringId
     * @return
     */
    private View getTabItemView(int iconRes, int stringId) {
        View view = getLayoutInflater().inflate(R.layout.tab_item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        if (iconRes != -1) {
            imageView.setImageResource(iconRes);
        }
        TextView textView = (TextView) view.findViewById(R.id.textview);//设置textview背景样式
        textView.setText(stringId);
        return view;
    }


    @Override
    public void onTabChanged(String tabId) {
//        mIconTitle.setText(tabId);
    }

}