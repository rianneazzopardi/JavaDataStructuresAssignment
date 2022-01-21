import java.util.Calendar;
import java.util.Set;

public class MeetupMeeting extends Meeting{


    public MeetupMeeting(int id,Set<Resource> resources, Calendar startTime, Calendar endTime){
        super(id,resources, startTime, endTime);
    }

    @Override
    protected void checkResources(Set<Resource> resources){
        int people = 0;
        int rooms = 0;

        for (Resource resource : resources){
            if (resource instanceof Person)
                people++;
            else if (resource instanceof Room)
                rooms++;
        }
        //Exceptions to be displayed in case invalid data is entered
        if (people!=2)
            throw new IllegalArgumentException("The meetup needs to have exactly 2 people");
        else if (rooms != 1){
            throw new IllegalArgumentException("The meetup needs to have exactly 1 room");
        }
    }
}
