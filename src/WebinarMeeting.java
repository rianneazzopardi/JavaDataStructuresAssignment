import java.util.Calendar;
import java.util.Set;

public class WebinarMeeting extends Meeting{
    public WebinarMeeting(int id,Set<Resource> resources, Calendar startTime, Calendar endTime){
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
        //Exception to be displayed in case invalid data is inputted
        if (people<1)
            throw new IllegalArgumentException("The webinar needs at least 1 person");
        else if (rooms >0){
            throw new IllegalArgumentException("The webinar needs to have exactly 0 rooms");
        }
    }
}
