package com.dotterbear.service.rss.reader.entity;

import java.util.Date;
import com.dotterbear.service.rss.reader.json.deserializer.JobsDbRssFeedDateTimeDeserializer;
import com.dotterbear.service.rss.reader.json.serializer.JsonDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Item {

  @JacksonXmlProperty(localName = "guid")
  private String guid;

  @JacksonXmlProperty(localName = "pubDate")
  @JsonDeserialize(using = JobsDbRssFeedDateTimeDeserializer.class)
  @JsonSerialize(using = JsonDateTimeSerializer.class)
  private Date pubDate;

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

  public Date getPubDate() {
    return pubDate;
  }

  public void setPubDate(Date pubDate) {
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
