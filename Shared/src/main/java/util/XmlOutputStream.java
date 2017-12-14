package main.java.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XmlOutputStream extends ByteArrayOutputStream {
    private DataOutputStream outchannel;

    public XmlOutputStream(OutputStream outchannel) {
        super();
        this.outchannel = new DataOutputStream(outchannel);
    }

    public void send() throws IOException {
        byte[] data = toByteArray();
        outchannel.writeInt(data.length);
        outchannel.write(data);
        reset();
    }





}
