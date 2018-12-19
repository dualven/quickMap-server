package org.quickmap.apiservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.quickmap.apiservice.service.ITokenService;
import org.quickmap.dataService.dao.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

@Service
public class TokenServiceImpl implements ITokenService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${token.expireSeconds}")
    private Integer expireSeconds;

    @Value("${token.secret}")
    private String secret;

    @Override
    public String generateToken(Map<String,Object>claims) {
        Assert.notNull(claims, "参数不能为空");
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireSeconds * 1000);
        return Jwts.builder().setClaims(claims).setIssuedAt(nowDate).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    @Override
    public UserInfo getUserByToken(String token) {
        Assert.hasText(token, "参数不能为空");
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            Date expiration = claims.getExpiration();
            Date now = new Date();
            if (now.getTime() > expiration.getTime()) {
                throw new CredentialsExpiredException("该账号已经过期,请重新登陆");
            }
            return new JSONObject(claims).toJavaObject(UserInfo.class);
        } catch (Exception e) {
            logger.debug("token解密失败", e);
            return null;
        }
    }

}
