package util;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(mappedName="jms/topic/MyTopic", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic")
})
public class MDBReceiver implements MessageListener{
    static final Logger logger = Logger.getLogger("SimpleMessageBean");
    public MDBReceiver() {
        super();
    }

    //метод, вызываемый при получении нового сообщения
    @Override
    public void onMessage(Message msg) {
        try {
            logger.info("Asdasdkalskdalsdlaksdjalksdjalks");
            System.out.println("asdasdasdasdasd");
            TextMessage message = (TextMessage)msg;
            //считываем свойство из соответствующего поля, заданное вручную в consumer
            System.out.println("FROM MDB - client type IS " + message.getStringProperty("clientType"));
            //считываем  само сообщение
            System.out.println("FROM MDB - payload  IS" + message.getText());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}