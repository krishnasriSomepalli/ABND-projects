package com.example.android.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.data.InventoryContract.InventoryEntry;

public class ProductCursorAdapter extends CursorAdapter{

    private Context cntxt;

    private String id;
    private String name;
    private String price;
    private String quantity;

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        cntxt = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView productName = (TextView) view.findViewById(R.id.product_name);
        TextView productPrice = (TextView) view.findViewById(R.id.price);
        final TextView productQuantity = (TextView) view.findViewById(R.id.quantity);
        Button saleBtn = (Button) view.findViewById(R.id.sale_button);

        id = cursor.getString(cursor.getColumnIndex(InventoryEntry._ID));
        name = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME));
        price = String.valueOf(cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE)));
        quantity = String.valueOf(cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY)));

        productName.setText(name);
        productPrice.setText(price);
        productQuantity.setText(quantity);

        saleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantityInt = Integer.parseInt(quantity);
                if(quantityInt-1 >= 0) {
                    quantityInt -= 1;
                    quantity = Integer.toString(quantityInt);
                    productQuantity.setText(quantity);
                    saveProduct();
                }
                else {
                    Toast.makeText(cntxt, R.string.negative_quantity_msg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveProduct() {

        Uri uri = Uri.withAppendedPath(InventoryEntry.CONTENT_URI, id);

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, name);
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);

        int rowsUpdated = cntxt.getContentResolver().update(uri, values, null, null);

        if (rowsUpdated != 1) {
            Toast.makeText(cntxt, R.string.negative_quantity_msg,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
