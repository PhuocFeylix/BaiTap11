package vn.feylix.service;
import vn.feylix.entity.*; import vn.feylix.repository.*; import jakarta.persistence.*; import vn.feylix.util.JpaUtils_24162099; import java.util.*;
public class BookServiceImpl_24162099 implements BookService_24162099{
    private final BookRepository_24162099 books=new BookRepositoryImpl_24162099(); private final AuthorRepository_24162099 authors=new AuthorRepositoryImpl_24162099();
    public PageResult_24162099<Book_24162099> findPage(int p,int s){return books.findPage(p,s);} public Book_24162099 findById(Long id){return books.findById(id);}
    public List<Book_24162099> findAll(){return books.findAll();} public List<Author_24162099> findAuthors(){return authors.findAll();}
    public void save(Book_24162099 b,List<Long> ids){books.save(b);syncAuthors(b.getId(),ids);}
    public void update(Book_24162099 b,List<Long> ids){books.update(b);syncAuthors(b.getId(),ids);}
    public void delete(Long id){books.delete(id);}
    private void syncAuthors(Long bookId,List<Long> ids){EntityManager e=JpaUtils_24162099.getEntityManager();EntityTransaction t=e.getTransaction();try{t.begin();e.createQuery("delete from BookAuthor_24162099 x where x.bookId=:id").setParameter("id",bookId).executeUpdate();if(ids!=null)for(Long a:ids)e.persist(new BookAuthor_24162099(bookId,a));t.commit();}catch(Exception x){if(t.isActive())t.rollback();throw x;}finally{e.close();}}
}
