package com.example.fragmentrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    public Context context;
    List<ListModel> list;

    public ListAdapter(Context context, List<ListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvList.setText("ID: " + list.get(position).getId() + "\n" +
                "User ID: " + list.get(position).getUserId() + "\n" +
                "Title: " + list.get(position).getTitle() + "\n" +
                "Text: " + list.get(position).getBody() + "\n\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvList;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvList = itemView.findViewById(R.id.tvList);
        }
    }
}
