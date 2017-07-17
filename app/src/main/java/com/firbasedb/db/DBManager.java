package com.firbasedb.db;

import com.firbasedb.db.model.User;
import com.firbasedb.db.model.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dima on 7/15/17.
 */

public class DBManager {

    public static final String CHILD_USERS = "users";
    public static final String CHILD_USERS_WITH_LIST = "users_with_list";

    private DatabaseReference mDatabaseReference;

    private static DBManager sInstance;

    public static DBManager getInstance() {
        if(sInstance == null){
            sInstance = new DBManager();
        }
        return sInstance;
    }

    private DBManager(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void addValueEventListener(ValueEventListener valueEventListener) {
        mDatabaseReference.addValueEventListener(valueEventListener);
    }

    public void removeEventListener(ValueEventListener valueEventListener) {
        mDatabaseReference.removeEventListener(valueEventListener);
    }

    public void addChildEventListener(ChildEventListener valueEventListener) {
        mDatabaseReference.addChildEventListener(valueEventListener);
    }

    public void removeChildEventListener(ChildEventListener valueEventListener) {
        mDatabaseReference.removeEventListener(valueEventListener);
    }
    public void saveUser(User user) {
        mDatabaseReference.child(CHILD_USERS).child("" + user.getId()).setValue(user);
    }

    public void getUsers(ChildEventListener childEventListener){
        mDatabaseReference.child(CHILD_USERS).addChildEventListener(childEventListener);
    }

    public void getUserByDate(ChildEventListener childEventListener){

      mDatabaseReference.child(CHILD_USERS).orderByChild("createdAt")
                .startAt(150011875010L).endAt(1500118751404L).addChildEventListener(childEventListener);
    }

    public void getUserByDateAndLogin(ChildEventListener childEventListener){
        mDatabaseReference.child(CHILD_USERS).orderByChild("login")
//                .startAt(150011875010L, "createdAt").endAt(1500118751404L, "createdAt")
                .equalTo("test").addChildEventListener(childEventListener);
    }
    public void saveUsers(Users users) {
        mDatabaseReference.child(CHILD_USERS_WITH_LIST).setValue(users);

    }
    public void getUserById(String id, ChildEventListener childEventListener){
        mDatabaseReference.child(CHILD_USERS).child(id).addChildEventListener(childEventListener);
    }
}
