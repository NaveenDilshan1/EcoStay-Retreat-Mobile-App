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
public class RoomBookActivity extends AppCompatActivity {
    ArrayList<RoomModel> roomModelList;
    ListView roomList;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_book);

        roomList = findViewById(R.id.roomListView);
        roomModelList = new ArrayList<>();
        fs = FirebaseFirestore.getInstance();
        loadDatainListView();
    }
    private void loadDatainListView()
    {
        fs.collection("Rooms").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list)
                            {
                                RoomModel roomModel = d.toObject(RoomModel.class);
                                roomModelList.add(roomModel);
                            }
                            RoomAdapter adapter = new RoomAdapter(RoomBookActivity.this, roomModelList);
                            roomList.setAdapter(adapter);
                        }
                    }
                });
    }
}