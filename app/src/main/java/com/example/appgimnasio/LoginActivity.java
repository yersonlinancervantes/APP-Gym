package com.example.appgimnasio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appgimnasio.entity.Usuario;
import com.example.appgimnasio.entity.UsuarioResponse;
import com.example.appgimnasio.repositories.local.Usuario.SessionManagement;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername;
    EditText txtPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnIngresar);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        /*Intent intentBackgroundService = new Intent(this,MyFirebaseMessagingService.class);
        startService(intentBackgroundService);
*/
    }

    protected void onStart(){
        super.onStart();
        checkSession();
    }

    private void checkSession(){
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userId = sessionManagement.getSession();
        if(userId != -1){
            moveToMainActivity();
        }
    }

    public void login(View view){

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(username.equals("")){
            Toast.makeText(LoginActivity.this,"El campo email está vacio.",Toast.LENGTH_LONG).show();
        }else if(password.equals("")){
            Toast.makeText(LoginActivity.this,"El campo password está vacio.",Toast.LENGTH_LONG).show();
        }else{
            showProgressDialog();
            UsuarioService jsonPlaceHolderApi = ServiceFactory.retrofit.create(UsuarioService.class);
            //username,password
            Call<UsuarioResponse> call = jsonPlaceHolderApi.login(username,password);
            call.enqueue(new Callback<UsuarioResponse>() {
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"OCURRIO UN ERROR EN EL SERVIDOR",Toast.LENGTH_LONG).show();
                    }
                    else{
                        UsuarioResponse usuario = response.body();

                        if(usuario.getCode() == 1){
                            SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                            sessionManagement.saveSession(new Usuario(usuario.getIdUsuario(),usuario.getNombre(),""));
                            moveToMainActivity();
                        }else{
                            Toast.makeText(LoginActivity.this,"USUARIO O PASSWORD INCORRECTO",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    hideProgressDialog();
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                    hideProgressDialog();
                    Toast.makeText(LoginActivity.this,"ERROR: "+t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(this);

        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                R.color.transparent
        );
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void hideProgressDialog(){
        progressDialog.dismiss();
    }

    public void moveToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"BIENVENIDO", Toast.LENGTH_SHORT).show();
    }

}