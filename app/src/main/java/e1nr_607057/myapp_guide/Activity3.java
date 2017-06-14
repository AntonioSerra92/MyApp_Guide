package e1nr_607057.myapp_guide;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity
                        implements InsFragment.NoticeDialogListener{


    /**
     * PASSO NO.1
     * POPOLARE UNA LISTVIEW
     */
    private TextView campo_nome;
    private Button inserisci;
    private String temp;
    private Button to4;

    // per popolare la lista ci appoggeremo su di un ArrayList di stringhe
    private ArrayList<String> elenco = new ArrayList();
    private ListView myList;





    /**
     * PASSO NO.2
     * UTILIZZARE DIALOG
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        campo_nome = (TextView) findViewById(R.id.ins_nome);
        inserisci = (Button) findViewById(R.id.inList_btn);
        myList = (ListView) findViewById(R.id.Lista);
        to4 = (Button) findViewById(R.id.btn_3to4);

    }


    @Override
    protected void onStart() {
        super.onStart();


        /**
         * Con onClick su onStart, ogni volta che clicchiamo il pulsante, torneremo nell'onStart facendo
         * ricompilare successivamente il metodo onResume
         * Se invece mettessimo l'onClick in onResume, la lista non si aggiornerebbe dato che il
         * metodo per popolarla si troverebbe esternamente e non verrà richiamato finchè l'activity non
         * verrà messa in pausa e ripresa.
         */

        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = (String) campo_nome.getText().toString();

                //elenco.add(temp);
                //campo_nome.setText("");
                //  SPOSTATI IN DIALOG


                chiamaDialog(temp); //CHiamo la dialog che mi chiede conferma dell'inserimento

                //OPPURE POSSO METTERE IL METODO setAdapter qui per farlo richiare ogni volta che
                // premo il pulsante
            }
        });


        to4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Activity4.class));
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();

        // CREO L'OGGETTO ADAPTER CHE MI PERMETTERà DI ADATTARE GLI OGGETTI CHE PASSERò NELLA LISTA
        // PER IL LORO INSERIMENTO
        // CHIEDIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIUIUIUIUIUIUIUIUIUIUUIUIUUIUIU
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                this,           //contesto
                android.R.layout.simple_list_item_1, //formattazione standard
                elenco          //il mio arrayList nel quale ci sono i dati
        );


        //inserisco nella view myList tutte le stringhe contenute nell'ArrayList di appoggio
        myList.setAdapter(adapter);

    }





    public void chiamaDialog(String a) {

        // richiamo classe e metodo che utilizza il costruttore per creare un nuovo fragment
        // del tipo desiderato e passargli parametri personalizzati come in questo caso una stringa
        DialogFragment dialog = new InsFragment().newInstance(a);
        dialog.show(getFragmentManager(), "dialog01");
    }



    // Il Dialog Fragment riceve un riferimento a questa Activity grazie il metodo onAttach()
    // definito nella classe fragment, che permette di avere un riferimento della classe nella quale
    // si troveranno i metodi definiti dall'interfaccia NoticeDialogListener
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        elenco.add(temp);
        campo_nome.setText("");
        onResume();
    }

    //non la uso
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button

    }
}


