package vn.feylix.repository;
import java.util.List;
public class PageResult_24162099<T>{
    private final List<T> items; private final int page,totalPages;
    public PageResult_24162099(List<T> i,int p,int t){items=i;page=p;totalPages=t;}
    public List<T> getItems(){return items;} public int getPage(){return page;} public int getTotalPages(){return totalPages;}
}
