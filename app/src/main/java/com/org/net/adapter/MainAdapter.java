package com.org.net.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.org.net.Bean.ClassBean;
import com.org.net.R;

import java.util.List;

/**
 * Created by guozhk
 * create time on 2017/12/27.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ClassBean> mData;

    public MainAdapter(List<ClassBean> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_list_item, viewGroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MainViewHolder holder = (MainViewHolder) viewHolder;
        holder.getTvName().setText(mData.get(i).getDes());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MainViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView tvName;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.getContext().startActivity(new Intent(view.getContext(),
                            mData.get(getLayoutPosition()).getaClass()));

                    Log.e("adapter", "pos:" + getLayoutPosition());
                }
            });

            tvName = (TextView) view.findViewById(R.id.item_name);
        }

        public TextView getTvName() {
            return tvName;
        }
    }


}
