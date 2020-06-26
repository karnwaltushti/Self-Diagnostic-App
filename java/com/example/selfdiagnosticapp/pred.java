package com.example.selfdiagnosticapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.io.IOException;
import java.io.FileReader;

public class pred extends Activity{
    HashMap<String, String> res = new HashMap<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pred);


        final Intent medicin=new Intent(pred.this, medicine.class);

        Intent intent=getIntent();
        String symptom1= intent.getStringExtra("symptom1");
        String symptom2= intent.getStringExtra("symptom2");
        String symptom3= intent.getStringExtra("symptom3");
        String symptom4= intent.getStringExtra("symptom4");

        String intensity1=intent.getStringExtra("intensity1");
        String intensity2=intent.getStringExtra("intensity2");
        String intensity3=intent.getStringExtra("intensity3");
        String intensity4=intent.getStringExtra("intensity4");
        //System.out.println(symptom1+" "+symptom2);
        String ln="";
        try
        {
            HashMap<String,List<String>> diseases=new HashMap<String,List<String>>();
            String string = "";
            StringBuilder stringBuilder = new StringBuilder();
            InputStream is = this.getResources().openRawResource(R.raw.dataset1);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line=br.readLine();
            String[] symptoms=line.split(",");
            HashMap<String,Integer> indices=new HashMap<String,Integer>();
            for(int i=1;i<symptoms.length;i++) {
                indices.put(symptoms[i], i);
            }

            while ((ln = br.readLine()) != null)   //returns a Boolean value
            {
                String[] symps=ln.split(",");
                if(symps.length!=0) {
                    List<String> x=new ArrayList<String>();
                    for(int i=1;i<symps.length;i++) {
                        if((symps[i]).toString().equals("1")){
                            x.add(symptoms[i]);
                        }
                    }
                    diseases.put(symps[0],x);
                }
            }
            // Here you have to added inputs of the spinners.
            Scanner sc=new Scanner(System.in);
            String[] usyms=new String[]{symptom1,symptom2,symptom3,symptom4};
            String[] inten=new String[]{intensity1,intensity2,intensity3,intensity4};
            Log.d("CREATION", Arrays.toString(usyms));

                /*for(int i=0;i<4;i++) {
                    usyms[i]=sc.nextLine();
                    inten[i]=sc.nextLine();
                }*/
            //here on starts the work of getting the values for each disease and the sum, create an hashmap to store the sum
            HashMap<String,Double> scores=new HashMap<String,Double>();
            HashMap<String,Integer> present=new HashMap<String,Integer>();
            for (Map.Entry dis : diseases.entrySet()) {
                List<String> sp=(List<String>) dis.getValue();
                int j=0;
                double score=sp.size();
                int con=0;
                for(int k=0;k<usyms.length;k++) {
                    if(sp.contains(usyms[k])) {
                        con=con+1;
                        if(inten[j].equals("High"))
                            score+=1*8.0;
                        else if(inten[j].equals("Medium"))
                            score=(score+5.0);
                        else if(inten[j].equals("Low"))
                            score=(score+3.0);
                        else if(inten[j].equals("--"))
                            score=score+5.3;
                    }
                    double nbs=0.0;
                    if(con>0) {
                        nbs=((double)(con/4)*(1/75)/(double)con/sp.size());
                    }
                    String key=(String)dis.getKey();
                    present.put(key,con);
                    scores.put(key,(Double)(score+nbs));
                }
            }
            LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
            scores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
            String[] keys=new String[3];
            double[] vals=new double[3];

            int c=0;
            for (Map.Entry rev: reverseSortedMap.entrySet()) {
                if(c==3) {
                    break;
                }
                keys[c]=((String)rev.getKey());
                vals[c]=((double)rev.getValue());
                //res.put(keys[c],vals[c]);
                c+=1;
            }
            //res.put("0",keys[0]);
            //res.put("1",keys[1]);
            //res.put("2",keys[2]);

            for(int i=0;i<keys.length;i++) {
                System.out.println(keys[i]+" "+vals[i]);
            }


            //return results;

            TextView textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText("1. " + symptom1);
            TextView textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText("2. " + symptom2);
            TextView textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText("3. " + symptom3);
            TextView textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText("4. " + symptom4);

            String d1 = res.get("0");
            String d2 = res.get("1");
            String d3 = res.get("2");
            TextView disease1 = (TextView) findViewById(R.id.disease);
            disease1.setText(keys[0]);
            TextView disease2 = (TextView) findViewById(R.id.disease2);
            disease2.setText(keys[1]);
            TextView disease3 = (TextView) findViewById(R.id.disease3);
            disease3.setText(keys[2]);


            ProgressBar p1 = (ProgressBar) findViewById(R.id.progressBar);
            p1.setProgress((int)vals[0]);
            ProgressBar p2 = (ProgressBar) findViewById(R.id.progressBar2);
            p2.setProgress((int)vals[1]);
            ProgressBar p3 = (ProgressBar) findViewById(R.id.progressBar3);
            p3.setProgress((int)vals[2]);

            Button b1 = (Button) findViewById(R.id.button);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    medicin.putExtra("disease",keys[0]);
                    startActivity(medicin);
                }


            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        //return new HashMap<String,String>();
         }




        finally {
            //HashMap<String,String> res=p.prediction(symptom1,symptom2,symptom3,symptom4,intensity1,intensity2,intensity3,intensity4);

            /*TextView textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText("1. " + symptom1);
            TextView textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText("2. " + symptom2);
            TextView textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText("3. " + symptom3);
            TextView textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText("4. " + symptom4);

            String d1 = res.get("0");
            String d2 = res.get("1");
            String d3 = res.get("2");
            TextView disease1 = (TextView) findViewById(R.id.disease);
            disease1.setText(d1);
            TextView disease2 = (TextView) findViewById(R.id.disease2);
            disease2.setText(d2);
            TextView disease3 = (TextView) findViewById(R.id.disease3);
            disease3.setText(d3);


            ProgressBar p1 = (ProgressBar) findViewById(R.id.progressBar);
            p1.setProgress(Integer.parseInt(res.get(d1)));
            ProgressBar p2 = (ProgressBar) findViewById(R.id.progressBar2);
            p2.setProgress(Integer.parseInt(res.get(d2)));
            ProgressBar p3 = (ProgressBar) findViewById(R.id.progressBar3);
            p3.setProgress(Integer.parseInt(res.get(d3)));

            Button b1 = (Button) findViewById(R.id.button);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    medicine.putExtra("disease", d1);
                    startActivity(medicine);
                }


            });*/


        }

    }
}

