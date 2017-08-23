package com.example.lixin.yuekaotestdemo3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by hua on 2017/8/23.
 */

public class PicActivity extends AppCompatActivity {

    private ArrayList<String> list1;
    private ArrayList<View> list = new ArrayList<>();
    private PhotoViewpager viewpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        viewpager = (PhotoViewpager) findViewById(R.id.viewpager);
        Intent intent = getIntent();
        list1 = intent.getStringArrayListExtra("key");
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();

        PhotoView p1 = new PhotoView(PicActivity.this);
        ImageLoader.getInstance().displayImage(list1.get(0),p1,options);


        PhotoView p2= new PhotoView(PicActivity.this);
        ImageLoader.getInstance().displayImage(list1.get(0), p2, options);
        PhotoView p3 = new PhotoView(PicActivity.this);
        ImageLoader.getInstance().displayImage(list1.get(0),p3,options);
//        放入集合中
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Myadapter myadapter = new Myadapter();
        viewpager.setAdapter(myadapter);

    }

    class Myadapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }



}
