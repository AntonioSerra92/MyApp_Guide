package e1nr_607057.myapp_guide;

import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/** MAIN ACTIVITY
 * la prima schermata che il programma presenterà, dato che all'interno del Manifest sarà indicata
 * come schermata di partenza tramite il comando:
 *                        <activity android:name=".MainActivity">
 */
public class MainActivity extends AppCompatActivity {

    //IL PRIMO PASSAGGIO è QUELLO DI INSTANZIARE GLI OGGETTI CHE VOGLIAMO UTILIZZARE NELL'ACTIVITY
    //successivamente li collegheremo con gli oggetti presenti nel Layout creato

    private EditText testo;                        //ci servirà un campo editabile
    private EditText numero;                      //per scrivere un testo, uno per un numero
    private Button bottone;                      // ed un bottone per convalidare le operazioni

    private Bundle pacco = new Bundle();    // ci servirà un oggetto di questo tipo per
                                            // passare dati tra schermate

    Intent passa = new Intent();    // ovvero il "ponte" per collegare due entità in android
                                            // in questo caso, 2 activity



    /**
     * ON CREATE
     * é il primo metodo che esegue l'activity e contiene tutte le info necessarie per la sua
     * definizione. Comprenderà tutte le operazioni che vengono effettuate SOLO LA PRIMA VOLTA
     * come l'implementazione del layout, con il relativo collegamento agli
     * oggetti che ne fanno parte.
     *
     * @param savedInstanceState        OnCreate è l'unico metodo del ciclo di vita di un'activity
     *                                  che può ricevere dati in ingresso, magari da altre activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     //costruttore di default

        // ASSOCIAMO IL LAYOUT CREATO PER TALE ACTIVITY
        setContentView(R.layout.activity_main);

        //LEGHIAMO FGLI OGGETTI ISTANZIATI PRECEDENTEMENTE ALLE RELATIVE CONTROPARTI GRAFICHE
        //INSERITE NEL FILE DI LAYOUT
        testo = (EditText) findViewById(R.id.my_text);
        numero = (EditText) findViewById(R.id.my_num);

        //oggetto dichiarato           cast       ritrovo l'oggetto cercandolo per id nelle risose
        bottone                 =    (Button)           findViewById(R.id.my_button);
        //             nel passaggio per id si perde il tipo


        passa = new Intent (getApplicationContext() , Activity2.class);
        // impostiamo l'Intent "passa" come l'oggetto che collegherà il contesto in cui ci troviamo
        // alla seconda Activity, ovvero la classe alla quale vogliamo passare i valori
    }




    /**
     * ON START
     * immediatamwnte successiva ad onCreate, si occuperà della presentazione delle informazioni
     * all'utente, ovvero di quando l'activity diventa visibile.
     * Si occuperà quindi di dare "forma" e valori agli oggetti precedentemente definiti.
     * Es.: richiesta info per presentare dati ecc. (listener)
     *
     * All'interno di questo metodo possiamo inizializzare tutte le componenti della GUI
     * magari con dati precedentemente salvati sul dispositivo o ricavati da un database
     * connesso alla nostra app
     *
     * Verrà richiamato più volte nel ciclo di vita dell'activity.
     */
    @Override
    protected void onStart() {
        super.onStart();


        //sul pulsante impostiamo il comando set per renderlo in grado di eseguire un'azione una
        //una volta premuto. Il resto è da copiare.
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //All'interno del bundle inseriremo sempre una coppia di valori
                //In questo caso essi saranno un ID scelto da noi ed il contenuto del campo "testo"
                pacco.putString( "Testo" , testo.getText().toString() );


                int a = Integer.valueOf(numero.getText().toString());   //IMPORTANTE prende INTEGER
                pacco.putInt( "Numero" , a );


                passa.putExtras(pacco);      // passiamo un oggetto di tipo Bundle all'Intent
                startActivity(passa);       // fa partire l'Intent specificando l'ambito Activity

            }
        }
        );




    }


    /**
     * ON RESUME
     * chiamato subito dopo OnStart(), si occupa di gestire l'effettiva interazione con l'utente
     * Ad esempio per l'accesso alle risorse desiderate e quindi la fruizione di materiale
     * multimediale (far partire musica, animazioni ecc.) e non.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }




}
