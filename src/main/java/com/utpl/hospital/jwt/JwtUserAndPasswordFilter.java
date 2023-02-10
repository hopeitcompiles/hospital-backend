package com.utpl.hospital.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utpl.hospital.authentication.ApplicationRole;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

// add the filter to validate credentials and send the jwt 
public class JwtUserAndPasswordFilter extends UsernamePasswordAuthenticationFilter{

	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	private final AuthenticationManager authenticationManager;
	
//	we don't @Autowired this, so we give this as a parameter from SecureConfig
	public JwtUserAndPasswordFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
		this.authenticationManager=authenticationManager;
		this.jwtConfig=jwtConfig;
		this.secretKey=secretKey;
	}
	
//	override the attempt auth method, so we use jwt instead 
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		try {
//			map the user and password from the http request to the UserPasswordAuthRequest object
			UserPasswordAuthRequest authRequest=
					new ObjectMapper().readValue(request.getInputStream(),UserPasswordAuthRequest.class);

//			use the user and password from the http request, 
			Authentication authentication=new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(),//this is the principal 
					authRequest.getPassword());//the password
//			we send the authentication object, the manager will check the username and the password
//			with the authentication provider in the SecurityConfig
			System.out.println(authRequest.getUsername()+" "+authRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;//we send the Authentication object
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.addHeader(jwtConfig.getAuthorizationHeader(),"Denied");
	}

	//	this method receives the FilterChain, we must pass the filter
//	we generate the token here and send it to the client
//	this method is executed after the attemptAuthentication, only if it is successful
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("Login success");
		String token=Jwts.builder()
			.setSubject(authResult.getName())//set the subject, use the username
			.claim("authorities", authResult.getAuthorities()!=null? authResult.getAuthorities(): ApplicationRole.PATIENT.getGrantedAuthorities())//set the body, set the authorities
			.setIssuedAt(new Date())
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))//when the token expires
			.signWith(secretKey)//the key must be really long
			.compact();
		response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix()+" "+token);//add the token to the header in the http response
//		ObjectMapper Obj = new ObjectMapper();
//		User user=((ApplicationUser)authResult.getPrincipal()).getUser();
//		user.setCreated(null);
//		response.getWriter().write(Obj.writeValueAsString(authResult));
	}

	
}
