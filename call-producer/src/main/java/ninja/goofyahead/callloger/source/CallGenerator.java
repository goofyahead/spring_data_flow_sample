package ninja.goofyahead.callloger.source;

import ninja.goofyahead.callloger.models.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@SpringBootApplication
@EnableScheduling
@EnableBinding(Source.class)
public class CallGenerator {

    @Autowired
    private Source source;

    private Logger logger = LoggerFactory.getLogger(CallGenerator.class);

    private String[] users = {"Alex", "Manu", "Juanjo", "Lucia", "Kike", "Marta", "Helena", "Kim", "Pablo", "Albert", "Maria", "Edgar", "Alicia", "Isa", "Olbin"};
    private String[] countries = {"Spain", "UAE", "Colombia", "Catalunya", "France", "Ireland"};


    @Scheduled(fixedDelay = 1000)
    public void sendEvents() {
        logger.debug("sending event of new call.");
        Call generatedCall = new Call(
                this.users[new Random().nextInt(this.users.length)],
                this.users[new Random().nextInt(this.users.length)],
                new Random().nextInt(60),
                this.countries[new Random().nextInt(this.countries.length)],
                this.countries[new Random().nextInt(this.countries.length)]);

        this.source.output().send(MessageBuilder.withPayload(generatedCall).build());
    }
}