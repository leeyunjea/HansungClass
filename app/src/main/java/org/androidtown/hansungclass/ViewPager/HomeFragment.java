package org.androidtown.hansungclass.ViewPager;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import org.androidtown.hansungclass.Class.NotificationService;
import org.androidtown.hansungclass.FirebaseClass.Login;
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
    private String ntime;
    private DatabaseReference mDatabase;
    private NotificationService notificationService;
    private SharedPreferences pref;
    private String id[];

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
        pref = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
        String name = pref.getString("IDemail","");
        id = name.split("@");
        adapter = new HomeListAdapter(getContext(), R.layout.listhome_item);
        //listView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("파이어베이스").child("USER_INFO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Login login = child.getValue(Login.class);
                    String name[] = login.getEmail().split("@");
                    if(name[0].equals(id[0])){
                        total_credit.setText(Integer.toString(login.getU_credit()));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
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
        pref = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
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
                    String sub = major.getSubject();
                    String lo = major.getNclass();
                    ntime = major.getNtime();
                    String str[] = major.getNtime().split(" ");
                    if(str[0].equals(doDayOfWeek())) {
                        adapter.addItem(time, subject);
                        listView.setAdapter(adapter);
                        todayNotifyAlarm(ntime, sub, lo);
                        Log.i("yunjae", "ntime = " + ntime + "sub = " + sub + "lo = " +lo);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void todayNotifyAlarm(String ntime, String subject, String location) {
        String arr[] = ntime.split(" ");
        String time = arr[1];
        String times[] = time.split(",");

        Log.i("yunjae", "time="+times[0]);

        switch (times[0]) {
            case "0":
                Log.i("yunjae", "000000000000000");
                notificationService = new NotificationService();
                Intent intent0 = new Intent(getContext(), notificationService.getClass());
                intent0.putExtra("course", subject);
                intent0.putExtra("location", location);
                intent0.putExtra("time", times[0]);
                getContext().startService(intent0);
                break;
            case "1":
                Log.i("yunjae", "11111111111111");
                notificationService = new NotificationService();
                Intent intent1 = new Intent(getContext(), notificationService.getClass());
                intent1.putExtra("course", subject);
                intent1.putExtra("location", location);
                intent1.putExtra("time", times[0]);
                getContext().startService(intent1);
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                break;
            case "9":
                break;
            case "10":
                break;
            case "11":
                break;
            case "12":
                break;
        }
    }


}
