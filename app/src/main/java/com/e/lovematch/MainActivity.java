package com.e.lovematch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_yourName, et_otherName;
    ImageView iv_meter;
    Button btn_compare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_yourName = findViewById(R.id.et_yourName);
        et_otherName = findViewById(R.id.et_otherName);

        iv_meter = findViewById(R.id.iv_meter);

        btn_compare = findViewById(R.id.btn_compare);

        btn_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yourName = et_yourName.getText().toString().toLowerCase();
                String otherName = et_otherName.getText().toString().toLowerCase();

                int totalLetter = yourName.length() + otherName.length();
                int totalMatches = 0;

                for (int i = 0; i < yourName.length(); i++){
                    for (int j = 0; j < otherName.length(); j++){
                        if (yourName.charAt(i) == otherName.charAt(j)){
                            totalMatches ++;
                        }
                    }
                }

                for (int i = 0; i < otherName.length(); i++){
                    for (int j = 0; j < yourName.length(); j++){
                        if (otherName.charAt(i) == yourName.charAt(j)){
                            totalMatches ++;
                        }
                    }
                }

                float computeScore = (float) totalMatches / totalLetter;

                int loveScore = (int) ((computeScore * 200) - 100);

                if (loveScore >= 100){
                    loveScore = 100;
                }

                RotateAnimation ra = new RotateAnimation(0, 360 + loveScore, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setFillAfter(true);
                ra.setDuration(2000);
                ra.setInterpolator(new AccelerateDecelerateInterpolator());
                iv_meter.startAnimation(ra);

                Toast.makeText(MainActivity.this, "Match " + loveScore + " and " + computeScore, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
