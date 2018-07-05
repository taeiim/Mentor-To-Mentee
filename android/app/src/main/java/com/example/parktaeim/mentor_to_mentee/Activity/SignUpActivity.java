package com.example.parktaeim.mentor_to_mentee.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaeim.mentor_to_mentee.API.Client;
import com.example.parktaeim.mentor_to_mentee.API.InfRetrofit;
import com.example.parktaeim.mentor_to_mentee.R;
import com.example.parktaeim.mentor_to_mentee.ServerCode;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//서버 접속 레트로핏 남음 attemptRegister 변경예정, 툴바에 홈버튼
public class SignUpActivity extends AppCompatActivity {
    private final String TAG = "SignUpActivity";
    private Toolbar toolbar;
    private TextView toolbar_title;
    private Button btnSignUp, btnOverlap;
    //et : EditText
    private EditText etId, etPw, etPwConfirm, etName, etAge, etPhone;
    private HashMap<String, EditText> etMap;
    private SignUp signUp;
    private boolean isIdOverlapChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPw);
        etPwConfirm = findViewById(R.id.etPwConfirm);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPhone = findViewById(R.id.etPhoneCall);

        etMap = new HashMap<>();
        etMap.put(strSignUp.ID, etId);
        etMap.put(strSignUp.PW, etPw);
        etMap.put(strSignUp.PWCONFIRM, etPwConfirm);
        etMap.put(strSignUp.NAME, etName);
        etMap.put(strSignUp.AGE, etAge);
        etMap.put(strSignUp.PHONE, etPhone);


        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isIdOverlapChecked) {
                    etId.requestFocus();
                    etId.setError("아이디 중복 체크를 진행해주세요");
                } else {
                    if (isSignUpDataVaild()) {
                        attemptSignUp(etMap.get(strSignUp.ID).getText().toString(),
                                etMap.get(strSignUp.PW).getText().toString(),
                                etMap.get(strSignUp.NAME).getText().toString(),
                                Integer.parseInt(etMap.get(strSignUp.AGE).getText().toString()),
                                etMap.get(strSignUp.PHONE).getText().toString());
                    }
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar.setTitle(getString(R.string.prompt_register));
        toolbar_title.setText(toolbar.getTitle());
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); //액션바에 표시되는 제목의 표시유무를 설정
        actionBar.setDisplayShowHomeEnabled(false); // 액션바 왼쪽의 <- 버튼

        btnOverlap = findViewById(R.id.btnOverlap);

        btnOverlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isIdVaild(etId.getText().toString())) {
                    isIdOverlap(etId.getText().toString());
                } else {
                    etId.requestFocus();
                    etId.setError(getString(R.string.error_invalid_id));
                }
            }
        });


    }

    //4글자 이상
    private boolean isIdVaild(String id) {
        return id.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * 입력된 정보가 회원가입을 시도하기에 유효한 데이터인지 검사
     * 에러 종류 : 미입력, 올바르지 못한 형식 (ex: @가 없는 이메일, 5자 미만의 비밀번호, 일치하지 않는 비밀번호와 확인)
     *
     * @return boolean
     */
    private boolean isSignUpDataVaild() {
        //etName, etId, etPw, etPwConfirm, etPhoneCall, etEmail, etAge, etJob
        boolean isError = false;
        View focusView = null;
        // Reset errors.
        for (String key : etMap.keySet()) {
            etMap.get(key).setError(null);
        }

        signUp = new SignUp(etMap.get(strSignUp.ID).getText().toString(),
                etMap.get(strSignUp.PW).getText().toString(),
                etMap.get(strSignUp.PWCONFIRM).getText().toString(),
                etMap.get(strSignUp.NAME).getText().toString(),
                Integer.parseInt(etMap.get(strSignUp.AGE).getText().toString()),
                etMap.get(strSignUp.PHONE).getText().toString());


        if (TextUtils.isEmpty(signUp.getName())) {
            etMap.get(strSignUp.NAME).setError(getString(R.string.error_no_input_name));
            focusView = etMap.get(strSignUp.NAME);
            isError = true;
        } else if (TextUtils.isEmpty(signUp.getPwd())) {
            etMap.get(strSignUp.PW).setError(getString(R.string.error_no_input_pw));
            focusView = etMap.get(strSignUp.PW);
            isError = true;
        } else if (!isPasswordValid(signUp.getPwd())) {
            etMap.get(strSignUp.PW).setError(getString(R.string.error_invalid_password));
            focusView = etMap.get(strSignUp.PW);
            isError = true;
        } else if (TextUtils.isEmpty(signUp.getPwdConfirm())) {
            etMap.get(strSignUp.PWCONFIRM).setError(getString(R.string.error_no_input_pwConfirm));
            focusView = etMap.get(strSignUp.PWCONFIRM);
            isError = true;
        } else if (!signUp.getPwd().equals(signUp.getPwdConfirm())) {
            etMap.get(strSignUp.PWCONFIRM).setError(getString(R.string.error_no_match_pwConfirm));
            focusView = etMap.get(strSignUp.PWCONFIRM);
            isError = true;
        } else if (TextUtils.isEmpty(signUp.getPhone())) {
            etMap.get(strSignUp.PHONE).setError(getString(R.string.error_no_input_phoneCall));
            focusView = etMap.get(strSignUp.PHONE);
            isError = true;
        } else if (TextUtils.isEmpty(etMap.get(strSignUp.AGE).getText().toString())) {
            etMap.get(strSignUp.AGE).setError(getString(R.string.error_no_input_age));
            focusView = etMap.get(strSignUp.AGE);
            isError = true;
        }


        if (isError) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void isIdOverlap(String id) {
        InfRetrofit insRetrofit = Client.getInstance().create(InfRetrofit.class);
        Call<Void> call = insRetrofit.idOverlap(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {

                    case ServerCode.OVERLAP_SUCCESS:
                        Toast.makeText(getApplicationContext(), "사용할 수 있는 아이디입니다", Toast.LENGTH_SHORT).show();
                        isIdOverlapChecked = true;
                        break;
                    case ServerCode.OVERLAP_FAILED:
                        Toast.makeText(getApplicationContext(), "사용할 수 없는 아이디입니다", Toast.LENGTH_SHORT).show();
                        isIdOverlapChecked = false;
                        break;
                    case ServerCode.OVERLAP_SERER_ERROR:
                        Toast.makeText(getApplicationContext(), "서버와의 통신에 문제가 발생했습니다. 잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                        isIdOverlapChecked = false;
                        break;
                }

                Log.i(TAG, "통신완료 " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.prompt_server_connect_error) + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("SignUp", "서버 불러오기 실패 : " + t.getMessage());
                Log.i("SignUp", "요청 메시지 : " + call.request());
            }
        });
    }

    private void attemptSignUp(String id, String pwd, String name, int age, String phone) {

        InfRetrofit InsRetrofit = Client.getInstance().create(InfRetrofit.class);
        Call<Void> call = InsRetrofit.signUp(id, pwd, name, age, phone);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case ServerCode.SIGNUP_RES_SUCCESS:
                        // 성공
//                        setJWTToken(response.body().getAsString());
                        Toast.makeText(getApplicationContext(), getString(R.string.prompt_server_success), Toast.LENGTH_SHORT).show();
                        Log.i("signUp", "가입 성공");
                        SignUpActivity.this.finish();
                        break;
                    case ServerCode.SIGNUP_RES_OVERLAP:
                        // ID 중복
                        Toast.makeText(getApplicationContext(), getString(R.string.prompt_server_id_overlap), Toast.LENGTH_SHORT).show();
                        Log.i("signUp", "아이디 중복");
                        etId.requestFocus();
                        break;
                    case 400:
                        Toast.makeText(SignUpActivity.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                    default:
                        Toast.makeText(getApplicationContext(), getString(R.string.prompt_server_error + response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            // 요청한 API 서버의 다운 또는 DB Query 중 오류 등와 같은 서버의 비정상적인 동작으로 인해 요청에 대한 응답을 받지 못하는 경우
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.prompt_server_connect_error) + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("SignUp", "서버 불러오기 실패 : " + t.getMessage());
                Log.d("SignUp", "요청 메시지 : " + call.request());
            }
        });
    }


    class SignUp {
        private int age;
        private String id, pwd, pwdConfirm, name, phone;

        public SignUp(String id, String pwd, String pwdConfirm, String name, int age, String phone) {
            this.id = id;
            this.pwd = pwd;
            this.pwdConfirm = pwdConfirm;
            this.name = name;
            this.age = age;
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public String getPwd() {
            return pwd;
        }

        public String getPwdConfirm() {
            return pwdConfirm;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getPhone() {
            return phone;
        }
    }

    private class strSignUp {
        static final String ID = "etId", PW = "etPw", PWCONFIRM = "etPwConfirm", NAME = "etName", AGE = "etAge", PHONE = "etPhone";
    }

}