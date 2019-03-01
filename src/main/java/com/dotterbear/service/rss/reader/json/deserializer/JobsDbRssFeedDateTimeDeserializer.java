package com.dotterbear.service.rss.reader.json.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JobsDbRssFeedDateTimeDeserializer extends JsonDeserializer<Date> {

  private static final Logger log =
      LoggerFactory.getLogger(JobsDbRssFeedDateTimeDeserializer.class);

  @Value("${com.dotterbear.rss.reader.deserializer.jobsdb.date-time}")
  private String dateFormat;

  @Override
  public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException, JsonProcessingException {
    String str = jsonparser.getText();
    try {
      return new SimpleDateFormat(dateFormat).parse(str);
    } catch (ParseException e) {
      log.error("unable to parse string to date, str: {}, dateFormat: {}", str, dateFormat, e);
      throw new IOException("unable to parse string to date");
    }
  }

}
