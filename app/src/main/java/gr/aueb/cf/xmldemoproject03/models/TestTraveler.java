package gr.aueb.cf.xmldemoproject03.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Travelerinformation")
public class TestTraveler {
    @Element(name = "name")
    private String name;

    @Element(name = "email")
    private String email;

    @Element(name = "adderes")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getters and setters
}
