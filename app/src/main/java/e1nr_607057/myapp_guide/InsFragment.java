package e1nr_607057.myapp_guide;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InsFragment extends DialogFragment {


    /**
     * CREO UN'INTERFACCIA PER COMUNICARE CON L'ACTIVITY HOST
     * CONTIENE I LISTENER CHE SI ATTIVERANNO IN CASO DELL'EVENTO DESCRITTO
     * dovrà essere importata tramite "implements" nell'activity chiamante
     */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }


    //creo il mio Listener personalizzato appena definito
    NoticeDialogListener mListener;

    private static String mParam1;


    /**
     * COSTRUTTORE DEL FRAGMENT, VUOTO MA NECESSARIO
     */
    public InsFragment() {
        // Required empty public constructor
    }


    /**
     *  TRAMITE QUESTO METODO, POSSIAMO CREARE UNA NUOVA ISTANZ DEL FRAGMENT PASSANDOGLI COME PARAMETRO
     *  QUALSIASI TIPO DI VALORE CHE CI SERVE, BASTERà MODIFICARE L'INTESTAZIONE
     */
    public static InsFragment newInstance(String ins) {
        InsFragment fragment = new InsFragment();
        Bundle args = new Bundle();
        args.putString( "Parametro" , ins);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("Parametro");
        }

        // OGGETTO BUILDER CHE SI OCCUPERà DELLA CRAZIONE DI ALCUNI OGGETTI NATIVI DELLA DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // OGGETTO INFLATER INVECE SI OCCUPERà DI INSERIRE OGGETTI DEFINITI ALTROVE (file di layout)
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle("AVVISO");  //il titolo è proprio della dialog
        builder.setMessage("Vuoi inserire il nome " +mParam1+ " ?");
        //ora invece assegnamo un layout creato in xml per il contenuto
        builder.setView(inflater.inflate(R.layout.fragment_ins, null))

                // Add action buttons
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick( InsFragment.this );
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        InsFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();


    }

/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/


    /**
     * segnala il momento in cui il Fragment scopre l’Activity di appartenenza. Attenzione, a
     * quel punto l’Activity non è stata ancora creata quindi si può solo conservare un riferimento
     * ad essa ma non interagirvi
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
