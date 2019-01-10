package pqab.service.rss.reader.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pqab.service.rss.reader.entity.RSSFeed;

@Component
public class RSSUtil {

  @Autowired private MappingJackson2XmlHttpMessageConverter httpMessageConverter;

  @Value("${rss.reader.url}")
  private String url;

  public Optional<RSSFeed> fetchRSSFeed() throws MalformedURLException, IOException {
    HttpURLConnection httpcon = (HttpURLConnection) new URL(this.url).openConnection();
    httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
    try (InputStream input = httpcon.getInputStream()) {
      String value = IOUtils.toString(input, "utf-8");
      ObjectMapper objectMapper = httpMessageConverter.getObjectMapper();
      objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
      objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      RSSFeed rssFeed = objectMapper.readValue(value, RSSFeed.class);
      return Optional.of(rssFeed);
    }
  }
}
