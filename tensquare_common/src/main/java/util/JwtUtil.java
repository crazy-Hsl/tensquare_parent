package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author crazy
 * @create 2021-04-24 11:33
 */
@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {

    private String key;
    private long ttl;//一个小时

    public JwtUtil() {
    }

    public JwtUtil(String key, long ttl) {
        this.key = key;
        this.ttl = ttl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "JwtUtil{" +
                "key='" + key + '\'' +
                ", ttl=" + ttl +
                '}';
    }


    public String createJWT(String id, String subject, String roles) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(date).signWith(SignatureAlgorithm.ES256, key).claim("roles", roles);
        if (ttl > 0) {
            jwtBuilder.setExpiration(new Date(millis + ttl));
        }
        return jwtBuilder.compact();

    }

    public Claims parseJWT(String jwtStr) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }
}

