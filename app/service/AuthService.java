package service;

import com.auth0.jwt.interfaces.DecodedJWT;
import jwt.JWTDecoder;
import jwt.JwtControllerHelperImpl;
import jwt.JwtValidatorImpl;
import models.Board;
import models.List;
import models.User;
import play.mvc.Http;
import play.mvc.Result;
import repository.BoardFinder;
import repository.ListFinder;
import repository.UserFinder;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Optional;

import static play.mvc.Results.forbidden;
import static play.mvc.Results.ok;

@Singleton
public class AuthService {

    @Inject
    private JwtControllerHelperImpl jwtControllerHelper;
    @Inject
    private JwtValidatorImpl jwtValidator;

    public Result validateRequest(Http.Request request){
        return jwtControllerHelper.verify(request, res -> {
            if (res.left.isPresent()) {
                return forbidden(res.left.get().toString());
            }
            return ok();
        });
    }

    public Integer getUserIdFromToken(Http.Request request){
        String token =  request.getHeaders().get(JwtControllerHelperImpl.HEADER_AUTHORIZATION).map(ah -> ah.replace(JwtControllerHelperImpl.BEARER, "")).orElse("");
        DecodedJWT jwt = jwtValidator.getVerifier().verify(token);
        JWTDecoder jwtDecoder = new JWTDecoder(jwt.getToken());
        return UserFinder.findByLogin(jwtDecoder.getPayload().getIssuer()).getId();
    }

    public boolean validateUserPermissionToList(Integer listId, Integer userId) {
        Board board = BoardFinder.findByListId(listId);
        if(BoardFinder.findByUserIdAndBoardId(userId, board.id) == null){
            return true;
        }
        return false;
    }

    public boolean validateUserPermissionToCard(Integer cardId, Integer userId) {
        List list = ListFinder.findByCardId(cardId);
        Board board = BoardFinder.findByListId(list.getId());
        if(BoardFinder.findByUserIdAndBoardId(userId, board.id) == null){
            return true;
        }
        return false;
    }

}
