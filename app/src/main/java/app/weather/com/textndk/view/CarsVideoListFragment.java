package app.weather.com.textndk.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.textndk.R;
import app.weather.com.textndk.bean.CarsDataBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by mengxj on 2018/5/15.
 */

public class CarsVideoListFragment extends Fragment {

    public int firstVisible=0,visibleCount=0, totalCount=0;

    @Bind(R.id.videoList)
    ListView videoList;
    private  List<CarsDataBean> dataBean
        =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        CarsDataBean dataBean1;
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2018/05/09/10c66700287c7e48-sd.mp4","1","东风雪铁龙云逸SUV 你的互联网专属SUV"
                ,"易车视频","http://img4.bitautoimg.com/newsimg-480-w0/Video/2017/11/02/2017112171224908.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2018/05/04/222239b35993377d-sd.mp4","2","【视频】立夏驾驭第二代CX-5，寻找最纯净的声音_行业视频_新闻中心_易车"
                ,"全球汽车时报","http://p2.so.qhimgs1.com/bdr/_240_/t011e21882ac2c99aae.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2014/2017/10/23/070a908e33944ab3-sd.mp4","3","【视频】寅子试驾全新换代质感升级的马自达CX-5_行业视频_新闻中心_易车"
                ,"寅子试驾","http://img4.bitautoimg.com/newsimg-300-w0/Video/2017/10/23/20171023221635481.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2018/05/09/10c66700287c7e48-sd.mp4","1","东风雪铁龙云逸SUV 你的互联网专属SUV"
                ,"易车视频","http://img4.bitautoimg.com/newsimg-480-w0/Video/2017/11/02/2017112171224908.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2018/05/04/222239b35993377d-sd.mp4","2","【视频】立夏驾驭第二代CX-5，寻找最纯净的声音_行业视频_新闻中心_易车"
                ,"全球汽车时报","http://p2.so.qhimgs1.com/bdr/_240_/t011e21882ac2c99aae.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2014/2017/10/23/070a908e33944ab3-sd.mp4","3","【视频】寅子试驾全新换代质感升级的马自达CX-5_行业视频_新闻中心_易车"
                ,"寅子试驾","http://img4.bitautoimg.com/newsimg-300-w0/Video/2017/10/23/20171023221635481.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2018/05/09/10c66700287c7e48-sd.mp4","1","东风雪铁龙云逸SUV 你的互联网专属SUV"
                ,"易车视频","http://img4.bitautoimg.com/newsimg-480-w0/Video/2017/11/02/2017112171224908.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2018/05/04/222239b35993377d-sd.mp4","2","【视频】立夏驾驭第二代CX-5，寻找最纯净的声音_行业视频_新闻中心_易车"
                ,"全球汽车时报","http://p2.so.qhimgs1.com/bdr/_240_/t011e21882ac2c99aae.jpg"));
        dataBean.add(new CarsDataBean("http://flv.bitauto.com/2014/2017/10/23/070a908e33944ab3-sd.mp4","3","【视频】寅子试驾全新换代质感升级的马自达CX-5_行业视频_新闻中心_易车"
                ,"寅子试驾","http://img4.bitautoimg.com/newsimg-300-w0/Video/2017/10/23/20171023221635481.jpg"));
//        recyclerView.addOnScrollListener
        videoList.setAdapter(new VideoListAdapter(getActivity()));
        videoList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.e("videoTest", "SCROLL_STATE_FLING");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.e("videoTest", "SCROLL_STATE_IDLE");
                        autoPlayVideo(view);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.e("videoTest", "SCROLL_STATE_TOUCH_SCROLL");

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // firstVisibleItem   当前第一个可见的item
                // visibleItemCount   当前可见的item个数
                if (firstVisible == firstVisibleItem) {
                    return;
                }
                firstVisible = firstVisibleItem;
                visibleCount = visibleItemCount;
                totalCount = totalItemCount;
            }
        });



    }


    void autoPlayVideo(AbsListView view){
        Log.e("videoTest", "firstVisiblePos  =  " + firstVisible + "visibleItemCount =  " + visibleCount);
        for (int i = 0; i < visibleCount; i++) {
            if (view!=null&&view.getChildAt(i)!=null&&view.getChildAt(i).findViewById(R.id.videoplayer) != null) {
                JCVideoPlayerStandard videoPlayerStandard1 = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.videoplayer);
                Rect rect = new Rect();
                videoPlayerStandard1.getLocalVisibleRect(rect);
                int videoheight3 = videoPlayerStandard1.getHeight();
                Log.e("videoTest","i="+i+"==="+"videoheight3:"+videoheight3+"==="+"rect.top:"+rect.top+"==="+"rect.bottom:"+rect.bottom);
                if (rect.top==0&&rect.bottom==videoheight3)
                {
                    if (videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                        Log.e("videoTest", videoPlayerStandard1.currentState + "======================performClick======================");
                        videoPlayerStandard1.startButton.performClick();
                        App.sInstance.VideoPlaying=videoPlayerStandard1;
                    }
                    return;
                }

            }
        }
        Log.e("videoTest", "======================releaseAllVideos=====================");
        JCVideoPlayer.releaseAllVideos();
        App.sInstance.VideoPlaying=null;
    }

    public class VideoListAdapter extends BaseAdapter {

        int[] videoIndexs = {0,2,1,2,1,0,2,1,0,0,1,2,1,0,1};
        Context context;
        LayoutInflater mInflater;

        public VideoListAdapter(Context context) {

            this.context = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return dataBean.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //This is the point
            if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
                ((VideoHolder) convertView.getTag()).jcVideoPlayer.release();
            }

            VideoHolder viewHolder;
            if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
                viewHolder = (VideoHolder) convertView.getTag();
            } else {
                viewHolder = new VideoHolder();
                convertView = mInflater.inflate(R.layout.item_videoview, null);
                viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
                viewHolder.g_tv = (GradientColorTextView) convertView.findViewById(R.id.g_tv);
                convertView.setTag(viewHolder);
            }

