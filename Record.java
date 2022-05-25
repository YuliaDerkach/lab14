package graph;

public class Record{
private int cod;
private String firstName;
private String secondName;
private String date;
private int peopleAmount;
private String phone;
public Record(int cod,String firstName,String secondName,String date,int peopleAmount,String phone){
        this.cod = cod;
        this.firstName = firstName;
        this.secondName = secondName;
        this.date = date;
        this.peopleAmount = peopleAmount;
        this.phone = phone;
        }
public Record(String firstName,String secondName,String date,int peopleAmount,String phone){
        this.firstName = firstName;
        this.secondName = secondName;
        this.date = date;
        this.peopleAmount = peopleAmount;
        this.phone = phone;
        }

public int getCod(){return cod;}
public int getPeopleAmount(){return peopleAmount;}
public String getFirstName(){return firstName;}
public String getSecondName(){return secondName;}
public String getDate(){return date;}
public String getPhone(){return phone;}
        }
