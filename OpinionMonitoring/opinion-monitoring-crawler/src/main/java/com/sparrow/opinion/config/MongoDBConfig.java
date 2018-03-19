package com.sparrow.opinion.config;

import com.google.common.collect.Lists;
import com.mongodb.*;
import com.sparrow.opinion.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.List;

/**
 * MongoDBConfig
 */
@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String ip;
    @Value("${spring.data.mongodb.port}")
    private int port;
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() throws Exception {
//		return new MongoClient(ip, port);

        List<ServerAddress> serverAddressList = Lists.newArrayList();
        if (ip.indexOf(",") != -1) {
            String[] ipArray = ip.split(",");
            for (String ipItem : ipArray) {
                if (StringUtils.isNotEmpty(ipItem)) {
                    ServerAddress serverAddress = new ServerAddress(ipItem, port);
                    serverAddressList.add(serverAddress);
                }
            }
        } else {
            ServerAddress serverAddress = new ServerAddress(ip, port);
            serverAddressList.add(serverAddress);
        }

        MongoCredential credential = MongoCredential.createCredential(
                username, database, password.toCharArray());
        List<MongoCredential> credentialsList = Lists.newArrayList();
        credentialsList.add(credential);
        MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder().build();
        MongoClient mongoClient = new MongoClient(serverAddressList, credentialsList,
                mongoClientOptions);

        return mongoClient;
    }

    // 都只是为了去除“_class”字段
    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter mmc = super.mappingMongoConverter();
        mmc.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mmc;
    }

}
