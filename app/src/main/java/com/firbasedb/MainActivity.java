package com.firbasedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firbasedb.db.DBManager;
import com.firbasedb.db.model.User;
import com.firbasedb.db.model.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private String mLastCreatedUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = createUser();
                mLastCreatedUserId = "" + user.getId();
                DBManager.getInstance().saveUser(user);
            }
        });
        findViewById(R.id.create_users_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users users = new Users();
                List<User> usersList = new ArrayList<>();
                usersList.add(createUser());
                usersList.add(createUser());
                usersList.add(createUser());
                users.setUsers(usersList);
                DBManager.getInstance().saveUsers(users);
            }
        });
        findViewById(R.id.get_all_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager.getInstance().getUserByDateAndLogin(mChildEventListener);
            }
        });
        findViewById(R.id.remove_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mLastCreatedUserId)){
                    return;
                }

                DBManager.getInstance().getUserById(mLastCreatedUserId, mDeleteUserEventListener);
            }
        });
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

    }

    private User createUser() {
        User user = new User();
        user.setId((int) System.currentTimeMillis());
        user.setLogin("login");
        user.setPassword("password");
        user.setCreatedAt(System.currentTimeMillis());
        return user;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        DBManager.getInstance().addValueEventListener(mValueEventListener);
//        DBManager.getInstance().addChildEventListener(mChildEventListener);
    }

    @Override
    protected void onStop() {
//        DBManager.getInstance().removeEventListener(mValueEventListener);
//        DBManager.getInstance().removeChildEventListener(mChildEventListener);
        super.onStop();
    }


    private ChildEventListener mChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

            Log.d(TAG, "onChildAdded : " + dataSnapshot.getKey() + " previousChildName " + previousChildName);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "onChildChanged : " + dataSnapshot.getKey());

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved : " + dataSnapshot.getKey());

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildMoved : " + dataSnapshot.getKey() + " previousChildName " + previousChildName);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "onCancelled : " + databaseError.getMessage());

        }
    };

    private ValueEventListener mValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot> data = dataSnapshot.getChildren();
            for(DataSnapshot snapshot : data){
                Log.d(TAG, "Key is: " + snapshot.getKey());
                for(DataSnapshot child : snapshot.getChildren()){
                    Log.d(TAG, "Child is: " + child.getKey());
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    };



    private ChildEventListener mDeleteUserEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "remove ite,d : " + dataSnapshot.getKey() + " previousChildName " + previousChildName);
            dataSnapshot.getRef().removeValue();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "onChildChanged : " + dataSnapshot.getKey());

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(TAG, "onChildRemoved : " + dataSnapshot.getKey());

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            Log.d(TAG, "onChildMoved : " + dataSnapshot.getKey() + " previousChildName " + previousChildName);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "onCancelled : " + databaseError.getMessage());

        }
    };
}
