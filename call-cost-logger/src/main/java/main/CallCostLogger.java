package main;

import main.models.CallCost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CallCostLogger {

    private static final Logger logger = LoggerFactory.getLogger(CallCostLogger.class);

    @StreamListener(Sink.INPUT)
    public void process(CallCost callCost) {
        logger.info(callCost.toString());
    }
}