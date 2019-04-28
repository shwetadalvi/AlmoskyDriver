package com.almosky.driver;

import com.almosky.driver.models.BaseError;

public interface NetworkResponses<T> {
    void success(T response);
    void failed(T failedResonse);
    void error(BaseError baseError);
}
