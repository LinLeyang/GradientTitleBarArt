package com.penta.gradienttitlebarart;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by fish on 16/6/4.
 */
public class FishAdapter extends RecyclerView.Adapter<FishViewHolder> {
    private List<String> data;
    private LayoutInflater inflater;
    private Context context;

    public FishAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FishViewHolder holder = new FishViewHolder(inflater.inflate(
                R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(FishViewHolder holder, int position) {
        if (position > 2) {
            holder.tv.setBackgroundResource(R.color.white);
        } else {
            holder.tv.setBackgroundResource(R.color.colorPrimary);
        }
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<String> pDatas) {
        data = pDatas;
    }
}


class FishViewHolder extends RecyclerView.ViewHolder {

    TextView tv;

    public FishViewHolder(View view) {
        super(view);
        tv = (TextView) view.findViewById(R.id.id_num);
    }
}
