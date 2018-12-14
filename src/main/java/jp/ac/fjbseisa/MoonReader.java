package jp.ac.fjbseisa;
import android.os.Handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MoonReader {
    interface OnMoonListener{
        void onMoon(List<Map> Moons);
    }
    public static void getMoon(final OnMoonListener listener){
        final Handler handler = new Handler();
        new Thread(){
            @Override
            public void run() {
                final List<Map> list = new ArrayList();
                Calendar calendar; //年月日取得用のカレンダー
                int ddata; //カレンダー日
                int mdata; //カレンダー月
                int ydata; //カレンダー年
                calendar = Calendar.getInstance(); //生成
                calendar.add(Calendar.DAY_OF_MONTH,-6);
                ydata = calendar.get(Calendar.YEAR); //年
                mdata = calendar.get(Calendar.MONTH) +1 ; //月
                ddata = calendar.get(Calendar.DAY_OF_MONTH); //日
                try {
                    //13日分の月相情報取得
                    for (int z = 0; z < 13; z++) {
                        //XMLデータを読み出す
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse("http://labs.bitmeister.jp/ohakon/api/?mode=moon_phase&year=" + ydata + "&month=" + mdata + "&day=" + ddata + "&hour=23.00");
                        //最上位エレメントの確認
                        Element element = doc.getDocumentElement();
                        if (!"result".equals(element.getTagName()))
                            return;
                        //子ノードを探索
                        NodeList nodeList = element.getChildNodes();
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);
                            //moon_phaseノードを見つけたらパラメータを格納
                            if ("moon_phase".equals(node.getNodeName())) {
                                Map map = new HashMap();
                                map.put(node.getNodeName(), node.getTextContent());
                                list.add(map);
                            }
                        }
                        //一日更新
                        calendar.add(Calendar.DAY_OF_MONTH,1);
                        ydata = calendar.get(Calendar.YEAR); //年
                        mdata = calendar.get(Calendar.MONTH) +1 ; //月
                        ddata = calendar.get(Calendar.DAY_OF_MONTH); //日
                    }
                    //結果をメインスレッドのリスナーに通知
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onMoon(list);
                        }
                    });
                } catch(Exception e){
                    e.printStackTrace();
                    //結果をメインスレッドのリスナーに通知
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onMoon(null);
                        }
                    });
                }
            }
        }.start();
    }
}