package com.graduation.project.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtTokenUtil 工具类
 * </p>
 * @author leejianhao
 *
 */
public class JwtTokenUtil {

    static final String CLAIM_KEY_SUB = "sub";

    static final String CLAIM_KEY_USER_ID = "userId";

    static final String CLAIM_KEY_AUDIENCE = "audience";

    static final String CLAIM_KEY_CREATED = "created";

    static final String CLAIM_KEY_EXPIRED = "exp";

    /**
     * Jwt salt
     */
    @Value(value = "${jwt.secret}")
    public String secret;

    /**
     * Jwt token expire
     */
    @Value(value = "${jwt.expire}")
    public long expire;

    /**
     * Token prefixs
     */
    @Value(value = "${jwt.token_prefix}")
    private String tokenPrefix;

    /**
     * 根据token获取用户ID
     * @param token
     * @return
     */
    public String getUserIDFromToken(String token) {
        String userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = (String) claims.get(CLAIM_KEY_USER_ID);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    /**
     * 获取token创建时间
     * </p>
     * 此处claims属于 private claims
     * </p>
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获取token过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USER_ID, userId);
        Date expirationDate = new Date(System.currentTimeMillis() + expire);
        return doCreateToken(claims, expirationDate);
    }

    public boolean canRefreshToken(String token, Date lastPasswordUpdate) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLasterPasswordUpdate(created, lastPasswordUpdate)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

//	public String refreshToken(String token) {
//		String refreshedToken ;
//		try {
//            final Claims claims = getClaimsFromToken(token);
//            claims.put(CLAIM_KEY_CREATED, ToolUtil.getSystemTime());
//            Date expirationDate = new Date(ToolUtil.getSystemTime() + expire);
//            refreshedToken = doCreateToken(claims, expirationDate);
//        } catch (Exception e) {
//            refreshedToken = null;
//        }
//        return refreshedToken;
//	}

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        if (expiration == null) {
            return false;
        }
        return expiration.getTime() < System.currentTimeMillis();
    }

    private boolean isCreatedBeforeLasterPasswordUpdate(Date created, Date lastetPasswordUpdate) {
        return (lastetPasswordUpdate != null && created.before(lastetPasswordUpdate));
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    private String doCreateToken(Map<String, Object> claims, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token.replace(tokenPrefix, "")).getBody();
    }

}
