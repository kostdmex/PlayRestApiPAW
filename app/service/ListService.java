package service;

import converters.list.ListJsonToList;
import converters.list.ListToListJson;

import json.list.ListJson;
import json.list.ListJsonPut;
import models.List;
import repository.ListFinder;
import validator.BoardValidator;
import validator.ListValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class ListService {

    @Inject
    private ListToListJson listToListJson;

    @Inject
    private ListJsonToList listJsonToList;

    public java.util.List<ListJson>  findByBoardId(Integer boardId){
        java.util.List<List> listList = ListFinder.findAllListsByBoardId(boardId);

        if(listList.isEmpty()){
            return null;
        }

        return listList.stream().map(listToListJson).collect(Collectors.toList());
    }

    public Integer createList(ListJson listJson){
        if(!BoardValidator.checkIfBoardExists(listJson.boardId)){
            return  -1;
        }

        List list = listJsonToList.apply(listJson);
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
