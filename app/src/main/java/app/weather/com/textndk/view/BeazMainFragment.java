package app.weather.com.textndk.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daren.common.util.Logger;
import com.daren.common.widget.WaitDialog;

import app.weather.com.textndk.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mengxj on 2018/5/14.
 */

public class BeazMainFragment extends Fragment {

    @BindView(R.id.webview)
    WebView mWebView;
    Unbinder unbinder;
    WaitDialog mWaitDialog = null;
    @BindView(R.id.header_img)
    ImageView headerImg;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.ly_title)
    LinearLayout lyTitle;
    @BindView(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    private int selectCode;
    private int index = 0;

    // 图片
    @DrawableRes
    private int mImages[] = {
            R.drawable.benz_msds_car,
            R.drawable.benz_msds_cp,
            R.drawable.benz_msds_xp,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_common_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String url = "https://special.mercedes-benz.com.cn/2018-dream-car/";
//        webview.loadUrl(url);

        mWaitDialog = WaitDialog.getDefaultWaitDialog(getActivity());
//        mWaitDialog.show();

        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setInitialScale(0);

        WebSettings settings = mWebView.getSettings();

        settings.setDefaultTextEncodingName("GBK");
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        settings.setSupportZoom(true);
        settings.setGeolocationEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);//设定支持缩放
//        mWebView.addJavascriptInterface(new AndroidJs(this), "Android");
//        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
            mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
            mWebView.getSettings().setAllowContentAccess(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        }
//
////        settings.setJavaScriptEnabled(true);
////        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebChromeClient(new WebChromeClient() {

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {

                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                getActivity().startActivityForResult(
                        Intent.createChooser(i, "选择文件"),
                        123);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Logger.e("wjl", "onJsAlert -------------- " + message + "| " + url);
                return super.onJsAlert(view, url, message, result);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWaitDialog.dismiss();
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Logger.e("wjl", "url -------- shouldOverrideUrlLoading " + url);
                Uri uri = Uri.parse(url);
                String scheme = uri.getScheme();
                view.loadUrl(url);

                return true;
            }
        });
//
//        syncCookie();

//        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
//        mWebView.getSettings().setTextZoom(200);

        //为js添加调用本地方法的能力
//        mWebView.addJavascriptInterface(this, "Android");
        mWebView.loadUrl(url);
//        mWebView.loadDataWithBaseURL("http://192.168.1.80:9999");
//        mWebView.loadDataWithBaseURL("file:///android_asset/", s, "text/html", "utf-8", null);


        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        final CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_layout);
//        mCollapsingToolbarLayout.setTitle("梅赛德斯-奔驰");
//        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT);//设置收缩后Toolbar上字体的颜色

        appbarlayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {
                Log.e("www", "mCollapsingToolbarLayout===>>" + state.name());
                Log.e("www", "int i===>>" + i);
//                toolbarMain.setVisibility(View.VISIBLE);
//                toolbarMain.setBackground(getResources().getDrawable(R.drawable.baoma_bg));
//                lyBanner.getBackground().setAlpha((int) (Math.abs(i*1.0f)/appBarLayout.getTotalScrollRange()));//toolbar透明度初始化为0(Math.abs(i*1.0f)/appBarLayout.getTotalScrollRange());
//                if(Math.abs(i*1.0f)/appBarLayout.getTotalScrollRange()==0){
//                    changeAlpha();
//                }else {
//                    changeTAlpha();
//                }
//                Log.e("www","互动值+透明度===》》"+Math.abs(i*1.0f)/appBarLayout.getTotalScrollRange());
                if (state == State.EXPANDED) {
                    //展开状态
                    lyTitle.setVisibility(View.GONE);
//                    lyBanner.setAlpha(1f);
//                    changeTAlpha();
//                    lyBanner.setVisibility(View.VISIBLE);
                } else if (state == State.COLLAPSED) {
//                    mCollapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
                    //折叠状态
//                    changeAlpha();
                    lyTitle.setVisibility(View.VISIBLE);
//                    lyBanner.setAlpha(0f);
//                    lyBanner.setVisibility(View.GONE);
//                    toolbarMain.setBackgroundColor(changeAlpha(getResources().getDrawable(R.drawable.baoma_bg)),Math.abs(i*1.0f)/appBarLayout.getTotalScrollRange()));
                } else {
                    lyTitle.setVisibility(View.GONE);
//                    lyBanner.setVisibility(View.VISIBLE);
                    //中间状态
                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_left, R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                index--;
                if (index == -1) {
                    index = 2;
                }
                changeAlpha(mImages[index]);
                break;
            case R.id.img_right:
                index++;
                if (index == mImages.length) {
                    index = 0;
                }
                changeAlpha(mImages[index]);
                break;
        }
    }


    public void changeAlpha(int mImage) {

        Animation alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_hide);
        headerImg.startAnimation(alphaAnimation);//开始动画
        alphaAnimation.setFillAfter(false);//动画结束后保持状态
        //添加动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时回调
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                changeTAlpha();
                //动画结束时回调
                headerImg.setImageResource(mImages[index]);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时回调
            }
        });
    }


    public void changeTAlpha() {

        Animation alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.talpha_show);
        headerImg.startAnimation(alphaAnimation);//开始动画
        alphaAnimation.setFillAfter(false);//动画结束后保持状态
        //添加动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时回调
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时回调
//                changeTAlpha();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时回调
            }
        });
    }
}
