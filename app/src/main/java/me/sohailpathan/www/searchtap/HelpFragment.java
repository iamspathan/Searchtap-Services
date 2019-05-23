package me.sohailpathan.www.searchtap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpFragment extends Fragment implements View.OnClickListener {

    EditText complaintOrderNo , complaintEmail, complaintMobileNo , complaintComment;
    Button submitComplaint;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.help_fragment ,container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Complaints");
        complaintOrderNo=(EditText)v.findViewById(R.id.orderNoEdit);
        complaintEmail=(EditText)v.findViewById(R.id.EditTextEmail);
        complaintEmail.setText(firebaseAuth.getInstance().getCurrentUser().getEmail().toString());;
        complaintMobileNo=(EditText)v.findViewById(R.id.EditTextComplaintMobileNo);
        complaintComment = (EditText)v.findViewById(R.id.CommentEditText);
        submitComplaint = (Button)v.findViewById(R.id.submitComplaint);
        submitComplaint.setOnClickListener(this);
    return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.submitComplaint)
        {

            String cOrderNo = complaintOrderNo.getText().toString();
            String cOrderEmail = complaintEmail.getText().toString();
            String cOrderMobileNo = complaintMobileNo.getText().toString();
            String cOrderComment = complaintComment.getText().toString();
            if (TextUtils.isEmpty(cOrderNo) || (TextUtils.isEmpty(cOrderEmail) || UserLogin.PatterMatch(cOrderEmail)==false) || (TextUtils.isEmpty(cOrderMobileNo) || cOrderMobileNo.length()<10 ) || (TextUtils.isEmpty(cOrderComment) || cOrderComment.length()< 50)) {
                Snackbar.make(v, "Please Enter Details Correctly\n Mobile No must be 10 DIGIT\n Email must be in abc@email.com\n Comment must have greater than 50 words" , Snackbar.LENGTH_SHORT).show();
            }
            else {
                String cComplaintId = databaseReference.push().getKey();
                Complaint complaint = new Complaint(cOrderNo,cOrderEmail,cOrderMobileNo,cOrderComment);
                databaseReference.child(cComplaintId).setValue(complaint);
                Toast.makeText(getActivity(), "Complaint Registered Successfully , We will get back to u soon \n Thank YOU", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity() ,MainActivity.class ));
            }
        }
    }
}
