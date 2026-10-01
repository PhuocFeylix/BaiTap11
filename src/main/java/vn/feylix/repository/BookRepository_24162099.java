package vn.feylix.repository;
import vn.feylix.entity.Book_24162099; import java.util.List;
public interface BookRepository_24162099 {
    PageResult_24162099<Book_24162099> findPage(int page,int size);
    List<Book_24162099> findAll();
    Book_24162099 findById(Long id);
    void save(Book_24162099 b);
    void update(Book_24162099 b);
    void delete(Long id);
    long count();
    List<Book_24162099> findByIds(List<Long> ids);
    double averageRating(Long id); long reviewCount(Long id);
}
