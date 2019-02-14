package com.dotterbear.service.rss.reader.manager;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dotterbear.service.rss.reader.entity.RSSFeed;
import com.dotterbear.service.rss.reader.repository.RSSRepository;
import com.dotterbear.service.rss.reader.util.RSSUtil;

@Component
public class RSSManager {

  @Autowired private RSSRepository rssRepository;

  @Autowired private RSSUtil rssUtil;

  public Optional<RSSFeed> fetchRSSFeed() throws IOException {
    Optional<RSSFeed> rssFeedOptional = rssUtil.fetchRSSFeed();
    RSSFeed rssFeed = rssFeedOptional.isPresent() ? rssFeedOptional.get() : null;
    rssFeed.setCreateDate(new Date());
    rssFeed.setLastModifiedDate(new Date());
    return Optional.ofNullable(Optional.ofNullable(rssFeed).map(o -> rssRepository.save(o)).get());
  }
}
