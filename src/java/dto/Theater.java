package dto;
import java.io.Serializable;

public class Theater implements Serializable
{
    private String name;
    private String location;
    private int theaterId;
    private int noHalls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getNoHalls() {
        return noHalls;
    }

    public void setNoHalls(int noHalls) {
        this.noHalls = noHalls;
    }
}
