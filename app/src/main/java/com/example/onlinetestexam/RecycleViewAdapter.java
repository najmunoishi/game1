package com.example.onlinetestexam;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{

    private static ClickListener clickListener;

    private Context context;
    List<DataModel> dataModels;



    public RecycleViewAdapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycle_layout, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel data = dataModels.get(position);
        holder.textView.setText(data.getCourseName());
        Picasso.get().load(data.getImage()).into(holder.imgView);

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView imgView;
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textView=itemView.findViewById(R.id.SampleTextId);
            imgView= itemView.findViewById(R.id.SampleImageId);


            itemView.setOnClickListener(this);



        }


        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(),v);

        }
    }

    public  interface ClickListener{


        void onItemClick(int position, View v);


    }
    public void setOnItemClickListener(ClickListener clickListener)
    {
        RecycleViewAdapter.clickListener=clickListener;

    }


}
