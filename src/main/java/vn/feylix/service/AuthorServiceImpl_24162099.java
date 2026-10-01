package vn.feylix.service;
import vn.feylix.entity.Author_24162099; import vn.feylix.repository.*;
public class AuthorServiceImpl_24162099 implements AuthorService_24162099{
    private final AuthorRepository_24162099 repo=new AuthorRepositoryImpl_24162099();
    public PageResult_24162099<Author_24162099> findPage(int p,int s){return repo.findPage(p,s);} public Author_24162099 findById(Long id){return repo.findById(id);}
    public void save(Author_24162099 a){repo.save(a);} public void update(Author_24162099 a){repo.update(a);} public void delete(Long id){repo.delete(id);}
}
