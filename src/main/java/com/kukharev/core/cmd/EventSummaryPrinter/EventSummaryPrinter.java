/*
A Java console program that reads an XML file containing a list of agricultural events, extracts the event details, and prints out a formatted summary of each event to the console.
 */

package com.kukharev.core.cmd.EventSummaryPrinter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventSummaryPrinter {

    /**
     * Parses the provided XML content and prints a summary of each event.
     *
     * @param xmlContent the XML content as a string
     */
    public static void printEventSummary(String xmlContent) {
        try {
            // Create a DocumentBuilderFactory and DocumentBuilder to parse the XML content
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Convert the XML content string into an InputStream
            InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());

            // Parse the InputStream to obtain a Document object representing the XML content
            Document document = builder.parse(inputStream);

            // Get a NodeList of all <event> elements in the XML
            NodeList eventNodes = document.getElementsByTagName("event");

            // Iterate over each <event> element
            for (int i = 0; i < eventNodes.getLength(); i++) {
                // Get the current <event> element
                Element eventElement = (Element) eventNodes.item(i);

                // Extract the text content of the <name>, <date>, and <location> elements
                String name = eventElement.getElementsByTagName("name").item(0).getTextContent();
                String dateString = eventElement.getElementsByTagName("date").item(0).getTextContent();
                String location = eventElement.getElementsByTagName("location").item(0).getTextContent();

                // Define the input and output date formats
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

                // Parse the date string into a Date object
                Date date = inputFormat.parse(dateString);

                // Format the Date object into the desired output format
                String formattedDate = outputFormat.format(date);

                // Print the event details in the specified format
                System.out.println("Event: " + name);
                System.out.println("Date: " + formattedDate);
                System.out.println("Location: " + location);
                System.out.println();
            }
        } catch (Exception e) {
            // Print stack trace if an exception occurs during XML parsing or date formatting
            e.printStackTrace();
        }
    }
}