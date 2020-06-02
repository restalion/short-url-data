package org.restalion.shorturl.dto;

/**
 * Input message asking for an actual Url to be translated in a shorten one.
 */
public class ShortUrlRequestDto {
    private String actualUrl;

    public String getActualUrl() {
        return actualUrl;
    }

    public void setActualUrl(String actualUrl) {
        this.actualUrl = actualUrl;
    }
}
    