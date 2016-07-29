package ua.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.EventLogger;

import java.util.Map;

/**
 * Created by Iurii_Kryshtop on 7/25/2016.
 */

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;


    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, Event event, String msgIn) {
        String messageOut = msgIn.replaceAll(client.getId(), client.getName());
        event.setMsg(messageOut);
        EventLogger log = loggers.get(type);
        if(log == null) {
            log = defaultLogger;
        }
        log.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) applicationContext.getBean("app");
        app.logEvent(EventType.INFO, (Event) applicationContext.getBean("event"), "Some event for user 1");
        app.logEvent(EventType.INFO, (Event) applicationContext.getBean("event"),  "Some event for user 2");
        app.logEvent(EventType.ERROR, (Event) applicationContext.getBean("event"), "Some event for user 3");
        app.logEvent(EventType.INFO, (Event) applicationContext.getBean("event"), "Some event for user 4");
        app.logEvent(EventType.ERROR, (Event) applicationContext.getBean("event"), "Some event for user 5");
        applicationContext.close();
    }
}
