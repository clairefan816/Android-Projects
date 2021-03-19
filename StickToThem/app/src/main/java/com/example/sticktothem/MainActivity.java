package com.example.sticktothem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    // set up the connection and get the entry point
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    // set up the connection: it gets to the reference to the root of the Firebase JSON tree
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private static final String TAG = "Login intent";

    private String deviceToken;
    private EditText userName;

    protected static String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.etEnterName);
        // check the database reference
        Toast.makeText(getApplicationContext(), mDatabase.toString(), Toast.LENGTH_LONG).show();
    }


    // This function will be fired when login button hit
    public void logIn(View view) {

        final String inputName = userName.getText().toString();

        if (inputName.isEmpty()) {
            Log.d(TAG, "name is null checked");
            Toast.makeText(this,"Please enter a valid user name!", Toast.LENGTH_LONG).show();
        } else {
            // check if existed in database
            currentUser = inputName;
            DatabaseReference myRef = database.getReference(currentUser);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);

                    if (value == null) { // create new user
                        User user = new User(currentUser, deviceToken);
                        database.getReference().child("users").child(user.username).setValue(user);

                    }
//                    startNewIntent();
                    Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
    }

//    private void startNewIntent() {
//        Intent intent = new Intent(this, GetUsersActivity.class);
//        startActivity(intent);
//    }
}