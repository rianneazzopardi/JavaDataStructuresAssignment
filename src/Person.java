import java.util.Objects;

public class Person extends Resource implements Comparable<Person>{
    private String name,surname,id,position;

    public Person(){
    }

    public Person(String name, String surname, String id, String position){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.position = position;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name+" "+surname + "(id: "+id+")" + " - " + position;
    }

    @Override
    public String toDisplayString(){
        return "ID:" + id + "\t" +
                "Name:" + name + "\t" +
                "Surname:" + surname + "\t" +
                "Position: " + position + "\t";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    @Override
    public int compareTo(Person o){
        return id.compareTo(o.id);
    }
}
