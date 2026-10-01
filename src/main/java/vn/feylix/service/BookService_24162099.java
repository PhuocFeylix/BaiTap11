package vn.feylix.service;
import vn.feylix.entity.*; import vn.feylix.repository.*; import java.util.List;
public interface BookService_24162099 {
    PageResult_24162099<Book_24162099> findPage(int page,int size);
    Book_24162099 findById(Long id); List<Book_24162099> findAll(); List<Author_24162099> findAuthors();
    void save(Book_24162099 b,List<Long> authorIds); void update(Book_24162099 b,List<Long> authorIds); void delete(Long id);
}
