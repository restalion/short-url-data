package org.restalion.shorturl.service;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.restalion.shorturl.entity.ShortenUrlEntity;
import org.restalion.shorturl.repository.ShortenUrlRepository;

@ApplicationScoped
public class ShortUrlService {

    @ConfigProperty(name = "shorturl.baseurl") 
    String baseUrl;

    @Inject
    ShortenUrlRepository repo;

    public String createShortenUrl(String actualUrl) {

        String shortenCode = generateUniqueShortenValue();

        ShortenUrlEntity shortenUrl = new ShortenUrlEntity();
        shortenUrl.setActualUrl(actualUrl);
        shortenUrl.setShortenCode(shortenCode);

        repo.persist(shortenUrl);

        return createUrl(shortenCode);
    }

    private String createUrl(String shortenCode) {
        return baseUrl + "/" + shortenCode;
    }

    private String generateUniqueShortenValue() {

        String generatedString = null;
        ShortenUrlEntity shortenUrl = null;
        RandomString randomString;
        do  {
            randomString = new RandomString(16);
            generatedString = randomString.nextString();
            shortenUrl = repo.findById(generatedString);
        } while (shortenUrl != null);

        return generatedString;
    }

    public String getActualUrl(String shortenUrl) {
        ShortenUrlEntity entity = repo.findById(shortenUrl);
        if (entity != null) {
            return entity.getActualUrl();
        } else {
            return null;
        }
    }
}