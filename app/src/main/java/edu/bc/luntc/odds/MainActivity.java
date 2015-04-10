package edu.bc.luntc.odds;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        final TextView percentage = (TextView)findViewById(R.id.percent);
        Button button = (Button)findViewById(R.id.button);
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                if(fromUser){
                    if(progress < 100 && progress > 0)
                        percentage.setText(progress+"");
                    else if(progress == 100)
                        percentage.setText("99");
                    else
                        percentage.setText("1");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener( new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Random r = new Random();
                percent = Integer.parseInt(((EditText) findViewById(R.id.percent)).getText().toString());
                phone = ((EditText)findViewById(R.id.phone)).getText().toString();
                message = ((EditText)findViewById(R.id.message)).getText().toString();

                if(phone.length() < 10 ){
                    result.setText("Enter a valid phone number");
                }
                else if(message.length() < 1){
                    result.setText("Enter a message");
                }
                else if(Math.abs(r.nextInt())%100 < percent){
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
