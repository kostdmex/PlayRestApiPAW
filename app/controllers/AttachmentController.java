package controllers;

import json.attachment.AttachmentJson;
import json.attachment.CardAttachment;
import json.attachment.CardAttachmentGet;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.AttachmentService;
import service.AuthService;

import javax.inject.Inject;
import java.util.List;

public class AttachmentController extends Controller {

    @Inject
    private AttachmentService attachmentService;
    @Inject
    private AuthService authService;

    public Result addAttachmentToCard(Integer cardId){
        Result result = authService.validateRequest(request());
        if (result.status() == 403) {
            return result;
        }
        if(authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        Integer attachmentId = attachmentService.addAttachmentToCard(cardId, Json.fromJson(request().body().asJson(), CardAttachment.class));
        if(attachmentId == -1){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(attachmentId));
    }

    public Result getAttachmentsByCardId(Integer cardId){
        Result result = authService.validateRequest(request());
        if (result.status() == 403) {
            return result;
        }
        if(authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        List<CardAttachmentGet> cardAttachments = attachmentService.getCardAttachment(cardId);
        if(cardAttachments == null){
            return badRequest();
        }

        return ok(Json.toJson(cardAttachments));
    }

    public Result findByAttachmentId(Integer attachmentId){
        Result result = authService.validateRequest(request());
        if (result.status() == 403) {
            return result;
        }

        AttachmentJson attachmentJson = attachmentService.getAttachmentById(attachmentId);
        if(attachmentJson == null){
            return notFound();
        }

        return ok(Json.toJson(attachmentJson));
    }

    public Result deleteAttachmentById(Integer attachmentId){
        Result result = authService.validateRequest(request());
        if (result.status() == 403) {
            return result;
        }
        attachmentService.deleteAttachmentById(attachmentId);
        return ok();
    }
}
