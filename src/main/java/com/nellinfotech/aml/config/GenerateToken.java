package com.nellinfotech.aml.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.model.DecodedTokenData;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GenerateToken {
    public static DecodedTokenData generateToken(String signingKey, UserMst user) {
        String token = "";
        DecodedTokenData tokenData = new DecodedTokenData();
        try {
            
            String userid = "" + user.getId();
            String sessionId = UUID.randomUUID().toString();
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("userid", userid);
            claims.put("sessionid", sessionId);
           // claims.put("usr", user.getUserName());
            claims.put("sub", "Authentication token");
            claims.put("iss", Iconstants.ISSUER);
            claims.put("role", "PGM, PGV");
            claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            /* long t1 = new Date().getTime(); */
            Date exptime = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
            claims.put("exptime", exptime);
            // To generate token base upon all combination of claims parameters
            token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY)
                    .compact();
            
            tokenData.setAccessToken(token);
            tokenData.setSessionid(sessionId);
            
        } catch (Exception e) {
            
        }
        return tokenData;
    }
    
    public static DecodedTokenData decodeToken(String AccessToken) {
        DecodedTokenData decodedTokenData = new DecodedTokenData();
        String decodedToken = "";
        try {
            //get the claim parameters from token 
            Claims decodeddata = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Iconstants.SECRET_KEY))
                    .parseClaimsJws(AccessToken).getBody();
            decodedToken = decodeddata.toString();
            List<String> result = Arrays.asList(decodedToken.split("\\s*,\\s*"));
            //split the token to get different parameters from token(e.g userId,sessionID)
            String[] sessionidsplit = result.get(5).split("=");
            String[] useridsplit = result.get(6).split("=");
            String sessionid = sessionidsplit[1];
            int userID = Integer.parseInt(useridsplit[1]);
            decodedTokenData.setUserid(userID);
            decodedTokenData.setSessionid(sessionid);
            System.out.println(userID);
        } catch (Exception e) {
            
        }
        return decodedTokenData;
    }
    
}
