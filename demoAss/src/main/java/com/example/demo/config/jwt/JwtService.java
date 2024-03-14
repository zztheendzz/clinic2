package com.example.demo.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

	public String extractUserName(String token) {

		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}
	/*
	 * public String generateToken(String userName){ Map<String,Object> claims=new
	 * HashMap<>(); return generateToken(claims,userName); }
	 */

//xem token có đúng k
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

	public String generateToken(Map<String, Object> extractClaims, UserDetails userDeatils) {
		return Jwts.builder().setClaims(extractClaims).setSubject(userDeatils.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public String generateToken1( String name) {
		return Jwts.builder().setSubject(name)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*24*60))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	
	
	
	
	
	
//	public String extractUserName(String token) {
//		// TODO Auto-generated method stub
//		 return extractClaim(token, Claims::getSubject);
//
//	}
//	public String generateToken(CustomUserDetails userDetails) {
//		return generateToken(new HashMap<>(), userDetails);
//	}
//	
//	public String generateToken(
//			Map<String,Object>extraClaims,
//			CustomUserDetails userDetails)
//		
//	{
////		tạo jwt từ thông tin user
//		
//		Jwts
//		.builder()
//		.setClaims(extraClaims)
//		.setSubject(userDetails.getUsername())
//		.setIssuedAt(new Date(System.currentTimeMillis()))
//		.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
//		.signWith(getSignInkey(),SignatureAlgorithm.HS256)
////		.signWith(getSignInkey(), null)
//		.compact();	
//		return Jwts
//				.builder()
//				.setClaims(extraClaims)
//				.setSubject(userDetails.getUsername())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
//				.signWith(getSignInkey(),SignatureAlgorithm.HS256)
////				.signWith(getSignInkey(), null)
//				.compact();
//	}
////	 xem token có đúng k
//	public boolean isTokenValid(String token , CustomUserDetails userDetails) {
//		final String userName = extractUserName(token);
//		return (userName.equals(userDetails.getUsername()))&& !isTokenExpired(token);
//	}
//	private boolean isTokenExpired(String token) {
//		// TODO Auto-generated method stub
//		return extractExpiration(token).before(new Date());
//	}
//	private Date extractExpiration(String token) {
//		// TODO Auto-generated method stub
//		return extractClaim(token,Claims::getExpiration);
//	}
//	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		    final Claims claims = extractAllClaims(token);
//		    return claimsResolver.apply(claims);
//		  }
//	
//// lấy ra thông tin của user từ jwt
//	private Claims extractAllClaims(String token) {
//
//		
//		return Jwts
//				.parserBuilder()
//				.setSigningKey(getSignInkey())
//				.build()
//				.parseClaimsJws(token)
//				.getBody();
//		
//	}
//
//	private Key getSignInkey() {
//		// TODO Auto-generated method stub
//		byte[]keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//		return Keys.hmacShaKeyFor(keyBytes);
//		
//	}
	

}
