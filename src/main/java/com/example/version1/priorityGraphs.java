package com.example.version1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class priorityGraphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_graphs);

        Bundle content = getIntent().getExtras();
        String value = content.getString("priorityAmount");
        String bank = content.getString("Bank");
        String APR = content.getString("APR");
        String Balance = content.getString("Balance");
        String Due = content.getString("Due");
        String minDue = content.getString("minDue");
        String[] priorityData = {value,bank,APR,minDue,Balance,Due};

        String cardStorage = readFromFile(getApplicationContext());
        String data = "(Bank:.[a-zA-z]*.APR Rate:.[0-90-9]*.[0-90-9]*.Minimum Payment:.[0-9]*.Balance Due:.[0-9]*.Due Date:.[0-9]*/[0-9]*/[0-9]*)?";
        String pattern = data+data+data+data+data+data+data+data+data+data;
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(cardStorage);
        if (m.find( )) {
            String match1 = m.group(1);
            String match2 = m.group(2);
            String match3 = m.group(3);
            String match4 = m.group(4);
            String match5 = m.group(5);
            String match6 = m.group(6);
            String match7 = m.group(7);
            String match8 = m.group(8);
            String match9 = m.group(9);
            String match10 = m.group(10);
            String[] cardDir = new String[10];
            cardDir[0] = match1;
            cardDir[1] = match2;
            cardDir[2] = match3;
            cardDir[3] = match4;
            cardDir[4] = match5;
            cardDir[5] = match6;
            cardDir[6] = match7;
            cardDir[7] = match8;
            cardDir[8] = match9;
            cardDir[9] = match10;

            int dirSize = trueSize(cardDir);
            createGraphs(cardDir,0,dirSize,priorityData);

        }
        else {
            Log.d("Error"," file minPay.java error with matches");
        }

    }
    public void createGraphs(String[] cardList, int num,int listSize,String[] priorityData){

        LinearLayout myRoot = (LinearLayout) findViewById(R.id.graphs);

        for(int i=num;i<listSize;i++) {

            CardView view = new CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardParams.setMargins(32, 100, 32, 32);
            view.setLayoutParams(cardParams);

            GraphView one = new GraphView(this);
            LinearLayout.LayoutParams graphParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
            graphParams.setMargins(32, 100, 32, 0);
            one.setLayoutParams(graphParams);
            view.addView(one);

            int[] overView = new int[12];
            String[] catCard;
            catCard = cardProp(cardList[num++]);

            TextView myText = new TextView(this);
            myText.setText(catCard[0]);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.setMarginStart(400);
            myText.setLayoutParams(textParams);
            myText.setLayoutParams(textParams);
            view.addView(myText);

            String[] myCardVals;
            myCardVals = cardValString(catCard[1], catCard[2], catCard[3]);
            double numAPR = Double.parseDouble(myCardVals[0]);
            int numMinPay;
            if(catCard[0].equals(priorityData[1])&&catCard[1].equals(priorityData[2])&&catCard[2].equals(priorityData[4])&&catCard[3].equals(priorityData[5])&&catCard[4].equals(priorityData[3]))
                numMinPay = Integer.parseInt(priorityData[0]);
            else
                 numMinPay = Integer.parseInt(myCardVals[1]);
            int numBal = Integer.parseInt(myCardVals[2]);

            overView = minPayMode(overView, numMinPay, numAPR, numBal, 0);
            int max = returnApex(overView,0);
            int[] overView4 = new int[12];
            overView4 = grabInterest(overView4, numMinPay, numAPR, numBal, 0);
            overView4 = compoundInterest(overView4);
            overView4 = compBalInt(overView4, overView);

            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                    new DataPoint(1, overView[0]),
                    new DataPoint(2, overView[1]),
                    new DataPoint(3, overView[2]),
                    new DataPoint(4, overView[3]),
                    new DataPoint(5, overView[4]),
                    new DataPoint(6, overView[5]),
                    new DataPoint(7, overView[6]),
                    new DataPoint(8, overView[7]),
                    new DataPoint(9, overView[8]),
                    new DataPoint(10, overView[9]),
                    new DataPoint(11, overView[10]),
                    new DataPoint(12, overView[11])
            });
            one.addSeries(series);
            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                @Override
                public int get(DataPoint data) {
                    return Color.rgb(13, 8, 53);
                }
            });
            series.setSpacing(40);
            double xInterval = 1.0;
            one.getViewport().setXAxisBoundsManual(true);
            if (series instanceof BarGraphSeries) {

                one.getViewport().setMinX(series.getLowestValueX() - (xInterval / 2.0));
                one.getViewport().setMaxX(series.getHighestValueX() + (xInterval / 2.0));
            } else {
                one.getViewport().setMinX(series.getLowestValueX());
                one.getViewport().setMaxX(series.getHighestValueX());
            }

            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.RED);
            series.setValuesOnTopSize(24);
            one.getViewport().setMinY(0);
            one.getViewport().setMaxY(max+1000);
            one.getViewport().setYAxisBoundsManual(true);
            one.getViewport().setXAxisBoundsManual(true);
            one.getGridLabelRenderer().setNumHorizontalLabels(12);

            PointsGraphSeries<DataPoint> series4 = new PointsGraphSeries<>(new DataPoint[]{
                    new DataPoint(1, overView4[0]),
                    new DataPoint(2, overView4[1]),
                    new DataPoint(3, overView4[2]),
                    new DataPoint(4, overView4[3]),
                    new DataPoint(5, overView4[4]),
                    new DataPoint(6, overView4[5]),
                    new DataPoint(7, overView4[6]),
                    new DataPoint(8, overView4[7]),
                    new DataPoint(9, overView4[8]),
                    new DataPoint(10, overView4[9]),
                    new DataPoint(11, overView4[10]),
                    new DataPoint(12, overView4[11])
            });
            one.addSeries(series4);
            series4.setColor(Color.RED);
            series4.setCustomShape(new PointsGraphSeries.CustomShape() {
                @Override
                public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                    paint.setStrokeWidth(3);
                    canvas.drawLine(x - 20, y - 0, x + 20, y + 0, paint);
                }
            });
            myRoot.addView(view);
        }


    }
    public static int[] grabInterest(int[] yearOverview,int minPay,double apr,int bal,int incre){

        double monthlyAPR = apr/100;
        monthlyAPR = monthlyAPR/12;
        double monthlyInterest = monthlyAPR * bal;
        if((monthlyInterest+.001) >= ((int)monthlyInterest+0.5))
            monthlyInterest = monthlyInterest + 1;
        int roundMI = (int)monthlyInterest;
        int newBal = bal + roundMI - minPay;
        if(newBal < 0)
            newBal = 0;

        yearOverview[incre++] = roundMI;
        if(incre < 12)
            yearOverview = grabInterest(yearOverview,minPay,apr,newBal,incre);
        return yearOverview;
    }
    public static int[] compoundInterest(int[] yearOverview){
        yearOverview[1] = yearOverview[1] + yearOverview[0];
        yearOverview[2] = yearOverview[2] + yearOverview[1];
        yearOverview[3] = yearOverview[3] + yearOverview[2];
        yearOverview[4] = yearOverview[4] + yearOverview[3];
        yearOverview[5] = yearOverview[5] + yearOverview[4];
        yearOverview[6] = yearOverview[6] + yearOverview[5];
        yearOverview[7] = yearOverview[7] + yearOverview[6];
        yearOverview[8] = yearOverview[8] + yearOverview[7];
        yearOverview[9] = yearOverview[9] + yearOverview[8];
        yearOverview[10] = yearOverview[10] + yearOverview[9];
        yearOverview[11] = yearOverview[11] + yearOverview[10];

        return yearOverview;
    }
    public static int[] compBalInt(int[] yearOverview,int[] yearOverview2){
        if(yearOverview[0] > yearOverview2[0])
            yearOverview[0] = yearOverview2[0];
        if(yearOverview[1] > yearOverview2[1])
            yearOverview[1] = yearOverview2[1];
        if(yearOverview[2] > yearOverview2[2])
            yearOverview[2] = yearOverview2[2];
        if(yearOverview[3] > yearOverview2[3])
            yearOverview[3] = yearOverview2[3];
        if(yearOverview[4] > yearOverview2[4])
            yearOverview[4] = yearOverview2[4];
        if(yearOverview[5] > yearOverview2[5])
            yearOverview[5] = yearOverview2[5];
        if(yearOverview[6] > yearOverview2[6])
            yearOverview[6] = yearOverview2[6];
        if(yearOverview[7] > yearOverview2[7])
            yearOverview[7] = yearOverview2[7];
        if(yearOverview[8] > yearOverview2[8])
            yearOverview[8] = yearOverview2[8];
        if(yearOverview[9] > yearOverview2[9])
            yearOverview[9] = yearOverview2[9];
        if(yearOverview[10] > yearOverview2[10])
            yearOverview[10] = yearOverview2[10];
        if(yearOverview[11] > yearOverview2[11])
            yearOverview[11] = yearOverview2[11];
        return yearOverview;
    }
    public static int[] minPayMode(int[] yearOverview,int minPay,double apr,int bal,int incre){

        double monthlyAPR = apr/100;
        monthlyAPR = monthlyAPR/12;
        double monthlyInterest = monthlyAPR * bal;
        if((monthlyInterest+.001) >= ((int)monthlyInterest+0.5))
            monthlyInterest = monthlyInterest + 1;
        int roundMI = (int)monthlyInterest;
        int newBal = bal + roundMI - minPay;

        if(newBal < 0)
            newBal = 0;

        yearOverview[incre++] = newBal;
        if(incre < 12)
            yearOverview = minPayMode(yearOverview,minPay,apr,newBal,incre);
        return yearOverview;
    }
    public static String[] cardValString(String APR,String minPay, String balDue){

        String storeAPR ="";
        String storeMinPay = "";
        String storeBal = "";

        Pattern aprPat = Pattern.compile("[0-9]*\\.[0-9]*");
        Matcher m6 = aprPat.matcher(APR);
        if (m6.find()) {
            storeAPR = m6.group(0);
        }

        Pattern mpPat = Pattern.compile("[0-9]+");
        Matcher m7 = mpPat.matcher(minPay);
        if (m7.find()) {
            storeMinPay = m7.group(0);
        }

        Pattern balPat = Pattern.compile("[0-9]+");
        Matcher m8 = balPat.matcher(balDue);
        if (m8.find()) {
            storeBal = m8.group(0);
        }
        String[] cardVals = new String[3];
        cardVals[0] = storeAPR;
        cardVals[1] = storeMinPay;
        cardVals[2] = storeBal;

        return cardVals;

    }
    public int trueSize(String[] array){
        int size = 0;
        int iterator = 0;
        for(int i=0; i<10; i++){
            iterator = i;
            if(array[iterator] != null && array[iterator].length() > 0) {
                size++;
            }
        }
        return size;
    }
    public String[] cardProp(String Card){


        String one = "(Bank:.[a-zA-z]*)";
        String pattern = one;
        Pattern a = Pattern.compile(pattern);
        Matcher b = a.matcher(Card);
        b.find();
        String match1 = b.group(1);

        String two = "(APR Rate:.[0-90-9]*.[0-90-9]*)";
        String pattern2 = two;
        Pattern c = Pattern.compile(pattern2);
        Matcher d = c.matcher(Card);
        d.find();
        String match2 = d.group(1);

        String three = "(Minimum Payment:.[0-9]*)";
        String pattern3 = three;
        Pattern e = Pattern.compile(pattern3);
        Matcher f = e.matcher(Card);
        f.find();
        String match3 = f.group(1);

        String four = "(Balance Due:.[0-9]*)";
        String pattern4 = four;
        Pattern g = Pattern.compile(pattern4);
        Matcher h = g.matcher(Card);
        h.find();
        String match4 = h.group(1);

        String five = "(Due Date:.[0-9]*/[0-9]*/[0-9]*)";
        String pattern5 = five;
        Pattern i = Pattern.compile(pattern5);
        Matcher j = i.matcher(Card);
        j.find();
        String match5 = j.group(1);

        String[] cardProps = new String[5];
        cardProps[0]=match1;
        cardProps[1]=match2;
        cardProps[2]=match3;
        cardProps[3]=match4;
        cardProps[4]=match5;

        return cardProps;
    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("myCards.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    public static int returnApex(int myArray[], int max){

        for(int i=0; i<myArray.length; i++){
            if(max < myArray[i])
                max = myArray[i];
        }

        return max;
    }
}
