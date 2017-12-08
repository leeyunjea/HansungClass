package org.androidtown.hansungclass.ViewPager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidtown.hansungclass.Adapter.MajorReadapter;
import org.androidtown.hansungclass.FirebaseClass.Login;
import org.androidtown.hansungclass.FirebaseClass.Major;
import org.androidtown.hansungclass.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnrollmentFragment extends Fragment {

    public EnrollmentFragment() {
        // Required empty public constructor
    }
    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter subjectAdapter;
    private Spinner subjectSpinner;
    private RecyclerView majorRecyclerView;
    private MajorReadapter adapter;
    private String id[];
    private ArrayList<Major> majorList;

    private Major name;
    private String courseUniversity = "";
    private DatabaseReference databaseReference;
    private DatabaseReference mConditionRef;
    private Handler handler;
    public static int total_credit=0;
    private int credit;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enrollment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RadioGroup courseGroup = (RadioGroup) getView().findViewById(R.id.courseUniversityGroup);
        yearSpinner = (Spinner) getView().findViewById(R.id.yearSpinner);
        termSpinner = (Spinner) getView().findViewById(R.id.termSpinner);
        areaSpinner = (Spinner) getView().findViewById(R.id.areaSpinner);
        subjectSpinner = (Spinner) getView().findViewById(R.id.majorSpiiner); //여기서 쓰이는데
        courseGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton courseButton = (RadioButton) getView().findViewById(checkedId);
                courseUniversity = courseButton.getText().toString();

                yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.year, android.R.layout.simple_dropdown_item_1line);
                yearSpinner.setAdapter(yearAdapter);

                termAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.term, android.R.layout.simple_dropdown_item_1line);
                termSpinner.setAdapter(termAdapter);

                databaseReference = FirebaseDatabase.getInstance().getReference();

                if (courseUniversity.equals("학부") == true) {
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.subjects, android.R.layout.simple_dropdown_item_1line);
                    areaSpinner.setAdapter(areaAdapter);

                    areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (areaSpinner.getItemAtPosition(position).toString().equals("전공")) {
                                subjectAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.major, android.R.layout.simple_dropdown_item_1line);
                                subjectSpinner.setAdapter(subjectAdapter);

                                subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if(subjectSpinner.getItemAtPosition(position).toString().equals("컴퓨터공학과")){
                                            mConditionRef = databaseReference.child("university").child("2018").child("1").child("major").child("computer");
                                        }
                                        else if(subjectSpinner.getItemAtPosition(position).toString().equals("전자정보과")){
                                            mConditionRef = databaseReference.child("university").child("2018").child("1").child("major").child("electronic");
                                        }
                                        else if(subjectSpinner.getItemAtPosition(position).toString().equals("IT응용시스템공학과")){
                                            mConditionRef = databaseReference.child("university").child("2018").child("1").child("major").child("itsystem");
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            } else if (areaSpinner.getItemAtPosition(position).toString().equals("일반교양")) {
                                mConditionRef = databaseReference.child("university").child("2018").child("1").child("교양").child("기초교양");
                                subjectAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.basic, android.R.layout.simple_dropdown_item_1line);
                                subjectSpinner.setAdapter(subjectAdapter);
                            } else if (areaSpinner.getItemAtPosition(position).toString().equals("필수교양")) {
                                mConditionRef = databaseReference.child("university").child("2018").child("1").child("필수").child("필수교양");
                                subjectAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.essent, android.R.layout.simple_dropdown_item_1line);
                                subjectSpinner.setAdapter(subjectAdapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                } else if (courseUniversity.equals("대학원") == true) {
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.subjects2, android.R.layout.simple_dropdown_item_1line);
                    areaSpinner.setAdapter(areaAdapter);

                    areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (areaSpinner.getItemAtPosition(position).toString().equals("석사")) {
                                mConditionRef = databaseReference.child("대학원").child("2018").child("1").child("박사").child("박사과정");
                                subjectAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hak, android.R.layout.simple_dropdown_item_1line);
                                subjectSpinner.setAdapter(subjectAdapter);
                            } else if (areaSpinner.getItemAtPosition(position).toString().equals("박사")) {
                                mConditionRef = databaseReference.child("대학원").child("2018").child("1").child("석사").child("석사과정");
                                subjectAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.park, android.R.layout.simple_dropdown_item_1line);
                                subjectSpinner.setAdapter(subjectAdapter);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });

        Button btn = (Button)getView().findViewById(R.id.SearchButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConditionRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        majorList = new ArrayList<Major>();
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                                name = child.getValue(Major.class);
                            majorList.add(name);
                        }

                        majorRecyclerView = (RecyclerView)getView().findViewById(R.id.courseRecycleView);
                        pref = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
                        String name1 = pref.getString("IDemail","");
                        id = name1.split("@");
                        adapter = new MajorReadapter(getContext().getApplicationContext(),majorList,id[0]);
                        majorRecyclerView.setAdapter(adapter);
                        majorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}