package com.soumit.firebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.soumit.firebook.model.SingleInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoListActivity extends BaseActivity {

    private static final String TAG = "InfoListActivity";
    private Context context;
    private DatabaseReference databaseReference;
    private List<SingleInfo> infoList = new ArrayList<>();
    Map<String, Object> postvalues = new HashMap<>();
    private RecyclerView recyclerView;
    private InfoAdapter infoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        recyclerView = findViewById(R.id.recycler_view);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        context = InfoListActivity.this;

        Intent receivedIntent = getIntent();
        String queryStr = receivedIntent.getStringExtra(Const.QUERY_VAR);

        showProgressDialog();

        Query queryByBatch = databaseReference
                .child("users")
                .orderByChild("batch")
                .equalTo(queryStr);
        queryByBatch.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    SingleInfo singleInfo = ds.getValue(SingleInfo.class);
                    Log.d(TAG, "onDataChange: singleInfo -----------" + singleInfo.toString()+ "-----------");

                    String userId = getUid();
                    String name = singleInfo.getName();
                    String batch = singleInfo.getBatch();
                    String address = singleInfo.getAddress();
                    String imageLink = singleInfo.getImageLink();
                    String email = singleInfo.getEmail();
                    String phoneNo = singleInfo.getPhoneNumber();
                    SingleInfo info = new SingleInfo(userId, name, batch, address, imageLink, email, phoneNo);

                    infoList.add(info);
                    infoAdapter.notifyDataSetChanged();
                    hideProgressDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: error occured while querying data !!");
                hideProgressDialog();
            }
        });


        infoAdapter = new InfoAdapter(getApplicationContext(), infoList);
        recyclerView.setHasFixedSize(true);
        //set a vertical layout so the list is displayed top down
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(infoAdapter);
        Log.d(TAG, "onCreate: " + infoList.toString() + "==============");
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        SingleInfo temp = infoList.get(position);
                        Intent intent = new Intent(InfoListActivity.this, ProfileDetailsActivity.class);
                        intent.putExtra("ProfileDataParcel", temp);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

//        infoAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hideProgressDialog();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
