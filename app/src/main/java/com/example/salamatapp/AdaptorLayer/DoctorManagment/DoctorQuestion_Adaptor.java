package com.example.salamatapp.AdaptorLayer.DoctorManagment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.salamatapp.DoctorManagment.ManagmentDoctorDragActivity;
import com.example.salamatapp.DoctorManagment.ManagmentDoctorQuestion2Activity;
import com.example.salamatapp.DomainLayer.Doctor.DoctorDrag;
import com.example.salamatapp.DomainLayer.Doctor.DoctorQuestion;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.DoctorQuestionApiService;

import java.util.List;

public class DoctorQuestion_Adaptor extends RecyclerView.Adapter<DoctorQuestion_Adaptor.DoctorQuestion_ViewHolder> {


    private Context context;
    private List<DoctorQuestion> allquestion;
    private DoctorQuestionApiService _doctorquestion;
    private Intent i;

    public DoctorQuestion_Adaptor(Context context, List<DoctorQuestion> allquestion){
        this.context = context;
        this.allquestion = allquestion;
    }

    @NonNull
    @Override
    public DoctorQuestion_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_doctor_question_managment,viewGroup,false);
        return new DoctorQuestion_Adaptor.DoctorQuestion_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorQuestion_ViewHolder doctorQuestion_viewHolder, int i) {

        DoctorQuestion question=allquestion.get(i);
        doctorQuestion_viewHolder.txtname.setText(question.getText());
    }

    @Override
    public int getItemCount() {
        return allquestion.size();
    }


    public class DoctorQuestion_ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname;
        Button btndelete;
        public DoctorQuestion_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txtnamquestionmanagment);

            btndelete=(Button)itemView.findViewById(R.id.btndeletequestionmanagment);

            _doctorquestion=new DoctorQuestionApiService(context);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    _doctorquestion.postdeletequestion(allquestion.get(getAdapterPosition()).getId(), new DoctorQuestionApiService.OnDoctorQuestionReceived() {
                        @Override
                        public void onReceived(String checkadddelete) {
                            i = new Intent(context, ManagmentDoctorQuestion2Activity.class);
                            context.startActivity(i);
                            ((Activity)context).finish();
                        }
                    });
                }
            });



        }

    }
}



