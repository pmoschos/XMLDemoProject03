package gr.aueb.cf.xmldemoproject03.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Travelerinformation")
public class Travelerinformation {
    @Element(name = "id")
    String id;
    @Element(name = "name")
    String name;
    @Element(name = "email")
    String email;

    @Element(name = "adderes", required = false)
    private String address; // Exclude the 'type' attribute

    @Element(name = "createdat")
    String createdat;
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCreatedat() {
        return createdat;
    }

    @Override
    public String toString() {
        return "<Travelerinformation xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <id>" + id + "</id>\n" +
                "    <name>" + name + "</name>\n" +
                "    <email>" + email + "</email>\n" +
                "    <adderes>" + address + "</adderes>\n" +
                "    <createdat>" + createdat + "</createdat>\n" +
                "</Travelerinformation>";
    }

}
