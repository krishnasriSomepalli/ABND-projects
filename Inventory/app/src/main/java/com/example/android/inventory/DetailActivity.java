package com.example.android.inventory;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.data.InventoryContract.InventoryEntry;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAIL_PRODUCT = 0;

    private Uri mCurrentProductUri;

    private TextView mProductNameText;
    private TextView mPriceText;
    private TextView mQuantityText;
    private TextView mSupplierNameText;
    private Button mSupplierPhoneBtn;
    private Button mEdit;
    private Button mDelete;
    private Button mIncrement;
    private Button mDecrement;
    private EditText mMagnitude;

    private String productName;
    private int price;
    private int quantity;
    private String supplierName;
    private String supplierPhone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        mProductNameText = (TextView) findViewById(R.id.detail_product_name);
        mPriceText = (TextView) findViewById(R.id.detail_price);
        mQuantityText = (TextView) findViewById(R.id.detail_quantity);
        mSupplierNameText = (TextView) findViewById(R.id.detail_supplier);
        mSupplierPhoneBtn = (Button) findViewById(R.id.btn_contact);
        mEdit = (Button) findViewById(R.id.btn_edit);
        mDelete = (Button) findViewById(R.id.btn_delete);
        mIncrement = (Button) findViewById(R.id.btn_increment);
        mDecrement = (Button) findViewById(R.id.btn_decrement);
        mMagnitude = (EditText) findViewById(R.id.edit_magnitude);

        getLoaderManager().initLoader(DETAIL_PRODUCT, null,this);
    }

    private void saveProduct() {

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(InventoryEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(InventoryEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierName);
        values.put(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE, supplierPhone);

        int rowsUpdated = getContentResolver().update(mCurrentProductUri, values, null, null);

        if (rowsUpdated != 1) {
            Toast.makeText(this, getString(R.string.editor_insert_product_failed),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {

        if(mCurrentProductUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentProductUri, null, null);

            if (rowsDeleted != 1) {
                Toast.makeText(this, R.string.editor_delete_product_failed,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.editor_delete_product_successful,
                        Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id == DETAIL_PRODUCT) {
            return new CursorLoader(this,
                    mCurrentProductUri,
                    null,
                    null,
                    null,
                    null
            );
        }
        else{
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_PRICE);
            final int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE);

            productName = cursor.getString(productNameColumnIndex);
            price = cursor.getInt(priceColumnIndex);
            quantity = cursor.getInt(quantityColumnIndex);
            supplierName = cursor.getString(supplierNameColumnIndex);
            final String finalSupplierPhone = cursor.getString(supplierPhoneColumnIndex);
            supplierPhone = finalSupplierPhone;

            mProductNameText.setText(productName);
            mPriceText.setText("Price: Rs."+Integer.toString(price));
            mQuantityText.setText("Quantity: "+Integer.toString(quantity));
            mSupplierNameText.setText("Supplier: "+supplierName);

            mSupplierPhoneBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent contactIntent = new Intent(Intent.ACTION_DIAL);
                    contactIntent.setData(Uri.parse("tel:"+finalSupplierPhone));
                    startActivity(contactIntent);
                }
            });

            mEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailActivity.this, EditorActivity.class);
                    intent.setData(mCurrentProductUri);
                    startActivity(intent);
                }
            });

            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDeleteConfirmationDialog();
                }
            });

            mIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String magnitudeString = mMagnitude.getText().toString().trim();
                    int magnitude = 1;
                    if(!magnitudeString.equals("")) {
                        magnitude = Integer.parseInt(magnitudeString);
                    }
                    quantity += magnitude;
                    mQuantityText.setText(Integer.toString(quantity));
                    saveProduct();
                }
            });

            mDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String magnitudeString = mMagnitude.getText().toString().trim();
                    int magnitude = 1;
                    if(!magnitudeString.equals("")) {
                        magnitude = Integer.parseInt(magnitudeString);
                    }
                    if(quantity-magnitude >= 0) {
                        quantity -= magnitude;
                        mQuantityText.setText(Integer.toString(quantity));
                        saveProduct();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.negative_quantity_msg,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mProductNameText.setText("");
        mPriceText.setText("");
        mQuantityText.setText("");
        mSupplierNameText.setText("");
        mSupplierPhoneBtn.setOnClickListener(null);
        mEdit.setOnClickListener(null);
        mDelete.setOnClickListener(null);
    }
}
