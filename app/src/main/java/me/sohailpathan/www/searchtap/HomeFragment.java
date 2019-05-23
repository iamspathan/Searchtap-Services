package me.sohailpathan.www.searchtap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment  extends Fragment {
     RecyclerView recyclerView;
     ArrayList<ServiceOrder> serviceOrdersList;
     ServiceOrderAdapter serviceOrderAdapter;
     ArrayList<ServiceOrder> a ;
     FirebaseAuth firebaseAuth;
     FirebaseAuth.AuthStateListener authStateListener;
     public HomeFragment()
     {

        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.home_fragment,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewService);
        serviceOrdersList=new ArrayList<ServiceOrder>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ServiceRequest");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot serviceOrderSnapshot : dataSnapshot.getChildren()) {
                        String mRetailerName = serviceOrderSnapshot.getValue(ServiceRequest.class).getUserServiceRetailerName();
                        String mServiceType = serviceOrderSnapshot.getValue(ServiceRequest.class).getsServiceType();
                        String mServiceCategory = serviceOrderSnapshot.getValue(ServiceRequest.class).getsServiceCategory();
                        String mServiceNumber = serviceOrderSnapshot.getValue(ServiceRequest.class).getUserServiceRetailerMobileNo();
                        String mUserEmail = serviceOrderSnapshot.getValue(ServiceRequest.class).getmUserEmail();
                        String uUserEmail = firebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                        if(mUserEmail.equalsIgnoreCase(uUserEmail)) {
                            serviceOrdersList.add(new ServiceOrder(mRetailerName, mServiceType, mServiceCategory, mServiceNumber));
                        }
                        //System.out.println(serviceOrdersList.size());
                    }
                    serviceOrderAdapter = new ServiceOrderAdapter(getContext(),serviceOrdersList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
                    recyclerView.setAdapter(serviceOrderAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
