package br.com.typematch.dto;

import java.util.List;

public class TypeListResponseDTO {
    private int count;
    private String next;
    private String previous;
    private List<NamedApiDTO> results;

    public TypeListResponseDTO() {}

    public int getCount() { return count; }
    public String getNext() { return next; }
    public String getPrevious() { return previous; }
    public List<NamedApiDTO> getResults() { return results; }

    public void setCount(int count) { this.count = count; }
    public void setNext(String next) { this.next = next; }
    public void setPrevious(String previous) { this.previous = previous; }
    public void setResults(List<NamedApiDTO> results) { this.results = results; }
}
