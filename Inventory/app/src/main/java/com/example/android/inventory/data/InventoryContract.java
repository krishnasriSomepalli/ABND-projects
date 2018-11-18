package com.example.android.inventory.data;

import android.provider.BaseColumns;

public final class InventoryContract {
    public static abstract class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String _ID = "_id";
        public static final String COLUMN_PRODUCT_NAME = "ProductName";
        public static final String COLUMN_PRODUCT_PRICE = "Price";
        public static final String COLUMN_PRODUCT_QUANTITY = "Quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "SupplierName";
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE = "SupplierPhoneNumber";
    }
}
