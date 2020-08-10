package com.nifcompany.kingboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.rollbar.android.Rollbar;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout enableSetting = findViewById(R.id.layout_activation);
        LinearLayout chooseTheme = findViewById(R.id.layout_ChooseTheme);
        LinearLayout manageDictionaries = findViewById(R.id.layout_ManageDictionary);
        LinearLayout about = findViewById(R.id.layout_about);

        enableSetting.setOnClickListener(this);
        chooseTheme.setOnClickListener(this);
        manageDictionaries.setOnClickListener(this);
        about.setOnClickListener(this);

        Rollbar.init(this);
        Rollbar.instance().error(new Exception("This is a test error"));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_activation:
                startActivity(new Intent(this, ActivationActivity.class));
//                startActivityForResult(
//                        new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
                break;
            case R.id.layout_ChooseTheme:
                startActivity(new Intent(this, ThemeActivity.class));
                break;
            case R.id.layout_ManageDictionary:
                startActivity(new Intent(this, DictionaryActivity.class));
                break;
            case R.id.layout_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                break;
        }
    }
}