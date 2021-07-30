package com.otsi.rpd.authservice.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AwsCognitoJwtAuthFilter  extends GenericFilter{
	 private static final Log logger = LogFactory.getLog(AwsCognitoJwtAuthFilter.class);
	    private AwsCognitoTokenProcessor cognitoTokenProcessor;

	    public AwsCognitoJwtAuthFilter(AwsCognitoTokenProcessor cognitoTokenProcessor) {
	        this.cognitoTokenProcessor = cognitoTokenProcessor;
	    }

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			  Authentication authentication;

//		        try {
//		            authentication = cognitoTokenProcessor.authenticate((HttpServletRequest)request);
//		            if (authentication != null) {
//		                SecurityContextHolder.getContext().setAuthentication(authentication);
//		            }
//		        } catch (Exception ex) {
//		            logger.error("Cognito ID Token processing error", ex);
//		         //   return ex.getMessage();
//		            SecurityContextHolder.clearContext();
//		           
//		        }

		        chain.doFilter(request, response);
		    }
			
		}

