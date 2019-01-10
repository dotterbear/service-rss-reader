package pqab.service.rss.reader.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pqab.service.rss.reader.entity.RSSFeed;
import pqab.service.rss.reader.repository.RSSRepository;
import pqab.service.rss.reader.util.RSSUtil;

@Component
public class RSSManager {

  @Autowired private RSSRepository rssRepository;

  @Autowired private RSSUtil rssUtil;

  public Optional<RSSFeed> fetchRSSFeed() throws MalformedURLException, IOException {
    Optional<RSSFeed> rssFeed = rssUtil.fetchRSSFeed();
    Optional.ofNullable(rssFeed).map(o -> rssRepository.save(o.get()));
    return rssFeed;
  }
}
