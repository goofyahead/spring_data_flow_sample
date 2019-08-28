package ninja.goofyahead.callloger.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Call {
    private int duration;
    private String from;
    private String to;
}
