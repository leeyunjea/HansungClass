package org.androidtown.hansungclass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidtown.hansungclass.FirebaseClass.Major;
import org.androidtown.hansungclass.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hscom-019 on 2017-11-19.
 */

public class MajorReadapter extends RecyclerView.Adapter<MajorReadapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView coursesubject;
        public TextView coursecredit;
        public TextView coursedivide;
        public TextView courseprofessor;
        public TextView coursenclass;
        public TextView coursentime;
        private Context context;
        private Major major;
        private MajorReadapter majorReadapter;
        private DatabaseReference mDatabase;
        private Button btn;

        public ViewHolder(Context context, View itemView,MajorReadapter majorReadapter){
            super(itemView);
            this.context = context;
            this.majorReadapter = majorReadapter;
            coursesubject = (TextView)itemView.findViewById(R.id.coursesubject);
            coursecredit = (TextView)itemView.findViewById(R.id.coursecredit);
            coursedivide = (TextView)itemView.findViewById(R.id.coursedivide);
            courseprofessor = (TextView)itemView.findViewById(R.id.courseprofessor);
            coursenclass = (TextView)itemView.findViewById(R.id.courseclass);
            coursentime = (TextView)itemView.findViewById(R.id.coursetime);
            btn = (Button)itemView.findViewById(R.id.courseButton1);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    private Context context;
    private List<Major> majorList;
    private Major major;

    public MajorReadapter(Context context, List<Major> majorList){
        this.context = context;
        this.majorList = majorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.enrollment_course,parent,false);
        ViewHolder viewHolder = new ViewHolder(context,view,this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        major = majorList.get(position);
        TextView subject = holder.coursesubject;
        subject.setText(major.getSubject());
        TextView credit = holder.coursecredit;
        credit.setText(Integer.toString(major.getCredit()));
        TextView divide = holder.coursedivide;
        divide.setText(major.getDivide());
        TextView professor = holder.courseprofessor;
        professor.setText(major.getProfessor());
        TextView nclass = holder.coursenclass;
        nclass.setText(major.getNclass());
        TextView ntime = holder.coursentime;
        ntime.setText(major.getNtime());
    }

    @Override
    public int getItemCount() {
        return majorList.size();
    }
}
