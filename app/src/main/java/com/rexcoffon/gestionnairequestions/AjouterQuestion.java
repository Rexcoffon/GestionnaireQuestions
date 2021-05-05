package com.rexcoffon.gestionnairequestions;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AjouterQuestion extends AppCompatActivity {

    FirebaseFirestore db;

    TextInputEditText questionTextView, reponseTextView;

    Button supprimerButton;
    Button validerButton;

    String questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_question);

        db = FirebaseFirestore.getInstance();

        questionTextView = findViewById(R.id.Question);
        reponseTextView = findViewById(R.id.Reponse);

        questionId = getIntent().getStringExtra("id");

        supprimerButton = findViewById(R.id.supprimer);
        if (questionId != null && !questionId.trim().isEmpty()) {
            questionTextView.setText(getIntent().getStringExtra("question"));
            reponseTextView.setText(getIntent().getStringExtra("reponse"));

            supprimerButton.setVisibility(View.VISIBLE);
            questionTextView.setFocusable(false);
        }

        supprimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
            }
        });

        validerButton = findViewById(R.id.valider);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> question = new HashMap<>();
                question.put("Question", questionTextView.getText().toString());
                question.put("Reponse", reponseTextView.getText().toString());

                if (questionId != null && !questionId.trim().isEmpty()) {
                    modifier(question);
                } else {
                    ajouter(question);
                }
            }
        });
    }

    public void ajouter(Map<String, Object> question) {
        db.collection("Questions")
                .add(question)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Snackbar.make(findViewById(R.id.activity_ajouter), "Question ajoutée avec succès !", Snackbar.LENGTH_LONG).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(findViewById(R.id.activity_ajouter), "Une erreur est survenue", Snackbar.LENGTH_LONG).show();
                    }
                });
    }
    public void modifier(Map<String, Object> question) {
        db.collection("Questions")
                .document(questionId)
                .set(question)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.activity_ajouter), "Question modifiée avec succès ! ", Snackbar.LENGTH_LONG).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(findViewById(R.id.activity_ajouter), "Une erreur est survenue", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public void supprimer() {
        if (questionId != null && !questionId.trim().isEmpty()) {
            db.collection("Questions")
                    .document(questionId)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Snackbar.make(findViewById(R.id.activity_ajouter), "Question suprimée avec succès ! ", Snackbar.LENGTH_LONG).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(findViewById(R.id.activity_ajouter), "Une erreur est survenue", Snackbar.LENGTH_LONG).show();
                        }
                    });
        }
    }
}