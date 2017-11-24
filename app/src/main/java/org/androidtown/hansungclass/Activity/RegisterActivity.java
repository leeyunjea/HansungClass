package org.androidtown.hansungclass.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidtown.hansungclass.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Context context;
    Button submit;
    private EditText name;
    private EditText student_id;
    private EditText grade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;

        name = (EditText)findViewById(R.id.name);
        student_id = (EditText)findViewById(R.id.student_id);
        grade = (EditText)findViewById(R.id.grade);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Log.i("yunjae", "signed_in");
                }else {
                    Log.i("yunjae", "signed_out");
                }
            }
        };

        email = (EditText)findViewById(R.id.register_email);
        password = (EditText)findViewById(R.id.register_password);
        submit = (Button)findViewById(R.id.register);
        databaseReference = FirebaseDatabase.getInstance().getReference();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("yunjae", "클릭");
                createAccount(email.getText().toString(), password.getText().toString());

            }
        });



    }
    //private FirebaseAuth.AuthStateListener

    private void createAccount(String email, String password) {
        if(!isValidEmail(email)) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_LONG).show();
            return;
        }

        if(!isValidPasswd(password)) {
            Toast.makeText(context, "Password is not valid", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("yunjae", "onComplete()");

                        if(!task.isSuccessful()) {
                            Toast.makeText(context, "회원가입 실패!", Toast.LENGTH_LONG).show();
                        }
                        else
                            finish();

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
    private boolean isValidEmail(String target) {
        if(target == null || TextUtils.isEmpty(target)) {
            return false;
        }
        else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isValidPasswd(String target) {
        Pattern p = Pattern.compile("(^.*(?=.{6,100})(?=.*[0-9])(?=.*[a-zA-Z]).*$)");

        Matcher m = p.matcher(target);
        if (m.find() && !target.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
            return true;
        }else{
            return false;
        }
    }
}