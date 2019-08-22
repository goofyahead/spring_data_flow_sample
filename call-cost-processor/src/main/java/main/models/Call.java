package main.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Call {
    private int duration;
    private String from;
    private String to;
}
