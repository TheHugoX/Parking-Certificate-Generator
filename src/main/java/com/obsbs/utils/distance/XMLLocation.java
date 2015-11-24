package com.obsbs.utils.distance;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLLocation {

    public XMLLocation() {

    }

    public String GetGPSPos(String XMLstring) {
        try {

            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XMLstring);
            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("gml:pos");


            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    return eElement.getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
