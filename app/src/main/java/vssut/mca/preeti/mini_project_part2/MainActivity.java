package vssut.mca.preeti.mini_project_part2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    private ProgressBar progressBar;
    private TextView tvNoRequest;
    private Button btnAccept,btnReject;
    private RecyclerView recyclerView;
    private RequestAdapter requestAdapter;
    private ArrayList<RequestDetails> requestDetailsArrayList;
    private ArrayList<String> keyLists;
    private ArrayList<RequestDetails> selected;
    private ArrayList<String> keyApproved;
    private String timestamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Leave Requests");
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        btnAccept=(Button)findViewById(R.id.btnAccept);
        btnReject=(Button)findViewById(R.id.btnReject);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvNoRequest=(TextView) findViewById(R.id.tvNoRequests);
        requestDetailsArrayList = new ArrayList<>();
        keyLists=new ArrayList<>();
        selected= new ArrayList<>();
        keyApproved=new ArrayList<>();

        createDataList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       timestamp=new SimpleDateFormat("dd-MMM-yyyy",Locale.getDefault()).format(new Date());

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                    selected=requestAdapter.getSelected(keyLists);
                    keyApproved=requestAdapter.getSelectedKeyValues();

                    if ( selected.size()> 0)
                    {
                        FirebaseAddOperationToApproved();
                        removeFromArrayList();
                    }
                    else if (selected.isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "List is Empty/ No item selected.", Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);


            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                    selected=requestAdapter.getSelected(keyLists);
                    keyApproved=requestAdapter.getSelectedKeyValues();

                    if ( selected.size()> 0)
                    {
                        removeFromArrayList();

                    }
                    else if (selected.isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "List is Empty/ No item selected.", Toast.LENGTH_LONG).show();
                    }

                    progressBar.setVisibility(View.GONE);




            }
        });
    }

    private void createDataList()
    {

        try {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("request");
            ValueEventListener eventListener=new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot child : dataSnapshot.getChildren()) {

                            requestDetailsArrayList.add(child.getValue(RequestDetails.class));
                            keyLists.add(child.getKey());

                        }

                    }
                    else
                    {
                        tvNoRequest.setVisibility(View.VISIBLE);
                        btnAccept.setEnabled(false);
                        btnReject.setEnabled(false);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, databaseError.toString(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            };


                databaseReference.addValueEventListener(eventListener);

                requestAdapter=new RequestAdapter(this,requestDetailsArrayList);
                recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(requestAdapter);
                requestAdapter.notifyDataSetChanged();
                //requestDetailsArrayList.clear();
                // keyLists.clear();
                Toast.makeText(this, "Sucessful loading of data", Toast.LENGTH_LONG).show();
                // databaseReference.removeEventListener(eventListener);


            progressBar.setVisibility(View.GONE);


        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }

    }
    private void FirebaseAddOperationToApproved()
    {
        try {
            final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("approved");
            ValueEventListener eventListener=new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (int i = 0; i < keyApproved.size(); i++)
                    {
                        dbRef.child(keyApproved.get(i)).setValue(new Approved(timestamp));

                    }

                    Toast.makeText(MainActivity.this, "Request(s) Successfully APPROVED", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            };
            dbRef.addValueEventListener(eventListener);
           // dbRef.removeEventListener(eventListener);

        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "Failed to ADD values to firebase", Toast.LENGTH_LONG).show();

        }

    }
    private void FirebaseRemoveOperation()
    {
        try {
            final DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference().child("request");

            ValueEventListener eventListener=new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        for (int i = 0; i < keyApproved.size(); i++)
                        {
                            dbRef2.child(keyApproved.get(i)).removeValue();

                        }
                    }
                    else
                        return;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

                }
            };

            dbRef2.addValueEventListener(eventListener);
          //  dbRef2.removeEventListener(eventListener);

        }
        catch(Exception e)
        {
             Toast.makeText(getApplicationContext(), "Failed to REMOVE values from firebase", Toast.LENGTH_LONG).show();
        }
    }

    private void removeFromArrayList()
    {
        requestDetailsArrayList.removeAll(selected);
        keyLists.removeAll(keyApproved);

        if (requestDetailsArrayList.isEmpty() || keyLists.isEmpty())
        {
            tvNoRequest.setVisibility(View.VISIBLE);
            btnAccept.setEnabled(false);
            btnReject.setEnabled(false);
        }
        else
        {
        HashSet<RequestDetails> hashSetRequest=new HashSet<RequestDetails>();
        hashSetRequest.addAll(requestDetailsArrayList);
        requestDetailsArrayList.clear();
        requestDetailsArrayList.addAll(hashSetRequest);

        HashSet<String> hashSetKey=new HashSet<String>();
        hashSetKey.addAll(keyLists);
        keyLists.clear();
        keyLists.addAll(hashSetKey);

        requestAdapter.notifyDataSetChanged();


        }
        FirebaseRemoveOperation();
        Toast.makeText(this, "Request(s) Successfully REJECTED", Toast.LENGTH_LONG).show();

       // requestDetailsArrayList.clear();
        //keyLists.clear();
    }

}

