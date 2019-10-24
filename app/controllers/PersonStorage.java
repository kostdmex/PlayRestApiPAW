package controllers;

import com.google.common.collect.Lists;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
/**
 * Very simple "database" needed for storing some Person objects.
 */
public class PersonStorage {

    private List<Person> persons = Lists.newArrayList();

    public Person addPerson(Person person) {

        persons.add(person);
        return person;

    }

    public List<Person> getPersons() {

        return persons;
    }

    public Optional<Person> getPerson(String email) {

        return persons.stream().filter(person -> person.email.equals(email)).findFirst();

    }

    /**
     * i do it as simple as possible. Find person with email, remove it, and then add "edited" element, if element is not found, just insert it.
     * @param email
     * @param person
     * @return
     */
    public Person editPerson(String email, Person person) {

        return persons.stream()
                .filter(p -> p.email.equals(email))
                .findFirst()
                .map(p -> {
                    persons.remove(p);
                    persons.add(person);
                    return person;
                })
                .orElseGet(() -> {
                    persons.add(person);
                    return person;
                });
    }
}
