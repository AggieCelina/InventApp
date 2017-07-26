package com.ac.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ac.inventoryapp.data.ItemContract;

/**
 * Created by marka1 on 7/25/17.
 */

public class ItemCursorAdapter extends CursorAdapter {
    private OnItemAction onItemAction;

    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        Button saleButton = (Button) view.findViewById(R.id.sale);
        ViewGroup row = (ViewGroup) view.findViewById(R.id.row);


        int idColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_PRICE);
        int imageColumnIndex = cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ITEM_IMAGE);

        final String itemName = cursor.getString(nameColumnIndex);
        final String itemQuantity = cursor.getString(quantityColumnIndex);
        final String itemPrice = cursor.getString(priceColumnIndex);
        final int imageId = cursor.getInt(imageColumnIndex);
        final int id = cursor.getInt(idColumnIndex);

        nameTextView.setText(itemName);
        quantityTextView.setText(itemQuantity);
        priceTextView.setText(itemPrice);
        imageView.setImageResource(imageId);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemAction != null) {
                    onItemAction.onItemClicked(id);
                }
            }
        });

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemAction != null) {
                    onItemAction.onSaleButtonClicked(id, itemQuantity);
                }
            }
        });
    }

    public void setOnItemAction(OnItemAction onItemAction) {
        this.onItemAction = onItemAction;
    }

    public interface OnItemAction {
        void onSaleButtonClicked(int id, String itemQuantity);

        void onItemClicked(int id);
    }
}
