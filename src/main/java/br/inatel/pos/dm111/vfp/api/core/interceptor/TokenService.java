package br.inatel.pos.dm111.vfp.api.core.interceptor;

import java.security.PublicKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

    private final PublicKey publicKey;

    public TokenService(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public Claims readToken(String token) {
        // Faz o parse e valida a assinatura
        Jws<Claims> jwsClaims = Jwts.parser()
                .verifyWith(publicKey)  // usa a chave pública para validar
                .build()
                .parseSignedClaims(token);

        // Retorna o corpo do JWT (claims)
        return jwsClaims.getPayload();
    }
}
