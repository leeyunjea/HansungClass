package org.androidtown.hansungclass.ViewPager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidtown.hansungclass.Adapter.ContactsAdapater;
import org.androidtown.hansungclass.Class.Contact;
import org.androidtown.hansungclass.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    ArrayList<Contact> contacts;
    ContactsAdapater adapter;
    View view;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_location, container, false);

        RecyclerView rvContacts = (RecyclerView)view.findViewById(R.id.recyclerView);

        contacts = Contact.createContactsList(10);

        adapter = new ContactsAdapater(getContext(), contacts);
        rvContacts.setAdapter(adapter);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvContacts.setLayoutManager(gridLayoutManager);

        //RecyclerView.ItemDecoration  itemDecoration = new MarginItem

        rvContacts.setHasFixedSize(true);


        contacts.add(0, new Contact("안드로이드1", "공학관102호"));
        contacts.add(1, new Contact("자바프로그래밍", "공학관102호"));


        adapter.notifyItemChanged(0);

        return view;
    }

}
