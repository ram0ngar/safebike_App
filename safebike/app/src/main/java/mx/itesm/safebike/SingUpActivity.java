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

public class SingUpActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnLogIn, btnSignUp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        auth=FirebaseAuth.getInstance();

        btnLogIn=(Button) findViewById(R.id.bLogin);
        btnSignUp=(Button) findViewById(R.id.bSingUp);
        inputEmail=(EditText) findViewById(R.id.email);
        inputPassword=(EditText) findViewById(R.id.password);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese un correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                return;
                }
                if(password.length()<8){
                    Toast.makeText(getApplicationContext(),"La contraseña es demasiado corta",Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SingUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(SingUpActivity.this,"Fallo el registro"+task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    //startActivityForResult(new Intent(SingUpActivity.this,MainMenuActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

    }
}
