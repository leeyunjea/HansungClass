package org.androidtown.hansungclass.ViewPager;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidtown.hansungclass.Adapter.MajorAdapter;
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

    private ListView majorListView;
    private MajorAdapter adapter;
    private ArrayList<Major> majorList;

    private  Major name;

    private String courseUniversity = "";
    private String courseYear = "";
    private String courseTerm = "";
    private String courseArea = "";
    private FirebaseDatabase fd ;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    //private DatabaseReference mConditionRef = databaseReference.child("학부").child("2013년").child("1학기").child("전공");
    private DatabaseReference mConditionRef = databaseReference.child("학부").child("2013년").child("1학기").child("전공");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enrollment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = (Button)getView().findViewById(R.id.SearchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mConditionRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            name = child.getValue(Major.class);
                        }
                        majorList = new ArrayList<Major>();
                        majorList.add(name);
                        majorListView = (ListView)getView().findViewById(R.id.courseListView);
                        adapter = new MajorAdapter(getContext().getApplicationContext(),majorList);
                        majorListView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        final RadioGroup courseGroup = (RadioGroup)getView().findViewById(R.id.courseUniversityGroup);
        yearSpinner = (Spinner)getView().findViewById(R.id.yearSpinner);
        termSpinner = (Spinner)getView().findViewById(R.id.termSpinner);
        areaSpinner = (Spinner)getView().findViewById(R.id.areaSpinner);
        subjectSpinner = (Spinner)getView().findViewById(R.id.majorSpiiner);

        courseGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton courseButton = (RadioButton)getView().findViewById(checkedId);
                courseUniversity = courseButton.getText().toString();

                yearAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.year, android.R.layout.simple_dropdown_item_1line);
                yearSpinner.setAdapter(yearAdapter);

                termAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.term, android.R.layout.simple_dropdown_item_1line);
                termSpinner.setAdapter(termAdapter);

                if(courseUniversity.equals("학부")){
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.subjects, android.R.layout.simple_dropdown_item_1line);
                    areaSpinner.setAdapter(areaAdapter);

                        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(areaSpinner.getItemAtPosition(position).toString().equals("전공")){
                                    subjectAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.major,android.R.layout.simple_dropdown_item_1line);
                                    subjectSpinner.setAdapter(subjectAdapter);
                                }
                                else if(areaSpinner.getItemAtPosition(position).toString().equals("일반교양")){
                                    subjectAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.general,android.R.layout.simple_dropdown_item_1line);
                                    subjectSpinner.setAdapter(subjectAdapter);
                                }
                                else if(areaSpinner.getItemAtPosition(position).toString().equals("필수교양")){
                                    subjectAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.essential,android.R.layout.simple_dropdown_item_1line);
                                    subjectSpinner.setAdapter(subjectAdapter);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                }
                else if(courseUniversity.equals("대학원")){
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.graduate, android.R.layout.simple_dropdown_item_1line);
                    areaSpinner.setAdapter(areaAdapter);
                }
            }
        });

    }
}