package gr.aueb.cf.xmldemoproject03.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TravelerinformationResponse")
public class TravelerinformationResponse {


    @Element
    private int page;

    @Element
    private int per_page;

    @Element
    private int totalrecord;

    @Element
    private int total_pages;

    @Element(name ="travelers")
    private Travelers travelers;



    public Travelers getTravelers() {
        return travelers;
    }
}
