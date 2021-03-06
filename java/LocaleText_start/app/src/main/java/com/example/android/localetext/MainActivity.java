/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.localetext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * This app demonstrates how to localize an app with text, an image,
 * a floating action button, an options menu, and the app bar.
 */
public class MainActivity extends AppCompatActivity {
    private NumberFormat mNumberFormat = NumberFormat.getInstance();
    private static final String TAG = MainActivity.class.getSimpleName();
    // Fixed price in U.S. dollars and cents: ten cents.
    private double mPrice = 0.10;
    // Exchange rates for France (FR) and Israel (IW).
    private double mFrExchangeRate = 0.93; // 0.93 euros = $1.
    private double mIwExchangeRate = 3.61; // 3.61 new shekels = $1.
    private NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
    private String myFormattedPrice;
    /**
     * Creates the view with a toolbar for the options menu
     * and a floating action button, and initialize the
     * app data.
     *
     * @param savedInstanceState Bundle with activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp();
            }
        });

        final Date currentDate = new Date();
        final long expirationDate = currentDate.getTime() + TimeUnit.DAYS.toMillis(5);
        currentDate.setTime(expirationDate);
        String formatterDate = DateFormat.getDateInstance().format(currentDate);

        TextView expirationDateView = (TextView) findViewById(R.id.expiration);
        expirationDateView.setText(formatterDate);

        EditText quantity = (EditText) findViewById(R.id.quantity);
        final TextView mtotal = (TextView) findViewById(R.id.total);

        String deviceLocale = Locale.getDefault().getCountry();
        // If country code is France or Israel, calculate price
        // with exchange rate and change to the country's currency format.
        if (deviceLocale.equals("FR") || deviceLocale.equals("IL")) {
            if (deviceLocale.equals("FR")) {
                // Calculate mPrice in euros.
                mPrice *= mFrExchangeRate;
            } else {
                // Calculate mPrice in new shekels.
                mPrice *= mIwExchangeRate;
            }
            // Use the user-chosen locale's currency format, which
            // is either France or Israel.
            myFormattedPrice = mCurrencyFormat.format(mPrice);
        } else {
            // mPrice is the same (based on U.S. dollar).
            // Use the currency format for the U.S.
            mCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
            myFormattedPrice = mCurrencyFormat.format(mPrice);
        }
        TextView localePrice = (TextView) findViewById(R.id.price);
        localePrice.setText(myFormattedPrice);

        quantity.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                InputMethodManager imn = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imn.hideSoftInputFromWindow(textView.getWindowToken(),0);
                try {
                    int mInputQuantity = mNumberFormat.parse(textView.getText().toString()).intValue();
                    String myFormattedQuantity = mNumberFormat.format(mInputQuantity);
                    Double total = mInputQuantity * mPrice;
                    mtotal.setText(String.valueOf(total));
                    textView.setText(myFormattedQuantity);
                    textView.setError(null);

                } catch (ParseException e) {
                    e.printStackTrace();
                    textView.setError(e.getMessage());
                }
                return true;
            }
        });




    }

    /**
     * Shows the Help screen.
     */
    private void showHelp() {
        // Create the intent.
        Intent helpIntent = new Intent(this, HelpActivity.class);
        // Start the HelpActivity.
        startActivity(helpIntent);
    }

    /**
     * Creates the options menu and returns true.
     *
     * @param menu       Options menu
     * @return boolean   True after creating options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles options menu item clicks.
     *
     * @param item      Menu item
     * @return boolean  True if menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_language){
            Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(languageIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
