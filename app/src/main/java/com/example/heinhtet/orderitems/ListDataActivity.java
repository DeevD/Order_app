package com.example.heinhtet.orderitems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by heinhtet on 2/1/17.
 */
public class ListDataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        final ArrayList<String> listar = new ArrayList<>();

        final String[] name = {"d", "b", "c"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listar);
        listar.add("helo");
        listar.add("a");

        final TextView res = (TextView) findViewById(R.id.result);
        final EditText editText = (EditText) findViewById(R.id.edit);
        ListView listView = (ListView) findViewById(R.id.list_view);
        //listView.setAdapter(adapter);
        Button button = (Button) findViewById(R.id.clic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                for (int i = 0; i < listar.size(); i++) {
                    if (text.equals(listar.get(i))) {
                        Log.d("Log", listar.get(i));
                        res.setText(listar.get(i));
                    }
                }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent home = new Intent(this, OderActivity.class);
                startActivity(home);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
