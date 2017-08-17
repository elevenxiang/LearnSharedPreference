package com.htc.eleven.learnsharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textEditor;
    private TextView result;

    private SharedPreferences preferences;
    private SharedPreferences.Editor preEditor;

    private final String KEY = "data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferences = getPreferences(MODE_PRIVATE);
        preEditor = preferences.edit();

        textEditor = (EditText) findViewById(R.id.editor);
        result = (TextView) findViewById(R.id.result);

        findViewById(R.id.ReadSharedPreference).setOnClickListener(this);
        findViewById(R.id.WriteSharedPreference).setOnClickListener(this);

        findViewById(R.id.StartPreferenceActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ReadSharedPreference:
                String data = preferences.getString(KEY,"default");

                result.setText(data);
                Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
                break;
            case R.id.WriteSharedPreference:
                preEditor.putString(KEY,textEditor.getText().toString());

                boolean ret = preEditor.commit();
                if(ret){
                    Toast.makeText(MainActivity.this,"写入成功 !", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.StartPreferenceActivity:
//                getFragmentManager().beginTransaction().addToBackStack(null).add(R.id.main, new MyPreferenceActivity()).commit();
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main, new MyPreferenceActivity()).commit();
//                startActivity(new Intent(MainActivity.this,MyPreferenceActivity.class));
                break;
        }

    }
}
