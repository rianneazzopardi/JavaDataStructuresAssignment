import java.io.Serializable;
import java.util.*;

//stores all the objects of a particular type of resource, ex all the 'Person's in the system.
//and provides all the UI menus necessary to modify them
public abstract class ResourceSet<E extends Resource> implements Serializable{

    protected Set<E> set = new HashSet<>();


    //returns the title of the menu, this will be shows as the 1st line whenever the menu is shown;
    public abstract String getMenuTitle();

    //shows the menu allowing the user to add,edit,remove and display the resources in this set
    public void showMenu(){

        int choice;

        do{
            System.out.println(getMenuTitle());
            System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("3. Edit");
            System.out.println("4. Display all");
            System.out.println("5. Back to main menu");
            System.out.println("Please enter your choice: ");
            choice = Utils.readInteger();
            switch (choice) {
                case 1:
                    addResource();
                    break;
                case 2:
                    removeResource();
                    break;
                case 3:
                    editResource();
                    break;
                case 4:
                    displayAll();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid input, try again");

            }
        } while (choice != 5);
    }

    public abstract void addResource();

    public abstract void removeResource();

    public abstract void editResource();

    public void displayAll(){
        //sort them by their key (id or room number) and output
        set.stream().forEach(e->System.out.println(e.toDisplayString()));
    }
}
