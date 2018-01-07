package util;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MDBSender {

    public void sendString(int idChanged, String name, String action) {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ConnectionFactory connectionFactory = (ConnectionFactory)
                    envCtx.lookup("jms/ConnectionFactory");

            Destination destination = (Destination)
                    envCtx.lookup("jms/topic/MyTopic");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setIntProperty("id", idChanged);
            message.setStringProperty("name", name);
            message.setStringProperty("action", action);
            message.setLongProperty("date", System.currentTimeMillis());
            producer.send(message);
            System.out.println("message sent");
            session.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
