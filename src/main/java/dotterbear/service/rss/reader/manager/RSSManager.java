package dotterbear.service.rss.reader.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dotterbear.service.rss.reader.entity.Channel;
import dotterbear.service.rss.reader.entity.Item;
import dotterbear.service.rss.reader.entity.RSSFeed;
import dotterbear.service.rss.reader.repository.RSSRepository;
import dotterbear.service.rss.reader.util.RSSUtil;

@Component
public class RSSManager {

	@Autowired
	private RSSRepository rssRepository;

	@Autowired
	private RSSUtil rssUtil;

	public Optional<RSSFeed> fetchRSSFeed() throws IOException {
		Optional<RSSFeed> rssFeedOptional = rssUtil.fetchRSSFeed();
		RSSFeed rssFeed = rssFeedOptional.isPresent() ? rssFeedOptional.get() : null;
		List<Item> rssFeedItems = Optional.ofNullable(rssFeed).map(RSSFeed::getChannel).map(Channel::getItem)
				.orElse(new ArrayList<Item>());
		Set<String> rssFeedLinks = rssFeedItems.stream().map(Item::getLink).collect(Collectors.toSet());

		RSSFeed latestRSSFeed = rssRepository.findByCreateDate().orElse(new RSSFeed());
		List<Item> latestRSSFeedItems = Optional.ofNullable(latestRSSFeed).map(RSSFeed::getChannel)
				.map(Channel::getItem).orElse(new ArrayList<Item>());
		Set<String> latestRSSFeedLinks = latestRSSFeedItems.stream().map(Item::getLink).collect(Collectors.toSet());

		rssFeedLinks.removeAll(latestRSSFeedLinks);
		return rssFeedLinks.isEmpty() ? Optional.empty()
				: Optional.ofNullable(Optional.ofNullable(rssFeed).map(o -> rssRepository.save(o)).get());
	}
}
