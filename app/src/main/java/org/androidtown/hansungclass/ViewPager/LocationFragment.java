package org.androidtown.hansungclass.ViewPager;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidtown.hansungclass.Adapter.ContactsAdapater;
import org.androidtown.hansungclass.Adapter.ExchangeUniversityAdapter;
import org.androidtown.hansungclass.Adapter.MarginitemDecoration;
import org.androidtown.hansungclass.Class.Contact;
import org.androidtown.hansungclass.Class.ExchangeUniversityContact;
import org.androidtown.hansungclass.FirebaseClass.Major;
import org.androidtown.hansungclass.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private ArrayList<Contact> contacts;
    private ContactsAdapater adapter;
    private RecyclerView rvContacts;

    private ArrayList<ExchangeUniversityContact> excontacts;
    private ExchangeUniversityAdapter exadapter;
    private RecyclerView exrvContacts;

    private View view;
    private DatabaseReference mConditionRef;
    private DatabaseReference databaseReference;
    private String subject;
    private String location;


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_location, container, false);

        rvContacts = (RecyclerView)view.findViewById(R.id.recyclerView);
        contacts = Contact.createContactsList(15);
        adapter = new ContactsAdapater(getContext(), contacts);
        rvContacts.setAdapter(adapter);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvContacts.setLayoutManager(gridLayoutManager);

        RecyclerView.ItemDecoration  itemDecoration = new MarginitemDecoration(10);
        rvContacts.addItemDecoration(itemDecoration);

        rvContacts.setHasFixedSize(true);

        getDataBase();

        adapter.notifyItemChanged(0);

        exrvContacts = (RecyclerView)view.findViewById(R.id.recyclerView2);
        excontacts = ExchangeUniversityContact.createContactsList(11);
        exadapter = new ExchangeUniversityAdapter(getContext(), excontacts);
        exrvContacts.setAdapter(exadapter);

        addExchangeUniversity();

        StaggeredGridLayoutManager gridLayoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        exrvContacts.setLayoutManager(gridLayoutManager2);
        exrvContacts.addItemDecoration(itemDecoration);
        exrvContacts.setHasFixedSize(true);
        exadapter.notifyItemChanged(0);

        return view;
    }

    public void addExchangeUniversity() {
        excontacts.add(0, new ExchangeUniversityContact("광운대학교", "광운대학교"));
        excontacts.add(1, new ExchangeUniversityContact("건국대학교", "건국대학교"));
        excontacts.add(2, new ExchangeUniversityContact("덕성여자대학교", "덕성여자대학교"));
        excontacts.add(3, new ExchangeUniversityContact("동덕여자대학교", "동덕여자대학교"));
        excontacts.add(4, new ExchangeUniversityContact("삼육대학교", "삼육대학교"));
        excontacts.add(5, new ExchangeUniversityContact("상명대학교", "상명대학교"));
        excontacts.add(6, new ExchangeUniversityContact("서경대학교", "서경대학교"));
        excontacts.add(7, new ExchangeUniversityContact("성신여자대학교", "성신여자대학교"));
        excontacts.add(8, new ExchangeUniversityContact("세종대학교", "세종대학교"));
        excontacts.add(9, new ExchangeUniversityContact("서울과학기술대학교", "서울과학기술대학교"));
        excontacts.add(10, new ExchangeUniversityContact("추계예술대학교", "추계예술대학교"));
    }

    public void getDataBase() {
        SharedPreferences pref = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
        String name = pref.getString("IDemail","");
        String id[] = name.split("@");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mConditionRef = databaseReference.child("파이어베이스").child("강의").child(id[0]);
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Major major = child.getValue(Major.class);
                    subject = major.getSubject();
                    location = major.getNclass();
                    contacts.add(i,new Contact(subject, location));
                    i++;

                    Log.i("yunjae", "i = "+ i);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
