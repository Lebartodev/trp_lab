package util;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="jms/topic/ITExpertsTopic")
public class MDBReceiver implements MessageListener {


    //метод, вызываемый при получении нового сообщения
    @Override
    public void onMessage(Message msg) {
        try {
            String name = msg.getStringProperty("name");
            System.out.println("Received msg " + msg + ", from " + name);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}