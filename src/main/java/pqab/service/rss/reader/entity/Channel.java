package pqab.service.rss.reader.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@DynamoDBDocument
@JacksonXmlRootElement(localName = "channel")
public class Channel {

  @JacksonXmlProperty(localName = "pubDate")
  private String pubDate;

  @JacksonXmlProperty(localName = "title")
  private String title;

  @JacksonXmlProperty(localName = "category")
  private String category;

  @JacksonXmlProperty(localName = "description")
  private String description;

  @JacksonXmlProperty(localName = "lastBuildDate")
  private String lastBuildDate;

  @JacksonXmlElementWrapper(localName = "items", useWrapping = true)
  @JacksonXmlProperty(localName = "item")
  private List<Item> item;

  @JacksonXmlProperty(localName = "language")
  private String language;

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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLastBuildDate() {
    return lastBuildDate;
  }

  public void setLastBuildDate(String lastBuildDate) {
    this.lastBuildDate = lastBuildDate;
  }

  public List<Item> getItem() {
    return item;
  }

  public void setItem(List<Item> item) {
    this.item = item;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return String.format(
        "Channel[pubDate=%s, title=%s, category=%s, description=%s, lastBuildDate=%s, item=%s, language=%s]",
        pubDate, title, category, description, lastBuildDate, item, language);
  }
}
