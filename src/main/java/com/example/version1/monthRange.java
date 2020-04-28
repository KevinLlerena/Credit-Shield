package com.example.version1;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class monthRange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_range);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void oneMonth(View view) {

        TextView textView = findViewById(R.id.textView);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void twoMonth(View view) {

        TextView textView = findViewById(R.id.textView2);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void threeMonth(View view) {

        TextView textView = findViewById(R.id.textView3);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void fourMonth(View view) {

        TextView textView = findViewById(R.id.textView4);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void fiveMonth(View view) {

        TextView textView = findViewById(R.id.textView5);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void sixMonth(View view) {

        TextView textView = findViewById(R.id.textView6);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void sevenMonth(View view) {

        TextView textView = findViewById(R.id.textView7);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void eightMonth(View view) {

        TextView textView = findViewById(R.id.textView8);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void nineMonth(View view) {

        TextView textView = findViewById(R.id.textView9);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void tenMonth(View view) {

        TextView textView = findViewById(R.id.textView10);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void elevenMonth(View view) {

        TextView textView = findViewById(R.id.textView11);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
    public void twelveMonth(View view) {

        TextView textView = findViewById(R.id.textView12);
        String text = textView.getText().toString();
        Intent intent = new Intent(this, batchGraphs.class);
        intent.putExtra("monthVal",text);
        startActivity(intent);
    }
}
