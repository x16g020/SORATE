package jp.ac.fjbseisa;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link DialogFragment} subclass.
 */
public class CtiyFragment extends DialogFragment implements View.OnClickListener {

    ViewGroup containerR;
    public CtiyFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ctiy, container, false);

    }
    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button id0 = view.findViewById(R.id.ctiy0);
        id0.setOnClickListener(this);
        Button id1 = view.findViewById(R.id.ctiy1);
        id1.setOnClickListener(this);
        Button id2 = view.findViewById(R.id.ctiy2);
        id2.setOnClickListener(this);
        Button id3 = view.findViewById(R.id.ctiy3);
        id3.setOnClickListener(this);
        Button id4 = view.findViewById(R.id.ctiy4);
        id4.setOnClickListener(this);
        Button id5 = view.findViewById(R.id.ctiy5);
        id5.setOnClickListener(this);
        Button id6 = view.findViewById(R.id.ctiy6);
        id6.setOnClickListener(this);
        Button id7 = view.findViewById(R.id.ctiy7);
        id7.setOnClickListener(this);
        Button id8 = view.findViewById(R.id.ctiy8);
        id8.setOnClickListener(this);
        Button id9 = view.findViewById(R.id.ctiy9);
        id9.setOnClickListener(this);
        Button id10 = view.findViewById(R.id.ctiy10);
        id10.setOnClickListener(this);
        Button id11 = view.findViewById(R.id.ctiy11);
        id11.setOnClickListener(this);
        Button id12 = view.findViewById(R.id.ctiy12);
        id12.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int ctiycnt = 12;
        if(view.getId()==R.id.ctiy0){
            ctiycnt = 0;
        }else if(view.getId()==R.id.ctiy1){
            ctiycnt = 1;
        }else if(view.getId()==R.id.ctiy2){
            ctiycnt = 2;
        }else if(view.getId()==R.id.ctiy3){
            ctiycnt = 3;
        }else if(view.getId()==R.id.ctiy4){
            ctiycnt = 4;
        }else if(view.getId()==R.id.ctiy5){
            ctiycnt = 5;
        }else if(view.getId()==R.id.ctiy6){
            ctiycnt = 6;
        }else if(view.getId()==R.id.ctiy7){
            ctiycnt = 7;
        }else if(view.getId()==R.id.ctiy8){
            ctiycnt = 8;
        }else if(view.getId()==R.id.ctiy9){
            ctiycnt = 9;
        }else if(view.getId()==R.id.ctiy10){
            ctiycnt = 10;
        }else if(view.getId()==R.id.ctiy11){
            ctiycnt = 11;
        }else if(view.getId()==R.id.ctiy12){
            ctiycnt = 12;
        }
        Weather w = (Weather)getActivity();
        w.APIWeather(ctiycnt);
        dismiss();
    }
}
