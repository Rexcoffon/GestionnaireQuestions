package com.rexcoffon.gestionnairequestions;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rexcoffon.gestionnairequestions.models.Question;

public class QuestionHolder extends RecyclerView.ViewHolder {
    public QuestionHolder(@NonNull View itemView) {
        super(itemView);
    }

    CardView cardView = itemView.findViewById(R.id.holder_card);
    TextView questionText = itemView.findViewById(R.id.holder_question);
    TextView response = itemView.findViewById(R.id.holder_response);
    TextView isChecked = itemView.findViewById(R.id.holder_checked);

    public void bindData(Question question, int position){
        questionText.setText(question.getQuestion());
        response.setText(question.getReponce());

        if (question.getReponce().isEmpty() || question.getReponce() == null){
            isChecked.setText("KO");
            isChecked.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            isChecked.setText("OK");
            isChecked.setTextColor(Color.parseColor("#00FF00"));
        }
    }
}
