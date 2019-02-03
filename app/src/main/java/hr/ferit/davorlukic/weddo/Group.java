package hr.ferit.davorlukic.weddo;


import io.realm.RealmObject;

public class Group extends RealmObject {
    String id;
    String name;
    Double downPayment;
    Double price;
    String phone;
    String note;

    public Group(){}

    public Group(String id){
        this.id = id;
        this.name = "Name";
        this.downPayment = 0.0;
        this.price = 0.0;
        this.phone = "Phone number";
        this.note = "Notes about contact and such";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public Double getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
