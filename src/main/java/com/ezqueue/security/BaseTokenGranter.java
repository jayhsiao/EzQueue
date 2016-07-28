//package com.ezqueue.security;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.TokenRequest;
//import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.asiaplay.sun.account.service.AccountActivityService;
//import com.asiaplay.sun.sms.model.Account;
//import com.asiaplay.sun.sms.model.AccountActivity;
//import com.asiaplay.sun.sms.model.AccountActivity.ACTION;
//
//public abstract class BaseTokenGranter extends AbstractTokenGranter {
//	
//	private static final Logger LOG = LoggerFactory.getLogger(BaseTokenGranter.class);
//
//    public static final String ACTIVITY_VERSION = "1.0";
//    
//    @Autowired
//    private AccountActivityService activityService;
//
//	protected BaseTokenGranter(AuthorizationServerTokenServices tokenServices,
//			ClientDetailsService clientDetailsService,
//			OAuth2RequestFactory requestFactory, String grantType) {
//		super(tokenServices, clientDetailsService, requestFactory, grantType);
//	}
//	
//	public AccountActivityService getActivityService(){
//		return this.activityService;
//	}
//	
//	protected abstract OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest);
//	
//	protected void createAccountActivity(ClientDetails client, Account account) {
//	    try {
//            ServletRequestAttributes reqAttributes = ((ServletRequestAttributes) RequestContextHolder
//                    .currentRequestAttributes());
//            HttpServletRequest request = reqAttributes.getRequest();
//            
//            AccountActivity activity = new AccountActivity();
//            activity.setAction(ACTION.login);
//            activity.setApplicationId(client.getClientId());
//            activity.setRemoteAddr(reqAttributes.getRequest().getRemoteAddr());
//            
//            String referrer = request.getHeader("referer");
//            if(StringUtils.isNotEmpty(referrer)) {
//                activity.setHttpReferrerUrl(referrer);
//            }
//            
//            activity.setVersion(ACTIVITY_VERSION);
//            BeanUtils.copyProperties(account, activity);
//            activityService.createAccountActivity(activity);
//        } catch (Exception ex) {
//            LOG.warn("create account activity for user {} failed", account.getAccountId(), ex);
//        }
//    }
//	
//	protected String getRemoteAddr() {
//        ServletRequestAttributes reqAttributes = ((ServletRequestAttributes) RequestContextHolder
//                .currentRequestAttributes());
//
//        return reqAttributes.getRequest().getRemoteAddr();
//    }
//}
