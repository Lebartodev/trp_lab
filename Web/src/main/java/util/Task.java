package util;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Nastya on 25.10.2015.
 */
@Entity
@Table(name="Tasks",uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
@XmlRootElement
public class Task implements Serializable {
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="description", nullable=true)
    private String description;
    @Column(name="date", nullable=true)
    private Calendar date;
    @Column(name="dateStr", nullable=true)
    private String dateStr;
    @Column(name="contacts", nullable=true)
    private String contacts;
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id", nullable=false)
    private Integer id;

    @XmlElement(name = "user_id")
    private int userID;
    public int getUserID() {

        return userID;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Task() {
        name = "New task";
        description = "-";
        date = new GregorianCalendar();
        contacts = "-";
    }

    public Task(String name, String description, Calendar date, String contacts) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.contacts = contacts;
    }
    public Task(int id, String name, String description, Calendar date, String contacts) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.date = date;
        this.contacts = contacts;
    }
    public Task(String name, String description, String date, String contacts) {
        this.name = name;
        this.description = description;
        this.dateStr = date;
        this.contacts = contacts;
    }

    public Task(String name, String description, Calendar date, String contacts, int userId) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.contacts = contacts;
        this.userID=userId;
    }
    public Task(int id, String name, String description, String date, String contacts, int userId) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.dateStr = date;
        this.contacts = contacts;
        this.userID=userId;
    }
    public Task(int id, String name, String description, String date, String contacts) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.dateStr = date;
        this.contacts = contacts;
    }
    public Task(String name, String description, String date, String contacts, int userId) {
        this.name = name;
        this.description = description;
        this.dateStr = date;
        this.contacts = contacts;
        this.userID=userId;
    }

    public Task(String name, Calendar date) {
        this.name = name;
        this.date = date;
        this.date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 1);
        description = "-";
        contacts = "-";
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date){
        this.date=date;
    }

    public String getContacts() {
        return contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setOrgDate(int year, int month, int day, int hour, int minute, int second) {
        this.date = new GregorianCalendar(year, month, day, hour, minute, second);
    }
    public String getOrgDate() {
        try {
            int day = date.get(Calendar.DATE);
            int month = date.get(Calendar.MONTH);
            int year = date.get(Calendar.YEAR);
            int hour = date.get(Calendar.HOUR_OF_DAY);
            int minutes = date.get(Calendar.MINUTE);

            return day + "." + month + "." + year + " " + hour + ":" + minutes;
        }
        catch (NullPointerException e){
            return "Date is null";
        }
    }

    /*
    public static void outputTask(Task task, OutputStream out)throws IOException{
-        DataOutputStream output = new DataOutputStream(out);
-        output.writeInt(task.id);
-        output.writeUTF(task.name);
-        output.writeUTF(task.description);
-        output.writeInt(task.date.get(task.date.YEAR));
-        output.writeInt(task.date.get(task.date.MONTH) + 1);
-        output.writeInt(task.date.get(task.date.DATE));
-        output.writeInt(task.date.get(task.date.HOUR));
-        output.writeInt(task.date.get(task.date.MINUTE));
-        output.writeInt(task.date.get(task.date.SECOND));
-        output.writeUTF(task.contacts);
-        output.close();
-    }
-    public static Task inputTask(InputStream in) throws IOException {
-        DataInputStream input = new DataInputStream(in);
-        int id = input.readInt();
-        String name = input.readUTF();
-        String description = input.readUTF();
-        int year = input.readInt();
-        int month = input.readInt();
-        int day = input.readInt();
-        int hour = input.readInt();
-        int minute = input.readInt();
-        int second = input.readInt();
-        String contacts = input.readUTF();
-        return new Task(id,name,description, new GregorianCalendar(year,month,day,hour,minute,second),contacts);
-    }
     */

    @Override
    public String toString() {
        return name + " " + description + " " + getDate() + " " + contacts;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (contacts != null ? !contacts.equals(task.contacts) : task.contacts != null) return false;
        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        return result;
    }
}