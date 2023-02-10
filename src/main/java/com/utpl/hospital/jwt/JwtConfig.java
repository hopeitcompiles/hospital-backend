package com.utpl.hospital.jwt;

import com.google.common.net.HttpHeaders;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@ConfigurationProperties(prefix="application.jwt")
public class JwtConfig {
	
	private String secretKey;
	private String tokenPrefix;
	private int	tokenExpirationAfterDays;
	
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public int getTokenExpirationAfterDays() {
		return tokenExpirationAfterDays;
	}
	public void setTokenExpirationAfterDays(int tokenExpirationAfterDays) {
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}

	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
}
