package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ToursBookActivity extends AppCompatActivity {
    ArrayList<ToursModel> toursModelList;
    ListView toursList;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours_book);

        toursList = findViewById(R.id.toursListView);
        toursModelList = new ArrayList<>();
        fs = FirebaseFirestore.getInstance();
        loadDatainListView();
    }

    private void loadDatainListView() {
        fs.collection("EcoTours").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                ToursModel toursModel = d.toObject(ToursModel.class);
                                toursModelList.add(toursModel);
                            }
                            ToursAdapter adapter = new ToursAdapter(ToursBookActivity.this, toursModelList);
                            toursList.setAdapter(adapter);
                        }
                    }
                });
    }
}