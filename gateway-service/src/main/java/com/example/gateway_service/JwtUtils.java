// package com.example.gateway_service;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import javax.crypto.SecretKey;

// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;

// import io.jsonwebtoken.security.Keys;

// @Service
// public class JwtUtils {

// 	private static final String SECRET_KEY = "kuhvlaeubljabvliheilvgbvlaijesbgvaievbaiwjacopsocps";

// 	public SecretKey generateKey() {
//     return Keys.hmacShaKeyFor(
//             SECRET_KEY.getBytes());
// }

// 	public String generateToken(Long userId,String email,String role) {
// 		Map<String, Object> claims = new HashMap<>();
// 		claims.put("userId", userId);
// 		claims.put("role", role);
// 		return Jwts.builder()
// 				.claims(claims)
// 				.subject(email)
// 				.issuer("Omkar")
// 				.issuedAt(new Date(System.currentTimeMillis()))
// 				.expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 10))
// 				.signWith(generateKey())
// 				.compact();
// 	}

// 	public String extractUsername(String token) {
// 		return extractClaim(token,Claims::getSubject);
// 	}

// 	public Long extractUserId(String token) {
// 		return extractAllClaims(token).get("userId", Long.class);
// 	}

// 	public String extractRole(String token) {
// 		return extractAllClaims(token).get("role", String.class);
// 	}

// 	public Date extractExpiration(String token) {
// 		return extractClaim(token,Claims::getExpiration);
// 	}

// 	public <T> T extractClaim(String token,Function<Claims, T> resolver) {
// 		Claims claims = extractAllClaims(token);
// 		return resolver.apply(claims);
// 	}

// 	public Claims extractAllClaims(String token) {
// 		return Jwts.parser()
// 				.verifyWith(generateKey())
// 				.build()
// 				.parseSignedClaims(token)
// 				.getPayload();
// 	}

// 	public boolean isTokenExpired(String token) {
// 		return extractExpiration(token)
// 				.before(new Date());
// 	}

// 	public boolean validateToken(String token) {
//     try {
//         extractAllClaims(token);
//         return !isTokenExpired(token);
//     } catch (Exception e) {
//         e.printStackTrace();
//         return false;
//     }
// }
// }