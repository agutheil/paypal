package com.mightymerce.paypal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by agutheil on 11.05.15.
 */
@Configuration
public class MyConfig {

	 @Value("${mightymerce.coreUrl}")
	private String coreUrl;

    @Bean
    public OAuth2Template oAuth2Template(){
        OAuth2Template oAuth2Template = new OAuth2Template("coreapp","mySecretOAuthSecret",coreUrl+"/oauth/authorize", coreUrl+"/oauth/authenticate", coreUrl+"/oauth/token");
        oAuth2Template.setUseParametersForClientAuthentication(false);
        return oAuth2Template;
    }
}
