package com.example.ovi.menudemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {
    EditText editTextDelete;
    Button submitButtonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextDelete= (EditText) findViewById(R.id.EditTestDelete);
        submitButtonDelete= (Button) findViewById(R.id.submitButtonDelete);
        submitButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = getIntent();
                String name = editTextDelete.getText().toString();
                i2.putExtra("Name", name);
                setResult(RESULT_OK, i2);
                finish();
            }
        });
    }
}
