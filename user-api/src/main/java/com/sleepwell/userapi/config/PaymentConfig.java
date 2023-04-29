package com.sleepwell.userapi.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    private final String apiKey = "4175370216044566";

    private final String apiSecret = "aLovTz0AuOhId4L0MXNBEV4rMYa7n044fRJRmFkSzk5uUrpS9qIzdTHw2Hbe2FU3a9CA5VAOOvaEuDFJ";

    public IamportClient iamportClient() {
        return new IamportClient(apiKey, apiSecret);
    }
}
