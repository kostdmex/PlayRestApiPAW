package service;

import converters.ListToListJson;

import json.ListJson;
import models.List;
import repository.ListFinder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class ListService {

    @Inject
    private ListToListJson listToListJson;

    public java.util.List<ListJson>  findByBoardId(Integer boardId){
        java.util.List<List> listList = ListFinder.findAllListsByBoardId(boardId);

        if(listList.isEmpty()){
            return null;
        }

        return listList.stream().map(listToListJson).collect(Collectors.toList());
    }

}
