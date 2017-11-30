package org.androidtown.hansungclass.ViewPager;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidtown.hansungclass.Adapter.HomeListAdapter;
import org.androidtown.hansungclass.Adapter.MajorReadapter;
import org.androidtown.hansungclass.FirebaseClass.Major;
import org.androidtown.hansungclass.R;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextView total_credit;
    private TextView day;
    private ListView listView;
    private View view;
    private HomeListAdapter adapter;
    private Date date;
    public static SharedPreferences sf = null;
    public static SharedPreferences.Editor editor = null;
    private Major major;
    private DatabaseReference mConditionRef;
    private DatabaseReference databaseReference;
    private String time;
    private String subject;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        total_credit = (TextView)view.findViewById(R.id.credit);
        day = (TextView)view.findViewById(R.id.day);
        listView = (ListView)view.findViewById(R.id.listView);

        adapter = new HomeListAdapter(getContext(), R.layout.listhome_item);
        //listView.setAdapter(adapter);

        total_credit.setText(MajorReadapter.total_credit + "점");
        day.setText(doDayOfWeek() + "요일");

        getTodayData();

        return view;
    }

    private String doDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        String day = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        switch(nWeek) {
            case 1: day = "일"; break;
            case 2: day = "월"; break;
            case 3: day = "화"; break;
            case 4: day = "수"; break;
            case 5: day = "목"; break;
            case 6: day = "금"; break;
            case 7: day = "토"; break;
        }
        return day;
    }

    public void getTodayData() {
        SharedPreferences pref = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
        String name = pref.getString("IDemail","");
        String id[] = name.split("@");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mConditionRef = databaseReference.child("파이어베이스").child("강의").child(id[0]);
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Major major = child.getValue(Major.class);
                    time = major.getNtime();
                    subject = major.getSubject() + "\n" + major.getProfessor() + "\n" + major.getNclass();
                    String str[] = major.getNtime().split(" ");
                    if(str[0].equals(doDayOfWeek())) {
                        adapter.addItem(time, subject);
                        listView.setAdapter(adapter);
                    }
                }
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
