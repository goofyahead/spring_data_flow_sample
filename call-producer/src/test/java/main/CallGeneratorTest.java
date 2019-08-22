package main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CallGeneratorTest {

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private Source source;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUsageDetailSender() throws Exception {
        Message message = this.messageCollector.forChannel(this.source.output()).poll(1, TimeUnit.SECONDS);
        String usageDetailJSON = message.getPayload().toString();
        assertTrue(usageDetailJSON.contains("caller"));
        assertTrue(usageDetailJSON.contains("receiver"));
        assertTrue(usageDetailJSON.contains("duration"));
    }

}