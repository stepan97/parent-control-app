package com.example.userasef.parentcontrolapp.selectUserPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/9/2018.
 */

public class SelectUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChildUser> childUserList;
    private ISelectUserContract.View mView;
    private Context context;

    public SelectUserAdapter(ISelectUserContract.View view){ this.mView = view; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_name, parent, false);

        return new SelectUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChildUser item = childUserList.get(position);
        SelectUserViewHolder viewHolder = (SelectUserViewHolder) holder;
        viewHolder.name_TextView.setText(item.getName());

        // Setting underline
//        SpannableString content = new SpannableString(viewHolder.name_TextView.getText().toString());
//        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        viewHolder.name_TextView.setText(content);
    }

    @Override
    public int getItemCount() {
        return childUserList == null ? 0 : childUserList.size();
    }

    public void setChildUserList(ArrayList<ChildUser> list){
        childUserList = list;
//   todo:    notifyDataSetChanged();
        notifyDataSetChanged();
    }


    class SelectUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_TextView;

        public SelectUserViewHolder(View itemView) {
            super(itemView);

            name_TextView = itemView.findViewById(R.id.select_name_item_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ChildUser selectedItem = childUserList.get(getAdapterPosition());
            mView.gotoSelectedUserFragment(selectedItem);
        }
    }


}
