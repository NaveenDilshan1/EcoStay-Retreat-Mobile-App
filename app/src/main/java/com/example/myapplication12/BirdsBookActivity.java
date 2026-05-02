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

public class BirdsBookActivity extends AppCompatActivity {
    ArrayList<BirdsModel> birdsModelList;
    ListView birdsList;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds_book);

        birdsList = findViewById(R.id.birdsListView);
        birdsModelList = new ArrayList<>();
        fs = FirebaseFirestore.getInstance();
        loadDatainListView();
    }

    private void loadDatainListView() {
        fs.collection("BirdWatching").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                BirdsModel birdsModel = d.toObject(BirdsModel.class);
                                birdsModelList.add(birdsModel);
                            }
                            BirdsAdapter adapter = new BirdsAdapter(BirdsBookActivity.this, birdsModelList);
                            birdsList.setAdapter(adapter);
                        }
                    }
                });
    }
}