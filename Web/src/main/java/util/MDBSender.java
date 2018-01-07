package util;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MDBSender {

    public void sendString(String enterString) {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ConnectionFactory connectionFactory = (ConnectionFactory)
                    envCtx.lookup("jms/ConnectionFactory");

            Destination destination = (Destination)
                    envCtx.lookup("jms/topic/MyTopic");
            //создаем подключение
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            //добавим в JMS сообщение собственное свойство в поле сообщения со свойствами
            message.setStringProperty("clientType", "web clien");
            //добавляем payload в сообщение
            message.setText(enterString);
            //отправляем сообщение
            producer.send(message);
            System.out.println("message sent");
            //закрываем соединения
            session.close();
            connection.close();

        } catch (Exception ex) {
            System.err.println("Sending message error");
            ex.printStackTrace();
        }
    }
}
