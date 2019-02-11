package dotterbear.service.rss.reader.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dotterbear.service.rss.reader.entity.RSSFeed;
import dotterbear.service.rss.reader.util.ApplicationContextUtil;

@Repository
public interface RSSRepository extends CrudRepository<RSSFeed, String> {

  Optional<RSSFeed> findById(String id);

  default Optional<RSSFeed> findByCreateDate() {
    MongoOperations mongoOperation =
        (MongoOperations)
            ApplicationContextUtil.getApplicationContext().getBean(MongoTemplate.class);
    Query query = new Query();
    query.limit(1);
    query.with(new Sort(Sort.Direction.DESC, "createDate"));
    List<RSSFeed> latestRSSFeeds = mongoOperation.find(query, RSSFeed.class);
    if (latestRSSFeeds.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.ofNullable(latestRSSFeeds.get(0));
    }
  }
}
