package com.why.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.why.test.MainActivity;
import com.why.test.R;
import com.why.test.bean.MyCar;
import com.why.test.bean.Result;
import com.why.test.core.GoodsCheck;

import java.util.ArrayList;
import java.util.List;

public class MyFAdapter extends RecyclerView.Adapter<MyFAdapter.MyHolder> implements GoodsCheck{
    GoodsCheck goodsCheck;

    Context context;
    List<MyCar> list = new ArrayList<>();


    public MyFAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.f_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MyCar myCar = list.get(position);
        MyCAdapter myCAdapter = new MyCAdapter(context);
        holder.child_re.setLayoutManager(new LinearLayoutManager(context));
        myCAdapter.setData(myCar.getList());
        /*

         */
        myCAdapter.setGoodsCheck(this);
        /*

         */
        holder.child_re.setAdapter(myCAdapter);
        holder.name.setText(myCar.getSellerName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(Result<List<MyCar>> data) {
        if (data!=null){
            list.addAll(data.getData());
        }
    }

    @Override
    public void addGood() {
        goodsCheck.checked();
    }

    @Override
    public void reduceGood() {
        goodsCheck.checked();

    }

    @Override
    public void itemcheck() {
        goodsCheck.checked();

    }

   /* public void addAll(List<MyCar> data1) {
        if (data1!=null){
            list.addAll(data1);
        }
    }*/

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        RecyclerView child_re;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.shop_name);
            child_re = itemView.findViewById(R.id.child_recycler);
        }
    }

    //    public interface

    public interface GoodsCheck {
        void checked();//监听接口里的三个方法 自定义接口回调 可以直接返回主界面

    }

    public void setGoodsCheck(GoodsCheck goodsCheck) {
        this.goodsCheck = goodsCheck;
    }
}
