package ninja.goofyahead.callloger.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallCost {
    private int costPerMinute;
    private int duration;
    private int totalCost;
}
