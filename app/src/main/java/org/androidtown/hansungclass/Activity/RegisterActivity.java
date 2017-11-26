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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidtown.hansungclass.FirebaseClass.Login;
import org.androidtown.hansungclass.R;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    private DatabaseReference databaseReference;
    private DatabaseReference mConditionRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Context context;
    Button submit;
    private EditText name;
    private EditText student_id;
    private EditText grade;
    private boolean check;


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
        student_id = (EditText)findViewById(R.id.student_id);
        name = (EditText)findViewById(R.id.name);
        grade = (EditText)findViewById(R.id.grade);
        submit = (Button)findViewById(R.id.register);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConditionRef = databaseReference.child("파이어베이스").child("USER_INFO");
                mConditionRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        check = false;
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                            Login login = child.getValue(Login.class);
                            Log.i("asdasd",login.getStudent_id() + " sad " + student_id.getText().toString());
                            if(login.getStudent_id().equals(student_id.getText().toString())){//13910801
                                check = true;
                                Toast.makeText(context,"동일한 학번이 있습니다.",Toast.LENGTH_LONG).show();
                            }
                        }

                        if(check == true){

                        }
                        else {
                            createAccount(email.getText().toString(), password.getText().toString(),grade.getText().toString(),name.getText().toString(),student_id.getText().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



    }
    //private FirebaseAuth.AuthStateListener

    private void createAccount(final String email, final String password,final String grade,final String name,final String student_id) {
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
                            public void onComplete(@NonNull final Task<AuthResult> task) {
                                Log.i("yunjae", "onComplete()");
                                HashMap<String,String> register = new HashMap<>();
                                if(!task.isSuccessful()) {
                                    Toast.makeText(context, "동일한 ID가 있습니다.!!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    register.put("email", email);
                                    register.put("password", password);
                                    register.put("student_id", student_id);
                                    register.put("s_name", name);
                                    register.put("s_grade", grade);
                                    String emails[] = email.split("@");
                                    databaseReference.child("파이어베이스").child("USER_INFO").child(emails[0]).setValue(register);
                                    finish();
                                }
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