package util;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MDBSender {


    public void sendString(String enterString) {
        try {
            InitialContext initCtx = new InitialContext();
            Context envContext = (Context) initCtx.lookup("java:comp/env");
            ConnectionFactory connectionFactory = (ConnectionFactory) envContext.lookup("jms/ConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("jms/topic/MyTopic");
            MessageProducer producer = session.createProducer(destination);
            TextMessage msg = session.createTextMessage();
            msg.setText("Message sent");
            producer.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
