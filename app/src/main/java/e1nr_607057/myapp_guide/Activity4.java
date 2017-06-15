package e1nr_607057.myapp_guide;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
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

    private TextView verificaLogin;
    /**
     * Oggetti che si occuperanno dell'autenticazione e dell'accesso a firebase
     * con l'oggetto che identificherà l'utente
     */
    private Firebase refDB;                         //Riferimento al DB
    private FirebaseAuth firebaseAutenticazione;       //Oggetto per l'autenticazione
    private FirebaseUser utente;                    //oggetto per definire l'utente del DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        Firebase.setAndroidContext(this);


        ins_email = (TextView) findViewById(R.id.email_field);
        ins_pass = (TextView) findViewById(R.id.pass_field);
        login_btn = (Button) findViewById(R.id.login_btn);
        verificaLogin = (TextView) findViewById(R.id.verificaLogin);

        /**
         * avvaloro gli oggetti per comunicare con Il DB Utenti
         */
        firebaseAutenticazione = FirebaseAuth.getInstance();
        refDB = new Firebase ("https://condomanager-a5aa6.firebaseio.com/");
        // UTILIZZO COME LINK LA RADICE DEL MIO DB, MA POSSO PERSONALIZZARE QUESTO RIFERIMENTO
        // A SECONDA DEL CONTESTO CHE DESIDERO
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

                // OGNI VOLTA CHE FACCIO RICHISTA DI LOGIN CANCELLO I DATI DI AUTENTICAZIONE
                // CHE HO USATO PRECEDENTEMENTE, EFFETTUANDO UN SIGNOUT
                firebaseAutenticazione.signOut();

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
                                    // PRENDO IL RIFERIMENTO DELL'UTENTE LOGGATO
                                    utente = firebaseAutenticazione.getCurrentUser();


                                    // NON POSSO IN ALCUN MODO CREARE UN PERCORSO CHE RESTERà APERTO
                                    // COME APPUNTO UN NODO NON FOGLIA (quindi senza valore)
                                    // O SENZA FIGLI
                                    if(1==2)
                                        refDB.child("Nome");


                                    // SCRIVO SUL DB UN NODO CON l'UID DELL'UTENTE
                                    // ED AL SUO INTERNO UNA FOGLIA CON CHIAVE-VALORE
                                    refDB.child(utente.getUid()).child("Nome").setValue("Davide");
                                    //non possiamo utilizzare la mail come campo chiave dato che contiene
                                    //caratteri non validi, potremo comunque conservarla come valore


                                    // visualizzo i dati dell'utente loggato
                                    if ( utente != null ){
                                        verificaLogin.setText("Questi sono i dati dell'utente\n" +
                                                utente.getDisplayName() +"\n" +
                                                utente.getEmail() +"\n" +
                                                utente.getUid() +"\n" );
                                    }

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
