package com.almosky.driver.ui.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.almosky.driver.R;
import com.almosky.driver.contracts.MyNewOrderContracts;
import com.almosky.driver.databinding.AddressLayoutItemBinding;
import com.almosky.driver.models.Address;

public class ServiceHeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView mHeader;

    public ServiceHeaderViewHolder(View view) {
        super(view);

    }
    public void bind(String header){
        mHeader = itemView.findViewById(R.id.service);
      mHeader.setText(header);
    }
}
