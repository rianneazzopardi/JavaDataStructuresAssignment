import java.util.Iterator;

//stores all the 'Person's in the system.
//and provides all the UI menus necessary to modify them
public class PersonSet extends ResourceSet<Person>{

    @Override
    public String getMenuTitle(){
        return "Manage People";
    }

    @Override
    public void addResource(){
        Person p = new Person();
        //Asks user to input the details
        System.out.println("Please enter the name: ");
        String name = Main.getScanner().next();
        p.setName(name);
        System.out.println("Please enter the surname: ");
        String surname = Main.getScanner().next();
        p.setSurname(surname);
        System.out.println("Please enter the id: ");
        String id = Main.getScanner().next();
        p.setId(id);
        System.out.println("Please enter the position: ");
        String pos = Main.getScanner().next();
        p.setPosition(pos);

        //tries to add a person and fails if the id number is already in use
        if (set.add(p))
            System.out.println("person added successfully");
        else
            System.out.println("Cannot add person because another person already exists with that id number");



    }

    @Override
    //Searches for the person by id and then removes if found
    public void removeResource(){
        //Asks user for the id of the person to be removed
        System.out.println("Please enter the id of the person to be removed: ");
        String id = Main.getScanner().next();

        //loops through the set.
        Iterator<Person> iterator = set.iterator();
        while (iterator.hasNext()){
            Person currentPerson = iterator.next();
            if (currentPerson.getId().equals(id)){
                //remove the person, inform the user and return
                iterator.remove();
                System.out.println("Person removed");
                return;
            }
        }

        //person was not found, so we inform the user and return
        System.out.println("Person not found!");

    }

    @Override
    public void editResource(){
        //Asks user to enter the id in order to search for the person to be edited
        System.out.println("Please enter the id of the person to be edited: ");
        String id = Main.getScanner().next();

        Person p = getPersonByID(id);

        if (p == null) {
                System.out.println("Person not found!");
        } else{
            int ch;
            do{
                //Asks users what details they want to edit
                System.out.println("What details do you want to edit?");
                System.out.println("1. Name");
                System.out.println("2. Surname");
                System.out.println("3. ID");
                System.out.println("4. Position");
                System.out.println("5. Save and exit");
                ch = Utils.readInteger();
                switch (ch) {
                    case 1: {
                        System.out.println("Please enter the new name: ");
                        String n = Main.getScanner().next();
                        p.setName(n);
                    }
                    break;
                    case 2: {
                        System.out.println("Please enter the new surname: ");
                        String sn = Main.getScanner().next();
                        p.setSurname(sn);
                    }
                    break;
                    case 3: {
                        System.out.println("Please enter the new ID: ");
                        String idno = Main.getScanner().next();

                        //check that the id is not in use by another person (with e!=p to account for the chance that the user entered the same id number as before)
                        if(set.stream().anyMatch(e->e.getId().equals(idno) && e!=p)){
                            //inform the user and continue to menu
                            System.out.println("Cannot edit person because another person already exists with that id number");
                            break;
                        }

                        p.setId(idno);
                    }
                    break;
                    case 4: {
                        System.out.println("Please enter the new position: ");
                        String pos = Main.getScanner().next();
                        p.setPosition(pos);
                    }
                    break;
                    case 5:
                        break;
                    default:
                        System.out.println("invalid input, try again");
                }
            } while (ch !=5);
        }
    }

    //returns the person object with the given ID
    public Person getPersonByID(String s){
        //loops through the set.
        for (Person e : set){
            if (e.getId().equals(s)){
                //return result
                return e;
            }
        }
        //not found
        return null;
    }



}


