//package com.ezqueue.security;
//
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Request;
//import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.TokenRequest;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//
//public class EzQueueDeviceValidateTokenGranter extends BaseTokenGranter {
//
//    private static final String GRANT_TYPE = "device_token";
//    
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    protected EzQueueDeviceValidateTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
//        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
//    }
//
//    @Override
//    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
//        Map<String, String> parameters = tokenRequest.getRequestParameters();
//        String deviceCode = parameters.get("device_code");
//        try {
//        	String accountId = accountService.validateDeviceCodeToken(deviceCode);
//            Account account = accountService.getAccount(accountId);
//            createAccountActivity(client, account);
//            Authentication userAuth = new UsernamePasswordAuthenticationToken(new SunUserDetails(account), null,
//                    ImmutableList.of(new SimpleGrantedAuthority(account.getAccountStatus().name())));
//            OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
//            return new OAuth2Authentication(storedOAuth2Request, userAuth);
//        } catch (SunException ex) {
//            LOG.error("login device with device code token {} failed.", ex);
//            throw ex;
//        }
//    }
//}
