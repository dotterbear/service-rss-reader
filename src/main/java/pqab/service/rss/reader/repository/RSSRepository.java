package pqab.service.rss.reader.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import pqab.service.rss.reader.entity.RSSFeed;

@EnableScan
public interface RSSRepository extends CrudRepository<RSSFeed, String> {
  Optional<RSSFeed> findById(String id);
}
