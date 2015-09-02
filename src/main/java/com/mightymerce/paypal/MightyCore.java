package com.mightymerce.paypal;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * Created by agutheil on 11.05.15.
 */
public class MightyCore extends AbstractOAuth2ApiBinding {
    private String coreUrl;

    protected MightyCore() {
        super();
    }

    protected MightyCore(String accessToken) {
        super(accessToken);
    }

    protected MightyCore(String accessToken, TokenStrategy tokenStrategy) {
        super(accessToken, tokenStrategy);
    }

    protected MightyCore(String accessToken, TokenStrategy tokenStrategy, String coreUrl) {
        super(accessToken, tokenStrategy);
        this.coreUrl = coreUrl;
    }

    public void createOrder(String articleId, String payerId, String txId, String paymentStatus) {
        Order order = new Order();
        order.setTest(String.format("ArticleID: %s; PayerId: %s; TransactionID: %s", articleId, payerId, txId));
        order.setTest2(String.format("PaymentStatus: %s", paymentStatus));
        getRestTemplate().postForObject(coreUrl+"/api/socialOrders/",order,Order.class);
    }
}
