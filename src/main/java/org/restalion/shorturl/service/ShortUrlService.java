package org.restalion.shorturl.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;

import org.apache.commons.validator.UrlValidator;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.restalion.shorturl.entity.ShortenUrlEntity;
import org.restalion.shorturl.repository.ShortenUrlRepository;

@ApplicationScoped
public class ShortUrlService {

    @ConfigProperty(name = "shorturl.baseurl") 
    String baseUrl;

    @ConfigProperty(name = "shorturl.digits") 
    Integer digits;

    @Inject
    ShortenUrlRepository repo;

    public String createShortenUrl(String actualUrl) throws ValidationException {

        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(actualUrl))
            throw new ValidationException();

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
            randomString = new RandomString(digits);
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