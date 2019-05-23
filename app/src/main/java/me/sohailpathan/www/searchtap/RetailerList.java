package me.sohailpathan.www.searchtap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetailerList extends AppCompatActivity {
    RecyclerView recyclerView ;
    RetailerAdapter retailerAdapter;
    List<Retailer> list;
    public static String GET_SERVICE_TYPE;
    public static String GET_SERVICE_CATEGORY;
    public static String GET_SERVICE_ADDRESS;
    public static String GET_SERVICE_PINCODE;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRetailer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        context=getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        GET_SERVICE_TYPE=(String)bundle.get(ToggleRegister.KEY_SERVICE_TYPE);
        GET_SERVICE_CATEGORY=(String)bundle.get(ToggleRegister.KEY_SERVICE_CATEGORY);
        GET_SERVICE_ADDRESS=(String)bundle.get(ToggleRegister.KEY_SERVICE_ADDRESS);
        GET_SERVICE_PINCODE=(String)bundle.get(ToggleRegister.KEY_SERVICE_PINCODE);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Retailers");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        Retailer retailer = productSnapshot.getValue(Retailer.class);
                        String s = String.valueOf((int)retailer.getPincode());
                        if(s.equalsIgnoreCase(GET_SERVICE_PINCODE)) {
                            list.add(retailer);
                        }
                    }
                    retailerAdapter = new RetailerAdapter(RetailerList.this, list);
                    recyclerView.setAdapter(retailerAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
