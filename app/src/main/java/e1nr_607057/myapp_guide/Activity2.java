package e1nr_607057.myapp_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class Activity2 extends AppCompatActivity {

    // Dichiarazione delle TextView con le quali interagire
    private TextView campo_testo;
    private TextView campo_numero;

    private Intent in;     // creo Intent per il collegamento
    private Bundle data_in; // creo il bundle per ricevere dati

    String t;
    int n;


    ///////////////////////////////////////////////////
    // SECONDA PARTE   ---> PASSAGGIO AD ACTIVITY 3

    private SwitchCompat myswitch;
    private Button  passa3_btn;
    Intent passa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);     //imposto la componenete di layout

        //assegno i campi di testo agli elem di layout
        campo_testo = (TextView) findViewById(R.id.txt2);
        campo_numero = (TextView) findViewById(R.id.num2);

        myswitch= (SwitchCompat) findViewById(R.id.switch1);
        passa3_btn = (Button) findViewById(R.id.avanti2_btn);



        //per stabilire il collegamento con l'activity chiamante, l'intent in questa pagina
        //dovrà far riferimento all'intent che ha fatto richiesta di passaggio e si dovrà creare
        //un legame tra i due proprio per la necessità di reperire informazioni


        in = new Intent (getIntent());  //imposto come argomento l'intent ricevuto
        data_in = in.getExtras();        //avvaloro il bundle creato con i dati portati dall'intent
        passa = new Intent(getApplicationContext(), Activity3.class);


        t = data_in.getString("Testo").toString();      //recupero con getString il valore testuale
        n = (int) (data_in.getInt("Numero"));       //recupero con getInt il valore intero

    }


    //Nel metodo onStart avvaloro quindi gli elementi presenti nel layout
    //nel primo ponendo la stringa e nel secondo l'intero aumentato di 1
    @Override
    protected void onStart() {
        super.onStart();

        n+=1;   //per confermare il suo trattamento come numero e non come testo
        campo_testo.setText(t);
        campo_numero.setText(""+n);
        //IMPORTANTE : in setText devo porre necessariamente una stringa, ma n è un intero
        //accodandolo ad una stringa vuota faccio in modo che venga inserito senza effettuare cast



        passa3_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if ( myswitch.isChecked()) {
                    startActivity(passa);
                }
                else{
                    //Creo un avviso che mi avvisa del fatto che devo abilitare lo switch
                    Toast.makeText(
                            getApplicationContext(),
                            "Devi abilitare lo Switch prima di cambiare Activity",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

    }
}
