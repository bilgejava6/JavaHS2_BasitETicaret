package com.muhammet.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {

    /**
     * Token oluştururken neler gerekli?
     * 1- SecretKey: token bilgisini şifrelemek(sign, imzalamak) için kullanacağımız gizli anahtar değer.
     * DİKKAT!!! şifre seçimi için önemli bilgilendirme
     * [][][][][][]
     * 10-10-10-10-10-10 -> kaç ihtimal var ? 1 milyon
     * -> CPU, 3.5 GHZ -> saniye de 3.500.000.000
     * rakam + harf + büyük harf + özel karakter => 10 + 30 + 30 + 10 => 80
     * 80-80-80-80-80-80 => 262.144.000.000 -> 100
     * 80^8 -> 1.677.721.600.000.000 -> 479_348 = 133saat
     * 2- Issuer: jwt token sahiplik bilgisi
     * 3- IssuerAt: sahibinin oluşturma zaman damgası
     * 4- ExpireAt: token için geçirlilik bitiş zamanı
     * 5- Claim Objects: bunlar key-value şelinde içerisinde bilgi barındıran nesnelerdır
     * herkese açık şekilde token içerisinde barındırılır. Özel verilerin buraya eklenmemesi
     * önemlidir.
     * 6- Sign: ilgili token ın imzalanması için gereklidir. Kriptolama algoritması belirlenir
     * ve buna göre şifreleme yapılır.
     */

    /**
     *  ÇOOK ÖNEMLİ!!!!!
     *  şifre, token, api key v.s. gibi önemli bilgiler kod içerisinde yazılmaz.
     */
    @Value("${my-jwt.secret-key}")
    private String secretKey;
    private String issuer = "MuhammetHOCA";
    private Long expirationDate = 1000L * 60 * 60 * 5;
    public String createToken(Long userId){
        String token="";
        Long now = System.currentTimeMillis(); // şuan ki zamanını long olarak değerini verir
        Date issuerAt = new Date(now);
        Date expiration = new Date(now + expirationDate);
        Algorithm algorithm = Algorithm.HMAC512(secretKey); // şifreleme algoritması.
        token = JWT.create()
                .withAudience()
                .withIssuer(issuer)
                .withIssuedAt(issuerAt)
                .withExpiresAt(expiration)
                .withClaim("userId", userId)
                .withClaim("ETicaret", "Yeni bir uygulama yazdık")
                .withClaim("log", "şuan saat tam olara "+ (new Date()))
                .sign(algorithm);
        return token;
    }


    public Optional<Long> validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier =  JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token); // aslında hem token bize mi ait hemde süresi doldu mu?
            if(Objects.isNull(decodedJWT)) // eğer decotedjwt boş ise
                return Optional.empty();
            Long userId = decodedJWT.getClaim("userId").asLong(); // ilgili claim nesnesini long olarak al
            return Optional.of(userId); // değeri optional olarak döndür.
        }catch (Exception exception){
            return Optional.empty();
        }
    }

}
