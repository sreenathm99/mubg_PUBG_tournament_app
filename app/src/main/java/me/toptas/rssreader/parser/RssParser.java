package me.toptas.rssreader.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import me.toptas.rssreader.model.RssItem;

public class RssParser extends DefaultHandler {

    private String elementValue = null;
    private boolean elementOn = false;
    private RssItem rssItem;

    private String tempTitle = "";
    private String tempLink;
    private String tempImage;
    private String tempPubdate;
    private String tempDescription;

    private boolean parsingTitle = false;
    private boolean parsingDesc = false;
    private boolean parsingLink = false;

    private final ArrayList<RssItem> items;

    public RssParser() {
        super();
        items = new ArrayList<>();
    }

    public ArrayList<RssItem> getItems() {
        return items;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        elementOn = true;
        if (localName.equals("item")) {
            rssItem = new RssItem();
        } else if (localName.equalsIgnoreCase("title") && !qName.contains("media")) {
            parsingTitle = true;
            tempTitle = "";
        } else if (localName.equalsIgnoreCase("description")) {
            parsingDesc = true;
            tempDescription = "";
        } else if (localName.equalsIgnoreCase("link") && !qName.equals("atom:link")) {
            parsingLink = true;
            tempLink = "";
        }
        if (attributes != null) {
            String url = attributes.getValue("url");
            if (url != null && !url.isEmpty()) {
                tempImage = url;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        elementOn = false;

        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (rssItem != null) {
            if (localName.equalsIgnoreCase("item")) {
                rssItem = new RssItem();
                rssItem.setTitle(tempTitle.trim());
                rssItem.setUrl(tempLink);
                rssItem.setImageUrl(tempImage);
                rssItem.setPubDate(tempPubdate);
                rssItem.setDescription((tempDescription));
                if (tempImage == null && tempDescription != null && getImageSourceFromDescription(tempDescription) != null) {
                    rssItem.setImageUrl(getImageSourceFromDescription(tempDescription));
                }
                items.add(rssItem);
                tempLink = "";
                tempImage = null;
                tempPubdate = "";
                // Log.v("asd","pended: " + tempTitle);

            } else if (localName.equalsIgnoreCase("title") && !qName.contains("media")) {
                // tempTitle = elementValue;
                parsingTitle = false;
                elementValue = "";
                tempTitle = tempTitle.replace("\n", "");
            } else if (localName.equalsIgnoreCase("link")
                    && !elementValue.isEmpty()) {
                // tempLink = elementValue;
                parsingLink = false;
                elementValue = "";
                tempLink = tempLink.replace("\n", "");
            } else if (localName.equalsIgnoreCase("image")
                    || localName.equalsIgnoreCase("url")) {
                if (elementValue != null && !elementValue.isEmpty()) {
                    tempImage = elementValue;
                }
            } else if (localName.equals("pubDate")) {
                tempPubdate = elementValue;
            } else if (localName.equals("description")) {
                parsingDesc = false;
                elementValue = "";
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String buff = new String(ch, start, length);
        if (elementOn) {
            if (buff.length() > 2) {
                elementValue = buff;
                elementOn = false;
            }
        }
        if (parsingTitle) {
            tempTitle = tempTitle + buff;
        }
        if (parsingDesc) {
            tempDescription = tempDescription + buff;
        }
        if (parsingLink) {
            tempLink = tempLink + buff;
        }
    }


    private String getImageSourceFromDescription(String description) {
        if (description.contains("<img") && description.contains("src")) {
            String[] parts = description.split("src=\"");
            if (parts.length == 2 && parts[1].length() > 0) {
                String src = parts[1].substring(0, parts[1].indexOf("\""));
                String[] srcParts = src.split("http");
                if (srcParts.length > 2) {
                    src = "http" + srcParts[2];
                }
                return src;
            }
        }
        return null;
    }
}