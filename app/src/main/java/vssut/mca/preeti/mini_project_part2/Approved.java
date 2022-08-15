package vssut.mca.preeti.mini_project_part2;

public class Approved {
    String timestamp;
    Approved()
    {

    }
    Approved(String timestamp)
    {
        //this.key=key;
        this.timestamp=timestamp;

    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
