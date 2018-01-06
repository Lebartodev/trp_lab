package util;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="Category",uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
@XmlRootElement
@XmlType(propOrder = {"name"}, name = "categoryItem")
public class CategoryItem implements java.io.Serializable {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id", nullable=false)
    private int id;
    @Column(name="name")
    private String name;

    public CategoryItem() {

    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryItem that = (CategoryItem) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "util.util.CategoryItem{" + System.lineSeparator() +
                "id=" + id + System.lineSeparator() +
                ", name='" + name + '\'' + System.lineSeparator() +
                '}';
    }

    public static Builder newBuilder() {
        return new CategoryItem().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder id(int id) {
            CategoryItem.this.id = id;
            return this;
        }

        public Builder name(String name) {
            CategoryItem.this.name = name;
            return this;
        }


        public CategoryItem build() {
            return CategoryItem.this;
        }
    }
}
