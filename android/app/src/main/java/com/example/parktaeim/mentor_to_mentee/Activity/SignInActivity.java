package com.example.parktaeim.mentor_to_mentee.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaeim.mentor_to_mentee.API.Client;
import com.example.parktaeim.mentor_to_mentee.API.InfRetrofit;
import com.example.parktaeim.mentor_to_mentee.Model.JWTModel;
import com.example.parktaeim.mentor_to_mentee.R;
import com.example.parktaeim.mentor_to_mentee.ServerCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A login screen that offers login via Id/password.
 */
public class SignInActivity extends AppCompatActivity {
    private static final String TAG_TOKEN = "TOKEN";

    private TextView registerTV;
    private Retrofit retrofit = null;

    // UI references.
    private AutoCompleteTextView mIDView;
    private EditText mPasswordView;
    private CheckBox autoLoginBox;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.
        mIDView = (AutoCompleteTextView) findViewById(R.id.id);

        mIDView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    mPasswordView.requestFocus();
                    return true;
                }
                return false;
            }
        });

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            //키보드 액션
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                //엔터키 누르면
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        registerTV = (TextView) findViewById(R.id.register_tv);
        registerTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        setCheckBox();
    }

    private void setCheckBox() {
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();

        autoLoginBox = findViewById(R.id.autoLoginCheckBox);
        autoLoginBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String ID = mIDView.getText().toString();
                    String PW = mPasswordView.getText().toString();

                    editor.putString("ID", ID);
                    editor.putString("PW", PW);
                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();
                } else {
                    editor.clear();
                    editor.commit();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        runAutoLogin();

    }

    private void runAutoLogin() {
        if (pref.getBoolean("Auto_Login_enabled", false)) {
            mIDView.setText(pref.getString("ID", ""));
            mPasswordView.setText(pref.getString("PW", ""));
            autoLoginBox.setChecked(true);
            // goto mainActivity
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid Id, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (retrofit == null) {
            retrofit = Client.getInstance();
        }

        // Reset errors.
        mIDView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String id = mIDView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_no_input_pw));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid Id address.
        if (TextUtils.isEmpty(id)) {
            mIDView.setError(getString(R.string.error_no_input_id));
            focusView = mIDView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // attempt login
            signIn(id, password);
        }
    }


    private void signIn(final String id, final String pwd) {
        InfRetrofit InsRetrofit = this.retrofit.create(InfRetrofit.class);
        Call<JWTModel> call = InsRetrofit.signIn(id, pwd);
        call.enqueue(new Callback<JWTModel>() {

            @Override
            public void onResponse(Call<JWTModel> call, Response<JWTModel> response) {
                switch (response.code()) {
                    case ServerCode.SIGNIN_RES_SUCCESS:
                        String token = null;
                        if (response.body() != null) {
                            token = response.body().getAuthorization();
                        }
                        setJWTToken(token);
                        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                        Log.i("SignIn", "로그인 성공 " + token);
                        SignInActivity.this.finish();
                        break;
                    case ServerCode.SIGNIN_RES_NO_ACCOUNT:
                        Toast.makeText(SignInActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        Log.i("SignIn", "null response \n response message : " + response.message() + "\n response code : " + response.code() + " errorBody : " + response.errorBody());
                        break;
                    default:
                        Log.i("SignIn", "failed get data\nresponse message : " + response.message() + "\nresponse code : " + response.code());
                        break;
                }
            }

            @Override
            public void onFailure(Call<JWTModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.prompt_server_connect_error) + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("SignIn", "서버 불러오기 실패 : " + t.getMessage());
                Log.d("SignIn", "요청 메시지 : " + call.request());
                Log.d("SignIn", t.getStackTrace().toString());
                Log.d("SignIn", t.getCause().toString());
            }
        });
    }

    private void setJWTToken(String token) {
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();

        editor.putString(TAG_TOKEN, token);
        editor.apply(); // commit()
    }

}