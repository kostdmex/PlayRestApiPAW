package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Header;
import com.auth0.jwt.interfaces.Payload;
import com.typesafe.config.Config;
import models.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import play.Logger;
import play.libs.F;
import repository.UserFinder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.UnsupportedEncodingException;

@Singleton
public class JwtValidatorImpl implements JwtValidator {
    private String secret;
    private JWTVerifier verifier;

    public JWTVerifier getVerifier() {
        return verifier;
    }

    @Inject
    public JwtValidatorImpl(Config config) throws UnsupportedEncodingException {
        this.secret = config.getString("play.http.secret.key");
        Algorithm algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm)
                .build();
    }

    @Override
    public F.Either<Error, VerifiedJwt> verify(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            JWTDecoder jwtDecoder = new JWTDecoder(jwt.getToken());
            User userToVerify = UserFinder.findByLogin(jwtDecoder.getPayload().getIssuer());
            if(userToVerify == null){
                return F.Either.Left(Error.ERR_INVALID_SIGNATURE_OR_CLAIM);
            }

            VerifiedJwtImpl verifiedJwt = new VerifiedJwtImpl(jwt);
            return F.Either.Right(verifiedJwt);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            Logger.error("f=JwtValidatorImpl, event=verify, exception=JWTVerificationException, msg={}",
                    exception.getMessage());
            return F.Either.Left(Error.ERR_INVALID_SIGNATURE_OR_CLAIM);
        }
    }
}
