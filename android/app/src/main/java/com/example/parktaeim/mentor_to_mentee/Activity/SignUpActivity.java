package com.example.parktaeim.mentor_to_mentee.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parktaeim.mentor_to_mentee.R;

import java.util.HashMap;

//서버 접속 레트로핏 남음 attemptRegister 변경예정, 툴바에 홈버튼
public class SignUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbar_title;
    private Button btnSignUp;
    //et : EditText
    private EditText etName, etId, etPw, etPwConfirm, etPhoneCall, etEmail, etAge, etJob;
    private HashMap<String, EditText> editTextHashMap;
    // 입력된 데이터들을 문자열로 담아둘 POJO 객체
    private SignUp signUp;
    private RegisterTask registerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = (EditText) findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPw);
        etPwConfirm = findViewById(R.id.etPwConfirm);
        etPhoneCall = findViewById(R.id.etPhoneCall);
        etEmail = findViewById(R.id.etEmail);
        etAge = findViewById(R.id.etAge);
        etJob = findViewById(R.id.etJob);


        editTextHashMap = new HashMap<>();
        editTextHashMap.put(strSignUp.NAME, etName);
        editTextHashMap.put(strSignUp.ID, etId);
        editTextHashMap.put(strSignUp.PW, etPw);
        editTextHashMap.put(strSignUp.PWCONFIRM, etPwConfirm);
        editTextHashMap.put(strSignUp.PHONECALL, etPhoneCall);
        editTextHashMap.put(strSignUp.EMAIL, etEmail);
        editTextHashMap.put(strSignUp.AGE, etAge);
        editTextHashMap.put(strSignUp.JOB, etJob);

        btnSignUp = findViewById(R.id.btnSignUp);

        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar.setTitle(getString(R.string.prompt_register));
        toolbar_title.setText(toolbar.getTitle());
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); //액션바에 표시되는 제목의 표시유무를 설정
        actionBar.setDisplayShowHomeEnabled(false); // 액션바 왼쪽의 <- 버튼

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRegisterDataVaild()) {
                    attemptRegister();
                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
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
    private boolean isRegisterDataVaild() {
        //etName, etId, etPw, etPwConfirm, etPhoneCall, etEmail, etAge, etJob
        boolean isError = false;
        View focusView = null;
        // Reset errors.
        for (String key : editTextHashMap.keySet()) {
            editTextHashMap.get(key).setError(null);
        }
        signUp = new SignUp(editTextHashMap.get(strSignUp.NAME).getText().toString(),
                editTextHashMap.get(strSignUp.ID).getText().toString(),
                editTextHashMap.get(strSignUp.PW).getText().toString(),
                editTextHashMap.get(strSignUp.PWCONFIRM).getText().toString(),
                editTextHashMap.get(strSignUp.PHONECALL).getText().toString(),
                editTextHashMap.get(strSignUp.EMAIL).getText().toString(),
                editTextHashMap.get(strSignUp.AGE).getText().toString(),
                editTextHashMap.get(strSignUp.JOB).getText().toString());

        if (TextUtils.isEmpty(signUp.getName())) {
            editTextHashMap.get(strSignUp.NAME).setError(getString(R.string.error_no_input_name));
            focusView = editTextHashMap.get(strSignUp.NAME);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getId())) {

            editTextHashMap.get(strSignUp.ID).setError(getString(R.string.error_no_input_id));
            focusView = editTextHashMap.get(strSignUp.ID);
            isError = true;
        } else if (!isIdVaild(signUp.getId())) {
            editTextHashMap.get(strSignUp.ID).setError(getString(R.string.error_invalid_id));
            focusView = editTextHashMap.get(strSignUp.ID);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getPw())) {
            editTextHashMap.get(strSignUp.PW).setError(getString(R.string.error_no_input_pw));
            focusView = editTextHashMap.get(strSignUp.PW);
            isError = true;
        } else if (!isPasswordValid(signUp.getPw())) {
            editTextHashMap.get(strSignUp.PW).setError(getString(R.string.error_invalid_password));
            focusView = editTextHashMap.get(strSignUp.PW);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getPwConfirm())) {
            editTextHashMap.get(strSignUp.PWCONFIRM).setError(getString(R.string.error_no_input_pwConfirm));
            focusView = editTextHashMap.get(strSignUp.PWCONFIRM);
            isError = true;
        } else if (!signUp.getPw().equals(signUp.getPwConfirm())) {
            editTextHashMap.get(strSignUp.PWCONFIRM).setError(getString(R.string.error_no_match_pwConfirm));
            focusView = editTextHashMap.get(strSignUp.PWCONFIRM);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getPhoneCall())) {
            editTextHashMap.get(strSignUp.PHONECALL).setError(getString(R.string.error_no_input_phoneCall));
            focusView = editTextHashMap.get(strSignUp.PHONECALL);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getEmail())) {
            editTextHashMap.get(strSignUp.EMAIL).setError(getString(R.string.error_no_input_email));
            focusView = editTextHashMap.get(strSignUp.EMAIL);
            isError = true;
        } else if (!isEmailValid(signUp.getEmail())) {
            editTextHashMap.get(strSignUp.EMAIL).setError(getString(R.string.error_invalid_email));
            focusView = editTextHashMap.get(strSignUp.EMAIL);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getAge())) {
            editTextHashMap.get(strSignUp.AGE).setError(getString(R.string.error_no_input_age));
            focusView = editTextHashMap.get(strSignUp.AGE);
            isError = true;
        }

        else if (TextUtils.isEmpty(signUp.getJob())) {
            editTextHashMap.get(strSignUp.JOB).setError(getString(R.string.error_no_input_job));
            focusView = editTextHashMap.get(strSignUp.JOB);
            isError = true;
        }


        if (isError) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void attemptRegister() {
        registerTask = new RegisterTask(signUp);
        registerTask.execute((Void) null);
    }

    class SignUp {
        private final String name, id, pw, pwConfirm, phoneCall, email, age, job;

        public SignUp(String name, String id, String pw, String pwConfirm, String phoneCall, String email, String age, String job) {
            this.name = name;
            this.id = id;
            this.pw = pw;
            this.pwConfirm = pwConfirm;
            this.phoneCall = phoneCall;
            this.email = email;
            this.age = age;
            this.job = job;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getPw() {
            return pw;
        }

        public String getPwConfirm() {
            return pwConfirm;
        }

        public String getPhoneCall() {
            return phoneCall;
        }

        public String getEmail() {
            return email;
        }

        public String getAge() {
            return age;
        }

        public String getJob() {
            return job;
        }
    }

    private class strSignUp {
        static final String NAME = "etName", ID = "etId", PW = "etPw", PWCONFIRM = "etPwConfirm", PHONECALL = "etPhoneCall", EMAIL = "etEmail", AGE = "etAge", JOB = "etJob";
    }
    public class RegisterTask extends AsyncTask<Void, Void, Boolean> {
        SignUp signUp;

        public RegisterTask(SignUp signUp) {
            this.signUp = signUp;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
//            try {
//                Map<String, String> params =
//            }catch () {
//                return false;
//            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            registerTask = null;

            if (success) {
                Snackbar.make(btnSignUp, getString(R.string.success_register), Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(btnSignUp, getString(R.string.fail_register), Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        }

        @Override
        protected void onCancelled() {
            registerTask = null;
        }
    }

}