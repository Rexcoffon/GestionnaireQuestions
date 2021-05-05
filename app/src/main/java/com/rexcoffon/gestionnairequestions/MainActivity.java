package com.rexcoffon.gestionnairequestions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rexcoffon.gestionnairequestions.models.Question;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView questionListView;

    List<Question> questions = new ArrayList<>();
    List<String> listeQuestions = new ArrayList<>();
    AdapterView.OnItemClickListener onClick;
    QuestionAdapter adapter;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionListView = findViewById(R.id.listeQuestions);

        db = FirebaseFirestore.getInstance();

;

        adapter = new QuestionAdapter();
        questionListView.setAdapter(adapter);
        questionListView.setLayoutManager(new LinearLayoutManager(context));

        chargerQuestions();

        FloatingActionButton ajouter = findViewById(R.id.btn_ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AjouterQuestion.class);
                startActivity(i);
            }
        });

    }

    public void chargerQuestions() {
        db.collection("Questions").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("ERROR", "Listen failed.", e);
                }

                questions = new ArrayList<>();
                listeQuestions = new ArrayList<>();
                for (QueryDocumentSnapshot document : value) {
                    questions.add(new Question(document.getId(), document.getString("Question"), document.getString("Reponse")));
                    listeQuestions.add(document.getString("Question"));
                }
//                questionListView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, listeQuestions));

                adapter.setQuestions(questions);
            }
        });
    }
}