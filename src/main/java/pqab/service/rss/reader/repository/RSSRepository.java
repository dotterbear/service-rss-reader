package pqab.service.rss.reader.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import pqab.service.rss.reader.entity.RSSFeed;
import pqab.service.rss.reader.util.ApplicationContextUtil;

@Repository
public interface RSSRepository extends CrudRepository<RSSFeed, String> {

  Optional<RSSFeed> findById(String id);

  default Optional<RSSFeed> findByCreateDate() {
    DynamoDBMapper mapper =
        new DynamoDBMapper(
            ApplicationContextUtil.applicationContext.getBean(AmazonDynamoDB.class),
            ApplicationContextUtil.applicationContext.getBean(DynamoDBMapperConfig.class));

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    String today = dateFormatter.format(new Date());

    Map<String, String> attributeNames = new HashMap<String, String>();
    attributeNames.put("#createDate", "createDate");

    Map<String, AttributeValue> attributeValues = new HashMap<String, AttributeValue>();
    attributeValues.put(":today", new AttributeValue().withS(today.toString()));

    DynamoDBScanExpression scanExpression =
        new DynamoDBScanExpression()
            .withFilterExpression("#createDate < :today")
            .withExpressionAttributeNames(attributeNames)
            .withExpressionAttributeValues(attributeValues)
            .withLimit(1);
    List<RSSFeed> latestRSSFeeds = mapper.scan(RSSFeed.class, scanExpression);
    if (latestRSSFeeds.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.ofNullable(latestRSSFeeds.get(0));
    }
  }
}
