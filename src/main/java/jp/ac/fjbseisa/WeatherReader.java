package jp.ac.fjbseisa;

import android.os.Handler;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WeatherReader {

    public static void getWeather(final String url, final OnStarListener listener) {
        final Handler handler = new Handler();
        new Thread() {
            @Override
            public void run() {

                //ArrayListの定義
                final List<Map> list = new ArrayList();
                try {
                    //XMLデータを読み出す
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(url);
                    //最上位エレメントの確認
                    Element element = doc.getDocumentElement();
                    if (!"weatherdata".equals(element.getTagName()))
                        return;
                    //子ノードを探索
                    //weatherdataの子ノード（location,meta,forecast）を取得
                    NodeList nodeList = element.getChildNodes();
                    for (int i = 0; i < nodeList.getLength(); i++) {
//                        Node node = nodeList.item(4);
                        Node node = nodeList.item(i);
                        //forecastノードを見つけたらパラメータを格納
                        if ("forecast".equals(node.getNodeName())) {
                            //forecastの子ノード（time）を格納
                            NodeList nodeList2 = node.getChildNodes();
                            for (int h = 0; h < nodeList2.getLength(); h++) {
                                Map map = new HashMap();
                                Node node2 = nodeList2.item(h);
                                if (String.valueOf(node2.getNodeName()).equals("time")) {
                                    //                                   map = new HashMap();
                                    NamedNodeMap attrs2 = node2.getAttributes();
                                    Node attr2 = attrs2.getNamedItem("from");
                                    Node attr3 = attrs2.getNamedItem("to");
                                    if (attr2 != null) {
                                        map.put("time_from", String.valueOf(attr2.getNodeValue()));
                                        map.put("time_to", String.valueOf(attr3.getNodeValue()));
//                                        list.add(map);
                                    }
//                                    Log.w("dgb00-3tf", "from:" + map.get("time_from").toString());
//                                    Log.w("dgb00-3tm", "to:" + map.get("time_to").toString());

                                    //timeの子ノード（symbol,precipitation,windDirectio,windSpeed,tempererure,pressure,humidity,clouds）を格納
                                    NodeList nodeList3 = node2.getChildNodes();
                                    for (int k = 0; k < nodeList3.getLength(); k++) {

                                        Node node3 = nodeList3.item(k);
//                                        Log.w("dgb00-3", k + "kai " + node3.getNodeName());
                                        if (String.valueOf(node3.getNodeName()).equals("symbol")) {

                                            NamedNodeMap attrs3 = node3.getAttributes();
                                            Node s_attr2 = attrs3.getNamedItem("number");
                                            Node s_attr3 = attrs3.getNamedItem("name");
                                            Node s_attr4 = attrs3.getNamedItem("var");
                                            if (s_attr2 != null) {
//                                          map = new HashMap();
                                                map.put("symbol_number", String.valueOf(s_attr2.getNodeValue()));
                                                map.put("symbol_name", String.valueOf(s_attr3.getNodeValue()));
                                                map.put("symbol_var", String.valueOf(s_attr4.getNodeValue()));
//                                                Log.w("dgb00-3s", "symbol_name:" + String.valueOf(s_attr3.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }

                                        if (String.valueOf(node3.getNodeName()).equals("precipitation")) {
                                            NamedNodeMap attrs4 = node3.getAttributes();
                                            Node p_attr2 = attrs4.getNamedItem("unit");
                                            Node p_attr3 = attrs4.getNamedItem("value");
                                            Node p_attr4 = attrs4.getNamedItem("type");
                                            if (p_attr4 != null) {
//                                            map = new HashMap();
                                                map.put("precipitation_unit", String.valueOf(p_attr2.getNodeValue()));
                                                map.put("precipitation_value", String.valueOf(p_attr3.getNodeValue()));
                                                map.put("precipitation_type", String.valueOf(p_attr4.getNodeValue()));
//                                                Log.w("dgb00-3p", "precipitation_unit:" + String.valueOf(p_attr2.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }

                                        if (String.valueOf(node3.getNodeName()).equals("windDirection")) {
                                            NamedNodeMap w_attrs2 = node3.getAttributes();
                                            Node w_attr2 = w_attrs2.getNamedItem("deg");
                                            Node w_attr3 = w_attrs2.getNamedItem("code");
                                            Node w_attr4 = w_attrs2.getNamedItem("name");
                                            if (w_attr2 != null) {
//                                            map = new HashMap();
                                                map.put("windDirection_deg", String.valueOf(w_attr2.getNodeValue()));
                                                map.put("windDirection_code", String.valueOf(w_attr3.getNodeValue()));
                                                map.put("windDirection_name", String.valueOf(w_attr4.getNodeValue()));
//                                                Log.w("dgb00-3w", "windDirection_name:" + String.valueOf(w_attr4.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }

                                        if (String.valueOf(node3.getNodeName()).equals("windSpeed")) {
                                            NamedNodeMap wi_attrs2 = node3.getAttributes();
                                            Node wi_attr2 = wi_attrs2.getNamedItem("mps");
                                            Node wi_attr3 = wi_attrs2.getNamedItem("name");
                                            if (wi_attr2 != null) {
//                                            map = new HashMap();
                                                map.put("windSpeed_mps", String.valueOf(wi_attr2.getNodeValue()));
                                                map.put("windSpeed_name", String.valueOf(wi_attr3.getNodeValue()));
//                                                Log.w("dgb00-3wi", "windSpeed_name:" + String.valueOf(wi_attr3.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }

                                        if (String.valueOf(node3.getNodeName()).equals("temperature")) {
                                            NamedNodeMap t_attrs2 = node3.getAttributes();
                                            Node t_attr2 = t_attrs2.getNamedItem("unit");
                                            Node t_attr3 = t_attrs2.getNamedItem("value");
                                            Node t_attr4 = t_attrs2.getNamedItem("min");
                                            Node t_attr5 = t_attrs2.getNamedItem("max");
                                            if (t_attr2 != null) {
//                                            map = new HashMap();
                                                map.put("temperature_unit", String.valueOf(t_attr2.getNodeValue()));
                                                map.put("temperature_value", String.valueOf(t_attr3.getNodeValue()));
                                                map.put("temperature_min", String.valueOf(t_attr4.getNodeValue()));
                                                map.put("temperature_max", String.valueOf(t_attr5.getNodeValue()));
//                                                Log.w("dgb00-3t", "temperature_value:" + String.valueOf(t_attr3.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }

                                        if (String.valueOf(node3.getNodeName()).equals("pressure")) {
                                            NamedNodeMap pr_attrs2 = node3.getAttributes();
                                            Node pr_attr2 = pr_attrs2.getNamedItem("unit");
                                            Node pr_attr3 = pr_attrs2.getNamedItem("value");
                                            if (pr_attr2 != null) {
//                                            map = new HashMap();
                                                map.put("pressure_unit", String.valueOf(pr_attr2.getNodeValue()));
                                                map.put("pressure_value", String.valueOf(pr_attr3.getNodeValue()));
//                                                Log.w("dgb00-3pr", "pressure_unit:" + String.valueOf(pr_attr2.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }


                                        if (String.valueOf(node3.getNodeName()).equals("humidity")) {
                                            NamedNodeMap h_attrs2 = node3.getAttributes();
                                            Node h_attr2 = h_attrs2.getNamedItem("value");
                                            Node h_attr3 = h_attrs2.getNamedItem("unit");
                                            if (h_attr2 != null) {
//                                            map = new HashMap();
                                                map.put("humidity_value", String.valueOf(h_attr2.getNodeValue()));
                                                map.put("humidity_unit", String.valueOf(h_attr3.getNodeValue()));
//                                                Log.w("dgb00-3h", "humidity_value:" + String.valueOf(h_attr2.getNodeValue()));
//                                                list.add(map);
                                            }
                                        }

                                        if (String.valueOf(node3.getNodeName()).equals("clouds")) {
                                            NamedNodeMap c_attrs2 = node3.getAttributes();
                                            Node c_attr2 = c_attrs2.getNamedItem("value");
                                            Node c_attr3 = c_attrs2.getNamedItem("all");
                                            Node c_attr4 = c_attrs2.getNamedItem("unit");
                                            if (c_attr2 != null) {
//                                            map = new HashMap();
                                                map.put("clouds_value", String.valueOf(c_attr2.getNodeValue()));
                                                map.put("clouds_all", String.valueOf(c_attr3.getNodeValue()));
                                                map.put("clouds_unit", String.valueOf(c_attr4.getNodeValue()));
//                                                Log.w("dgb00-3c", "clouds_unit:" + String.valueOf(c_attr4.getNodeValue()));
//                                                list.add(map);
                                            }

                                        }

                                    }

                                }

                                if(node2.getNodeType() == Node.ELEMENT_NODE){
                                    map.put(node2.getNodeName(),node2.getTextContent());
                                }
                                list.add(map);
                            }
                        }

                    }


                    for (int i = 0; i < list.size(); i++) {
                        Map mp = list.get(i);
                        Log.w("dgb00-4", i + "kai");
                        Log.w("dgb00-4", mp.get("time_from").toString());
                        Log.w("dgb00-4", mp.get("symbol_name") .toString());

                    }

                    //結果をメインスレッドのリスナーに通知
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStar(list);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    //結果をメインスレッドのリスナーに通知
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onStar(null);
                        }
                    });
                }
            }
        }.start();

    }


    interface OnStarListener {
        void onStar(List<Map> weather);
    }

}