package com.example.salamatapp.AdaptorLayer.IllnessManagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salamatapp.DomainLayer.Illness.IllnessVisitList;
import com.example.salamatapp.DomainLayer.Question;
import com.example.salamatapp.ListDoctorActivity;
import com.example.salamatapp.NoskheManagmentPharmacyActivity;
import com.example.salamatapp.R;
import com.example.salamatapp.ServiceLayer.IllnessApiService;
import com.example.salamatapp.bottomActivity;

import java.util.List;

public class IllnessQuestion_Adaptor extends RecyclerView.Adapter<IllnessQuestion_Adaptor.IllnessQuestion_ViewHolder> {


    private Context context;
    private List<Question> allquestion;
    private Integer visitid;
    private IllnessApiService illnessapi;

    public IllnessQuestion_Adaptor(Context context, List<Question> allquestion,Integer visitid){
        this.context = context;
        this.allquestion = allquestion;
        this.visitid=visitid;
    }

    @NonNull
    @Override
    public IllnessQuestion_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_illness_question_managment,viewGroup,false);
        return new IllnessQuestion_Adaptor.IllnessQuestion_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IllnessQuestion_ViewHolder illnessQuestion_viewHolder, int i) {

        Question newquestion=allquestion.get(i);
        illnessQuestion_viewHolder.txtquestion.setText(newquestion.getText().toString());
        illnessQuestion_viewHolder.txtanswer.setText(newquestion.getAnswer().toString());


    }

    @Override
    public int getItemCount() {
        return allquestion.size();
    }


    public class IllnessQuestion_ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtquestion;
        private EditText txtanswer;
        private Button btnanswer;

        public IllnessQuestion_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtquestion=(TextView)itemView.findViewById(R.id.txtquestionofquestionlist);
            btnanswer=(Button)itemView.findViewById(R.id.btnsendanswerttoserverquestion);
            txtanswer=(EditText)itemView.findViewById(R.id.txtanswerofquestion);


            illnessapi=new IllnessApiService(context);
            btnanswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (allquestion.get(getAdapterPosition()).getHave_Answer()){
                        illnessapi.postUpdateAnswerQuestion(allquestion.get(getAdapterPosition()).getId(), visitid, txtanswer.getText().toString(), new IllnessApiService.OnillnessimgReceived() {
                            @Override
                            public void onReceived(String doccheck) {
                                Toast.makeText(context, "ویرایش شد",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }else {
                        illnessapi.postAddAnswerQuestion(allquestion.get(getAdapterPosition()).getId(), visitid, txtanswer.getText().toString(), new IllnessApiService.OnillnessimgReceived() {
                            @Override
                            public void onReceived(String doccheck) {
                                Toast.makeText(context, "افزوده شد",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }
            });

        }

    }
}


