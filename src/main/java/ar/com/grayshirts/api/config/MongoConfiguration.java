package ar.com.grayshirts.api.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.UnknownHostException;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;


/**
 * MongoDB configuration.
 */
@Configuration
public class MongoConfiguration {

    private static Logger log = LoggerFactory.getLogger(MongoConfiguration.class);

    @Value("${mongo.endpoints}")
    private String mongoEndpoints;

    @Value("${mongo.dbname}")
    private String mongoDbName;

    @Bean
    @ConditionalOnMissingBean
    public Datastore mongoDbDataStore() throws UnknownHostException {
        log.info("Connecting with MongoDB. dbname=" + mongoDbName + ", addresses="  + mongoEndpoints);
        MongoClient client = new MongoClient(stream(mongoEndpoints.split(",")).map(ServerAddress::new).collect(toList()));
        Datastore ds = new Morphia().mapPackage("ar.com.grayshirts.api.model").createDatastore(client, mongoDbName);
        ds.ensureIndexes();
        return ds;
    }
}
