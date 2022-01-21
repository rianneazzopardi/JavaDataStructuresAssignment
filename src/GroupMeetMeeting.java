import java.util.Calendar;
import java.util.Set;

public class GroupMeetMeeting extends Meeting{

    public GroupMeetMeeting(int id,Set<Resource> resources, Calendar startTime, Calendar endTime){
        super(id,resources, startTime, endTime);
    }

    @Override
    protected void checkResources(Set<Resource> resources){
        int people = 0;
        int rooms = 0;

        //stores the one and only room being used.
        Room room = null;

        for (Resource resource : resources){
            if (resource instanceof Person)
                people++;
            else if (resource instanceof Room){
                rooms++;
                room = (Room) resource;
            }
        }

        //The following are errors to be displayed in case the user enters unacceptable data
        if (rooms != 1)
            throw new IllegalArgumentException("The group meet needs to have exactly 1 room");
        else if (people<2)
            throw new IllegalArgumentException("The group meet needs to have more than 2 people");
        else if (people>room.getRoomCapacity())
            throw new IllegalArgumentException("The group meet can't have more people than the room's capacity ("+room.getRoomCapacity()+" people)");
    }
}
