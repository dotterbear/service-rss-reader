package dotterbear.service.rss.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

import dotterbear.service.rss.reader.entity.RSSFeed;

@SpringBootApplication
public class ServiceRSSReaderApplication {

  public static void main(String[] args) {

    ConfigurableApplicationContext context =
        SpringApplication.run(ServiceRSSReaderApplication.class, args);
    initializeTables(context.getBean(AmazonDynamoDB.class));
  }

  private static void initializeTables(AmazonDynamoDB amazonDynamoDB) {
    DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(RSSFeed.class);
    tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
    TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
  }
}
