package gr.aueb.cf.xmldemoproject03.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "travelers")
public class Travelers {
    public List<Travelerinformation> getTravelerinformations() {
        return travelerinformations;
    }

    @ElementList(inline = true, entry = "Travelerinformation")
    private List<Travelerinformation> travelerinformations;

}
