package com.almosky.driver.interactors;

import com.almosky.driver.NetworkResponses;
import com.almosky.driver.contracts.LoginContracts;
import com.almosky.driver.models.BaseError;
import com.almosky.driver.models.Response;
import com.almosky.driver.network.API;
import com.almosky.driver.network.RetrofitClient;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginInteracterImpl implements LoginContracts.ILoginInteracter {
    private NetworkResponses<Response> mNetworkResponses;

    public LoginInteracterImpl(NetworkResponses<Response> networkResponses) {
        mNetworkResponses = networkResponses;
    }

    @Override public void doLogin(String token,String pin) {
        RequestBody RequestBodyPin = RequestBody.create(MediaType.parse("multipart/form-data"), pin);
        RequestBody RequestBodyToken = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        API apiList = RetrofitClient.getClient ().create (API.class);
        Call<Response> call = apiList.driverLogin(RequestBodyPin,RequestBodyToken);
        call.enqueue (new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code()==200) {
                  Response result = response.body();
                  mNetworkResponses.success(result);
                }else {
                    Throwable throwable = new Throwable("Login Failed");
                    BaseError baseError = new BaseError(throwable);
                    mNetworkResponses.failed(baseError);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                BaseError baseError = new BaseError(t);
                mNetworkResponses.error(baseError);
            }
        });
    }


}