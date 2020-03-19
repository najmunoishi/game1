package com.example.onlinetestexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class newAdapter extends RecyclerView.Adapter<newAdapter.myviewholder>{

    private Context context;
    List<EeeDataModel> eeedataModels;
    private static ClickSoftListener clickSoftListener;


    public newAdapter(Context context, List<EeeDataModel> eeedataModels) {
        this.context = context;
        this.eeedataModels = eeedataModels;
    }

    public newAdapter() {

    }

    @NonNull
    @Override
    public newAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  return null;
        View view = LayoutInflater.from(context).inflate(R.layout.sample_row, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newAdapter.myviewholder holder, int position) {

        EeeDataModel data = eeedataModels.get(position);
        holder.textView.setText(data.getCourseName());
    }

    @Override
    public int getItemCount() {
        return eeedataModels.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.EeeDashSampleTextId);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            clickSoftListener.onItemClick(getAdapterPosition(),v);

        }
    }
    public interface ClickSoftListener{
        void onItemClick(int position, View v);


    }
    public void setOnItemClickListener(newAdapter.ClickSoftListener clickSoftListener)
    {
        newAdapter.clickSoftListener=clickSoftListener;

    }
}
