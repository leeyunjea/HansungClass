package org.androidtown.hansungclass.ViewPager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.androidtown.hansungclass.Adapter.HomeListAdapter;
import org.androidtown.hansungclass.R;

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

        adapter = new HomeListAdapter(getContext());
        //total_credit.setText(Integer.toString(EnrollmentFragment.total_credit));

        return view;
    }


}
