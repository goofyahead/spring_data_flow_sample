package ninja.goofyahead.callloger.processor;

import ninja.goofyahead.callloger.models.Call;
import ninja.goofyahead.callloger.models.CallCost;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBinding(Processor.class)
public class CallCostProcessor {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public CallCost processUsageCost(Call call) {
        CallCost callCost = new CallCost();
        int costPerMinute = call.getFrom().equalsIgnoreCase(call.getTo()) ? 1 : 3;
        callCost.setCostPerMinute(costPerMinute);
        callCost.setTotalCost(costPerMinute * call.getDuration());
        callCost.setDuration(call.getDuration());
        return callCost;
    }
}