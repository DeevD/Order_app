package com.example.heinhtet.orderitems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class OderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int Quantity = 1;
    private int priccePerCup = 5;
    private int pricePerCold = 0;
    //Spinner spinner;
    int total = Quantity * priccePerCup  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        final Spinner items = (Spinner)findViewById(R.id.spinnerid);
        final Spinner cold = (Spinner)findViewById(R.id.spinner_cold);
        items.setOnItemSelectedListener(this);

        cold.setOnItemSelectedListener(this);
        List<String>item = new ArrayList<String>();
        final List<String>coldSpinner = new ArrayList<String>();
        item.add("Default" );
        item.add("Mocha" + "\n"+ "price: 3$");
        item.add("Capachino" + "\n"+ "price: 4$");
        item.add("American" + "\n"+ "price: 4$");
        coldSpinner.add("Default");
        coldSpinner.add("Orange" + "\n"+ "price: 3$");
        coldSpinner.add("Lemon" + "\n"+ "price: 3$");
        coldSpinner.add("Stawbery" + "\n"+ "price: 3$");
        ArrayAdapter<String>arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,coldSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        items.setAdapter(arrayAdapter);
        cold.setAdapter(adapter);
        items.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
        Button order = (Button)findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.name);
                String name = editText.getText().toString();
                //Get spinnerItems to String;
                String spinnerItems = items.getSelectedItem().toString();
                String spinnerColds = cold.getSelectedItem().toString();
                CheckBox suger = (CheckBox)findViewById(R.id.suger_check);
                boolean addSuger = suger.isChecked();
                CheckBox milk = (CheckBox)findViewById(R.id.milk_check);
                boolean addMilk = milk.isChecked();
                int calculatePriece = Calculate(spinnerItems,spinnerColds,addSuger,addMilk);
                display(name,spinnerItems,calculatePriece,spinnerColds);
            }
        });

    }

    private int Calculate(String spinnerItems,String spinnerCold,boolean suger, boolean milk) {


        int total = Quantity * priccePerCup  ;
        if (spinnerItems == "Mocha" && spinnerCold == "Orange"){
            pricePerCold = 5;
            total = Quantity * (priccePerCup + pricePerCold);
        }
        else if (spinnerItems == "Mocha" && spinnerCold == "Lemon"){
            pricePerCold = 7;
            total = Quantity * (priccePerCup+pricePerCold);
        }
        else if (spinnerItems == "Mocha" && spinnerCold == "Stawbery"){
            pricePerCold = 8;
            total = Quantity * (priccePerCup+pricePerCold);
        }
        if (spinnerItems == "Capachino" && spinnerCold == "Orange"){
            priccePerCup = 6;
            pricePerCold = 5;
            total = Quantity * (priccePerCup + pricePerCold );
        }
        else if (spinnerItems == "Capachino" && spinnerCold == "Lemon"){
            pricePerCold = 7;
            total = Quantity * (priccePerCup+pricePerCold);
        }
        else if (spinnerItems == "Capachino" && spinnerCold == "Stawbery"){
            pricePerCold = 8;
            total = Quantity * (priccePerCup+pricePerCold);
        }
        else if (spinnerItems == "American"){
            priccePerCup = 7;
            total = Quantity * (priccePerCup);
        }
        else if (spinnerItems == "American" && spinnerCold == "Lemon"){
            pricePerCold = 7;
            total = Quantity * (priccePerCup+pricePerCold);
        }
        else if (spinnerItems == "American" && spinnerCold == "Stawbery"){
            pricePerCold = 8;
            total = Quantity * (priccePerCup+pricePerCold);
        }

        if (suger && milk){
            total = Quantity * (priccePerCup + 3 + priccePerCup);
        }
        else if (milk){
            total = Quantity * (priccePerCup + 3 + priccePerCup);
        }
        else if (suger){
            total = Quantity*(priccePerCup + 1 + priccePerCup);
        }
        return total;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       String position = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),"Selected Coffee:"+adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void display(String name,String spinner,int cal,String spinnerCold) {
        TextView Ordname = (TextView)findViewById(R.id.order_list);
        String priceMessage = "Name: "+ name;
        priceMessage += "\nCoffee: " + spinner + " for" +  priccePerCup;
        priceMessage += "\nCold: " + spinnerCold;
        priceMessage += "\n Price: " + cal + "$" + " for :"  + Quantity + "Cup!";
        priceMessage += "\n Time: " + formattDate();
        Ordname.setText(priceMessage);
    }

    public void IncreaseQuantity(View view) {
        if (Quantity ==100){
            return;
        }
         Quantity = Quantity + 1;
         displayQuantity(Quantity);
    }

    private void displayQuantity(int quantity) {
        TextView displayQuantity = (TextView)findViewById(R.id.display_quantity);
        displayQuantity.setText(String.valueOf(quantity));
    }

    public void DecreaseQuantity(View view) {
        if (Quantity==1){
            return;
        }
        Quantity = Quantity -1;
        displayQuantity(Quantity);

    }
    private String formattDate (){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String formattedDate = simpleDateFormat.format(new Date());
        return formattedDate;
    }

    public void NextActivity(View view) {
        Intent next = new Intent(this,ListDataActivity.class);
        startActivity(next);
    }
}
