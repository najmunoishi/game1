package com.example.onlinetestexam;

import android.content.Context;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class QuestionRecycleViewAdapter extends RecyclerView.Adapter<QuestionRecycleViewAdapter.ViewHolder>{

    private static  QuestionClick questionClick;


    private Context context;
    List<QuestionDataModel> questiondataModels;



    public QuestionRecycleViewAdapter(Context context, List<QuestionDataModel> questiondataModels) {
        this.context = context;
        this.questiondataModels = questiondataModels;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.question_pattern_row, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionDataModel data = questiondataModels.get(position);
        holder.textView.setText(data.getQuestion());
        holder.op1.setText(data.getA());
        holder.op2.setText(data.getB());
        holder.op3.setText(data.getC());
        holder.op4.setText(data.getD());




    }

    @Override
    public int getItemCount() {
        return questiondataModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        public TextView textView;
        public RadioButton op1,op2,op3,op4;
        public RadioGroup radiogroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.SoftEasyQuizQuestion);
            op1 = itemView.findViewById(R.id.softEasyOp1);
            op2 = itemView.findViewById(R.id.softEasyOp2);
            op3 = itemView.findViewById(R.id.softEasyOp3);
            op4 = itemView.findViewById(R.id.softEasyOp4);


            View view1=(View) itemView.getParent();
            //View view2=(View) view1.getParent();

           // radiogroup=view1.findViewById(R.id.radioGroup);
            itemView.setOnClickListener(this);

           // Log.i("check1",radioGroup.getCheckedRadioButtonId()+"");

        }

        @Override
        public void onClick(View v) {
            questionClick.onItemClick(getAdapterPosition(),v);

        }
    }

    public interface QuestionClick{

        void onItemClick(int position,View v);

    }

public void setOnItemClick(QuestionRecycleViewAdapter.QuestionClick questionClick)
{
    QuestionRecycleViewAdapter.questionClick=questionClick;

}


}
