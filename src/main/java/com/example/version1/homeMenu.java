package com.example.version1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;

public class homeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
    }

    public void registerCard(View view) {

        Intent intent = new Intent(this, registerCard.class);
        startActivity(intent);
    }
    public void viewCard(View view) {

        String cardStorage = readFromFile(getApplicationContext());
        if ((cardStorage.equals(""))==true) {
            Context context = getApplicationContext();
            CharSequence text = "No Cards Found";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, viewCards.class);
            startActivity(intent);
        }
    }
    public void minPay(View view) {
        String cardStorage = readFromFile(getApplicationContext());
        if ((cardStorage.equals(""))==true) {
            Context context = getApplicationContext();
            CharSequence text = "No Cards Found";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, minPay.class);
            startActivity(intent);
        }
    }
    public void batchMode(View view) {

        String cardStorage = readFromFile(getApplicationContext());
        if ((cardStorage.equals(""))==true) {
            Context context = getApplicationContext();
            CharSequence text = "No Cards Found";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, monthRange.class);
            startActivity(intent);
        }
    }
    public void chosePriority(View view) {

        String cardStorage = readFromFile(getApplicationContext());
        if ((cardStorage.equals(""))==true) {
            Context context = getApplicationContext();
            CharSequence text = "No Cards Found";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, selectCard.class);
            startActivity(intent);
        }
    }
    public void helpMenu(View view) {
        Intent intent = new Intent(this, help_info.class);
        startActivity(intent);
    }
    public void  clearCards(final View view){


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        try{

                            String path = getFilesDir().toString();
                            File file = new File(path+"/myCards.txt");
                            FileOutputStream fos = new FileOutputStream(file,false);
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
                            String card = "";
                            outputStreamWriter.write(card);
                            outputStreamWriter.close();


                            CharSequence text = "Cards Cleared";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(view.getContext(), text, duration);
                            toast.show();

                        }
                        catch (IOException e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

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
