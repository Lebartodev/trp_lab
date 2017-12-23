package model.data.response;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"exception"}, name = "responseException")
public class ResponseException  {
    private String exception;

    public ResponseException() {
    }

    public ResponseException(String exception) {
        this.exception = exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return this.exception;
    }
}
