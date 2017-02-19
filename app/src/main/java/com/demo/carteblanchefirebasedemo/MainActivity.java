package com.demo.carteblanchefirebasedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText mMessageBox;
    private Button mSendButton;
    private TextView mServerMessage;
    private ImageView mSampleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference messageReference = mDatabase.getReference().child("message");

        mMessageBox = (EditText) findViewById(R.id.mytextbox);
        mSendButton = (Button) findViewById(R.id.sendButton);
        mServerMessage = (TextView) findViewById(R.id.serverMessage);
        mSampleImageView = (ImageView) findViewById(R.id.sample_image);

        mMessageBox.setHint("Enter your message");

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String message = mMessageBox.getText().toString();
                messageReference.setValue(message);
                mMessageBox.setText("");*/

               /* User mUser = new User();
                mUser.setName("Alice");
                mUser.setEmail("alice@gmail.com");

                User mUser1 = new User();
                mUser1.setName("Bob");
                mUser1.setEmail("Bob@gmail.com");

                User mUser2 = new User();
                mUser2.setName("rahim");
                mUser2.setEmail("rahim@gmail.com");

                DatabaseReference databaseReference = mDatabase.getReference().child("users");
                databaseReference.push().setValue(mUser);
                databaseReference.push().setValue(mUser1);
                databaseReference.push().setValue(mUser2);*/

               /* DatabaseReference databaseReference = mDatabase.getReference().child("user");
                databaseReference.setValue(mUser);*/

                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                StorageReference storageReference = firebaseStorage.getReference().child("myImages");

                mSampleImageView.setDrawingCacheEnabled(true);
                mSampleImageView.buildDrawingCache();

                Bitmap bitmap = mSampleImageView.getDrawingCache();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] data = byteArrayOutputStream.toByteArray();

                storageReference.putBytes(data);
                


            }
        });

        messageReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String dataFromServer = dataSnapshot.getValue(String.class);
                mServerMessage.setText(dataFromServer);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
