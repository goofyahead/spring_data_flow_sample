package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.models.Call;
import main.models.CallCost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCostProcessorTest {

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private Processor processor;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUsageDetailSender() throws Exception {
        //Given
        Message<Call> message = MessageBuilder.withPayload(new Call(32, "Italy", "Spain")).build();
        processor.input().send(message);

        //When message process
        Message received = messageCollector.forChannel(processor.output()).poll(1, TimeUnit.SECONDS);

        //Then
        CallCost callCost = mapper.readValue((String) received.getPayload(), CallCost.class);
        assertThat(callCost.getTotalCost(), equalTo(96));
    }
}