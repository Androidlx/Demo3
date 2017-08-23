package com.example.lixin.yuekaotestdemo3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.limxing.xlistview.view.XListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by hua on 2017/8/23.
 */

public class MyFragment extends Fragment implements XListView.IXListViewListener {

    private View view;
    private XListView xListView;
    private MyBaseAdapter adapter;
    private int index = 1;
    private boolean flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xListView = (XListView) view.findViewById(R.id.xlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        getData();
    }

    private void getData() {
        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index");
        params.addQueryStringParameter("key","5b6258c74f4346147b12fe38490a12b2");
        params.addQueryStringParameter("type","top");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                PicInfo picInfo = gson.fromJson(result, PicInfo.class);
                List<PicInfo.ResultBean.DataBean> data = picInfo.getResult().getData();

                if (adapter == null){
                    adapter = new MyBaseAdapter(data,getActivity(),xListView);
                    xListView.setAdapter(adapter);
                }else {
                    adapter.loadMore(data,flag);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onRefresh() {
        index++;
        getData();
        flag = true;
        xListView.stopRefresh(true);
    }

    @Override
    public void onLoadMore() {
        index++;
        getData();
        flag = false;
        xListView.stopLoadMore();
    }
}
