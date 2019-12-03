package service;

import converters.list.ListJsonPostToList;
import converters.list.ListToListJson;

import io.ebean.Model;
import json.list.ListJson;
import json.list.ListJsonPost;
import json.list.ListJsonPut;
import json.list.ListJsonPutOrder;
import models.Board;
import models.List;
import play.db.ebean.Transactional;
import repository.BoardFinder;
import repository.ListFinder;
import validator.ListValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Singleton
public class ListService {

    @Inject
    private ListToListJson listToListJson;
    @Inject
    private AuthService authService;
    @Inject
    private ListJsonPostToList listJsonPostToList;

    public java.util.List<ListJson>  findByBoardId(Integer boardId){
        java.util.List<List> listList = ListFinder.findAllListsByBoardId(boardId);

        if(listList.isEmpty()){
            return null;
        }

        return listList.stream().map(listToListJson).sorted(Comparator.comparingInt(ListJson::getNumberOnBoard)).collect(Collectors.toList());
    }

    public Integer createList(ListJsonPost listJson){
        if(!ListValidator.validateListPost(listJson)){
            return  null;
        }

        List list = listJsonPostToList.apply(listJson);
        list.save();
        return list.id;
    }

    public boolean updateList(Integer listId, ListJsonPut listJsonPut, Integer userId){
        if(!ListValidator.checkIfListExists(listId)){
            return false;
        }

        if (authService.validateUserPermissionToList(listId, userId)){
            return false;
        }

        List listToUpdate = ListFinder.findListById(listId);

        if(listJsonPut.getName() != null){
            listToUpdate.setName(listJsonPut.getName());
        }

        if(listJsonPut.getNumberOnBoard() != null){
            listToUpdate.setNumberOnBoard(listJsonPut.getNumberOnBoard());
        }

        listToUpdate.save();

        return true;
    }

    @Transactional
    public boolean setListOrder(Integer boardId, java.util.List<ListJsonPutOrder> lists){
        if(lists.stream().anyMatch(i -> Collections.frequency(lists, i.getNumberOnBoard()) > 1)){
            return false;
        }
        java.util.List<List> listsOnBoard = ListFinder.findAllListsByBoardId(boardId);

        if(listsOnBoard.size() != lists.size()){
            return false;
        }

        for (ListJsonPutOrder list : lists) {
            if(listsOnBoard.stream().filter(li -> li.getId().equals(list.getListId())).findAny().isEmpty()){
                return false;
            }
        }

        boolean isNeedToChange = false;

        for (List list : listsOnBoard) {
            if(list.getNumberOnBoard().equals(lists.stream().filter(li -> !li.getListId().equals(list.getId())).findFirst().get().getNumberOnBoard())){
                isNeedToChange = true;
            }
        }

        if(isNeedToChange) {
            System.out.println("changing");
            listsOnBoard.forEach(list -> list.setNumberOnBoard(null));
            listsOnBoard.forEach(Model::save);
            listsOnBoard.forEach(list -> list.setNumberOnBoard(lists.stream().filter(listPut -> listPut.getListId().equals(list.getId())).findFirst().get().getNumberOnBoard()));
            listsOnBoard.forEach(Model::save);
        }
        return true;
    }

}
