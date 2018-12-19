package jp.ac.fjbseisa;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link DialogFragment} subclass.
 */
public class CtiyFragment extends DialogFragment implements View.OnClickListener {

    public CtiyFragment() {

        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ctiy, container, false);
    }
    //インタフェイスの定義
    public interface APIWeatherListener{
        void APIWeather(int value);
    }
    //インタフェイスのインスタンス保存用
    APIWeatherListener mListener;

    //ボタン動作のインスタンスを受け取る
    public void setAPIWeatherListener(APIWeatherListener listener){
        mListener =  listener;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      view.findViewById(R.id.ctiy0).setOnClickListener(this);
      view.findViewById(R.id.ctiy1).setOnClickListener(this);
      view.findViewById(R.id.ctiy2).setOnClickListener(this);
      view.findViewById(R.id.ctiy3).setOnClickListener(this);
      view.findViewById(R.id.ctiy4).setOnClickListener(this);
      view.findViewById(R.id.ctiy5).setOnClickListener(this);
      view.findViewById(R.id.ctiy6).setOnClickListener(this);
      view.findViewById(R.id.ctiy7).setOnClickListener(this);
      view.findViewById(R.id.ctiy8).setOnClickListener(this);
      view.findViewById(R.id.ctiy9).setOnClickListener(this);
      view.findViewById(R.id.ctiy10).setOnClickListener(this);
      view.findViewById(R.id.ctiy11).setOnClickListener(this);
      view.findViewById(R.id.ctiy12).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int ctiycnt = 12;
        if(view.getId()==R.id.ctiy0){
            ctiycnt = 0;
            mListener.APIWeather(0);
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
        mListener.APIWeather(ctiycnt);
        dismiss();

    }

}
