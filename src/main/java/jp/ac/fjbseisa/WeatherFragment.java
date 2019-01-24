package jp.ac.fjbseisa;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements WeatherReader.OnStarListener, View.OnClickListener, CtiyFragment.APIWeatherListener {
    final Double KELVIN = 273.15;
    int num;
    int cnt;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.b1).setOnClickListener(this);

        view.findViewById(R.id.b2).setOnClickListener(this);

        view.findViewById(R.id.b3).setOnClickListener(this);
    }

    String datetext;
    String year;
    String month;
    String day;
    String hour;
    String w_text;

    int month_int;
    int day_int;
    int hour_int;

    Double temperature;

    Double w_speed;

    ImageView weatherImage;
    FrameLayout w_frame;

    TextView dateText;
    TextView temp;
    TextView windSpeed;

    Button day_before;
    Button hour_before;
    Button hour_after;
    Button day_after;
    Button ctiyChange;
    Button ctiyname;

    List<Map> weatherlist;

    int ctiycnt;
    int ctiyID;
    TypedArray ctiyList;
    TypedArray ctiynameList;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        weatherImage = view.findViewById(R.id.weatherImage);
        w_frame = view.findViewById(R.id.weather_frame);

        dateText = view.findViewById(R.id.dateT);
        temp = view.findViewById(R.id.temperature);
        windSpeed = view.findViewById(R.id.windspeed);

        ctiyname = view.findViewById(R.id.ctiybutton);
        day_before = (Button) view.findViewById(R.id.before_day);
        hour_before = (Button) view.findViewById(R.id.before_hour);
        hour_after = (Button) view.findViewById(R.id.after_hour);
        day_after = (Button) view.findViewById(R.id.after_day);
        ctiyChange = view.findViewById(R.id.ctiybutton);

        day_before.setOnClickListener(this);
        hour_before.setOnClickListener(this);
        hour_after.setOnClickListener(this);
        day_after.setOnClickListener(this);
        ctiyChange.setOnClickListener(this);

        day_after.setEnabled(true);
        hour_after.setEnabled(true);
        day_before.setEnabled(false);
        hour_before.setEnabled(false);
        CtiyFragment C = new CtiyFragment();
        WeatherReader.getWeather("http://api.openweathermap.org/data/2.5/forecast?id=1850144&APPID=d21a1076e3577e18ffe577b79bef2496&mode=xml", this);
        //ダイアログのボタンが押された場合の動作
        C.setAPIWeatherListener(this);
        C.show(getFragmentManager(),"");
        return view;
    }

    @Override
    public void onClick(View view) {
        day_after.setEnabled(true);
        hour_after.setEnabled(true);
        day_before.setEnabled(true);
        hour_before.setEnabled(true);

        if (view.getId()==R.id.b1){
            ((UnityPlayerActivity)getActivity()).changeFragment(UnityFragment.class);
        } else if (view.getId()==R.id.b2){
            ((UnityPlayerActivity)getActivity()).changeFragment(MoonFragment.class);
        } else if (view.getId()==R.id.b3){
            ((UnityPlayerActivity)getActivity()).changeFragment(SelectFragment.class);
        } else if (view.getId() == R.id.before_day) {
            cnt = cnt - 8;
        } else if (view.getId() == R.id.before_hour) {
            cnt = cnt - 1;
        } else if (view.getId() == R.id.after_hour) {
            cnt = cnt + 1;
        } else if (view.getId() == R.id.after_day) {
            cnt = cnt + 8;
        } else if (view.getId() == R.id.ctiybutton) {
            CtiyFragment C = new CtiyFragment();
            //ダイアログのボタンが押された場合の動作
            C.setAPIWeatherListener(this);
            C.show(getFragmentManager(),"");

        }
        if (cnt > weatherlist.size() - 2) {
            cnt = weatherlist.size() - 1;
            day_after.setEnabled(false);
            hour_after.setEnabled(false);
        } else if (cnt < 1) {
            cnt = 0;
            day_before.setEnabled(false);
            hour_before.setEnabled(false);
        }
        //天候情報表示処理呼び出し
        WeatherInfo();
    }

    @Override
    public void onStar(List<Map> weather) {
        //イベントの設定
        Map map;
        if (weather != null) {
            weatherlist = new ArrayList<>();
            for (int z = 0; z < weather.size(); z++) {
                map = weather.get(z);
                weatherlist.add(map);
            }
        }
        //天候情報表示処理呼び出し
        WeatherInfo();
    }

    public void WeatherInfo() {
        Map map;
        map = weatherlist.get(cnt);
        num = Integer.parseInt(map.get("symbol_number").toString());

        if (num >= 200 && num < 300) {
            //雷雨、強風、嵐
            weatherImage.setImageResource(R.drawable.thunderstorm);
            w_frame.setBackground(getResources().getDrawable(R.drawable.rainny_sky));
        } else if (num >= 300 && num < 400) {
            //霧雨
            weatherImage.setImageResource(R.drawable.rainny);
            w_frame.setBackground(getResources().getDrawable(R.drawable.rainny_sky));
        } else if (num >= 400 && num < 500) {

        } else if (num >= 500 && num < 600) {
            //雨
            weatherImage.setImageResource(R.drawable.rainny);
            w_frame.setBackground(getResources().getDrawable(R.drawable.rainny_sky));
        } else if (num >= 600 && num < 700) {
            //雪
            weatherImage.setImageResource(R.drawable.snow);
            w_frame.setBackground(getResources().getDrawable(R.drawable.rainny_sky));
        } else if (num >= 700 && num < 800) {
            //霧
            weatherImage.setImageResource(R.drawable.atmosphere);
            w_frame.setBackground(getResources().getDrawable(R.drawable.atmosphere_sky));
        } else {
            if (num == 800) {
                //晴れ
                weatherImage.setImageResource(R.drawable.sunny);
                w_frame.setBackground(getResources().getDrawable(R.drawable.sunny_sky));
            } else {
                //曇り
                weatherImage.setImageResource(R.drawable.cloudy);
                w_frame.setBackground(getResources().getDrawable(R.drawable.cloudy_sky));
            }
        }

        //日にちの処理
        datetext = map.get("time_from").toString(); //日にち情報を取得

        //datetextに格納されている年・月・日・時間をそれぞれ切り取って格納
        year = datetext.substring(0, 4);     //年
        month = datetext.substring(5, 7);    //月
        day = datetext.substring(8, 10);     //日
        hour = datetext.substring(11, 13);   //時間

        //月・日・時間のデータをint型へ変換
        month_int = Integer.parseInt(month);
        day_int = Integer.parseInt(day);
        hour_int = Integer.parseInt(hour);

        //各データを再結合して表示
        dateText.setText(year + "年 " + month_int + "月 " + day_int + "日 " + hour_int + "時");

        //気温の処理
        temperature = (Double.parseDouble(map.get("temperature_value").toString())) - KELVIN;

        BigDecimal bd = new BigDecimal(temperature);
        BigDecimal temp_bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);

        temp.setText(String.valueOf(temp_bd) + "℃");

        //風速の処理
        w_speed = Double.parseDouble(map.get("windSpeed_mps").toString());

        if (w_speed >= 0 && w_speed < 0.4){
            w_text = "無風";
        } else if (w_speed >= 0.4 && w_speed < 10.0){
            w_text = "弱い";
        } else if (w_speed >= 10.0 && w_speed < 15.0){
            w_text = "やや強い";
        } else if (w_speed >= 15.0 && w_speed < 20.0){
            w_text = "強い";
        } else if (w_speed >= 20.0 && w_speed < 30.0){
            w_text = "非常に強い";
        } else if (w_speed >= 30.0){
            w_text = "猛烈";
        }

        windSpeed.setText(String.valueOf(w_text));

    }

    @Override
    public void APIWeather(int ctiycnt) {
        ctiyList = getResources().obtainTypedArray(R.array.default_ctiyList);
        ctiynameList = getResources().obtainTypedArray(R.array.default_ctiyNameList);
        ctiyID = ctiyList.getInteger(ctiycnt, 0);
        String ctiynl = ctiynameList.getString(ctiycnt);
        cnt = 0;
        day_after.setEnabled(true);
        hour_after.setEnabled(true);
        day_before.setEnabled(false);
        hour_before.setEnabled(false);
        ctiyname.setText(ctiynl);
        //URLをもとに天気情報を取得
        WeatherReader.getWeather("http://api.openweathermap.org/data/2.5/forecast?id=" + ctiyID + "&APPID=d21a1076e3577e18ffe577b79bef2496&mode=xml", this);
      }
}