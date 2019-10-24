package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonController extends Controller {

    @Inject
    private PersonStorage personStorage;

    public Result getPersons() {

        return ok(Json.toJson(personStorage.getPersons()));
    }

    public Result addPerson() {

        Person person = Json.fromJson(request().body().asJson(), Person.class);


        return ok(Json.toJson(personStorage.addPerson(person)));

    }

    public Result editPerson(String email) {

        Person person = Json.fromJson(request().body().asJson(), Person.class);


        return ok(Json.toJson(personStorage.editPerson(email,person)));

    }

    public Result getPerson(String email) {

        return personStorage.getPerson(email).map(person -> ok(Json.toJson(person))).orElseGet(Results::notFound);


    }


}
