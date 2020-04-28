package com.example.version1;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class viewCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        String cardStorage = readFromFile(getApplicationContext());

            String data = "(Bank:.[a-zA-z]*.APR Rate:.[0-90-9]*.[0-90-9]*.Minimum Payment:.[0-9]*.Balance Due:.[0-9]*.Due Date:.[0-9]*/[0-9]*/[0-9]*)?";
            String pattern = data + data + data + data + data + data + data + data + data + data;
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(cardStorage);
            if (m.find()) {
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
                createCard(cardDir, 0, dirSize);

            } else {
                Log.d("Error", " file viewCards.java error with matches");
            }


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
    public void createCard(String[] cardList, int num,int listSize){

        LinearLayout myRoot = (LinearLayout) findViewById(R.id.list);

        for(int i=num;i<listSize;i++) {

            CardView view = new CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardParams.setMargins(32, 100, 32, 32);
            view.setLayoutParams(cardParams);

            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(imgParams);
            imageView.setBackgroundResource(R.drawable.loadcard);
            imgParams.setMarginStart(50);
            view.addView(imageView);

            TextView myText = new TextView(this);
            String[] loadCards = cardProp(cardList[num++]);
            String autoFill = loadCards[0]+"\n"+loadCards[1]+"\n"+loadCards[2]+"\n"+loadCards[3]+"\n"+loadCards[4];
            myText.setText(autoFill);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.setMarginStart(400);
            myText.setLayoutParams(textParams);
            view.addView(myText);

            myRoot.addView(view);
        }

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
            ret="";
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
