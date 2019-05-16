package atshunhengli.com.entity.app.conditions;

public class Condition_CycleTimer implements Conditions{

    private String type;
    private String id;
    private Condition_CycleTimer timeRange;
    private String interval;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Condition_CycleTimer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Condition_CycleTimer timeRange) {
        this.timeRange = timeRange;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

}
