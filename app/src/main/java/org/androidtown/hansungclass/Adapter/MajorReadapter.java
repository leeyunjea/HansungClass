package org.androidtown.hansungclass.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static java.lang.Integer.parseInt;

/**
 * Created by hscom-019 on 2017-11-19.
 */

public class MajorReadapter extends RecyclerView.Adapter<MajorReadapter.ViewHolder>{
    public static int i=0;
    public static int total_credit=0;

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView coursesubject;
        public TextView coursecredit;
        public TextView coursedivide;
        public TextView courseprofessor;
        public TextView coursenclass;
        public TextView coursentime;
        public String count = "30";
        public String color = "0";
        private Context context;
        private Major major;
        private MajorReadapter majorReadapter;
        private DatabaseReference mDatabase;
        private TextView cancel;
        private Button btn;
        private int colors[] = new int[15];
        //private int i=0;

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


            colors[0] = Color.rgb(214, 252, 251);
            colors[1] = Color.rgb(252, 214, 248);
            colors[2] = Color.rgb(255, 185, 185);
            colors[3] = Color.rgb(214, 252, 251);
            colors[4] = Color.rgb(198, 198, 255);
            colors[5] = Color.rgb(255, 220, 185);
            colors[6] = Color.rgb(228, 228, 228);
            colors[7] = Color.rgb(255, 255, 204);
            colors[8] = Color.rgb(255, 213, 234);
            colors[9] = Color.rgb(231, 206, 255);
            colors[10] = Color.rgb(200, 255, 214);
            colors[11] = Color.rgb(255, 218, 181);
            colors[12] = Color.rgb(209, 209, 233);
            colors[13] = Color.rgb(196, 196, 255);
            colors[14] = Color.rgb(230, 204, 204);

        }

        @Override
        public void onClick(View v) {

            mDatabase = FirebaseDatabase.getInstance().getReference();
            HashMap<String, Object> majorHashMap = new HashMap<>();
            majorHashMap.put("color", colors[MajorReadapter.i]);
            majorHashMap.put("count",count);
            majorHashMap.put("credit",coursecredit.getText().toString());
            majorHashMap.put("divide",coursedivide.getText().toString());
            majorHashMap.put("nclass",coursenclass.getText().toString());
            majorHashMap.put("ntime",coursentime.getText().toString());
            majorHashMap.put("professor",courseprofessor.getText().toString());
            majorHashMap.put("subject",coursesubject.getText().toString());

            Log.i("yunjae", "onClick i = " + MajorReadapter.i);
            MajorReadapter.i++;

            if(btn.getText().equals("신청")){
                mDatabase.child("파이어베이스").child("강의").child(email).child(coursesubject.getText().toString()).setValue(majorHashMap);
                total_credit += parseInt((String) majorHashMap.get("credit"));
                Log.i("yunjae", " total_credit = " + total_credit);
                        btn.setText("취소");
    }
            else if(btn.getText().equals("취소")){
        mDatabase.child("파이어베이스").child("강의").child(email).child(coursesubject.getText().toString()).setValue(null);
                total_credit -= parseInt((String) majorHashMap.get("credit"));
                Log.i("yunjae", " total_credit = " + total_credit);
        btn.setText("신청");
    }
}
    }

    private Context context;
    private List<Major> majorList;
    private Major major;
    private String email;

    public MajorReadapter(Context context, List<Major> majorList, String email){
        this.context = context;
        this.majorList = majorList;
        this.email = email;
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
        credit.setText(major.getCredit());
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
