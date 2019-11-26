package jwt;

import play.Logger;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Function;

import static play.mvc.Results.forbidden;

public class JwtControllerHelperImpl implements JwtControllerHelper {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    private static final String ERR_AUTHORIZATION_HEADER = "ERR_AUTHORIZATION_HEADER";
    private JwtValidatorImpl jwtValidator;

    @Inject
    public JwtControllerHelperImpl(JwtValidatorImpl jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public Result verify(Http.Request request, Function<F.Either<JwtValidator.Error, VerifiedJwt>, Result> f) {
        System.out.println("Headers");
        request.getHeaders().forEach(System.out::println);
		System.out.println(request.getHeaders().get(HEADER_AUTHORIZATION));
        Optional<String> authHeader =  request.getHeaders().get(HEADER_AUTHORIZATION);

        if (!authHeader.filter(ah -> ah.contains(BEARER)).isPresent()) {
            Logger.error("f=JwtControllerHelperImpl, event=verify, error=authHeaderNotPresent");
            return forbidden(ERR_AUTHORIZATION_HEADER);
        }

        String token = authHeader.map(ah -> ah.replace(BEARER, "")).orElse("");
        return f.apply(jwtValidator.verify(token));
    }
}
