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
    private String times;
    private String daynumber[];
    private String ntime[];
    private String ntimer[];
    private Random random;
    public int i = 0;
    private int number;
    public TableFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
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
                        Major major = child.getValue(Major.class);
                        findDay(major,major.getColor());
                    }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void  findDay(Major major,int color1){
        String nntime = major.getNtime();
        daynumber = nntime.split(" "); //월   1,2,3
        ntime = daynumber[1].split(","); // 1 2 3

        if(daynumber[0].equals("월"))
        {
            for(int i=0; i<ArrayText.MONTEXT.length; i++){
                TextView md = (TextView)this.getView().findViewById(ArrayText.MONTEXT[i]);
                if(md.getText().toString().startsWith("월")){
                    ntime = md.getText().toString().split(" ");
                    findntime(md,daynumber[1],ntime[1],color1,major);
                }
                else{
                    resetTime(md,major);
                }
            }
        }
        else if(daynumber[0].equals("화")){
            for(int i=0; i<ArrayText.TUETEXT.length; i++){
                TextView td = (TextView)this.getView().findViewById(ArrayText.TUETEXT[i]);
                if(td.getText().toString().startsWith("화")){
                    ntime = td.getText().toString().split(" ");
                    findntime(td,daynumber[1],ntime[1],color1,major);
                }
                else{
                    resetTime(td,major);
                }
            }
        }
        else if(daynumber[0].equals("수")){
            for(int i=0; i<ArrayText.WEDTEXT.length; i++){
                TextView wd = (TextView)this.getView().findViewById(ArrayText.WEDTEXT[i]);
                if(wd.getText().toString().startsWith("수")){
                    ntime = wd.getText().toString().split(" ");
                    findntime(wd,daynumber[1],ntime[1],color1,major);
                }
                else{
                    resetTime(wd,major);
                }
            }
        }
        else if(daynumber[0].equals("목")){
            for(int i=0; i<ArrayText.THUTEXT.length; i++){
                TextView th = (TextView)this.getView().findViewById(ArrayText.THUTEXT[i]);
                if(th.getText().toString().startsWith("목")){
                    ntime = th.getText().toString().split(" ");
                    findntime(th,daynumber[1],ntime[1],color1,major);
                }
                else{
                    resetTime(th,major);
                }
            }
        }
        else if(daynumber[0].equals("금")){
            for(int i=0; i<ArrayText.FRITEXT.length; i++){
                TextView fr = (TextView)this.getView().findViewById(ArrayText.FRITEXT[i]);
                if(fr.getText().toString().startsWith("금")){
                    ntime = fr.getText().toString().split(" ");
                    findntime(fr,daynumber[1],ntime[1],color1,major);
                }
                else{
                    resetTime(fr,major);
                }
            }
        }
    }

    public void resetTime(TextView tv,Major major){

            String m = major.getNtime();
            System.out.println("resetTime " + m);

    }

        public void findntime(TextView tv,String number,String ntime,int color1,Major major){
            String[] timer = number.split(",");
            for(int i=0;i<timer.length; i++){
                if(timer[i].equals(ntime)){
                    if(i==0){
                        tv.setText(major.getSubject() + "(" + major.getDivide() + ")");
                        tv.setTextColor(Color.BLACK);
                    }
                    else if(i==1){
                        tv.setText(major.getProfessor());
                        tv.setTextColor(Color.BLACK);
                    }
                    else if(i==2){
                        tv.setText(major.getNclass());
                        tv.setTextColor(Color.BLACK);
                    }
                    tv.setBackgroundColor(color1);
                }
        }
    }
}