package pqab.service.rss.reader.manager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pqab.service.rss.reader.entity.Channel;
import pqab.service.rss.reader.entity.Item;
import pqab.service.rss.reader.entity.RSSFeed;
import pqab.service.rss.reader.repository.RSSRepository;
import pqab.service.rss.reader.util.RSSUtil;

@Component
public class RSSManager {

  @Autowired private RSSRepository rssRepository;

  @Autowired private RSSUtil rssUtil;

  public Optional<RSSFeed> fetchRSSFeed() throws MalformedURLException, IOException {
    RSSFeed rssFeed = rssUtil.fetchRSSFeed().get();
    List<Item> rssFeedItems =
        Optional.ofNullable(rssFeed)
            .map(RSSFeed::getChannel)
            .map(Channel::getItem)
            .orElse(new ArrayList<Item>());
    Set<String> rssFeedLinks = rssFeedItems.stream().map(Item::getLink).collect(Collectors.toSet());

    RSSFeed latestRSSFeed = rssRepository.findByCreateDate().orElse(new RSSFeed());
    List<Item> latestRSSFeedItems =
        Optional.ofNullable(latestRSSFeed)
            .map(RSSFeed::getChannel)
            .map(Channel::getItem)
            .orElse(new ArrayList<Item>());
    Set<String> latestRSSFeedLinks =
        latestRSSFeedItems.stream().map(Item::getLink).collect(Collectors.toSet());

    rssFeedLinks.removeAll(latestRSSFeedLinks);
    return rssFeedLinks.size() == 0
        ? Optional.empty()
        : Optional.ofNullable(Optional.ofNullable(rssFeed).map(o -> rssRepository.save(o)).get());
  }
}
