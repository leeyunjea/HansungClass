package org.androidtown.hansungclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button signup;
    Button login;
    EditText email;
    EditText password;
    String email_str;
    String password_str;
    String yunjae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        email_str = email.getText().toString();
        password_str = password.getText().toString();

        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount(email.getText().toString(), password.getText().toString());
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() { //인증상태리스너

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //인증상태가 불리면 불리는 콜백
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Log.i("yunjae", "signed_in");
                }else {
                    Log.i("yunjae", "signed_out");
                }
            }
        };

        Log.i("yunjae", "onCreate()");
    }

    private void loginAccount(String email, String password) {

        if(TextUtils.isEmpty(email)) {
            Log.i("yunjae", "eamil이 비어있음");
            Toast.makeText(this, "email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Log.i("yunjae", "password이 비어있음");
            Toast.makeText(this, "password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("yunjae", "signInWithEmail:onComplete:");

                        if(!task.isSuccessful()) {
                            Log.i("yunjae", "signInWithEmail:failed");
                            Toast.makeText(getApplicationContext(), "로그인 실패!",
                                    Toast.LENGTH_SHORT).show();
                        }else if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "로그인 성공!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

}

