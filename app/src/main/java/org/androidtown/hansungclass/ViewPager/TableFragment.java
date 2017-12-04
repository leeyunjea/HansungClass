package org.androidtown.hansungclass.ViewPager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidtown.hansungclass.Class.ArrayText;
import org.androidtown.hansungclass.FirebaseClass.Major;
import org.androidtown.hansungclass.R;

import java.util.Random;

public class TableFragment extends Fragment {
    private DatabaseReference databaseReference;
    private DatabaseReference mConditionRef;
    private Major major;
    private String times;
    private String daynumber[];
    private String ntime[];
    private Random random;
    public int i = 0;
    public TableFragment() {
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences pref = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
        String name = pref.getString("IDemail","");
        String id[] = name.split("@");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mConditionRef = databaseReference.child("파이어베이스").child("강의").child(id[0]);
        mConditionRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                        major = child.getValue(Major.class);
                        times = major.getNtime();
                        findDay(major,major.getColor());
                    }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    public void  findDay(Major major,int color1){
        String nntime = major.getNtime();
        daynumber = times.split(" ");
        ntime = daynumber[1].split(",");

        if(daynumber[0].equals("월"))
        {
            for(int i=0; i<ArrayText.MONTEXT.length; i++){
                TextView md = (TextView)this.getView().findViewById(ArrayText.MONTEXT[i]);
                ntime = md.getText().toString().split(" ");
                findntime(md,daynumber[1],ntime[1],color1);
            }
        }
        else if(daynumber[0].equals("화")){
            for(int i=0; i<ArrayText.TUETEXT.length; i++){
                TextView td = (TextView)this.getView().findViewById(ArrayText.TUETEXT[i]);
                ntime = td.getText().toString().split(" ");
                findntime(td,daynumber[1],ntime[1],color1);
            }
        }
        else if(daynumber[0].equals("수")){
            for(int i=0; i<ArrayText.WEDTEXT.length; i++){
                TextView wd = (TextView)this.getView().findViewById(ArrayText.WEDTEXT[i]);
                ntime = wd.getText().toString().split(" ");
                findntime(wd,daynumber[1],ntime[1],color1);
            }
        }
        else if(daynumber[0].equals("목")){
            for(int i=0; i<ArrayText.THUTEXT.length; i++){
                TextView th = (TextView)this.getView().findViewById(ArrayText.THUTEXT[i]);
                ntime = th.getText().toString().split(" ");
                findntime(th,daynumber[1],ntime[1],color1);
            }
        }
        else if(daynumber[0].equals("금")){
            for(int i=0; i<ArrayText.FRITEXT.length; i++){
                TextView fr = (TextView)this.getView().findViewById(ArrayText.FRITEXT[i]);
                ntime = fr.getText().toString().split(" ");
                findntime(fr,daynumber[1],ntime[1],color1);
            }
        }
    }

        public void findntime(TextView tv,String number,String ntime,int color1){
            String[] timer = number.split(",");
            for(int i=0;i<timer.length; i++){
                if(timer[i].equals(ntime)){
                    tv.setBackgroundColor(color1);
                }
        }
    }
}