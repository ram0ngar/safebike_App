package mx.itesm.safebike;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText inputEmail,
                     inputPassword; //Son los textos en donde ingresas el email y password
    private FirebaseAuth auth;                  //Este es un objeto de tipo FIrebase
    private Button  btnSignup,
                    btnLogin,
                    btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);

        inputEmail=(EditText) findViewById(R.id.email);
        inputPassword=(EditText) findViewById(R.id.password);
        btnSignup=(Button) findViewById(R.id.bSingUp);
        btnLogin=(Button) findViewById(R.id.bLogin);
        btnReset=(Button) findViewById(R.id.bPassword);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,SingUpActivity.class));

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,ResetActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=inputEmail.getText().toString();
                final String password=inputPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Ingrese un correo",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Por favor igrese una contraseña",Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Intent intent=new Intent(login.this,MainMenuActivity.class);
                                    //startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(login.this,"Correo o contraseña incorrecta",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });

        setContentView(R.layout.activity_login);


    }
}
