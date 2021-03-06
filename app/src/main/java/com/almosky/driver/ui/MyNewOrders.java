package com.almosky.driver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.almosky.driver.BaseActivity;
import com.almosky.driver.R;
import com.almosky.driver.contracts.MyNewOrderContracts;
import com.almosky.driver.databinding.ActivityMyNewOrdersBinding;
import com.almosky.driver.models.Address;
import com.almosky.driver.room.AppDatabase;
import com.almosky.driver.ui.adapter.AddressAdapter;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

public class MyNewOrders extends BaseActivity implements MyNewOrderContracts.IMyNewOrderView {

    private ActivityMyNewOrdersBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_new_orders);
        showProgress("");


        AppDatabase database = AppDatabase.getInstance(this);
        LiveData<List<Address>> newOrderAddressList = database.mAddressDao().getNewAddresses();
        newOrderAddressList.observe(this, this::setUpNewOrderAddressRecycler);
        mBinding.backButton.setOnClickListener(view-> onBackPressed());
    }

    private void setUpNewOrderAddressRecycler(List<Address> addresses) {
        if(addresses.size()==0){
            mBinding.emptyOrder.setVisibility(View.VISIBLE);
        }else {
            AddressAdapter addressAdapter = new AddressAdapter(addresses, this,this);
            mBinding.myNewOrderAddresses.setAdapter(addressAdapter);
        }
        hideProgress();
    }

    @Override public void onClickedAddress(Address address) {
        startActivity(new Intent(this,OrderActiivity.class).putExtra("orderId",address.getOrderId()));
    }

}
