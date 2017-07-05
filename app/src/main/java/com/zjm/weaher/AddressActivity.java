package com.zjm.weaher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AddressActivity extends AppCompatActivity {
    private ListView listView;
    private Button btnBack;
    private TextView tvTitleCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        initview();



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
