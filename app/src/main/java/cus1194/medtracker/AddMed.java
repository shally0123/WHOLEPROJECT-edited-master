package cus1194.medtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pruan086 on 4/4/2017.
 */

public class AddMed extends AppCompatActivity
{

    EditText addMedDos;
    EditText addMedID;
    Button save;
    Button cancel;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_med);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        addMedDos = (EditText)findViewById(R.id.addMedDos);
        addMedID = (EditText)findViewById(R.id.addMedID);
        save = (Button)findViewById(R.id.saveMed);
        cancel = (Button)findViewById(R.id.cancelMed);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMedInformation();
                Intent intent1 = new Intent(AddMed.this, PatientMain.class);
                startActivity(intent1);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent2 = new Intent(AddMed.this, PatientMain.class);
                startActivity(intent2);
            }
        });


    }

    private void saveMedInformation() {
        String name = addMedDos.getText().toString().trim();
        String id = addMedID.getText().toString().trim();

        MedInfo MedInfo = new MedInfo(name, id);

        FirebaseUser newMed = firebaseAuth.getCurrentUser();

        databaseReference.child(newMed.getUid()).setValue(MedInfo);

        Toast.makeText(this, "Medication added!", Toast.LENGTH_LONG).show();
    }



}
