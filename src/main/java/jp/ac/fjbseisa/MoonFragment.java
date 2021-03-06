package jp.ac.fjbseisa;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoonFragment extends Fragment implements View.OnClickListener, MoonReader.OnMoonListener {

    public MoonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moon, container, false);
        mImg =  view.findViewById(R.id.Moonimg); //Moonimg(月相画像)
        minfo = view.findViewById(R.id.Mooninfo);//Mooninfo(月相状態)
        time = view.findViewById(R.id.MoonTime);//MoonTime(指定時刻)
        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UnityPlayer unityPlayer = ((UnityPlayerActivity) getActivity()).getUnityPlayer();
        ViewGroup parent = (ViewGroup) unityPlayer.getParent();
        if (parent != null)
            parent.removeView(unityPlayer);
        ((LinearLayout) view.findViewById(R.id.moon)).addView(unityPlayer);


        //初期値入力
        day = 0;
        Moon = 6;
        calendar = Calendar.getInstance(); //カレンダー生成
        MoonReader.getMoon(this); //API処理、MoonReaderへ
        //日にち変更用ボタンの設定
        upday = view.findViewById(R.id.Plus);
        upday.setOnClickListener(this);
        downday = view.findViewById(R.id.Minus);
        downday.setOnClickListener(this);
        b1 = view.findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = view.findViewById(R.id.b2);
        b2.setOnClickListener(this);
        b3 = view.findViewById(R.id.b3);
        b3.setOnClickListener(this);
        //読み込み完了まで無効化
        upday.setEnabled(false);
        downday.setEnabled(false);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
    }

    Calendar calendar; //年月日取得用のカレンダー
    ArrayList<Integer> moonAPI; //取得した月相をint型で格納
    String get; //月相変換用
    Object set; //月相変換用
    int mphase; //月相変換用
    double dphase; //月相変換用
    int ddata; //カレンダー日
    int mdata; //カレンダー月
    int ydata; //カレンダー年
    TextView time; //年月日
    int mselect; //月相ID（仮）
    TypedArray imgArray; //画像用リスト
    ImageView mImg; //画像表示用
    Drawable mcount; //リストにナンバーＩＤを付与
    TypedArray infoArray; //月相の名前リスト
    TypedArray infoArray2; //月相の名前(読み仮名)リスト
    TextView minfo; //表示用
    TextView minfo2; //フリガナ表示用
    String mtext; //String挿入用
    String mtext2; //String挿入用
    ArrayList<Integer> moonlist; //画像及び状態ID格納用
    int getmoon;
    Button upday;//日付加算ボタン
    Button downday;//日付減算ボタン
    int day; //日付処理用(初期値0)
    int Moon;//月相処理用(初期値6)

    Button b1;
    Button b2;
    Button b3;

    //画面下部ボタン日付表示処理
    public void DayCalendar(int day) {
        calendar = Calendar.getInstance(); //内容リセット
        calendar.add(Calendar.DAY_OF_MONTH, day + 1); //年月日加算
        mdata = calendar.get(Calendar.MONTH) + 1; //月
        ddata = calendar.get(Calendar.DAY_OF_MONTH); //日
        upday.setText(mdata + "月" + ddata + "日" + "：→"); //年月日表示]
        calendar = Calendar.getInstance(); //内容リセット
        calendar.add(Calendar.DAY_OF_MONTH, day - 1); //年月日加算
        mdata = calendar.get(Calendar.MONTH) + 1; //月
        ddata = calendar.get(Calendar.DAY_OF_MONTH); //日
        downday.setText("←：" + mdata + "月" + ddata + "日"); //年月日表示
        calendar = Calendar.getInstance(); //内容リセット
    }

    //月相状態及び画面上部日付表示処理
    public void Moon(int a, int b) {
        calendar.add(Calendar.DAY_OF_MONTH, a); //年月日加算
        ydata = calendar.get(Calendar.YEAR); //年
        mdata = calendar.get(Calendar.MONTH) + 1; //月
        ddata = calendar.get(Calendar.DAY_OF_MONTH); //日
        time.setText(ydata + "年" + mdata + "月" + ddata + "日"); //年月日表示
        calendar = Calendar.getInstance(); //内容リセット
        mselect = moonlist.get(b);//月相状態ID
        mtext = infoArray.getString(mselect);//IDから対応したTextを取り出す
        minfo.setText(mtext); //minfoに結果を送る
        mtext2 = infoArray2.getString(mselect);//IDから対応したText(フリガナ)を取り出す
        minfo2.setText(mtext2); //minfo2に結果を送る
        mcount = imgArray.getDrawable(mselect);//IDから対応したimgを取り出す
        mImg.setImageDrawable(mcount); //mImgに結果を送る
    }

    @Override
    public void onMoon(List<Map> Moons) {
        mImg = (ImageView) getView().findViewById(R.id.Moonimg); //Moonimg(月相画像)
        minfo = (TextView) getView().findViewById(R.id.Mooninfo);//Mooninfo(月相状態)
        minfo2 = (TextView) getView().findViewById(R.id.Mooninfo2);//Mooninfo(月相状態)
        time = (TextView) getView().findViewById(R.id.MoonTime);//MoonTime(指定時刻)
        imgArray = getResources().obtainTypedArray(R.array.default_moonimg2); //moonimg2.xmlから画像アレイリスト準備
        infoArray = getResources().obtainTypedArray(R.array.default_moontext); //moontext.xnlから状態アレイリスト準備
        infoArray2 = getResources().obtainTypedArray(R.array.default_moontext2); //moontext.xnlから状態アレイリスト準備
        calendar = Calendar.getInstance(); //生成
        ydata = calendar.get(Calendar.YEAR); //年
        mdata = calendar.get(Calendar.MONTH) + 1; //月
        ddata = calendar.get(Calendar.DAY_OF_MONTH); //日
        time.setText(ydata + "年" + mdata + "月" + ddata + "日"); //年月日表示
        if (Moons != null) {
            //moonAPI宣言
            moonAPI = new ArrayList();
            //13日分の月相情報をmoonAPIに
            for (Map map : Moons) {
                set = map.get("moon_phase");        //MoonReaderから月相取得
                get = set.toString();               //String型に変換
                dphase = Double.parseDouble(get);   //Double型に変換
                mphase = (int) dphase;               //Doubleをint型に
                moonAPI.add(mphase);                //moonAPIに格納
            }
        } else {
            //MoonReaderの中身がNullの時　エラー処理
            minfo.setText("エラー\n");
            return;
        }
        //月相判定結果格納アレイリスト
        moonlist = new ArrayList();
        //月相判定(13日分)
        for (int i = 0; i < 13; i++) {
            //moonAPIのi番目を判定用に取り出す
            getmoon = moonAPI.get(i);
            if (((getmoon >= 0) && (getmoon <= 4)) || ((getmoon >= 356))) {
                moonlist.add(0);//新月
            } else if ((getmoon >= 86) && (getmoon <= 94)) {
                moonlist.add(7);//上弦
            } else if ((getmoon >= 176) && (getmoon <= 184)) {
                moonlist.add(14);//満月
            } else if ((getmoon >= 266) && (getmoon <= 274)) {
                moonlist.add(21);//下弦
                // 以下15度ずつ判定
            } else if ((getmoon >= 0) && (getmoon <= 15)) {
                moonlist.add(1);
            } else if ((getmoon >= 15) && (getmoon <= 30)) {
                moonlist.add(2);
            } else if ((getmoon >= 30) && (getmoon <= 45)) {
                moonlist.add(3);
            } else if ((getmoon >= 45) && (getmoon <= 60)) {
                moonlist.add(4);
            } else if ((getmoon >= 60) && (getmoon <= 75)) {
                moonlist.add(5);
            } else if ((getmoon >= 75) && (getmoon <= 90)) {
                moonlist.add(6);
            } else if ((getmoon >= 90) && (getmoon <= 105)) {
                moonlist.add(8);
            } else if ((getmoon >= 105) && (getmoon <= 120)) {
                moonlist.add(9);
            } else if ((getmoon >= 120) && (getmoon <= 135)) {
                moonlist.add(10);
            } else if ((getmoon >= 135) && (getmoon <= 150)) {
                moonlist.add(11);
            } else if ((getmoon >= 150) && (getmoon <= 165)) {
                moonlist.add(12);
            } else if ((getmoon >= 165) && (getmoon <= 180)) {
                moonlist.add(13);
            } else if ((getmoon >= 180) && (getmoon <= 195)) {
                moonlist.add(15);
            } else if ((getmoon >= 195) && (getmoon <= 210)) {
                moonlist.add(16);
            } else if ((getmoon >= 210) && (getmoon <= 225)) {
                moonlist.add(17);
            } else if ((getmoon >= 225) && (getmoon <= 240)) {
                moonlist.add(18);
            } else if ((getmoon >= 240) && (getmoon <= 255)) {
                moonlist.add(19);
            } else if ((getmoon >= 255) && (getmoon <= 270)) {
                moonlist.add(20);
            } else if ((getmoon >= 270) && (getmoon <= 285)) {
                moonlist.add(22);
            } else if ((getmoon >= 285) && (getmoon <= 300)) {
                moonlist.add(23);
            } else if ((getmoon >= 300) && (getmoon <= 315)) {
                moonlist.add(24);
            } else if ((getmoon >= 315) && (getmoon <= 330)) {
                moonlist.add(25);
            } else if ((getmoon >= 330) && (getmoon <= 345)) {
                moonlist.add(26);
            } else if (getmoon >= 345) {
                moonlist.add(27);
            }
        }
        //初期画面　日付表示、月相状態表示
        DayCalendar(day);
        Moon(day, Moon);
        //ボタンの有効化
        upday.setEnabled(true);
        downday.setEnabled(true);
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
    }

    // メニューをActivity上に設置する
    @Override
    public void onClick(View v) {

//        if (v.getId()==R.id.b1){
//            ((UnityPlayerActivity)getActivity()).changeFragment(UnityFragment.class);
//        } else if (v.getId()==R.id.b2){
//            ((UnityPlayerActivity)getActivity()).changeFragment(SelectFragment.class);
//        } else if (v.getId()==R.id.b3){
//            ((UnityPlayerActivity)getActivity()).changeFragment(WeatherFragment.class);

        switch (v.getId()) {
            case R.id.Plus:
                //ボタン有効化
                upday.setEnabled(true);
                downday.setEnabled(true);
                //日付と月相状態番号加算
                day++;
                Moon++;
                //6日後処理
                if (Moon == 12) {
                    //日付表示、月相状態更新表示
                    DayCalendar(day);
                    Moon(day, Moon);
                    //ボタンの無効化及び非表示
                    upday.setEnabled(false);
                    upday.setText("");
                    return;
                }
                //日付表示、月相状態更新表示
                DayCalendar(day);
                Moon(day, Moon);
                //処理終了
                break;
            case R.id.Minus:
                //ボタン有効化
                upday.setEnabled(true);
                downday.setEnabled(true);
                //日付と月相状態番号減算
                day--;
                Moon--;
                //6日前処理
                if (Moon == 0) {
                    //日付表示、月相状態表示
                    DayCalendar(day);
                    Moon(day, Moon);
                    //ボタンの無効化及び非表示
                    downday.setEnabled(false);
                    downday.setText("");
                    return;
                }
                //日付表示、月相状態更新表示
                DayCalendar(day);
                Moon(day, Moon);
                //処理終了
                break;
            case R.id.b1:
                ((UnityPlayerActivity)getActivity()).changeFragment(UnityFragment.class);
                break;
            case R.id.b2:
                ((UnityPlayerActivity)getActivity()).changeFragment(SelectFragment.class);
                break;
            case R.id.b3:
                ((UnityPlayerActivity)getActivity()).changeFragment(WeatherFragment.class);
                break;

        }
    }
}