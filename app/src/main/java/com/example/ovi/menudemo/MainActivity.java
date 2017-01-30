package com.example.ovi.menudemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add("ovi");
        arrayList.add("imam");
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        registerForContextMenu(listView);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        Toast.makeText(getApplicationContext(), "" + textView.getText(), Toast.LENGTH_SHORT).show();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo) {
        menu.setHeaderTitle("Select The Action");
        menu.add(0, 1123, 0, "View Details");
        menu.add(0, 1124, 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == 1123) {
            Toast.makeText(getApplicationContext(), "" + arrayList.get(info.position), Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == 1124) {
            Toast.makeText(getApplicationContext(), arrayList.get(info.position) + " deleted", Toast.LENGTH_LONG).show();
            arrayList.remove(info.position);
            adapter.notifyDataSetChanged();
        } else {
            return false;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add:
                final Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.delete:
                final Intent intent2 = new Intent(MainActivity.this, ThirdActivity.class);
                startActivityForResult(intent2,2);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                String edit = data.getStringExtra("Name");
                arrayList.add(edit);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                String delete = data.getStringExtra("Name");
                int del_pos=arrayList.indexOf(delete);
                arrayList.remove(del_pos);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
