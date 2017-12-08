package org.androidtown.hansungclass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.androidtown.hansungclass.R;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button signup;
    private Button login;
    private EditText email;
    private EditText password;
    private String email_str;
    private String password_str;
    private CheckBox autoLogin;

    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        email_str = email.getText().toString();
        password_str = password.getText().toString();
        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();
        autoLogin = (CheckBox)findViewById(R.id.autoLogin);

        /*if(setting.getBoolean("chk_auto", false)) {
            email.setText(setting.getString("ID", ""));
            password.setText(setting.getString("PW", ""));
            //autoLogin.setChecked(true);
        }
        else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }*/

        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        login = (Button) findViewById(R.id.login);
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
                if (user != null) {
                    Log.i("yunjae", "signed_in");
                } else {
                    Log.i("yunjae", "signed_out");
                }
            }
        };
        Log.i("yunjae", "onCreate()");
    }

    private void loginAccount(final String email, String password) {


        if (TextUtils.isEmpty(email)) {
            Log.i("yunjae", "eamil이 비어있음");
            Toast.makeText(this, "email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Log.i("yunjae", "password이 비어있음");
            Toast.makeText(this, "password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("yunjae", "signInWithEmail:onComplete:");

                        if (!task.isSuccessful()) {
                            Log.i("yunjae", "signInWithEmail:failed");
                            Toast.makeText(getApplicationContext(), "로그인 실패!",
                                    Toast.LENGTH_SHORT).show();
                        } else if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "로그인 성공!",
                                    Toast.LENGTH_SHORT).show();
                            if(autoLogin.isChecked()) {
                                editor.putString("ID", email_str);
                                editor.putString("PW", password_str);
                                editor.putBoolean("chk_auto", true);
                                editor.commit();
                                Log.i("yunjae", "eamil_str = " + email_str);
                                Log.i("yunjae", "password_str = " + password_str);
                                Log.i("yunjae", "editor에 저장");
                            }else {
                                editor.clear();
                                editor.commit();
                            }
                            pref = getSharedPreferences("ID", Activity.MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putString("IDemail",email);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
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

