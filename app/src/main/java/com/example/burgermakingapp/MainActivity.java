package com.example.burgermakingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    Spinner sp_flavour;
    RadioGroup rg_cooked;
    RadioButton cooked;
    CheckBox adds_cheese, adds_letture, adds_tomato, adds_onion;
    TextView tv;
    String done = "";
    DatabaseReference reff;
    OrderInfo orderinfo;

    double price_flavour = 0, price_cooked = 0, price_adds = 0, total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_flavour = (Spinner)findViewById(R.id.sp_flavour);
        rg_cooked = (RadioGroup)findViewById(R.id.rg_cooked);
        cooked = (RadioButton)rg_cooked.findViewById(rg_cooked.getCheckedRadioButtonId());
        adds_cheese = (CheckBox)findViewById(R.id.cheese);
        adds_letture = (CheckBox)findViewById(R.id.letture);
        adds_tomato = (CheckBox)findViewById(R.id.tomato);
        adds_onion = (CheckBox)findViewById(R.id.onion);
        tv = (TextView)findViewById(R.id.total);
        btnSubmit = findViewById(R.id.btnSubmit);
        orderinfo = new OrderInfo();

        sp_flavour.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position = sp_flavour.getSelectedItemPosition();

                switch (position) {
                    case 0:
                        price_flavour = 3.00;
                        TotalAmount();
                        break;

                    case 1:
                        price_flavour = 2.50;
                        TotalAmount();
                        break;

                    case 2:
                        price_flavour = 1.50;
                        TotalAmount();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rg_cooked.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checked){
                cooked = (RadioButton)group.findViewById(checked);
                boolean isChecked = cooked.isChecked();

                if (isChecked) {
                    done = cooked.getText().toString();
                }

                switch (checked){
                    case R.id.rare:
                        price_cooked = 0.50;
                        TotalAmount();
                        break;

                    case R.id.medium:
                        price_cooked = 1.00;
                        TotalAmount();
                        break;

                    case R.id.well:
                        price_cooked = 1.50;
                        TotalAmount();
                        break;
                }
            }
        });
    }

    public void TotalAmount(){
        total = price_flavour + price_cooked + price_adds;
        tv.setText(String.format("Your total order is RM%.2f",total));
    }

    public void cheese_onClick(View view) {
        CheckBox c = (CheckBox)view;

        if(c.isChecked()){
            price_adds += 2.00;
            TotalAmount();
        }
        else
        {
            price_adds -= 2.00;
            TotalAmount();
        }
    }

    public void letture_onClick(View view) {
        CheckBox c = (CheckBox)view;

        if(c.isChecked()){
            price_adds += 1.00;
            TotalAmount();
        }
        else
        {
            price_adds -= 1.00;
            TotalAmount();
        }
    }

    public void tomato_onClick(View view) {
        CheckBox c = (CheckBox)view;

        if(c.isChecked()) {
            price_adds += 1.00;
            TotalAmount();
        }
        else
        {
            price_adds -= 1.00;
            TotalAmount();
        }
    }

    public void onion_onClick(View view) {
        CheckBox c = (CheckBox)view;

        if(c.isChecked()){
            price_adds += 0.50;
            TotalAmount();
        }
        else
        {
            price_adds -= 0.50;
            TotalAmount();
        }
    }

    public void btnSubmit_onClick(View view) {
        reff = FirebaseDatabase.getInstance().getReference().child("OrderInfo");
        if(price_cooked>0 && price_flavour>0){

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Your Order");
            alert.setMessage("Are you confirm with your order?\n\nDetails:\n" +
                    "Type:  " + sp_flavour.getSelectedItem().toString() + String.format(" RM%.2f",price_flavour) + "\n" +
                    "Cooked: " + done + String.format(" RM%.2f",price_cooked) + "\n" +
                    "Add Ons: " + String.format("RM%.2f",price_adds) + "\n\n" +
                    "Total Amount: " + String.format("RM%.2f",total));

            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String burgertype = sp_flavour.getSelectedItem().toString().trim();
                    String doneness = cooked.getText().toString().trim();

                    orderinfo.setBurgertype(burgertype);
                    orderinfo.setDoneness(doneness);
                    reff.child("Order").setValue(orderinfo);
                    Toast.makeText(MainActivity.this, "Your order has been sent", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Data inserted sucessfully", Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Order is not confirmed", Toast.LENGTH_SHORT).show();
                }
            });

            alert.setCancelable(false);
            alert.show();

        }else{
            Toast.makeText(this,"Burger type and cook must be selected!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.burger_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_print:
                Toast.makeText(this, "Print is selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Retrieve.class));
                break;

            case R.id.menu_cart:
                Toast.makeText(this,"Shopping cart is selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                break;
        }
        return true;
    }

    public void btn_AboutUs(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, AboutUs.class);
        startActivity(intent);
    }

    public void btn_FAQ(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, FAQ.class);
        startActivity(intent);
    }
}
