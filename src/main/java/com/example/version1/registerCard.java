package com.example.version1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class registerCard extends AppCompatActivity {

    private static final String TAG = "registerCard";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_card);
        mDisplayDate =  (TextView) findViewById(R.id.textView9);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        registerCard.this,
                        android.R.style.Theme,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;

                String date = month+"/"+dayOfMonth+"/"+year;
                mDisplayDate.setText(date);
            }
        };


    }
    public void sendInfo(View view) {

        String cardStorage = readFromFile(getApplicationContext());
        String data = "(Bank:.[a-zA-z]*.APR Rate:.[0-90-9]*.[0-90-9]*.Minimum Payment:.[0-9]*.Balance Due:.[0-9]*.Due Date:.[0-9]*/[0-9]*/[0-9]*)?";
        String pattern = data+data+data+data+data+data+data+data+data+data;
        Pattern r = Pattern.compile(pattern);
        int dirSize = 0;
        String[] cardDir = new String[10];
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

            dirSize = trueSize(cardDir);

        }
        else {
            Log.d("Error"," file registerCard.java error with matches");
        }

        EditText creditCard = (EditText) findViewById(R.id.textView);
        String message = creditCard.getText().toString();

        EditText balanceDue = (EditText) findViewById(R.id.textView3);
        String message1 = balanceDue.getText().toString();

        EditText aprRate = (EditText) findViewById(R.id.textView5);
        String message2 = aprRate.getText().toString();

        EditText minPay = (EditText) findViewById(R.id.textView7);
        String message3 = minPay.getText().toString();

        TextView dueDay = (TextView) findViewById(R.id.textView9);
        String message4 = dueDay.getText().toString();


        boolean bank = checkBlank(message,false);
        if(message.matches("[a-z]*") != true)
        {
            Context context = getApplicationContext();
            CharSequence text = "Bank must be lower case characters only no spaces";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            bank = false;
        }
        boolean balance = checkBlank(message1,false);
        boolean apr = checkBlank(message2,false);
        if(message2.matches("\\d{1,2}[.]\\d{0,2}") != true)
        {
            Context context = getApplicationContext();
            CharSequence text = "Incorrect Input must be decimal standard";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            apr = false;
        }

        boolean minDue = checkBlank(message3,false);
        boolean dueDate = checkBlank(message4,false);
        String[] info = {message,message1,message2,message3,message4};
        boolean flag = duplicateCard(cardDir,info,0,dirSize,false);
        if(bank==true&&balance==true&&apr==true&&minDue==true&&dueDate==true&&dirSize<10&&flag==false) {
            addCard(message, message1, message2, message3, message4);
            Context context = getApplicationContext();
            CharSequence text = "Card Added";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            ((EditText) findViewById(R.id.textView)).getText().clear();
            ((EditText) findViewById(R.id.textView3)).getText().clear();
            ((EditText) findViewById(R.id.textView5)).getText().clear();
            ((EditText) findViewById(R.id.textView7)).getText().clear();
            ((TextView) findViewById(R.id.textView9)).setText("");

        }
        if(dirSize==10){
            Context context = getApplicationContext();
            CharSequence text = "Card Limit Reached";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public boolean  checkBlank(String input,boolean flag){
        if(input.equals("")){
            Context context = getApplicationContext();
            CharSequence text = "Incorrect Input";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        else{
            return flag=true;
        }
        return flag=false;

    }
    public void  addCard(String bankName,String balanceDue,String aprRate,String minPay,String dueDate)
    {
        try{

            String path = getFilesDir().toString();
            File file = new File(path+"/myCards.txt");
            FileOutputStream fos = new FileOutputStream(file,true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

            String card = "Bank: "+bankName+" APR Rate: "+aprRate+" Minimum Payment: "+minPay+" Balance Due: "+balanceDue+" Due Date: "+dueDate;
            outputStreamWriter.write(card);
            outputStreamWriter.close();
            String out = readFromFile(getApplicationContext());
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
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
    public boolean duplicateCard(String[] cardList,String[] info, int num,int listSize,boolean flag){

        String bank = "Bank: "+info[0];
        String apr = "APR Rate: "+info[2];
        String minDue = "Minimum Payment: "+info[1];
        String balance = "Balance Due: "+info[3];
        String dueDate = "Due Date: "+info[4];
        for(int i=num;i<listSize;i++) {
            String[] catCard;
            catCard = cardProp(cardList[num++]);

            if(catCard[0].equals(bank)&&catCard[1].equals(apr)&&catCard[2].equals(minDue)&&catCard[3].equals(balance)&&catCard[4].equals(dueDate)) {
                flag = true;
                Context context = getApplicationContext();
                CharSequence text = "Duplicate card found renter";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else
                flag=false;
        }
        return flag;
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



}
