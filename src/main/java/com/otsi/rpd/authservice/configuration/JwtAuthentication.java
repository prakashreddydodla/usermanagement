package com.otsi.rpd.authservice.configuration;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


import com.nimbusds.jwt.JWTClaimsSet;

import sun.text.normalizer.ICUBinary.Authenticate;

public class JwtAuthentication extends AbstractAuthenticationToken  {

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Object principal;
    private JWTClaimsSet jwtClaimsSet;

    public JwtAuthentication(Object principal, JWTClaimsSet jwtClaimsSet, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.jwtClaimsSet = jwtClaimsSet;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public JWTClaimsSet getJwtClaimsSet() {
        return this.jwtClaimsSet;
    }

	
}
