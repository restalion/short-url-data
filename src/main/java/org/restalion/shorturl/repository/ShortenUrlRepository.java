package org.restalion.shorturl.repository;

import javax.enterprise.context.ApplicationScoped;

import org.restalion.shorturl.entity.ShortenUrlEntity;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

@ApplicationScoped
public class ShortenUrlRepository implements PanacheMongoRepositoryBase<ShortenUrlEntity, String> {

}