package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.muddzdev.styleabletoast.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 4000;
    private Button button;
    private String zip;
    private String country;
    private ArrayList<String> quotes;
    private ArrayList<TextView> datelist;
    private ArrayList<TextView> xurrent;
    private ArrayList<TextView> highlist;
    private ArrayList<TextView> lowlist;
    private ArrayList<ImageView> imageViewArrayList;
    private ArrayList<Integer> imageids;
    private String compiledURL;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Mountain Yeti (Monsters Inc) theme
        //This code is meant to be run on a Pixel XL
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zip = "yes";
        new Asyncthread().execute(zip);
        country = "us";
        editText = findViewById(R.id.editText2);
        compiledURL = "http://api.openweathermap.org/data/2.5/forecast?id=5101312&APPID=d9aaaf7c1c44b457a7ee6167f0a24033";
        //new asyncthread().execute();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String yeet = s.toString();
                new Asyncthread().execute(yeet);
            }

            @Override
            public void afterTextChanged(Editable s){

            }
        });

    }
    public class Asyncthread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Log.d("DOES", "str");
            try {
                String tempz = "";
                Boolean xxxten = true;
                Boolean isUser = false;
                int xer = 0;
                System.out.println(strings[0]);
                  System.out.println("http://api.openweathermap.org/data/2.5/forecast?zip="+strings[0]+"&APPID=d9aaaf7c1c44b457a7ee6167f0a24033");

                if(strings[0] == null){
                    isUser = false;
                    tempz = "http://api.openweathermap.org/data/2.5/forecast?id=5101312&APPID=d9aaaf7c1c44b457a7ee6167f0a24033";
                }else{
                    isUser = true;
                    tempz = "http://api.openweathermap.org/data/2.5/forecast?zip="+strings[0]+"&APPID=d9aaaf7c1c44b457a7ee6167f0a24033";
                    try{
                        URL url = new URL(tempz);
                        URLConnection urlConnection = url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    }catch(Exception e){
                        tempz = "http://api.openweathermap.org/data/2.5/forecast?id=5101312&APPID=d9aaaf7c1c44b457a7ee6167f0a24033";
                        System.out.println("NOT WORKS");
                        xxxten = false;
               //         showRemoveText();
                    }
                }
                if((xxxten == true)&&(isUser)){
                    tempz = "http://api.openweathermap.org/data/2.5/forecast?zip="+strings[0]+"&APPID=d9aaaf7c1c44b457a7ee6167f0a24033";
                }
                URL url = new URL(tempz);
                System.out.println("WORKS UWU"+url);
                URLConnection urlConnection = (URLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String mydata = "yoot";
                if (bufferedReader != null)
                    mydata = bufferedReader.readLine() + " ";
                System.out.println(mydata);
                return mydata;



            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("wrong UEL");
           //     showRemoveText();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("wrong IO");
             //   showRemoveText();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("wrong JSON");
             //   showRemoveText();
            }
            return null;


        }

        @Override
        protected void onPostExecute(String mydata) {
            try {
                ArrayList<Date> dat = new ArrayList<>();
                ArrayList<Double> hig = new ArrayList<>();
                ArrayList<Double> lo = new ArrayList<>();
                ArrayList<Double> curre = new ArrayList<>();
                ArrayList<String> descs = new ArrayList<>();
                ArrayList<ArrayList> arrayLists = new ArrayList<>();
                JSONObject hold = new JSONObject(mydata);
                JSONArray jsonArray = hold.getJSONArray("list");
                System.out.println(jsonArray);
                JSONObject temp = new JSONObject();
                JSONObject temper = new JSONObject();
                JSONArray weat = new JSONArray();
                JSONObject holl = new JSONObject();
                String clouds = "";
                String weather = "";

                double temperature = 0;


                int timeStamp = 0;


                for (int i = 0; i < jsonArray.length(); i++) {
                    temp = jsonArray.getJSONObject(i);
                    temper = temp.getJSONObject("main");
                    timeStamp = temp.getInt("dt");
                    java.util.Date time = new java.util.Date((long) timeStamp * 1000);
                    dat.add(time);


                    Log.d("TIME", timeStamp + " ");
                    temperature = temper.getDouble("temp");
                    //Log.d("YY", clouds);
                    //Log.d("YY", temperature + " ");
                    double tempera = (temperature - 273.15) * 9 / 5;
                    tempera += 32;
                    tempera = (double) Math.round(tempera * 100d) / 100d;
                    curre.add(tempera);
                    //Log.d("YY", tempera+" ");
                    temperature = temper.getDouble("temp_min");
                    Log.d("CHECK", temperature + "");
                    tempera = (temperature - 273.15) * 9 / 5;
                    tempera += 32;
                    tempera = (double) Math.round(tempera * 100d) / 100d;
                    lo.add(tempera);
                    //Log.d("YY", tempera+" temp min");
                    temperature = temper.getDouble("temp_max");
                    //Log.d("CHECKMAX", temperature+"");
                    tempera = (temperature - 273.15) * 9 / 5;
                    tempera += 32;
                    tempera = (double) Math.round(tempera * 100d) / 100d;
                    hig.add(tempera);
                    Log.d("YY", tempera + " temp max");

                    weat = temp.getJSONArray("weather");
                    Log.d("weather", weat + "");
                    for (int z = 0; z < weat.length(); z++) {
                        temp = weat.getJSONObject(z);
                        Log.d("weatherr", temp + "");
                        weather = temp.getString("description");
                        Log.d("weatherrr", weather + "");
                        descs.add(weather);
                    }
                }

                arrayLists.add(curre);
                arrayLists.add(dat);
                arrayLists.add(lo);
                arrayLists.add(hig);
                arrayLists.add(descs);
                dat = arrayLists.get(1);
                hig = arrayLists.get(3);
                lo = arrayLists.get(2);
                curre = arrayLists.get(0);

                datelist = new ArrayList<>();
                highlist = new ArrayList<>();
                lowlist = new ArrayList<>();
                xurrent = new ArrayList<>();
                imageViewArrayList = new ArrayList<>();
                imageids = new ArrayList<>();

                //imageview list
                ImageView i = findViewById(R.id.pic1);
                imageViewArrayList.add(i);
                i = findViewById(R.id.pic2);
                imageViewArrayList.add(i);
                i = findViewById(R.id.pic3);
                imageViewArrayList.add(i);
                i = findViewById(R.id.pic4);
                imageViewArrayList.add(i);
                i = findViewById(R.id.pic5);
                imageViewArrayList.add(i);
                i = findViewById(R.id.pic6);
                imageViewArrayList.add(i);

                //imageids
                int j = R.drawable.thunderstorm;
                imageids.add(j);
                j = R.drawable.clear;
                imageids.add(j);
                j = R.drawable.cloud;
                imageids.add(j);
                j = R.drawable.drizzle;
                imageids.add(j);
                j = R.drawable.heavysnow;
                imageids.add(j);
                j = R.drawable.moderate;
                imageids.add(j);
                j = R.drawable.fog;
                imageids.add(j);
                j = R.drawable.sleet;
                imageids.add(j);
                j = R.drawable.rain;
                imageids.add(j);
                j = R.drawable.snow;
                imageids.add(j);

                quotes = new ArrayList<>();
                quotes.add("Welcome to Mawsynram");
                //place with heavy rain or thunderstorm
                quotes.add("Wasteland I think you mean SUNNY WONDERLAND");
                //clear
                quotes.add("All those free snowcones");
                //clouds
                quotes.add("We're all out of rain");
                //light rain ordrizzle
                quotes.add("Snowcones?");
                //snow
                quotes.add("You can't see a thing out there");
                //fog
                quotes.add("Ow! Why is this snow fighting");
                //sleet and rain/snow
                quotes.add("It's rainy down there");
                //rain
                quotes.add("Hmmmm do we really wanna go out?");
                //moderate rain
                quotes.add("Never Go Out In A Blizzard!");
                //heavy snow


                TextView forecast = findViewById(R.id.forecast);
                TextView quote = findViewById(R.id.quote);
                forecast.setText(arrayLists.get(4).get(0) + "");
                ArrayList<String> fore = arrayLists.get(4);
                String ineedavar = fore.get(0);
                if ((ineedavar.contains("thunderstorm")) || ((ineedavar.contains("heavy")) && (ineedavar.contains("rain")))) {
                    quote.setText(quotes.get(0) + " ");
                } else if ((ineedavar.contains("clear"))) {
                    quote.setText(quotes.get(1) + " ");
                } else if ((ineedavar.contains("clouds"))) {
                    quote.setText(quotes.get(2) + " ");
                } else if ((ineedavar.contains("drizzle")) || ((ineedavar.contains("light")) && (ineedavar.contains("rain")))) {
                    quote.setText(quotes.get(3) + " ");
                } else if ((ineedavar.contains("snow")) && (ineedavar.contains("heavy"))) {
                    quote.setText(quotes.get(9) + " ");
                } else if ((ineedavar.contains("rain")) && (ineedavar.contains("moderate"))) {
                    quote.setText(quotes.get(8) + " ");
                } else if (ineedavar.contains("fog")) {
                    quote.setText(quotes.get(5) + " ");
                } else if ((ineedavar.contains("sleet")) && ((ineedavar.contains("rain")) && (ineedavar.contains("snow")))) {
                    quote.setText(quotes.get(6) + " ");
                } else if (ineedavar.contains("rain")) {
                    quote.setText(quotes.get(7) + " ");
                } else if (ineedavar.contains("snow")) {
                    quote.setText(quotes.get(4) + " ");
                }

                int g = 0;
                ineedavar = "";
                for (ImageView f : imageViewArrayList) {
                    ineedavar = fore.get(g);
                    if ((ineedavar.contains("thunderstorm")) || ((ineedavar.contains("heavy")) && (ineedavar.contains("rain")))) {
                        f.setImageResource(imageids.get(0));
                    } else if ((ineedavar.contains("clear"))) {
                        f.setImageResource(imageids.get(1));
                    } else if ((ineedavar.contains("clouds"))) {
                        f.setImageResource(imageids.get(2));
                    } else if ((ineedavar.contains("drizzle")) || ((ineedavar.contains("light")) && (ineedavar.contains("rain")))) {
                        f.setImageResource(imageids.get(3));
                    } else if ((ineedavar.contains("snow")) && (ineedavar.contains("heavy"))) {
                        f.setImageResource(imageids.get(4));
                    } else if ((ineedavar.contains("rain")) && (ineedavar.contains("moderate"))) {
                        f.setImageResource(imageids.get(5));
                    } else if (ineedavar.contains("fog")) {
                        f.setImageResource(imageids.get(6));
                    } else if ((ineedavar.contains("sleet")) && ((ineedavar.contains("rain")) && (ineedavar.contains("snow")))) {
                        f.setImageResource(imageids.get(7));
                    } else if (ineedavar.contains("rain")) {
                        f.setImageResource(imageids.get(8));
                    } else if (ineedavar.contains("snow")) {
                        f.setImageResource(imageids.get(9));
                    }


                    g++;
                    //  showAddText();
                }


                //this is me declaring the date arraylist
                TextView date = findViewById(R.id.date1);
                datelist.add(date);
                date = findViewById(R.id.date2);
                datelist.add(date);
                date = findViewById(R.id.date3);
                datelist.add(date);
                date = findViewById(R.id.date4);
                datelist.add(date);
                date = findViewById(R.id.date5);
                datelist.add(date);
                date = findViewById(R.id.date6);
                datelist.add(date);

                //this is me declaring the high arraylist
                TextView high = findViewById(R.id.high1);
                highlist.add(high);
                high = findViewById(R.id.high2);
                highlist.add(high);
                high = findViewById(R.id.high3);
                highlist.add(high);
                high = findViewById(R.id.high4);
                highlist.add(high);
                high = findViewById(R.id.high5);
                highlist.add(high);

                //me declaring low arraylist
                TextView low = findViewById(R.id.low1);
                lowlist.add(low);
                low = findViewById(R.id.low2);
                lowlist.add(low);
                low = findViewById(R.id.low3);
                lowlist.add(low);
                low = findViewById(R.id.low4);
                lowlist.add(low);
                low = findViewById(R.id.low5);
                lowlist.add(low);
                low = findViewById(R.id.temp1);
                xurrent.add(low);

                //setting the stuff
                xurrent.get(0).setText(curre.get(0) + "°");


                int x = 0;
                for (TextView t : datelist) {
                    String form = "MM/dd/yyyy HH:mm";
                    DateFormat df = new SimpleDateFormat(form);
                    String dastr = df.format(dat.get(x));
                    t.setText(dastr + " ");
                    x++;
                }
                x = 1;
                for (TextView t : highlist) {
                    t.setText("High: " + hig.get(x));
                    x++;
                }
                x = 1;
                for (TextView t : lowlist) {
                    t.setText("Low: " + lo.get(x));
                    x++;
                }
                //works up to this point and puts all the text on scream except quote and image

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}