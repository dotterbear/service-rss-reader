package pqab.service.rss.reader.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@DynamoDBDocument
public class Item {

  @JacksonXmlProperty(localName = "guid")
  private String guid;

  @JacksonXmlProperty(localName = "pubDate")
  private String pubDate;

  @JacksonXmlCData
  @JacksonXmlProperty(localName = "title")
  private String title;

  @JacksonXmlCData
  @JacksonXmlProperty(localName = "description")
  private String description;

  @JacksonXmlProperty(localName = "link")
  private String link;

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public String toString() {
    return String.format(
        "Item[guid=%s, pubDate=%s, title=%s, description=%s, link=%s]",
        guid, pubDate, title, description, link);
  }
}
