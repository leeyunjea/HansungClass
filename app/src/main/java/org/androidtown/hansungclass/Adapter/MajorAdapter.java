package org.androidtown.hansungclass.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.androidtown.hansungclass.FirebaseClass.Major;
import org.androidtown.hansungclass.R;

import java.util.List;

/**
 * Created by LG on 2017-11-13.
 */

public class MajorAdapter extends BaseAdapter{

    private Context context;
    private List<Major> myItems;


    public MajorAdapter(Context context, List<Major> items){
        this.context = context;
        this.myItems = items;
    }

    @Override
    public int getCount() { // 사이즈 리턴!!
        return myItems.size();
    }
    @Override
    public Object getItem(int position) {
        return myItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.enrollment_course,null);
        TextView subject = (TextView)v.findViewById(R.id.coursesubject);
        TextView professor = (TextView)v.findViewById(R.id.courseprofessor);
        TextView nclass = (TextView)v.findViewById(R.id.courseclass);
        TextView divide = (TextView)v.findViewById(R.id.coursedivide);
        TextView ntime = (TextView)v.findViewById(R.id.coursetime);

        subject.setText(myItems.get(position).getSubject());
        professor.setText(myItems.get(position).getProfessor());
        nclass.setText(myItems.get(position).getNclass());
        divide.setText(myItems.get(position).getDivide());
        ntime.setText(myItems.get(position).getNtime());

        return v;
    }
}
