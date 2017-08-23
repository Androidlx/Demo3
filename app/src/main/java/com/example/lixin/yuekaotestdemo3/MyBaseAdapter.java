package com.example.lixin.yuekaotestdemo3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.limxing.xlistview.view.XListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua on 2017/8/23.
 */

public class MyBaseAdapter extends BaseAdapter {

    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .showImageOnLoading(R.drawable.loading)
            .build();

    private List<PicInfo.ResultBean.DataBean> data;
    private Context context;
    private XListView xlistview;
    public MyBaseAdapter(List<PicInfo.ResultBean.DataBean> data, Context context, XListView xlistview){
        this.data = data;
        this.context = context;
        this.xlistview = xlistview;
    }

    public void loadMore(List<PicInfo.ResultBean.DataBean> datas,boolean flag){
        for (PicInfo.ResultBean.DataBean bean:datas) {
            if (flag){
                data.add(0,bean);
            }else {
                data.add(bean);
            }
        }
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = View.inflate(context,R.layout.item,null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(R.mipmap.ic_launcher);
        ImageLoader.getInstance().displayImage(data.get(position).getThumbnail_pic_s(),holder.iv,options);



        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ArrayList<String> list = new ArrayList<>();
                list.add(data.get(position-1).getThumbnail_pic_s());
                list.add(data.get(position-1).getThumbnail_pic_s02());
                list.add(data.get(position - 1).getThumbnail_pic_s03());
                Intent intent = new Intent(context,PicActivity.class);
                intent.putStringArrayListExtra("key", list);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
    }
}
