package hr.ferit.davorlukic.weddo;

import io.realm.RealmObject;

public class Person extends RealmObject {
    private String name;
    private String lastName;

    public Person(){}

    Person(String Name, String LastName){
        name = Name;
        lastName = LastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
