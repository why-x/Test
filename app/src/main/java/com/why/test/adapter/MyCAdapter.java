package com.why.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.why.test.R;
import com.why.test.bean.MyCar;
import com.why.test.core.GoodsCheck;


import java.util.ArrayList;
import java.util.List;

public class MyCAdapter extends RecyclerView.Adapter<MyCAdapter.MyHolder> {
    GoodsCheck goodsCheck;
    Context context;
    List<MyCar.ListBean> lists=new ArrayList<>();
    public MyCAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.c_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        final MyCar.ListBean listBean = lists.get(position);
        String images = listBean.getImages();
        String[] split = images.split("!");
        holder.c_img.setImageURI(split[0]);
        holder.price.setText("￥"+listBean.getPrice()+"");
        holder.title.setText(listBean.getTitle());
        holder.checkBox.setChecked(listBean.isChecked());

        holder.num.setText(String.valueOf(listBean.getNum()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setNum(listBean.getNum()+1);
                if (holder.checkBox.isChecked()){
                    goodsCheck.addGood();
                }
                notifyDataSetChanged();
            }
        });
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (0==listBean.getNum()){
                    return;
                }
                listBean.setNum(listBean.getNum()-1);
                if (holder.checkBox.isChecked()){
                    goodsCheck.reduceGood();
                }
                notifyDataSetChanged();
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setChecked(holder.checkBox.isChecked());
                goodsCheck.itemcheck();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<MyCar.ListBean> datalist) {
        if (datalist!=null){
            lists.clear();
            lists=datalist;
            notifyDataSetChanged();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView c_img;
        TextView title,price,num;
        CheckBox checkBox;
        Button add,reduce;
        public MyHolder(View itemView) {
            super(itemView);
            c_img=itemView.findViewById(R.id.c_img);
            title=itemView.findViewById(R.id.c_title);
            price=itemView.findViewById(R.id.c_price);
            checkBox=itemView.findViewById(R.id.c_check);
            add=itemView.findViewById(R.id.add);
            reduce=itemView.findViewById(R.id.reduce);
            num=itemView.findViewById(R.id.num);


        }
    }

    public void setGoodsCheck(GoodsCheck goodsCheck) {
        this.goodsCheck = goodsCheck;
    }

}
