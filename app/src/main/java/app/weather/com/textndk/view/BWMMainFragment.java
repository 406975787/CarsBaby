package app.weather.com.textndk.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.textndk.R;
import app.weather.com.textndk.banner.Banner;
import app.weather.com.textndk.banner.BannerAdapter;
import app.weather.com.textndk.banner.SmartViewPager;
import app.weather.com.textndk.utils.ImageUtil;
import app.weather.com.textndk.utils.ScreenUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BWMMainFragment extends Fragment {


    @Bind(R.id.ly_title)
    LinearLayout lyTitle;
    @Bind(R.id.toolbar_main)
    Toolbar toolbarMain;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.baomagainian)
    ImageView baomagainian;
    @Bind(R.id.baoma5xi)
    ImageView baoma5xi;
    @Bind(R.id.baomam5)
    ImageView baomam5;
    @Bind(R.id.baomax2)
    ImageView baomax2;
    @Bind(R.id.banner)
    SmartViewPager banner;
    @Bind(R.id.ly_banner)
    LinearLayout lyBanner;
    @Bind(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    @Bind(R.id.layout)
    CoordinatorLayout layout;
    private LiveBannerAdapter adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_bg, container, false);
        ButterKnife.bind(this, view);
//        mRecyclerView = view.findViewById(R.id.id_recyclerview);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }

        final List<Banner> banners = new ArrayList<>();

        Banner bannerData = new Banner();
        bannerData.setImg("http://www.bmw.com.cn/content/dam/bmw/marketCN/common/HomepageTeaser/Medium/2018/calculator.jpg/_jcr_content/renditions/cq5dam.resized.img.585.low.time1518076035208.jpg");
        bannerData.setLink("http://i0.hdslb.com/bfs/vc/427111bb9d49cb558238451a38d385583c083101.jpg");
        bannerData.setTitle("岩井俊二和他的乐队Hectopascal直播");

        Banner bannerData1 = new Banner();
        bannerData1.setImg("http://www.bmw.com.cn/content/dam/bmw/marketCN/common/HomepageTeaser/Large/homepage-teaser-mission-i-20180323.jpg/_jcr_content/renditions/cq5dam.resized.img.1680.large.time1522320020810.jpg");
        bannerData1.setLink("https://www.bilibili.com/blackboard/activity-singh5.html");
        bannerData1.setTitle("红人的歌声，你听过没？");

        Banner bannerData2 = new Banner();
        bannerData2.setImg("http://www.bmw.com.cn/content/dam/bmw/marketCN/common/HomepageTeaser/Medium/2018/find.jpg/_jcr_content/renditions/cq5dam.resized.img.585.low.time1518076034475.jpg");
        bannerData2.setLink("https://www.bilibili.com/blackboard/activity-svh5.html");
        bannerData2.setTitle("《影之诗》直播开启华丽对决！");

        banners.add(0, bannerData);
        banners.add(1, bannerData1);
        banners.add(2, bannerData2);

        //是否是圆角
        banner.setNeedCirculate(true);
        banner.setNeedAutoScroll(true);
        //提示点的位置
        banner.setIndicatorGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        //提示点选中的状态颜色
        banner.setIndicatorColor(ContextCompat.getColor(getActivity(), R.color.white),
                ContextCompat.getColor(getActivity(), R.color.pink));
        int height = getActivity().getResources().getDimensionPixelSize(R.dimen.top_banner_height);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        banner.setLayoutParams(params);
        adapter = new LiveBannerAdapter(getActivity());
        //赋值图片展示
        setBannerData(banners);

        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        final CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("宝马时尚中国");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT);//设置收缩后Toolbar上字体的颜色

        appbarlayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {
                Log.e("www", "mCollapsingToolbarLayout===>>" + state.name());
                Log.e("www", "int i===>>" + i);
                toolbarMain.setVisibility(View.VISIBLE);
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

        toolbarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                MoveToPosition((LinearLayoutManager) mRecyclerView.getLayoutManager(), mRecyclerView, 0);
            }
        });


//        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(getActivity()));
//
//        mRecyclerView.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item, mockDatas) {
//            @Override
//            protected void convert(ViewHolder holder, String o, int position) {
//
//                if (position > 0 && position % 7 == 0) {
//                    holder.setVisible(R.id.id_tv_title, false);
//                    holder.setVisible(R.id.id_tv_desc, false);
//                    holder.setVisible(R.id.id_iv_ad, true);
//
//                } else {
//                    holder.setVisible(R.id.id_tv_title, true);
//                    holder.setVisible(R.id.id_tv_desc, true);
//                    holder.setVisible(R.id.id_iv_ad, false);
//
//                }
//            }
//        });
//
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
//                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
//                for (int i = fPos; i <= lPos; i++) {
//                    View view = mLinearLayoutManager.findViewByPosition(i);
//                    AdImageView adImageView = view.findViewById(R.id.id_iv_ad);
//                    if (adImageView.getVisibility() == View.VISIBLE) {
//                        adImageView.setDx(mLinearLayoutManager.getHeight() - view.getTop());
//                    }
//                }
//            }
//        });
    }


    private void setBannerData(List<Banner> data) {
        adapter.setData(data, true);
        banner.setAdapter(adapter);
    }


    public void changeAlpha() {

        Animation alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
        lyBanner.startAnimation(alphaAnimation);//开始动画
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
//                Toast.makeText(MainActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
                lyBanner.setVisibility(View.GONE);
//                lyBanner.setAnimation(AnimationUtil.moveToViewLocation());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时回调
            }
        });
    }


    public void changeTAlpha() {

        lyBanner.setVisibility(View.VISIBLE);
        Animation alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.talpha);
        lyBanner.startAnimation(alphaAnimation);//开始动画
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
//                Toast.makeText(MainActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
                lyBanner.setVisibility(View.VISIBLE);
//                lyBanner.setAnimation(AnimationUtil.moveToViewBottom());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时回调
            }
        });
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    static class LiveBannerAdapter extends BannerAdapter<Banner, SimpleDraweeView> {

        private Context context;

        public LiveBannerAdapter(Context ctx) {
            context = ctx;
        }

        @Override
        protected int getLayoutId() {
            return INVALID_ID;
        }

        @Override
        protected SimpleDraweeView getItemView() {

            SimpleDraweeView imageView = new SimpleDraweeView(context);
            GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(imageView.getContext().getResources())
                    .setRoundingParams(RoundingParams.fromCornersRadius(5))
                    .build();
            imageView.setHierarchy(hierarchy);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            return imageView;
        }

        @Override
        protected void bindData(SimpleDraweeView itemView, Banner item) {
            int width = ScreenUtil.getScreenWidth(context);
            int height = context.getResources().getDimensionPixelSize(R.dimen.top_banner_height);
            ImageUtil.load(itemView, item.getImg(), width, height);
        }
    }
}