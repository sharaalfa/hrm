package rostelecom.sha.hrmtech2.model;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXML {

    private String number;

    private String department;

    private String centrality;

    private static final Logger log = Logger.getLogger(ReadXML.class);


    public String getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public String getCentrality() {
        return centrality;
    }

    public ReadXML values(String doc, String fileName) {
        try {
            File fromXml1 = new File(
                    "/home/sha/Documents/hrm/" + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(fromXml1);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(doc);

            Node node = nodeList.item(0);
            Element element = (Element) node;
            number = element.getElementsByTagName("number").item(0).getTextContent();
            department = element.getElementsByTagName("department").item(0).getTextContent();
            centrality = element.getElementsByTagName("centrality").item(0).getTextContent();
        }
        catch (Exception e) {
            log.error("ошибка чтения xml");
        }
        return null;
    }
}
