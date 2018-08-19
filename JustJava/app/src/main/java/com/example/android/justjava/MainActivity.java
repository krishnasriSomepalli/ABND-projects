/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 2;

    public void increment(View view) {
        if(quantity == 100) {
            CharSequence text = getResources().getString(R.string.warning_large_order);
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(this, text, duration).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1) {
            CharSequence text = getResources().getString(R.string.warning_small_order);
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(this, text, duration).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean addWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean addChocolate = chocolate.isChecked();

        EditText name = (EditText) findViewById(R.id.customer_name);
        String customer_name = name.getText().toString();

        createOrderSummary(50, addWhippedCream, addChocolate, customer_name);
    }

    private int calculatePrice(int pricePerCup, boolean addWhippedCream, boolean addChocolate) {
        int price = quantity*pricePerCup;
        int whippedCreamCost = 10;
        int chocolateCost = 20;

        if(addWhippedCream == true)
            price += quantity*whippedCreamCost;
        if(addChocolate == true)
            price += quantity*chocolateCost;
        return price;
    }

    private void createOrderSummary(int pricePerCup, boolean addWhippedCream, boolean addChocolate, String customer_name) {
        int total = calculatePrice(pricePerCup, addWhippedCream, addChocolate);
        String orderSubject = getResources().getString(R.string.order_subject, customer_name);
        String orderSummary = getResources().getString(R.string.order_name, customer_name);
        orderSummary += "\n" + getResources().getString(R.string.order_toppings_whipped_cream, addWhippedCream);
        orderSummary += "\n" + getResources().getString(R.string.order_toppings_chocolate, addChocolate);
        orderSummary += "\n" + getResources().getString(R.string.order_quantity, quantity);;
        orderSummary += "\n" + getResources().getString(R.string.order_total, NumberFormat.getCurrencyInstance().format(total));;
        orderSummary += "\n" + getResources().getString(R.string.order_greeting);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto:"));
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, orderSubject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
        else
            Log.v("MainActivity.java", "Couldn't place order.");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}