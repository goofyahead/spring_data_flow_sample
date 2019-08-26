package ninja.goofyahead.callloger.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallCost {
    private int duration;
    private int totalCost;
}