//                http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4
            viewHolder.g_tv.setText(dataBean.get(position).getTitle());
            boolean setUp = viewHolder.jcVideoPlayer.setUp(
                    dataBean.get(position).getUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            if (setUp) {
                Glide.with(getActivity()).load(dataBean.get(position).getPicUrl()).into(viewHolder.jcVideoPlayer.thumbImageView);
            }




//            if (videoIndexs[position] == 1) {
//                VideoHolder viewHolder;
//                if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
//                    viewHolder = (VideoHolder) convertView.getTag();
//                } else {
//                    viewHolder = new VideoHolder();
//                    convertView = mInflater.inflate(R.layout.item_videoview, null);
//                    viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
//                    viewHolder.g_tv = (GradientColorTextView) convertView.findViewById(R.id.g_tv);
//                    convertView.setTag(viewHolder);
//                }
//
////                http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4
//                viewHolder.g_tv.setText("全球汽车时报");
//                boolean setUp = viewHolder.jcVideoPlayer.setUp(
//                        "http://t.cn/Ru7MaGQ", JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                        "");
//                if (setUp) {
//                    Glide.with(getActivity()).load("http://p2.so.qhimgs1.com/bdr/_240_/t011e21882ac2c99aae.jpg").into(viewHolder.jcVideoPlayer.thumbImageView);
//                }
//            } else if(videoIndexs[position] == 2){
//
//                VideoHolder viewHolder;
//                if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
//                    viewHolder = (VideoHolder) convertView.getTag();
//                } else {
//                    viewHolder = new VideoHolder();
//                    convertView = mInflater.inflate(R.layout.item_videoview, null);
//                    viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
//                    viewHolder.g_tv = (GradientColorTextView) convertView.findViewById(R.id.g_tv);
//                    convertView.setTag(viewHolder);
//                }
//                viewHolder.g_tv.setText("汽车梦工场");
//                boolean setUp = viewHolder.jcVideoPlayer.setUp(
//                        "http://111.26.155.64/v.cctv.com/flash/mp4video6/TMS/2011/01/05/cf752b1c12ce452b3040cab2f90bc265_h264818000nero_aac32-1.mp4?wsrid_tag=5afaa35f_PSjlzcydcu63_32574-18269&wsiphost=local", JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                        "");
//                if (setUp) {
//                    Glide.with(getActivity()).load("http://p3.so.qhimgs1.com/bdr/_240_/t013053f79634e11d93.jpg").into(viewHolder.jcVideoPlayer.thumbImageView);
//                }
//            }
//
//
//            else {
//
//                VideoHolder viewHolder;
//                if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof VideoHolder) {
//                    viewHolder = (VideoHolder) convertView.getTag();
//                } else {
//                    viewHolder = new VideoHolder();
//                    convertView = mInflater.inflate(R.layout.item_videoview, null);
//                    viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
//                    viewHolder.g_tv = (GradientColorTextView) convertView.findViewById(R.id.g_tv);
//
//                    convertView.setTag(viewHolder);
//                }
//
//                boolean setUp = viewHolder.jcVideoPlayer.setUp(
//                        "http://vjs.zencdn.net/v/oceans.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                        "");
//                if (setUp) {
//                    Glide.with(getActivity()).load("http://img0.imgtn.bdimg.com/it/u=2933143552,3912159480&fm=27&gp=0.jpg").into(viewHolder.jcVideoPlayer.thumbImageView);
//                }
//                ImageViewHolder imageViewHolder;
//                if (convertView != null && convertView.getTag() != null && convertView.getTag() instanceof ImageViewHolder) {
//                    imageViewHolder = (ImageViewHolder) convertView.getTag();
//                } else {
//                    imageViewHolder = new ImageViewHolder();
//                    LayoutInflater mInflater = LayoutInflater.from(context);
//                    convertView = mInflater.inflate(R.layout.item_textview, null);
//                    imageViewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
//                    Glide.with(getActivity()).load("http://img04.tooopen.com/images/20131019/sy_43185978222.jpg").into(imageViewHolder.imageView);
//                    convertView.setTag(imageViewHolder);
//                }
//            }
            return convertView;
        }

        class VideoHolder {
            JCVideoPlayerStandard jcVideoPlayer;
            GradientColorTextView g_tv;
        }

        class ImageViewHolder {
            ImageView imageView;
        }
    }


    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
