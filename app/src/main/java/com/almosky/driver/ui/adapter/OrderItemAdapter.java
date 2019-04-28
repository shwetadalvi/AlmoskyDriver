package com.almosky.driver.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.almosky.driver.R;
import com.almosky.driver.models.Item;
import com.almosky.driver.models.Service;
import com.almosky.driver.ui.viewholders.OrderItemViewHolder;
import com.almosky.driver.ui.viewholders.ServiceHeaderViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Service> mServiceList;
    String currentService;

    public OrderItemAdapter(List<Service> addressList) {
        mServiceList = addressList;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new OrderItemViewHolder(layoutInflater.inflate(R.layout.items_layout, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        currentService = mServiceList.get(position).getServiceName();
        OrderItemViewHolder footerHolder = (OrderItemViewHolder) holder;
        List<Item> listItem = mServiceList.get(position).getItems();
        for (int j = 0; j < listItem.size(); j++) {
            footerHolder.bind(listItem.get(j).getOrderItems());
        }

    }

    @Override public int getItemCount()
    {
        return mServiceList.size();
    }
}
