package me.sohailpathan.www.searchtap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RetailerAdapter extends RecyclerView.Adapter<RetailerAdapter.RetailerViewHolder> {

    Context context;
    List<Retailer> retailersList;
    DatabaseReference databaseServiceRequest;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    public RetailerAdapter(Context context, List<Retailer> retailersList) {
        this.context = context;
        this.retailersList = retailersList;
    }

    @NonNull
    @Override
    public RetailerAdapter.RetailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.retailer_layout,viewGroup,false);
        RetailerViewHolder retailerViewHolder = new RetailerViewHolder(view);
        return retailerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RetailerViewHolder retailerViewHolder, int i) {
        final Retailer retailer = retailersList.get(i);
        retailerViewHolder.textViewName.setText(retailer.getName());
        retailerViewHolder.textViewServicetype.setText(retailer.getServiceType());
        retailerViewHolder.textViewpincode.setText(String.valueOf((int)retailer.getPincode()));
        retailerViewHolder.textviewPrice.setText(String.valueOf(retailer.getPrice()));
        retailerViewHolder.textViewRating.setText(String.valueOf(retailer.getRating()));
        retailerViewHolder.retailerImage.setImageResource(R.drawable.carpenter);
        retailerViewHolder.selectRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseServiceRequest = FirebaseDatabase.getInstance().getReference("ServiceRequest");
                String id = databaseServiceRequest.push().getKey();
                String userEmail = firebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                String userServiceRetailerName= retailer.getName().toString();
                String userServiceRetailerMobileNumber = "9967339082";
                String userServiceType = RetailerList.GET_SERVICE_TYPE;
                String userServiceCategory = RetailerList.GET_SERVICE_CATEGORY;
                String userDeliveryAddress = RetailerList.GET_SERVICE_ADDRESS;
                String userDeliveryPincode =RetailerList.GET_SERVICE_PINCODE;
                ServiceRequest serviceRequest = new ServiceRequest(id, userEmail, userServiceRetailerName, userServiceRetailerMobileNumber, userServiceType, userServiceCategory, userDeliveryAddress, userDeliveryPincode);
                databaseServiceRequest.child(id).setValue(serviceRequest);
                Toast.makeText(RetailerList.context, "SERVICE BOOKED:) THANK YOU", Toast.LENGTH_SHORT).show();
                String sms = "Your Have Service Request from : "+userEmail+" for "+userServiceCategory+" on the Address : " + userDeliveryAddress + " PINCODE: " + userDeliveryPincode ;
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(userServiceRetailerMobileNumber,null,sms,null,null);
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return retailersList.size();
    }

    class RetailerViewHolder extends RecyclerView.ViewHolder{

        ImageView retailerImage;
        TextView textViewName, textViewpincode, textviewPrice, textViewRating, textViewServicetype;
        Button selectRetailer;
        public RetailerViewHolder(@NonNull View itemView) {
            super(itemView);
            retailerImage=itemView.findViewById(R.id.imageViewRetailer);
            textViewName = itemView.findViewById(R.id.textViewTitleRetailer);
            textViewServicetype =itemView.findViewById(R.id.textViewShortDescRetailer);
            textViewpincode = itemView.findViewById(R.id.textViewPincode);
            textViewRating = itemView.findViewById(R.id.textViewRatingRetailer);
            textviewPrice = itemView.findViewById(R.id.textViewPriceRetailer);
            selectRetailer=itemView.findViewById(R.id.buttonSelectRetailer);

        }
    }
}
