package ninja.goofyahead.callloger.models;

import lombok.Data;

@Data
public class CallCost {
    private int costPerMinute;
    private int duration;
    private int totalCost;
}
