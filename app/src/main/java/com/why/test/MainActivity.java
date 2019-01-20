package com.why.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.why.test.adapter.MyFAdapter;
import com.why.test.bean.MyCar;
import com.why.test.bean.Result;
import com.why.test.core.DataCall;
import com.why.test.presenter.MyCarPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyFAdapter.GoodsCheck{
    @BindView(R.id.sum)
    TextView mysum;
    private List<MyCar> data1;

    @BindView(R.id.f_recycler)
    RecyclerView fRecycler;
    @BindView(R.id.f_check)
    CheckBox fCheck;
    @BindView(R.id.f_price)
    Button fPrice;
    private MyFAdapter myFAdapter;
    private MyCarPresenter myCarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fRecycler.setLayoutManager(new LinearLayoutManager(this));
        myFAdapter = new MyFAdapter(this);
        /*

         */
        myFAdapter.setGoodsCheck(this);
        /*

         */
        fRecycler.setAdapter(myFAdapter);
        myCarPresenter = new MyCarPresenter(new MyCarCall());
        myCarPresenter.request(71);
        fCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = fCheck.isChecked();
                for (int i = 0; i < data1.size(); i++) {
                    for (int j = 0; j < data1.get(i).getList().size(); j++) {
                        data1.get(i).getList().get(j).setChecked(checked);
                    }
                }
                if (checked) {
                    totalPrice();
                } else {
                    mysum.setText("¥0.0");
                    fPrice.setText("去结算（0)");
                }
                myFAdapter.notifyDataSetChanged();
            }
        });

    }

    private void totalPrice() {
        double sum=0;
        int num=0;
        for (int i = 0; i < data1.size(); i++) {
            for (int j = 0; j < data1.get(i).getList().size(); j++) {
                if ( data1.get(i).getList().get(j).isChecked()){
                    Log.i("++++++++++++", "getSum: "+data1.get(i).getList().get(j).getNum()*data1.get(i).getList().get(j).getPrice());
                    Log.i("++++++++++++", "getSum: "+data1.get(i).getList().get(j).getNum());
                    sum+= data1.get(i).getList().get(j).getNum()*data1.get(i).getList().get(j).getPrice();
                    num+= data1.get(i).getList().get(j).getNum();
                }
            }
        }
        mysum.setText("¥"+sum+"");
        fPrice.setText("去结算（"+num+")");

    }

    @Override
    public void checked() {
        totalPrice();
        int chnum=0;
        for (int i = 0; i < data1.size(); i++) {
            for (int j = 0; j < data1.get(i).getList().size(); j++) {
                if (data1.get(i).getList().get(j).isChecked()) {
                    chnum++;
                }
            }
        }
        if (chnum==data1.size()){
            fCheck.setChecked(true);
        }else {
            fCheck.setChecked(false);

        }
    }



    private class MyCarCall implements DataCall<Result<List<MyCar>>> {


        @Override
        public void success(Result<List<MyCar>> data) {
            data1 = data.getData();
            myFAdapter.addAll(data);
            myFAdapter.notifyDataSetChanged();

        }

        @Override
        public void fail(Result result) {
            Toast.makeText(getBaseContext(), "失败了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myCarPresenter.unbind();
    }
}
