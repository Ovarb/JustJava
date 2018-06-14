/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.jar.Attributes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox top01CheckBox = (CheckBox) findViewById(R.id.item01_check_box);
        boolean isCheckedItem01 = top01CheckBox.isChecked();
        Log.i("MainActivity", "Has whipped cream: " + isCheckedItem01);

        CheckBox top02CheckBox = (CheckBox) findViewById(R.id.item02_check_box);
        boolean isCheckedItem02 = top02CheckBox.isChecked();
        Log.i("MainActivity", "Has chocolate: " + isCheckedItem02);

        int price = calculatePrice(isCheckedItem01,isCheckedItem02);
        String priceMessage = createOrderASummary(price,isCheckedItem01,isCheckedItem02);
        displayMessage(priceMessage);
    }

    /**
     * This method calculates totalPrice
     *
     * @param isCheckedOption01 is about added topping01
     * @param isCheckedOption02 is about added topping02
     * @return totalPrice of the order
     */
    public int calculatePrice(boolean isCheckedOption01, boolean isCheckedOption02){
        int totalPrice;
        totalPrice = quantity * 5;
        if (isCheckedOption01) {
            totalPrice += 10;
        }
        if (isCheckedOption02) {
            totalPrice += 12;
        }
        Log.i("MainActivity", "The price is: " + totalPrice);
        return totalPrice;
    }

    /**
     * Create summary of the order
     *
     * @param price of the order
     * @param isCheckedOption01 is about added topping01
     * @param isCheckedOption02 is about added topping02
     * @return text summary about the order
     */
    private String createOrderASummary(int price, boolean isCheckedOption01, boolean isCheckedOption02){
        String customerName = "Kaptain Kunal";
        String message;
        message = "Name: " + customerName;
        message += "\n" + "Add whipped cream? " + isCheckedOption01;
        message += "\n" + "Add chocolate? " + isCheckedOption02;
        message += "\n" + "Quantity: " + quantity;
        message += "\n" + "Total: " + "$" + price;
        message += "\n" + "Thank you!";
        return message;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment (View view) {
        quantity++;
        display(quantity);
        //displayPrice(numberOfCoffees*5);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement (View view) {
        quantity--;
        display(quantity);
        //displayPrice(numberOfCoffees*5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
