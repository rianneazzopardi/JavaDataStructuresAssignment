import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main{

    //the sets that will hold the people, rooms and meetings
    //these sets also contain their own UI.
    public static PersonSet personSet;
    public static RoomSet roomSet;
    public static MeetingSet meetingSet;

    //Declaring the file that will hold the save file
    private static final File saveFile = new File("save.sav");

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        scanner.useDelimiter("\n");

        personSet = new PersonSet();
        roomSet = new RoomSet();
        meetingSet = new MeetingSet();

        load();
        showMenu();
    }



    private static void showMenu(){

        int choice;
        //Displaying the menu
        do {
            System.out.println("Meeting organizer");
            System.out.println("1. Manage people");
            System.out.println("2. Manage Rooms");
            System.out.println("3. Manage Meetings");
            System.out.println("4. Query");
            System.out.println("5. Save");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice: ");
            choice = Utils.readInteger();
            switch (choice) {
                case 1: {
                    personSet.showMenu();
                }
                break;
                case 2: {
                    roomSet.showMenu();
                }
                break;
                case 3: {
                    meetingSet.showMenu();
                }
                break;
                case 4: {
                    queryMenu();
                }
                case 5:
                    //case 6 will save, then exit
                case 6:
                {
                    save();
                }
                break;
                //validation in case the user selects an option not within the menu's scope
                default:
                    System.out.println("Incorrect option, please try again");
            }
        }while(choice!=6);
    }

    private static void queryMenu(){
        int resourceType;
        System.out.println("Get all meetings with resource");
        System.out.println("Choose which type of resource:");
        System.out.println("1.Room");
        System.out.println("2.Person");
        //user input which type of resource (room or person) they want to display the meetings of
        resourceType = Utils.readInteger();

        Resource r = null;

        switch (resourceType) {
            case 1:
                System.out.println("Enter the number of the room to query");
                r = roomSet.getRoomByNumber(Utils.readInteger());
                //checks if the room exists or not
                if (r == null)
                    System.out.println("Room doesn't exist");
                break;

            case 2:
                System.out.println("Input id of person to query");
                r = personSet.getPersonByID(Main.getScanner().next());
                //checks if person exists or not
                if (r == null)
                    System.out.println("Person doesn't exist");
                break;
            }

            if (r != null){
                //copy r into a final variable to remove lambda error
                Resource finalR = r;
                meetingSet.getSet().stream().filter(m->m.getResources().contains(finalR)).forEach(System.out::println);
            }

    }

    //saves the sets into a save file
    private static void save(){
        try (FileOutputStream fout = new FileOutputStream(saveFile)){
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(personSet);
            out.writeObject(roomSet);
            out.writeObject(meetingSet);
            out.flush();
            out.close();
            System.out.println("Success");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //loads the sets with the contents from the save file
    private static void load(){
        if (saveFile.exists()){
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(saveFile))) {

                personSet = (PersonSet) in.readObject();
                roomSet = (RoomSet) in.readObject();
                meetingSet = (MeetingSet) in.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //this will happen if the save file is missing or if the program is being run for the 1st time.
            //in this case, the sets will be
            System.out.println("No save file found");
        }
    }

    public static Scanner getScanner(){
        return scanner;
    }
}
