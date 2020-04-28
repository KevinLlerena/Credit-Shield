package com.example.version1;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class priorityMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_mode);
    }
    public void startPriority(View view) {

        EditText priorityPay = (EditText) findViewById(R.id.amt);
        String priorityPayment = priorityPay.getText().toString();
        boolean checkPay = checkBlank(priorityPayment,false);
        if(checkPay==true) {
            ((EditText) findViewById(R.id.amt)).getText().clear();
            Intent intent = new Intent(this, priorityGraphs.class);
            Bundle content = getIntent().getExtras();
            String bank = content.getString("Bank");
            String APR = content.getString("APR");
            String Balance = content.getString("Balance");
            String Due = content.getString("Due");
            String minDue = content.getString("minDue");
            intent.putExtra("priorityAmount",priorityPayment);
            intent.putExtra("Bank",bank);
            intent.putExtra("APR",APR);
            intent.putExtra("Balance",Balance);
            intent.putExtra("Due",Due);
            intent.putExtra("minDue",minDue);
            startActivity(intent);
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
}
