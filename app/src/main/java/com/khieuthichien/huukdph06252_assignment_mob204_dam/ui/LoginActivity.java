package com.khieuthichien.huukdph06252_assignment_mob204_dam.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.khieuthichien.huukdph06252_assignment_mob204_dam.R;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.dao.UserDAO;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.User;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.sqlite.DatabaseHelper;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText edtusername;
    private EditText edtpassword;
    private CheckBox cbRemember;
    private Button btndangnhap;
    private Button btndangky;

    //đặt tên cho tập tin lưu trạng thái
    String prefname = "my_data";

    private DatabaseHelper databaseHelper;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initActions();

        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);

        // kiem tra user da ton tai trong DB chua !!!
        User user2;
        user2 = userDAO.getUser("admin");
        if (user2 == null) {
            Toast.makeText(this, "user chưa tồn tại", Toast.LENGTH_SHORT).show();
            User user = new User( "admin", "12345678", "khieu dinh huu", "01668339456");
            userDAO.insertUser(user);
            Log.e("",""+user.getUsername());
        } else {
            Toast.makeText(this, "user đã tồn tại", Toast.LENGTH_SHORT).show();
        }


    }

    private void initViews() {
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        cbRemember = findViewById(R.id.cbRemember);
        btndangnhap = findViewById(R.id.btndangnhap);
        btndangky = findViewById(R.id.btndangky);
    }

    private void initActions() {
        btndangnhap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtusername.getText().toString().trim();
                String pass = edtpassword.getText().toString().trim();

                if (username.isEmpty() || pass.isEmpty() || pass.length() < 6) {
                    if (username.equals(""))
                        edtusername.setError(getString(R.string.notify_empty_name));

                    if (pass.equals(""))
                        edtpassword.setError(getString(R.string.notify_empty_name));

                    if (pass.length() < 6)
                        edtpassword.setError(getString(R.string.notify_empty_pass_work));

                } else {

//                    //b1:dinh nghia Progress Dialog
//                    ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
//                    dialog.setTitle("Loanding....");
//                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                    //hien thi
//                    dialog.show();
//                    //b4: thiet lap gia tri cho thanh
//                    dialog.setProgressStyle(70);

                    User user = userDAO.getUser(username);
                    if (user != null && user.getUsername() != null) {
                        if (pass.matches(user.getPasswork())) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();

                        }
                    }


                }


            }
        });


        btndangky.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //gọi hàm lưu trạng thái ở đây
        savingPreferences();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //gọi hàm đọc trạng thái ở đây
        restoringPreferences();
    }

    //hàm lưu trạng thái
    private void savingPreferences() {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();
        String name = edtusername.getText().toString();     //khai báo
        String pass = edtpassword.getText().toString();     //khai báo
        boolean ghinho = cbRemember.isChecked();   //khai báo

        if (!ghinho) {
            //xóa mọi lưu trữ trước đó
            editor.clear();
        } else {
            //lưu vào editor
            editor.putString("name", name);
            editor.putString("pass", pass);
            editor.putBoolean("checked", ghinho);
        }
        //chấp nhận lưu xuống file
        editor.commit();
    }

    //hàm đọc trạng thái đã lưu trước đó
    private void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        boolean ghinho = pre.getBoolean("checked", false);

        if (ghinho) {
            //lấy name, pass, nếu không thấy giá trị mặc định là rỗng
            String username = pre.getString("name", "");
            String pass = pre.getString("pass", "");

            edtusername.setText(username);
            edtpassword.setText(pass);
        }
        cbRemember.setChecked(ghinho);
    }


}

