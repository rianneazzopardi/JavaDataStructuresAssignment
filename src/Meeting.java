import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Meeting implements Serializable{

    Set<Resource> resources;
    Calendar startTime,endTime;
    int id;

    public Meeting(int id,Set<Resource> resources,Calendar startTime,Calendar endTime){
        checkResources(resources);

        this.id = id;
        this.resources=resources;
        this.startTime=startTime;
        this.endTime = endTime;


    }

    //method for subclasses to check that their requirements are met.
    protected abstract void checkResources(Set<Resource> resources);


    public Calendar getStartTime(){
        return startTime;
    }

    public void setStartTime(Calendar startTime){
        this.startTime = startTime;
    }

    public Calendar getEndTime(){
        return endTime;
    }

    public void setEndTime(Calendar endTime){
        this.endTime = endTime;
    }

    public Set<Resource> getResources(){
        return Collections.unmodifiableSet(resources);
    }

    public void setResources(Set<Resource> resources){
        checkResources(resources);
        this.resources = resources;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm");

    @Override
    public String toString(){
        return "ID:" + id + dateFormat.format(startTime.getTime())+ " - " +dateFormat.format(endTime.getTime());
    }

    public String toDisplayString(){
        return "ID:" + id + "\t" +
                "start time:" + dateFormat.format(startTime.getTime()) + "\t" +
                "end time:" + dateFormat.format(endTime.getTime()) + "\t" +
                "Resources: [\n" + resources.stream().map(Objects::toString).collect(Collectors.joining("\n")) + "\n]";
    }



}

