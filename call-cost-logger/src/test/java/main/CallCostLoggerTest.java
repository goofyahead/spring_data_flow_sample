package main;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.models.CallCost;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CallCostLoggerTest {

    @Autowired
    Sink sink;

    private Logger logger;
    private ListAppender<ILoggingEvent> appender;

    @Before
    public void before() {
        appender = new ListAppender<>();
        logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME); // cast from facade (SLF4J) to implementation class (logback)
        logger.addAppender(appender);
        appender.start();
    }

    @After
    public void after() {
        logger.detachAppender(appender);
    }

    public List<ILoggingEvent> getEvents() throws Exception {
        if (appender == null) {
            throw new Exception("LogSpy needs to be annotated with @Rule");
        }
        return appender.list;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCostCallsAreLogged() throws Exception {
        //Given
        Message<CallCost> message = MessageBuilder.withPayload(new CallCost(12, 234)).build();
        sink.input().send(message);

        assertEquals(getEvents().get(0).getMessage(), "CallCost(duration=12, totalCost=234)");
    }

}