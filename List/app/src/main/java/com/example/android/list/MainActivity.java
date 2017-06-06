package com.example.android.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.ems;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    ListView L;
    public static String dispstring;
    ArrayAdapter adapter;
    Intent i;
    EditText E1,E2;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L = (ListView) findViewById(R.id.listview);
        E1 = (EditText) findViewById(R.id.edittext1);
        E2 = (EditText) findViewById(R.id.edittext2);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 dispstring = items.get(position);
                 intentfn();

            }
        });
    }


    public void intentfn()
    {
        i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }

    public void addItem(View v)
    {
        String newItem = E1.getText().toString();

        items.add(newItem);

        adapter.notifyDataSetChanged();

        E1.setText("");
    }


    public void removeItem(View v)
    {
        Toast toast;
        String text = E2.getText().toString();
        int position;

        if(text.length()==0)
        {
            toast = Toast.makeText(this,"Enter position",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

        else
        {
            position = Integer.parseInt(text);

            if (position == 0 || position > items.size())
            {
                toast = Toast.makeText(this, "Invalid. No item in entered position", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

            else
            {
                items.remove(position - 1);

                adapter.notifyDataSetChanged();
            }
        }


        E2.setText("");

    }

}
