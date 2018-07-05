package com.example.parktaeim.mentor_to_mentee.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaeim.mentor_to_mentee.API.Client;
import com.example.parktaeim.mentor_to_mentee.API.InfRetrofit;
import com.example.parktaeim.mentor_to_mentee.R;
import com.example.parktaeim.mentor_to_mentee.ServerCode;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMentoringActivity extends AppCompatActivity {
    private static final int PICK_FROM_ALBUM = 10;
    ImageView imgProfile;
    EditText etTitle;
    EditText etInfo;
    EditText etCategory;
    EditText etMaxMember;
    EditText etSd;
    EditText etEd;
    Button btnCreate;

    EditText etAdd;
    Button btnAdd;
    private Uri imageUri = null;
    TextView imgText;
    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    private static final String TAG_TOKEN = "TOKEN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mentoring);

        TextView tvCancel = findViewById(R.id.creatementoringactivity_tv_toolbar_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgText = findViewById(R.id.creatementoringactivity_tv_profile_text);

        imgProfile = findViewById(R.id.creatementoringactivity_img_profile);
        etTitle = findViewById(R.id.creatementoringactivity_et_mentoring_title);
        etInfo = findViewById(R.id.creatementoringactivity_et_mentoring_subscription);
        etCategory = findViewById(R.id.creatementoringactivity_et_category);
        etSd = findViewById(R.id.creatementoringactivity_et_sd);
        etEd = findViewById(R.id.creatementoringactivity_et_ed);
        etMaxMember = findViewById(R.id.creatementoringactivity_et_maxMember);

        btnCreate = findViewById(R.id.creatementoringactivity_btn_create);
        recyclerView = findViewById(R.id.creatementoringactivity_recyclerview_questions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        questionAdapter = new QuestionAdapter();
        recyclerView.setAdapter(questionAdapter);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });
        etAdd = findViewById(R.id.creatementoringactivity_et_question);

        btnAdd = findViewById(R.id.creatementoringactivity_btn_question);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setEnabled(false);
                if(!TextUtils.isEmpty(etAdd.getText().toString())){
                    questionAdapter.addQuestion(etAdd.getText().toString());
                    etAdd.setText("");
                } else {
                    Toast.makeText(v.getContext(), "질문을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                btnAdd.setEnabled(true);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCreate.setEnabled(false);
                if (isDataVailed()) {
                    createMentoring(etTitle.getText().toString(), etInfo.getText().toString(), etCategory.getText().toString(), Integer.parseInt(etMaxMember.getText().toString()), etSd.getText().toString(), etEd.getText().toString(), questionAdapter.getQuestions(), imageUri);
                } else {
                    Toast.makeText(CreateMentoringActivity.this, "입력되지 않은 항목이 있습니다", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(CreateMentoringActivity.this, "개설 눌림", Toast.LENGTH_SHORT).show();
                btnCreate.setEnabled(true);
            }
        });



    }

    private boolean isDataVailed() {
        boolean res = true;
        //이미지
        if (imageUri == null) {
            res = false;
        } else if (TextUtils.isEmpty(etTitle.getText().toString())) {
            etTitle.requestFocus();
            res = false;
        } else if (TextUtils.isEmpty(etInfo.getText().toString())) {
            etInfo.requestFocus();
            res = false;
        } else if (TextUtils.isEmpty(etCategory.getText().toString())) {
            etCategory.requestFocus();
            res = false;
        } else if (TextUtils.isEmpty(etMaxMember.getText().toString())) {
            etMaxMember.requestFocus();
            res = false;
        } else if (TextUtils.isEmpty(etSd.getText().toString())) {
            etSd.requestFocus();
            res = false;
        } else if (TextUtils.isEmpty(etEd.getText().toString())) {
            etEd.requestFocus();
            res = false;
        } else if (!(questionAdapter.getItemCount() > 0)) {
            res = false;
        }
        return res;
    }

    private void createMentoring(String title, String info, String category, int max_member,
                                 String start_date, String end_date, ArrayList<String> questions, Uri uri) {
        String token = getJWTToken();

        File profile = new File(uri.getPath());
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), profile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", profile.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

        InfRetrofit retrofit = Client.getInstance().create(InfRetrofit.class);
        Call<Void> call = retrofit.createMentoring(token, title, info, category, max_member, start_date, end_date, questions);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case ServerCode.CREATE_MENTORING_SUCCESS:
                        Toast.makeText(CreateMentoringActivity.this, "멘토링 생성 성공", Toast.LENGTH_SHORT).show();
                        CreateMentoringActivity.this.finish();
                        break;
                    case ServerCode.CREATE_MENTORING_FAILED:
                        Toast.makeText(CreateMentoringActivity.this, "멘토링 생성 실패", Toast.LENGTH_LONG).show();
                        break;
                    case ServerCode.CREATE_MENTORING_SERER_ERROR:
                        Toast.makeText(CreateMentoringActivity.this, "서버에 문제가 발생했습니다", Toast.LENGTH_LONG).show();
                        break;
                        default:
                            Toast.makeText(getApplicationContext(), "개설 중 문제 발생 코드 : " + response.code(), Toast.LENGTH_SHORT).show();
                            Log.d("CreateMentoringActivity", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "fetch 실패\n에러메시지 : " + t.getMessage().toString(), Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

                Log.i("CreateMentoringActivity", "fetch 실패\n에러메시지 : " + t.getMessage().toString());
            }
        });
    }

    private String getJWTToken() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        return pref.getString(TAG_TOKEN, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK) {
            imgProfile.setImageURI(data.getData()); //뷰 이미지 변경
            imageUri = data.getData(); // 이미지 경로 저장
            imgText.setVisibility(View.GONE);
        } else {
            imgText.setVisibility(View.VISIBLE);
        }
    }

    class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<String> questions = new ArrayList<>();

        public void addQuestion(String question) {
            this.questions.add(question);
            notifyDataSetChanged();
            Toast.makeText(CreateMentoringActivity.this, "추가됨", Toast.LENGTH_SHORT).show();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questions, parent, false);
            return new QuestionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            QuestionViewHolder questionViewHolder = (QuestionViewHolder) holder;
            questionViewHolder.textView.setText(questions.get(position));
            questionViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    questions.remove(position);
                    notifyDataSetChanged();
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return questions.size();
        }

        public ArrayList<String> getQuestions() {
            return questions;
        }

        private class QuestionViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public QuestionViewHolder(View view) {
                super(view);
                textView = view.findViewById(R.id.questionItem_textview);
            }
        }
    }

}
