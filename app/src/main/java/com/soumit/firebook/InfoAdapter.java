package com.soumit.firebook;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.soumit.firebook.model.SingleInfo;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    private static final String TAG = "InfoAdapter";
    private Context context;
    private List<SingleInfo> infoList;

    public InfoAdapter(Context context, List<SingleInfo> infoList) {
        Log.d(TAG, "InfoAdapter: called--------------------");
        this.context = context;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snippet_info_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        SingleInfo info = infoList.get(position);
        holder.name.setText(info.getName());
        holder.email.setText(info.getEmail());
        holder.address.setText(info.getAddress());
        Glide.with(context).load(info.getImageLink()).into(holder.listImage);
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private TextView address;
        private ImageView listImage;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            address = view.findViewById(R.id.address);
            listImage = view.findViewById(R.id.list_image);
        }
    }

}
