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

public class HikisBookActivity extends AppCompatActivity {
    ArrayList<HikisModel> hikisModelList;
    ListView hikisList;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikis_book);

        hikisList = findViewById(R.id.hikisListView);
        hikisModelList = new ArrayList<>();
        fs = FirebaseFirestore.getInstance();
        loadDatainListView();
    }

    private void loadDatainListView() {
        fs.collection("Hikis").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                HikisModel hikisModel = d.toObject(HikisModel.class);
                                hikisModelList.add(hikisModel);
                            }
                            HikisAdapter adapter = new HikisAdapter(HikisBookActivity.this, hikisModelList);
                            hikisList.setAdapter(adapter);
                        }
                    }
                });
    }
}