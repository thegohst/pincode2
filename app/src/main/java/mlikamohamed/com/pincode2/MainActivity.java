package mlikamohamed.com.pincode2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mlikamohamed.com.pincode.PinCode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PinCode pinCode = findViewById(R.id.pinCode);
        pinCode.setTextChangeListener(new PinCode.OnTextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                // this method will be called when text change
            }

            @Override
            public void onAllBoxFilled(String text) {
// this method will be called when all boxes are filled
            }
        });
        pinCode.showText();
    }
}
