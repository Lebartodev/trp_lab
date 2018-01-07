package util;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/topic/MyTopic", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic")
})
public class MDBReceiver implements MessageListener {


    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage message = (TextMessage) msg;
            int id = message.getIntProperty("id");
            String name = message.getStringProperty("name");
            String action = message.getStringProperty("action");
            long date = message.getLongProperty("date");
            System.out.println(id + " " + action + " " + name + " " + new Date(date));
            writeHistory(id, name, action, date);

        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    private void writeHistory(int entityId, String entityName, String action, long date){
        try {
            History history = new History();
            history.setEntityId(entityId);
            history.setEntityName(entityName);
            history.setAction(action);
            history.setDate(new Date(date));
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(history);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}