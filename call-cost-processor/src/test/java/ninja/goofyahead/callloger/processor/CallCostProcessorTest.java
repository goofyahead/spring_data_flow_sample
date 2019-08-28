package ninja.goofyahead.callloger.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ninja.goofyahead.callloger.models.Call;
import ninja.goofyahead.callloger.models.CallCost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.apache.commons.io.IOUtils;

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
    public void testUsageDetailSenderWithClass() throws Exception {
        //Given
        Message<Call> message = MessageBuilder.withPayload(new Call(32, "Italy", "Spain")).build();
        processor.input().send(message);

        //When message process
        Message received = messageCollector.forChannel(processor.output()).poll(1, TimeUnit.SECONDS);

        //Then
        CallCost callCost = mapper.readValue((String) received.getPayload(), CallCost.class);
        assertThat(callCost.getTotalCost(), equalTo(96));
    }

    @Test
    public void testUsageDetailSenderWithJson() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/call_sample.json");
        String jsonInput = IOUtils.toString(fis, "UTF-8");

        //Given
        Message<String> message = MessageBuilder.withPayload(jsonInput).build();
        processor.input().send(message);

        //When message process
        Message received = messageCollector.forChannel(processor.output()).poll(1, TimeUnit.SECONDS);

        //Then
        CallCost callCost = mapper.readValue((String) received.getPayload(), CallCost.class);
        assertThat(callCost.getTotalCost(), equalTo(36));
    }


    @Test
    public void testUsageDetailSenderWithJsonWithExtraFields() throws Exception {
        FileInputStream fis = new FileInputStream("src/test/resources/call_sample_with_unknown_fields.json");
        String jsonInput = IOUtils.toString(fis, "UTF-8");

        //Given
        Message<String> message = MessageBuilder.withPayload(jsonInput).build();
        processor.input().send(message);

        //When message process
        Message received = messageCollector.forChannel(processor.output()).poll(1, TimeUnit.SECONDS);

        //Then
        CallCost callCost = mapper.readValue((String) received.getPayload(), CallCost.class);
        assertThat(callCost.getTotalCost(), equalTo(69));
    }
}