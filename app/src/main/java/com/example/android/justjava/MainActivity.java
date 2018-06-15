/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Context;
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
import java.util.jar.Attributes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //initiate an order from 2 cups of coffee
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String customerName = nameField.getText().toString();
        Log.i("MainActivity", "Customer's name:" + customerName);

        CheckBox top01CheckBox = (CheckBox) findViewById(R.id.item01_check_box);
        boolean isCheckedItem01 = top01CheckBox.isChecked();
        Log.i("MainActivity", "Has whipped cream: " + isCheckedItem01);

        CheckBox top02CheckBox = (CheckBox) findViewById(R.id.item02_check_box);
        boolean isCheckedItem02 = top02CheckBox.isChecked();
        Log.i("MainActivity", "Has chocolate: " + isCheckedItem02);

        int price = calculatePrice(isCheckedItem01, isCheckedItem02);
        String priceMessage = createOrderASummary(customerName, price, isCheckedItem01, isCheckedItem02);
        displayMessage(priceMessage);

        String[] mailAddresses = {"ovarb6@gmail.com"};
        String mailSubject = getString(R.string.app_name) + ": " + customerName + " orders " + quantity + " cups of coffee";
        Log.i("MainActivity", "Mail subject string: " + mailSubject);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, mailAddresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            this.startActivity(intent);
        }
    }

    /**
     * This method calculates totalPrice
     *
     * @param isCheckedOption01 whether or not topping01 is added
     * @param isCheckedOption02 whether or not topping01 is added
     * @return totalPrice of the order
     */
    public int calculatePrice(boolean isCheckedOption01, boolean isCheckedOption02){

        int basePrice;
        int totalPrice;

        basePrice = 5;

        if (isCheckedOption01) {
            basePrice += 1;
        }

        if (isCheckedOption02) {
            basePrice += 2;
        }

        Log.i("MainActivity", "The base price of single cup: " + basePrice);

        totalPrice = quantity * basePrice;
        Log.i("MainActivity", "The total price is: " + totalPrice);

        return totalPrice;
    }

    /**
     * Create summary of the order
     *
     * @param customerName of the buyer
     * @param price of the order
     * @param isCheckedOption01 whether or not topping01 is added
     * @param isCheckedOption02 whether or not topping02 is added
     * @return text summary about the order
     */
    private String createOrderASummary(String customerName, int price, boolean isCheckedOption01, boolean isCheckedOption02){
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
        if(10 == quantity) {
            //shows an error message
            Context context = getApplicationContext();
            String toastText = "You can't order more than 10 cup of coffee";
            int toastDuration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, toastText, toastDuration);
            toast.show();

            //return from the method if quantity is too small
            return;
        }
        quantity++;
        display(quantity);
        //displayPrice(numberOfCoffees*5);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement (View view) {
        if(1 == quantity ) {
            //shows an error message
            Toast.makeText(this, "You can't order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            //return from the method if quantity is too small
            return;
        }
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
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
