package org.restalion.shorturl.dto;

/**
 * Output message asking for an actual Url to be translated in a shorten one.
 */
public class ShortenUrlDto {
    private String actualUrl;
    private String shortenUrl;

    public String getActualUrl() {
        return actualUrl;
    }

    public void setActualUrl(String actualUrl) {
        this.actualUrl = actualUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    
}