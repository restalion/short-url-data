package org.restalion.shorturl.entity;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="ShortenUrl")
public class ShortenUrlEntity extends PanacheMongoEntity {

    @BsonId
    public String shortenCode;
    public String actualUrl;


    public String getActualUrl() {
        return actualUrl;
    }

    public void setActualUrl(String actualUrl) {
        this.actualUrl = actualUrl;
    }

    public String getShortenCode() {
        return shortenCode;
    }

    public void setShortenCode(String shortenCode) {
        this.shortenCode = shortenCode;
    }
}