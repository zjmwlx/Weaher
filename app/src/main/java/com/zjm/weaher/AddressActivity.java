package com.zjm.weaher;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjm.weaher.db.Province;
import com.zjm.weaher.util.HttpUtil;
import com.zjm.weaher.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddressActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnBack;
    private TextView tvTitleCity;
    private Adapter adapter;
    private List<String> dataList = new ArrayList<>();
    private List<Province> provinces;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        //初始化控件
        initview();
        //给listview设置adapter
        setAdapter();
        //获取省级数据
        queryProvince();

    }

    /**
     * 获取省级数据，优先从数据库中查询，如果查询不到，就从网络获取
     */
    private void queryProvince() {
        tvTitleCity.setText("中国");
        btnBack.setVisibility(View.GONE);
        provinces = Province.findAll(Province.class);
        if(provinces.size()>0){
            dataList.clear();
            for (Province province:provinces) {
                dataList.add(province.getProvinceName());
            }
            adapter.notify();
            listView.setSelection(0);
        }else {
            String address = "http://guolin.tech/api/china";
            querFormService(address,"province");

        }


    }

    /**
     * 从网络获取省市县数据
     * @param address:请求地址
     * @param province：请求的数据类型
     */
    private void querFormService(String address,final String Type) {
        showProgressDialog();
        HttpUtil.sendOkhttpRequest(address, new Callback() {
            //请求失败的回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(AddressActivity.this,"请求失败，请检查网络连接",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responceText = response.body().string();
                boolean resule = false;
                if("province".equals(Type)){
                    resule = Utility.handlerProvinceResponse(responceText);
                }
                if (resule){
                    closeProgressDialog();
                }

            }
        });
    }

    /**
     * 显示进度条对话框
     */
    private void showProgressDialog() {
        if(progressDialog==null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载数据...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    /**
     * 给listview设置adapter
     */
    private void setAdapter() {
        if (adapter == null) {

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        }
        listView.setAdapter((ListAdapter) adapter);

    }

    /**
     * 初始化控件
     */
    private void initview() {
        listView = (ListView) findViewById(R.id.list_add);
        btnBack = (Button) findViewById(R.id.btn_add_back);
        tvTitleCity = (TextView) findViewById(R.id.tv_title_city);
    }
}
