package me.sohailpathan.www.searchtap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

public class ToggleRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner service,cateogary,pincode;
    EditText address;
    Button findRetailer;
    ProgressDialog progressDialog;
    List<ServiceRequest> serviceRequestsList;
    public static String KEY_SERVICE_CATEGORY="me.sohailpathan.www.searchtap.ToggleRegister.SERVICE_CATEGORY";
    public static String KEY_SERVICE_TYPE="me.sohailpathan.www.searchtap.ToggleRegister.SERVICE_TYPE";
    public static String KEY_SERVICE_ADDRESS="me.sohailpathan.www.searchtap.ToggleRegister.SERVICE_ADDRESS";
    public static String KEY_SERVICE_PINCODE="me.sohailpathan.www.searchtap.ToggleRegister.SERVICE_PINCODE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_register);
        service=(Spinner)findViewById(R.id.serviceType);
        cateogary=(Spinner)findViewById(R.id.serviceCategory);
        address=(EditText) findViewById(R.id.editAddress);
        pincode=(Spinner)findViewById(R.id.pincode);
        findRetailer=(Button)findViewById(R.id.findRetailer);
        serviceRequestsList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Finding Best Retailer for You");
        ArrayAdapter serviceTypeArray = ArrayAdapter.createFromResource(this,R.array.ServiceType,R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter pincodeAdapter = ArrayAdapter.createFromResource(this , R.array.Pincode,R.layout.support_simple_spinner_dropdown_item);
        pincode.setAdapter(pincodeAdapter);
        service.setAdapter(serviceTypeArray);
        service.setOnItemSelectedListener(this);
        findRetailer.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(service.getSelectedItem().equals("Home Washing"))
        {
            ArrayAdapter homeArray = ArrayAdapter.createFromResource(this, R.array.HomeWashingCategory,R.layout.support_simple_spinner_dropdown_item);
            cateogary.setAdapter(homeArray);
        }
        else if(service.getSelectedItem().equals("Electronics & Repairing"))
        {
            ArrayAdapter EandRArray = ArrayAdapter.createFromResource(this, R.array.ElectronicsRepairingCategory,R.layout.support_simple_spinner_dropdown_item);
            cateogary.setAdapter(EandRArray);
        }
        else if(service.getSelectedItem().equals("Salon and Spa"))
        {
            ArrayAdapter salonArray = ArrayAdapter.createFromResource(this, R.array.SalonAndSpaCategory,R.layout.support_simple_spinner_dropdown_item);
            cateogary.setAdapter(salonArray);
        }
        else
        {
            cateogary.setEnabled(false);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.findRetailer) {
            String firstSpinner = service.getSelectedItem().toString().trim();
            String secondSpinner = cateogary.getSelectedItem().toString().trim();
            String deliveryAddress = address.getText().toString().trim();
            String deliveryPincode = pincode.getSelectedItem().toString().trim();
            if (TextUtils.isEmpty(firstSpinner) || TextUtils.isEmpty(secondSpinner) || TextUtils.isEmpty(deliveryAddress) || TextUtils.isEmpty(deliveryPincode)) {
                Snackbar.make(v, "Enter Details Correctly", Snackbar.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, RetailerList.class);
                intent.putExtra(KEY_SERVICE_TYPE , firstSpinner);
                intent.putExtra(KEY_SERVICE_CATEGORY,secondSpinner);
                intent.putExtra(KEY_SERVICE_ADDRESS,deliveryAddress);
                intent.putExtra(KEY_SERVICE_PINCODE,deliveryPincode);
                startActivity(intent);
            }
        }
    }
}
