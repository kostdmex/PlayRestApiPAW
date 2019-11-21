package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import jwt.JwtControllerHelperImpl;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;

import javax.inject.Inject;
import play.mvc.Result;
import repository.UserFinder;

import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


public class AuthController extends Controller {

    @Inject
    private JwtControllerHelperImpl jwtControllerHelper;

    @Inject
    private Config config;

    public Result autorize() throws UnsupportedEncodingException {
        JsonNode body = request().body().asJson();
        if (body == null) {
            Logger.error("json body is null");
            return forbidden();
        }

        if (body.hasNonNull("login") && body.hasNonNull("password")) {
            ObjectNode result = Json.newObject();
            result.put("access_token", getSignedToken(UserFinder.findByLoginAndPassword(body.get("login").asText(), body.get("password").asText())));



            return ok(result);
        } else {
            Logger.error("json body not as expected: {}", body.toString());
        }

        return forbidden();
    }


    private String getSignedToken(User user) throws UnsupportedEncodingException {
        String secret = config.getString("play.http.secret.key");

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(user.getLogin())
                .withClaim("user_id", user.getId())
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusHours(24).toInstant()))
                .sign(algorithm);
    }

}
