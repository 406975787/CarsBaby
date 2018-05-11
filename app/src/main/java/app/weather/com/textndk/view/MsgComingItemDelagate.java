//package app.weather.com.textndk.view;
//
//import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
//import com.zhy.adapter.recyclerview.base.ViewHolder;
//
//import app.weather.com.textndk.R;
//import app.weather.com.textndk.banner.Banner;
//
///**
// * Created by mengxj on 2018/5/11.
// */
//
//public class MsgComingItemDelagate implements ItemViewDelegate<Banner>
//{
//
//    @Override
//    public int getItemViewLayoutId()
//    {
//        return R.layout.main_chat_from_msg;
//    }
//
//
//    @Override
//    public boolean isForViewType(Banner item, int position)
//    {
////        return item.isComMeg();
//        return false;
//    }
//
//    @Override
//    public void convert(ViewHolder holder, Banner chatMessage, int position)
//    {
//        holder.setText(R.id.chat_from_content, chatMessage.getContent());
//        holder.setText(R.id.chat_from_name, chatMessage.getName());
//        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
//    }
//}