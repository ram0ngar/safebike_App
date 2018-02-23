package mx.itesm.safebike;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout,
                    ponchado,
                    panico;

    NotificationCompat.Builder notificacion;
    private final  int idUnica=51623;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button) findViewById(R.id.btnLogout);
        panico=(Button) findViewById(R.id.btnPanico);
        ponchado=(Button) findViewById(R.id.btnPonchado);
        notificacion=new NotificationCompat.Builder(this);
        notificacion.setAutoCancel(true);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        ponchado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPage.this,MapsActivity.class);
                startActivity(intent);
            }
        });



        panico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificacion.setSmallIcon(R.mipmap.ic_launcher_round);
                notificacion.setTicker("Nueva notificacion");
                notificacion.setWhen(System.currentTimeMillis());
                notificacion.setContentTitle("Panico");
                notificacion.setContentText("Estoy en panico");

                Intent intent=new Intent(MainPage.this,MainPage.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(MainPage.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notificacion.setContentIntent(pendingIntent);

                NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(idUnica,notificacion.build());
            }
        });

    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainPage.this, SingIn.class));
    }


}
