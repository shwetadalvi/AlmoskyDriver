package com.almosky.driver.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.almosky.driver.BaseActivity;
import com.almosky.driver.R;
import com.almosky.driver.contracts.MyNewOrderContracts;
import com.almosky.driver.databinding.ActivityUnpayedOrderBinding;
import com.almosky.driver.models.Address;
import com.almosky.driver.room.AppDatabase;
import com.almosky.driver.ui.adapter.AddressAdapter;
import com.almosky.driver.utils.PrefUtils;

import java.util.List;

import static com.almosky.driver.utils.Constants.DEFAULT_PREFERENCE;
import static com.almosky.driver.utils.Constants.LOGIN_PREFERENCE_DRVER_ID;

public class UnpayedOrderActivity extends BaseActivity implements MyNewOrderContracts.IMyNewOrderView {

    private ActivityUnpayedOrderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_unpayed_order);
        showProgress("Fetching Data");
        mBinding.backButton.setOnClickListener(view->onBackPressed());
        AppDatabase database = AppDatabase.getInstance(this);
        LiveData<List<Address>> newOrderAddressList = database.mAddressDao().getAddressesWithoutPayment();
        newOrderAddressList.observe(this, this::setUpNewOrderAddressRecycler);
    }

    private void setUpNewOrderAddressRecycler(List<Address> addresses) {
        if(addresses.size()==0){
            mBinding.emptyOrder.setVisibility(View.VISIBLE);
        }else {
            AddressAdapter addressAdapter = new AddressAdapter(addresses, this,this);
            mBinding.customerAddress.setAdapter(addressAdapter);
        }
        hideProgress();
    }

    @Override public void onClickedAddress(Address address) {
        int driverId = Integer.parseInt(new PrefUtils(this).getStringPreference(DEFAULT_PREFERENCE, LOGIN_PREFERENCE_DRVER_ID));
        PaymentStatusDialog newFragment = PaymentStatusDialog.newInstance(null, Integer.parseInt(address.getOrderId()), driverId);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }
}