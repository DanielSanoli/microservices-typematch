package br.com.typematch.dto;

public class NamedApiDTO {
    private String name;
    private String url;
    public NamedApiDTO() {}

    public String getName() { return name; }
    public String getUrl() { return url; }

    public void setName(String name) { this.name = name; }
    public void setUrl(String url) { this.url = url; }
}