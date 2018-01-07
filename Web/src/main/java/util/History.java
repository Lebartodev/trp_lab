package util;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Entity
@Table(name="History",uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
@XmlRootElement
@XmlType(propOrder = {"id", "date", "entityId", "entityName", "action"}, name = "history")
public class History {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id", nullable=false)
    private int id;
    @Column(name="date")
    private Date date;
    @Column(name="entityId")
    private int entityId;
    @Column(name="entityName")
    private String entityName;
    @Column(name="action")
    private String action;

    public History() {
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
