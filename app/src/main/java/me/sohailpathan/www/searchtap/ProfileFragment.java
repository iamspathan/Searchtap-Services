package me.sohailpathan.www.searchtap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment{

    TextView email;
    EditText mobile;
    Button logout;
    FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment ,container, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Logout");
        builder.setMessage("Are you Sure ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(),UserLogin.class));
            }
        });
        builder.setNegativeButton("No" , null);
        email=view.findViewById(R.id.profileEmail);
        email.setText(firebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        mobile=view.findViewById(R.id.profileMobileNumber);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
        return view;
    }
}
