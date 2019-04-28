package com.almosky.driver.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.almosky.driver.contracts.MyNewOrderContracts;
import com.almosky.driver.databinding.AddressLayoutItemBinding;
import com.almosky.driver.models.Address;
import com.almosky.driver.ui.MyNewOrders;
import com.almosky.driver.ui.UnpayedOrderActivity;
import com.almosky.driver.ui.viewholders.AddressLayoutViewHolder;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressLayoutViewHolder> {
    List<Address> mAddressList;
    private MyNewOrderContracts.IMyNewOrderView mMyNewOrderView;
    private AppCompatActivity mActivity;

    public AddressAdapter(List<Address> addressList, MyNewOrderContracts.IMyNewOrderView myNewOrderView, AppCompatActivity activity) {
        mAddressList = addressList;
        mMyNewOrderView = myNewOrderView;
        mActivity = activity;
    }

    @NonNull @Override public AddressLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AddressLayoutItemBinding itemBinding = AddressLayoutItemBinding.inflate(layoutInflater, parent, false);
        return new AddressLayoutViewHolder(itemBinding, mMyNewOrderView);
    }

    @Override public void onBindViewHolder(@NonNull AddressLayoutViewHolder holder, int position) {
        if (mActivity instanceof MyNewOrders) {
            Address address = mAddressList.get(position);
            if (address.getDeliveryStatus() != 2 || address.getDeliveryStatus() != 4) {
                holder.bindAddress(mAddressList.get(position));
            }
        }else {
            Address address = mAddressList.get(position);
            holder.bindAddress(address);
        }
    }

    @Override public int getItemCount() {
        return mAddressList.size();
    }
}
