/*
A Java program that simulates a simple physics experiment.
The program reads an XML file containing a list of objects with their masses and initial velocities.
It then calculates the final velocity of each object after a perfectly elastic collision with a wall and writes the results to a new XML file.
Assume all objects move along a straight line and the wall is fixed and immovable.
 */

package com.kukharev.core.JavaFile.PhysicsSimulation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

public class PhysicsSimulation {

    /**
     * Reads the initial conditions (masses and velocities) from an XML file.
     *
     * @param filePath The path to the XML file.
     * @return A map of object IDs to their initial velocities.
     */
    public Map<String, Double> readInitialConditions(String filePath) {
        Map<String, Double> initialConditions = new HashMap<>();
        try {
            // Load and parse the XML file
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Get all <object> elements
            NodeList nList = doc.getElementsByTagName("object");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // Extract the ID and velocity of the object
                    String id = eElement.getAttribute("id");
                    double velocity = Double.parseDouble(eElement.getElementsByTagName("velocity").item(0).getTextContent());
                    initialConditions.put(id, velocity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initialConditions;
    }

    /**
     * Calculates the final velocities of objects after a perfectly elastic collision with a wall.
     *
     * @param initialConditions A map of object IDs to their initial velocities.
     * @return A map of object IDs to their final velocities.
     */
    public Map<String, Double> calculateFinalVelocities(Map<String, Double> initialConditions) {
        Map<String, Double> finalVelocities = new HashMap<>();
        for (Map.Entry<String, Double> entry : initialConditions.entrySet()) {
            // The final velocity is the negative of the initial velocity (perfectly elastic collision)
            finalVelocities.put(entry.getKey(), -entry.getValue());
        }
        return finalVelocities;
    }

    /**
     * Writes the final velocities of objects to an XML file.
     *
     * @param filePath The path to the output XML file.
     * @param finalVelocities A map of object IDs to their final velocities.
     */
    public void writeFinalVelocities(String filePath, Map<String, Double> finalVelocities) {
        try {
            // Create a new XML document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create the root element <objects>
            Element rootElement = doc.createElement("objects");
            doc.appendChild(rootElement);

            // Append each object's final velocity to the document
            for (Map.Entry<String, Double> entry : finalVelocities.entrySet()) {
                Element object = doc.createElement("object");
                object.setAttribute("id", entry.getKey());
                Element velocity = doc.createElement("velocity");
                velocity.appendChild(doc.createTextNode(entry.getValue().toString()));
                object.appendChild(velocity);
                rootElement.appendChild(object);
            }

            // Write the content into the XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
