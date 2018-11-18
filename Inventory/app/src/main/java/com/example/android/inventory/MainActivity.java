package com.example.android.inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.data.InventoryContract.InventoryEntry;
import com.example.android.inventory.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new InventoryDbHelper(this);
        displayDatabaseInfo();

        final Button insertDummy = (Button) findViewById(R.id.insert_dummy_data);
        insertDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                displayDatabaseInfo();
            }
        });
    }

    private void insertData() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, "Hit Refresh");
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, 599);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, 20);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, "Walden");
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE, "987543210");

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);

        String toastMessage;
        if(newRowId == -1)
            toastMessage = "Error with inserting product";
        else
            toastMessage = "Product saved with id: " + Long.toString(newRowId);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    private Cursor queryData() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(InventoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    public void displayDatabaseInfo() {

        Cursor cursor = queryData();
        TextView displayView = (TextView) findViewById(R.id.temp_text);

        try {
            displayView.setText("The products table contains " + cursor.getCount() + " products.\n\n");

            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            int suppNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int suppPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSuppName = cursor.getString(suppNameColumnIndex);
                String currentSuppPhone = cursor.getString(suppPhoneColumnIndex);
                displayView.append("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSuppName + " - " +
                        currentSuppPhone);
            }
        } finally {
            cursor.close();
        }
    }
}
