package vn.feylix.repository;
import jakarta.persistence.*; import vn.feylix.entity.Author_24162099; import vn.feylix.util.JpaUtils_24162099; import java.util.*;
public class AuthorRepositoryImpl_24162099 implements AuthorRepository_24162099{
    public PageResult_24162099<Author_24162099> findPage(int page,int size){EntityManager e=JpaUtils_24162099.getEntityManager();try{long total=e.createQuery("select count(a) from Author_24162099 a",Long.class).getSingleResult();int pages=(int)Math.ceil(total/(double)size);page=Math.max(1,Math.min(page,Math.max(1,pages)));List<Author_24162099> list=e.createQuery("select a from Author_24162099 a order by a.id desc",Author_24162099.class).setFirstResult((page-1)*size).setMaxResults(size).getResultList();return new PageResult_24162099<>(list,page,pages);}finally{e.close();}}
    public List<Author_24162099> findAll(){EntityManager e=JpaUtils_24162099.getEntityManager();try{return e.createQuery("select a from Author_24162099 a order by a.id desc",Author_24162099.class).getResultList();}finally{e.close();}}
    public Author_24162099 findById(Long id){EntityManager e=JpaUtils_24162099.getEntityManager();try{return e.find(Author_24162099.class,id);}finally{e.close();}}
    public void save(Author_24162099 a){tx(a,true);} public void update(Author_24162099 a){tx(a,false);}
    public void delete(Long id){EntityManager e=JpaUtils_24162099.getEntityManager();EntityTransaction t=e.getTransaction();try{t.begin();Author_24162099 a=e.find(Author_24162099.class,id);if(a!=null)e.remove(a);t.commit();}catch(Exception x){if(t.isActive())t.rollback();throw x;}finally{e.close();}}
    private void tx(Author_24162099 a,boolean ins){EntityManager e=JpaUtils_24162099.getEntityManager();EntityTransaction t=e.getTransaction();try{t.begin();if(ins)e.persist(a);else e.merge(a);t.commit();}catch(Exception x){if(t.isActive())t.rollback();throw x;}finally{e.close();}}
}
