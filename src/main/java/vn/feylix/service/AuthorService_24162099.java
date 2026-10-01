package vn.feylix.service;
import vn.feylix.entity.Author_24162099; import vn.feylix.repository.PageResult_24162099;
public interface AuthorService_24162099 {PageResult_24162099<Author_24162099> findPage(int page,int size);Author_24162099 findById(Long id);void save(Author_24162099 a);void update(Author_24162099 a);void delete(Long id);}
