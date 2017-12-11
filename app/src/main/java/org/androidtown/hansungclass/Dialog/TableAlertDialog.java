package org.androidtown.hansungclass.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.androidtown.hansungclass.R;

public class TableAlertDialog extends AppCompatActivity implements View.OnClickListener{
    private String subject;
    private String professor;
    private String nclass;
    private TextView tv;
    private Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_table_alert_dialog);

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        professor = intent.getStringExtra("professor");
        nclass = intent.getStringExtra("nclass");

        tv = (TextView)findViewById(R.id.tv);
        tv.setText(subject + "\n" + professor + "\n" + nclass);
        Log.i("yunjae", "TableAlertDialog : " + subject + " " + professor + " " + nclass);

        check = (Button)findViewById(R.id.check);
        check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
