package org.androidtown.hansungclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.androidtown.hansungclass.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hscom006 on 2017-11-25.
 */

public class HomeListAdapter extends BaseAdapter {
    private Context context;
    private List<HomeTodayItem> items = new ArrayList<HomeTodayItem>();
    private int resource;

    public HomeListAdapter(Context context, int resource) {
        this.context = context;
        this.resource = resource;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }

        HomeTodayItem item = items.get(position);
        TextView time = (TextView)convertView.findViewById(R.id.ntime);
        TextView subject = (TextView)convertView.findViewById(R.id.course);

        time.setText(item.getTime());
        subject.setText(item.getSubject());

        return convertView;
    }

    public void addItem(String time, String subject) {
        HomeTodayItem item = new HomeTodayItem();

        item.setTime(time);
        item.setSubject(subject);

        items.add(item);
    }
}
