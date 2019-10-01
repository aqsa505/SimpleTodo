package com.codepath.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

       List<String> items;
       Button btnAdd;
       EditText etItem;
       RecyclerView rvItems;
       ItemAdapter itemAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd =findViewById(R.id.btnAdd);
        etItem =findViewById(R.id.etitem);
        rvItems =findViewById(R.id.rvItems);

        items = new ArrayList<> ();
        items.add("Go to the gym");
        items.add("Buy Milk");

        ItemAdapter.OnLongClickListener OnLongClickListener =  new ItemAdapter.OnLongClickListener(){

            @Override
            public void onItemLongClicked(int position) {
                 //delete the item form the model
                items.remove(position);
                itemAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"Item was removed", Toast.LENGTH_SHORT).show();
            }
        };
        itemAdapter = new ItemAdapter(items,OnLongClickListener );
       rvItems.setAdapter(itemAdapter);
       rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String todoItem  = etItem.getText().toString();
                //add item m to model
                items.add(todoItem);

                // notify adaptor
                itemAdapter.notifyItemInserted(items.size()-1);

                //clear the edit text 
                etItem.setText("");
                Toast.makeText(getApplicationContext(),"Item was added",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
