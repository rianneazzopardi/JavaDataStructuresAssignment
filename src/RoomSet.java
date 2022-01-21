import java.util.*;

//stores all the 'Room's in the system.
//and provides all the UI menus necessary to modify them
public class RoomSet extends ResourceSet<Room>{

    @Override
    public String getMenuTitle(){
        return "Manage Rooms";
    }

    @Override
    public void addResource(){
        System.out.println("Enter room number: ");
        int RN = Utils.readInteger();

        int RC;
        boolean repeatRC;

        do{
            System.out.println("Enter room capacity: ");

            RC = Utils.readInteger();

            repeatRC = RC <= 0;

            if (repeatRC)
                System.out.println("Capacity cannot be 0 or less");
        } while (repeatRC);

        if (set.add(new Room(RN, RC)))
            System.out.println("room added successfully");
        else
            System.out.println("Cannot add room because another room already exists with that room number");
    }

    @Override
    //Searches for the room by number and then removes if found
    public void removeResource(){
        //Asks user for the number of the room to be removed
        System.out.println("Please enter the id of the room to be removed: ");
        int number = Utils.readInteger();

        //loops through the set.
        Iterator<Room> iterator = set.iterator();
        while (iterator.hasNext()){
            Room currentRoom = iterator.next();
            if (currentRoom.getRoomNumber() == number){
                //remove the room, inform the user and return
                iterator.remove();
                System.out.println("Room removed");
                return;
            }
        }

        //room was not found, so we inform the user and return
        System.out.println("Room not found!");
    }

    @Override
    public void editResource(){
        boolean found = false;
        System.out.println("Enter room number you want to edit: ");
        int roomNumber = Utils.readInteger();

        Room r = getRoomByNumber(roomNumber);

        if (r == null)
            System.out.println("Room not found");
        else {
            int ch;
            do{
                //Asks users what details they want to edit
                System.out.println("What details do you want to edit?");
                System.out.println("1. Room number");
                System.out.println("2. Capacity");
                System.out.println("3. Save and exit");
                ch = Utils.readInteger();
                switch (ch) {
                    case 1: {
                        System.out.println("Please enter the new room number: ");
                        int n = Utils.readInteger();

                        //check that the number is not in use by another room (with e!=r to account for the chance that the user entered the same room number as before)
                        if(set.stream().anyMatch(e->e.getRoomNumber() == n && e!=r)){
                            //inform the user and continue to menu
                            System.out.println("Cannot edit room because another room already exists with that room number");
                            break;
                        }

                        r.setRoomNumber(n);
                    }
                    break;
                    case 2: {
                        System.out.println("Please enter the new capacity: ");
                        int c;
                        boolean repeat;

                        do{
                            System.out.println("Enter room capacity: ");

                            c = Utils.readInteger();

                            repeat = c <= 0;

                            if (repeat)
                                System.out.println("Capacity cannot be 0 or less");
                        } while (repeat);
                        r.setRoomCapacity(c);
                    }
                    break;
                    case 3:
                        break;
                    default:
                        System.out.println("invalid input, try again");
                }

            } while (ch !=3);
        }
    }

    //returns the room object with the given room number
    public Room getRoomByNumber(int num){
        //loops through the set.
        for (Room e : set){
            if (e.getRoomNumber() == num){
                //return result
                return e;
            }
        }
        //not found
        return null;
    }


}


