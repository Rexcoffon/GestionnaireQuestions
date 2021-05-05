package com.rexcoffon.gestionnairequestions;

import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rexcoffon.gestionnairequestions.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>{

     List<Question> questions = new ArrayList<Question>();

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        Question question = questions.get(position);
        holder.bindData(question, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AjouterQuestion.class);

                i.putExtra("id", question.getId());
                i.putExtra("question", question.getQuestion());
                i.putExtra("reponse", question.getReponce());

                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> questionList){
        questions.clear();
        questions.addAll(questionList);
        notifyDataSetChanged();
    }
}
