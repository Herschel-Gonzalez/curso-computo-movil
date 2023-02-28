package com.practica.practicas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=null;

        switch (item.getItemId()){
            case R.id.textView:
                intent = new Intent(this,TextViewActivity.class);
                break;
            case R.id.ediText:
                intent = new Intent(getApplicationContext(),EditTextActivity.class);
                break;
            case R.id.button:
                intent = new Intent(getApplicationContext(),ButtonActivity.class);
                break;
            case R.id.radio:
                intent = new Intent(getApplicationContext(),RadioActivity.class);
                break;
            case R.id.checkbox:
                intent = new Intent(getApplicationContext(),CheckBoxActivity.class);
                break;
            case R.id.spinner:
                intent = new Intent(getApplicationContext(),SpinnerActivity.class);
                break;
            case R.id.listView:
                intent = new Intent(getApplicationContext(),ListViewActivity.class);
                break;
        }
        startActivity(intent);
        return true;
    }
}