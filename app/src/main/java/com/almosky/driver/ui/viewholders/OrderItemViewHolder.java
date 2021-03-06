package com.almosky.driver.ui.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.almosky.driver.R;
import com.almosky.driver.contracts.MyNewOrderContracts;
import com.almosky.driver.databinding.ItemsLayoutBinding;
import com.almosky.driver.models.OrderItems;

import java.util.Locale;

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    ItemsLayoutBinding mItemsLayoutBinding;
    private MyNewOrderContracts.IMyNewOrderView mMyNewOrderView;
    TextView itemName, priceTv, qty;

    public OrderItemViewHolder(View binding) {
        super(binding);
        itemName = binding.findViewById(R.id.itemName);
        priceTv = binding.findViewById(R.id.price);
        qty = binding.findViewById(R.id.qty);
    }

    public void bind(OrderItems orderItem) {
        itemName.setText(orderItem.getItemName());
        double price = orderItem.getQuantity() * orderItem.getPrice();
        priceTv.setText(String.format(Locale.US, "AED %.2f", price));
        qty.setText(String.format(Locale.US, "%d", orderItem.getQuantity()));
    }
}