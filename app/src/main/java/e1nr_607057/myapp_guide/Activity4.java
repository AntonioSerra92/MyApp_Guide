package e1nr_607057.myapp_guide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity4 extends AppCompatActivity {

    private TextView ins_email;
    private TextView ins_pass;
    private Button login_btn;
    private String email;
    private String pass;

    /**
     * Oggetto che si occuperà dell'autenticazione su firebase
     * e l'oggetto che identificherà l'utente
     */
    private FirebaseAuth firebaseAutenticazione;
    private FirebaseUser utente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        ins_email = (TextView) findViewById(R.id.email_field);
        ins_pass = (TextView) findViewById(R.id.pass_field);
        login_btn = (Button) findViewById(R.id.login_btn);

        /**
         * avvaloro l'oggetto per comunicare con Il DB Utenti
         */
        firebaseAutenticazione = FirebaseAuth.getInstance();

    }


    @Override
    protected void onStart() {
        super.onStart();


        /**
         *  Click sul pulsante che mi permette di fare il Login
         */
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //Prendo i dati per l'accesso -> trim è necessario per la formattazione
                email = ins_email.getText().toString().trim();
                pass = ins_pass.getText().toString().trim();

                /**
                 * Effettua il Login con il relativo metodo, con il Listener posso effettuare
                 * controlli o operazioni varie
                 */
                firebaseAutenticazione.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                /**
                                 * Ad operazione effettuata, tramite l'if controllo che l'utente  restituito
                                 * non sia null, ovvero che io abbia effettivmento immesso i miei dati
                                 */
                                if (firebaseAutenticazione.getCurrentUser() != null){

                                    Toast.makeText(
                                            getApplicationContext(),
                                            "LOGIN EFFETTUATO",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    //utente = firebaseAutenticazione.getCurrentUser();

                                }else{
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "DATI NON CORRETTI",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }


                            }
                        });

            }
        });

    }
}
