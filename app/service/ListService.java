package service;

import converters.list.ListJsonPostToList;
import converters.list.ListToListJson;

import json.list.ListJson;
import json.list.ListJsonPost;
import json.list.ListJsonPut;
import models.List;
import repository.ListFinder;
import validator.ListValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class ListService {

    @Inject
    private ListToListJson listToListJson;

    @Inject
    private ListJsonPostToList listJsonPostToList;

    public java.util.List<ListJson>  findByBoardId(Integer boardId){
        java.util.List<List> listList = ListFinder.findAllListsByBoardId(boardId);

        if(listList.isEmpty()){
            return null;
        }

        return listList.stream().map(listToListJson).collect(Collectors.toList());
    }

    public Integer createList(ListJsonPost listJson){
        if(!ListValidator.validateListPost(listJson)){
            return  null;
        }

        List list = listJsonPostToList.apply(listJson);
        list.save();
        return list.id;
    }

    public boolean updateList(Integer listId, ListJsonPut listJsonPut){
        if(!ListValidator.checkIfListExists(listId)){
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

}
