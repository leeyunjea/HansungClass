package org.androidtown.hansungclass.ViewPager;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.androidtown.hansungclass.Adapter.HomeListAdapter;
import org.androidtown.hansungclass.Adapter.MajorReadapter;
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

        total_credit.setText(MajorReadapter.total_credit + "점");
        day.setText(doDayOfWeek());

        return view;
    }

    private String doDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        String day = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        switch(nWeek) {
            case 1: day = "일요일"; break;
            case 2: day = "월요일"; break;
            case 3: day = "화요일"; break;
            case 4: day = "수요일"; break;
            case 5: day = "목요일"; break;
            case 6: day = "금요일"; break;
            case 7: day = "토요일"; break;
        }
        return day;
    }


}
