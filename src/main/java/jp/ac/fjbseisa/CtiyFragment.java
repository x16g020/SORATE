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
        Button id13 = view.findViewById(R.id.ctiy13);
        id13.setOnClickListener(this);
        Button id14 = view.findViewById(R.id.ctiy14);
        id14.setOnClickListener(this);
        Button id15 = view.findViewById(R.id.ctiy15);
        id15.setOnClickListener(this);
        Button id16 = view.findViewById(R.id.ctiy16);
        id16.setOnClickListener(this);
        Button id17 = view.findViewById(R.id.ctiy17);
        id17.setOnClickListener(this);
        Button id18 = view.findViewById(R.id.ctiy18);
        id18.setOnClickListener(this);
        Button id19 = view.findViewById(R.id.ctiy19);
        id19.setOnClickListener(this);
        Button id20 = view.findViewById(R.id.ctiy20);
        id20.setOnClickListener(this);
        Button id21 = view.findViewById(R.id.ctiy21);
        id21.setOnClickListener(this);
        Button id22 = view.findViewById(R.id.ctiy22);
        id22.setOnClickListener(this);
        Button id23 = view.findViewById(R.id.ctiy23);
        id23.setOnClickListener(this);
        Button id24 = view.findViewById(R.id.ctiy24);
        id24.setOnClickListener(this);
        Button id25 = view.findViewById(R.id.ctiy25);
        id25.setOnClickListener(this);
        Button id26 = view.findViewById(R.id.ctiy26);
        id26.setOnClickListener(this);
        Button id27 = view.findViewById(R.id.ctiy27);
        id27.setOnClickListener(this);
        Button id28 = view.findViewById(R.id.ctiy28);
        id28.setOnClickListener(this);
        Button id29 = view.findViewById(R.id.ctiy29);
        id29.setOnClickListener(this);
        Button id30 = view.findViewById(R.id.ctiy30);
        id30.setOnClickListener(this);
        Button id31 = view.findViewById(R.id.ctiy31);
        id31.setOnClickListener(this);
        Button id32 = view.findViewById(R.id.ctiy32);
        id32.setOnClickListener(this);
        Button id33 = view.findViewById(R.id.ctiy33);
        id33.setOnClickListener(this);
        Button id34 = view.findViewById(R.id.ctiy34);
        id34.setOnClickListener(this);
        Button id35 = view.findViewById(R.id.ctiy35);
        id35.setOnClickListener(this);
        Button id36 = view.findViewById(R.id.ctiy36);
        id36.setOnClickListener(this);
        Button id37 = view.findViewById(R.id.ctiy37);
        id37.setOnClickListener(this);
        Button id38 = view.findViewById(R.id.ctiy38);
        id38.setOnClickListener(this);
        Button id39 = view.findViewById(R.id.ctiy39);
        id39.setOnClickListener(this);
        Button id40 = view.findViewById(R.id.ctiy40);
        id40.setOnClickListener(this);
        Button id41 = view.findViewById(R.id.ctiy41);
        id41.setOnClickListener(this);
        Button id42 = view.findViewById(R.id.ctiy42);
        id42.setOnClickListener(this);
        Button id43 = view.findViewById(R.id.ctiy43);
        id43.setOnClickListener(this);
        Button id44 = view.findViewById(R.id.ctiy44);
        id44.setOnClickListener(this);
        Button id45 = view.findViewById(R.id.ctiy45);
        id45.setOnClickListener(this);
        Button id46 = view.findViewById(R.id.ctiy46);
        id46.setOnClickListener(this);

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
        }else if(view.getId()==R.id.ctiy13){
            ctiycnt = 13;
        }else if(view.getId()==R.id.ctiy14){
            ctiycnt = 14;
        }else if(view.getId()==R.id.ctiy15){
            ctiycnt = 15;
        }else if(view.getId()==R.id.ctiy16){
            ctiycnt = 16;
        }else if(view.getId()==R.id.ctiy17){
            ctiycnt = 17;
        }else if(view.getId()==R.id.ctiy18){
            ctiycnt = 18;
        }else if(view.getId()==R.id.ctiy19){
            ctiycnt = 19;
        }else if(view.getId()==R.id.ctiy20){
            ctiycnt = 20;
        }else if(view.getId()==R.id.ctiy21){
            ctiycnt = 21;
        }else if(view.getId()==R.id.ctiy22){
            ctiycnt = 22;
        }else if(view.getId()==R.id.ctiy23){
            ctiycnt = 23;
        }else if(view.getId()==R.id.ctiy24){
            ctiycnt = 24;
        }else if(view.getId()==R.id.ctiy25){
            ctiycnt = 25;
        }else if(view.getId()==R.id.ctiy26){
            ctiycnt = 26;
        }else if(view.getId()==R.id.ctiy27){
            ctiycnt = 27;
        }else if(view.getId()==R.id.ctiy28){
            ctiycnt = 28;
        }else if(view.getId()==R.id.ctiy29){
            ctiycnt = 29;
        }else if(view.getId()==R.id.ctiy30){
            ctiycnt = 30;
        }else if(view.getId()==R.id.ctiy31){
            ctiycnt = 31;
        }else if(view.getId()==R.id.ctiy32){
            ctiycnt = 32;
        }else if(view.getId()==R.id.ctiy33){
            ctiycnt = 33;
        }else if(view.getId()==R.id.ctiy34){
            ctiycnt = 34;
        }else if(view.getId()==R.id.ctiy35){
            ctiycnt = 35;
        }else if(view.getId()==R.id.ctiy36){
            ctiycnt = 36;
        }else if(view.getId()==R.id.ctiy37){
            ctiycnt = 37;
        }else if(view.getId()==R.id.ctiy38){
            ctiycnt = 38;
        }else if(view.getId()==R.id.ctiy39){
            ctiycnt = 39;
        }else if(view.getId()==R.id.ctiy40){
            ctiycnt = 40;
        }else if(view.getId()==R.id.ctiy41){
            ctiycnt = 41;
        }else if(view.getId()==R.id.ctiy42){
            ctiycnt = 42;
        }else if(view.getId()==R.id.ctiy43){
            ctiycnt = 43;
        }else if(view.getId()==R.id.ctiy44){
            ctiycnt = 44;
        }else if(view.getId()==R.id.ctiy45){
            ctiycnt = 45;
        }else if(view.getId()==R.id.ctiy46){
            ctiycnt = 46;
        }
        mListener.APIWeather(ctiycnt);
        dismiss();

    }

}
