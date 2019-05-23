package me.sohailpathan.www.searchtap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class ServiceOrderAdapter extends Adapter<ServiceOrderAdapter.ServiceOrderViewHolder> {

    Context context;
    List<ServiceOrder> list;
    public static String mobileNumber;

    public ServiceOrderAdapter(Context context, ArrayList<ServiceOrder> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ServiceOrderAdapter.ServiceOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.service_order_layout, viewGroup , false);
        ServiceOrderViewHolder serviceOrderViewHolder = new ServiceOrderViewHolder(view);
        return serviceOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceOrderViewHolder serviceOrderViewHolder, int i) {
        ServiceOrder serviceOrder = list.get(i);
        mobileNumber = String.valueOf(serviceOrder.getmRetailerMobileNo());
        serviceOrderViewHolder.imageView.setImageResource(R.drawable.addfriend);
        serviceOrderViewHolder.uRetailerName.setText(serviceOrder.getmRetailerName());
        serviceOrderViewHolder.uServiceCategory.setText(serviceOrder.getmServiceCategory());
        serviceOrderViewHolder.uServicetype.setText(serviceOrder.getmServiceType());
        serviceOrderViewHolder.rCallButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+mobileNumber));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


   public static class ServiceOrderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView  uRetailerName;
        private TextView uServicetype;
        private TextView uServiceCategory;
        private Button rCallButton;

        public ServiceOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewRetailerOrder);
            uRetailerName = itemView.findViewById(R.id.textViewRetailerName);
            uServicetype = itemView.findViewById(R.id.textViewServiceType);
            uServiceCategory = itemView.findViewById(R.id.textViewServiceCategory);
            rCallButton = itemView.findViewById(R.id.retailerCallButton);
        }
    }
}
