/**
 * Created by Iurii_Kryshtop on 7/25/2016.
 */

package ua.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.loggers.EventLogger;

public class App {
    private Client client;
    private EventLogger eventLogger;


    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event, String msgIn){
        String messageOut = msgIn.replaceAll(client.getId(), client.getName());
        event.setMsg(messageOut);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) applicationContext.getBean("app");
        app.logEvent((Event)applicationContext.getBean("event"), "Some event for user 1");
        app.logEvent((Event)applicationContext.getBean("event"), "Some event for user 2");
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
