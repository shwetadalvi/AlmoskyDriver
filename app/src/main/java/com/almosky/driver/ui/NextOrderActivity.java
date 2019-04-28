package com.almosky.driver.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.almosky.driver.Almoski;
import com.almosky.driver.BaseActivity;
import com.almosky.driver.LauncherActivity;
import com.almosky.driver.R;
import com.almosky.driver.databinding.ActivityNextOrderBinding;
import com.almosky.driver.models.Address;
import com.almosky.driver.models.BaseError;
import com.almosky.driver.models.Item;
import com.almosky.driver.models.NewOrderItem;
import com.almosky.driver.models.Order;
import com.almosky.driver.models.Response;
import com.almosky.driver.models.Service;
import com.almosky.driver.network.API;
import com.almosky.driver.network.RetrofitClient;
import com.almosky.driver.room.AppDatabase;
import com.almosky.driver.utils.PrefUtils;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.print.PrnAlignTypeEnum;
import com.zcs.sdk.print.PrnFontSizeTypeEnum;
import com.zcs.sdk.print.PrnSpeedTypeEnum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import vpos.apipackage.PosApiHelper;

import static com.almosky.driver.utils.Constants.COMPANY_ADDRESS1;
import static com.almosky.driver.utils.Constants.COMPANY_ADDRESS2;
import static com.almosky.driver.utils.Constants.COMPANY_ADDRESS3;
import static com.almosky.driver.utils.Constants.COMPANY_BILL_GREETING1;
import static com.almosky.driver.utils.Constants.COMPANY_BILL_GREETING2;
import static com.almosky.driver.utils.Constants.COMPANY_DATE;
import static com.almosky.driver.utils.Constants.COMPANY_GROSS;
import static com.almosky.driver.utils.Constants.COMPANY_ITEM_AMOUNT;
import static com.almosky.driver.utils.Constants.COMPANY_ITEM_DESCRIPTION;
import static com.almosky.driver.utils.Constants.COMPANY_ITEM_QUANTITY;
import static com.almosky.driver.utils.Constants.COMPANY_ITEM_TOTAL;
import static com.almosky.driver.utils.Constants.COMPANY_NAME;
import static com.almosky.driver.utils.Constants.COMPANY_ORDER_NO;
import static com.almosky.driver.utils.Constants.COMPANY_TAX;
import static com.almosky.driver.utils.Constants.COMPANY_TELE;
import static com.almosky.driver.utils.Constants.COMPANY_TELE2;
import static com.almosky.driver.utils.Constants.COMPANY_TRN;
import static com.almosky.driver.utils.Constants.COMPANY_VAT;
import static com.almosky.driver.utils.Constants.DEFAULT_PREFERENCE;
import static com.almosky.driver.utils.Constants.LOGIN_PREFERENCE;
import static com.almosky.driver.utils.Constants.LOGIN_PREFERENCE_DRVER_ID;
import static com.almosky.driver.utils.Constants.getDate;
import static com.almosky.driver.utils.Constants.getTime;

public class NextOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private final PosApiHelper posApiHelper = PosApiHelper.getInstance();
    private int ret;
    private ActivityNextOrderBinding mBinding;
    private PrefUtils mPrefUtils;
  private DriverManager mDriverManager;
    private Printer mPrinter;
    public static final String PRINT_TEXT = "Shwetasgdfjgjhhkjhlkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
            "dgfhggggggggggggggggggggggggggggggggg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Almoski.getInst().setIronList(null);
        Almoski.getInst().setWashList(null);
        Almoski.getInst().setDrycleanList(null);*/
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_next_order);
        mBinding.goToNextOrder.setOnClickListener(this);
        mBinding.myOrders.setOnClickListener(this);
        mBinding.logout.setOnClickListener(this);
        mBinding.myOrders2.setOnClickListener(this);
        mBinding.settings.setOnClickListener(this);
        mPrefUtils = new PrefUtils(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                String driverId = new PrefUtils(this).getStringPreference(DEFAULT_PREFERENCE, LOGIN_PREFERENCE_DRVER_ID);
            logoutApi(driverId);
                mPrefUtils.putBooleanPreference(DEFAULT_PREFERENCE, LOGIN_PREFERENCE, false);
                startActivity(new Intent(this, LauncherActivity.class));
                finish();
                break;
            case R.id.goToNextOrder:

                AppDatabase appDatabase = AppDatabase.getInstance(this);
                LiveData<Address> liveData = appDatabase.mAddressDao().getLatestAddress();
                liveData.observe(this, address -> {
                    try {
//
                        if (address != null) {
                            startActivity(new Intent(this, OrderActiivity.class).putExtra("orderId", address.getOrderId()));
                        } else {
                          //  mBinding.emptyOrder.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.there_are_new_orders_for_you_right_now), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                       // mBinding.emptyOrder.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.there_are_new_orders_for_you_right_now), Toast.LENGTH_SHORT).show();

                    }

                });
                break;
            case R.id.myOrders:
          startActivity(new Intent(this, MyNewOrders.class));
            //  print();

                break;
            case R.id.myOrders2:
                startActivity(new Intent(this, UnpayedOrderActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
    }

    private void print() {

        mPrinter = DriverManager.getInstance().getPrinter();

        mPrinter.setPrintSpeed(PrnSpeedTypeEnum.HIGH_SPEED);
        mPrinter.setPrintFontSize(PrnFontSizeTypeEnum.DEFAULT_SIZE);
        mPrinter.setPrintAlign(PrnAlignTypeEnum.ALIGN_LEFT);
        mPrinter.setPrintString(PRINT_TEXT.getBytes());
        mPrinter.setPrintStart();
    }


    private void logoutApi(String driverId) {
        RequestBody RequestBodyId = RequestBody.create(MediaType.parse("multipart/form-data"), driverId);
              API apiList = RetrofitClient.getClient ().create (API.class);
        Call<Response> call = apiList.driverLogout(RequestBodyId);
        call.enqueue (new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code()==200) {
                    Response result = response.body();
                  //  mNetworkResponses.success(result);
                }else {
                    Throwable throwable = new Throwable("Logout Failed");
                    BaseError baseError = new BaseError(throwable);
                    //mNetworkResponses.failed(baseError);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                BaseError baseError = new BaseError(t);
              //  mNetworkResponses.error(baseError);
            }
        });
    }
}
