package org.androidtown.hansungclass.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidtown.hansungclass.Activity.MapActivity;
import org.androidtown.hansungclass.Class.ExchangeUniversityContact;
import org.androidtown.hansungclass.R;

import java.util.List;

/**
 * Created by hscom006 on 2017-11-30.
 */

public class ExchangeUniversityAdapter extends RecyclerView.Adapter<ExchangeUniversityAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView take;
        public TextView take_location;

        private ExchangeUniversityAdapter mContacts;
        private Context context;

        public ViewHolder(Context context, View itemView, ExchangeUniversityAdapter contacts) {
            super(itemView);
            take = (TextView)itemView.findViewById(R.id.take);
            take_location = (TextView)itemView.findViewById(R.id.take_location);
            this.context = context;
            this.mContacts = contacts;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { //위치 띄우기
            Intent intent = new Intent(context, MapActivity.class);
            intent.putExtra("location", take.getText());
            context.startActivity(intent);
        }
    }

    private List<ExchangeUniversityContact> mContacts;
    private Context mContext;

    public ExchangeUniversityAdapter(Context context, List<ExchangeUniversityContact> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, contactView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExchangeUniversityAdapter.ViewHolder holder, int position) {
        ExchangeUniversityContact contact = mContacts.get(position);

        TextView take = holder.take;
        take.setText(contact.getTake());
        TextView take_location = holder.take_location;
        //take_location.setText(contact.getTake_location());
        //take_location.setText("");
    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}
