package vn.feylix.repository;
import vn.feylix.entity.Author_24162099; import java.util.List;
public interface AuthorRepository_24162099 {
    PageResult_24162099<Author_24162099> findPage(int page,int size);
    List<Author_24162099> findAll(); Author_24162099 findById(Long id);
    void save(Author_24162099 a); void update(Author_24162099 a); void delete(Long id);
}
