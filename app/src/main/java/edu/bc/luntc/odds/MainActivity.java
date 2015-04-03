package edu.bc.luntc.odds;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    String message;
    int percent;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView result = (TextView)findViewById(R.id.result);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener( new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Random r = new Random();
                percent = Integer.parseInt(((EditText) findViewById(R.id.percent)).getText().toString());
                phone = ((EditText)findViewById(R.id.phone)).getText().toString();
                message = ((EditText)findViewById(R.id.message)).getText().toString();

                if(Math.abs(r.nextInt())%100 < percent){
                    SmsManager.getDefault().sendTextMessage(phone, null, message, null, null);
                    result.setText("SENT!!");
                }
                else{
                    result.setText("Lucked out!");
                }
            }
        });

    }

}
