package com.zjm.weaher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_addCity;
    private TextView tv_titleWeatherCity;
    private Button btn_home;

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //设置监听
        setListener();




    }

    /**
     * 给控件设置监听器
     */
    private void setListener() {
        btn_addCity.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_addCity = (Button) findViewById(R.id.btn_add_city);
        tv_titleWeatherCity = (TextView) findViewById(R.id.tv_title_weathercity);
        btn_home = (Button) findViewById(R.id.btn_home);
    }

    /**
     * 按钮的监听方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_city:
                if(intent==null){
                    intent = new Intent(this,AddressActivity.class);
                }
                startActivity(intent);
                break;
        }
    }
}
