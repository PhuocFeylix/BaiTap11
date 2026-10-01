package vn.feylix.repository;
import jakarta.persistence.*; import vn.feylix.entity.*; import vn.feylix.util.JpaUtils_24162099; import java.util.*;
public class RatingRepositoryImpl_24162099 implements RatingRepository_24162099{
    public List<Rating_24162099> findByBook(Long id){EntityManager e=JpaUtils_24162099.getEntityManager();try{return e.createQuery("select r from Rating_24162099 r join fetch r.user where r.bookId=:id order by r.bookId",Rating_24162099.class).setParameter("id",id).getResultList();}finally{e.close();}}
    public void saveOrUpdate(Rating_24162099 r){EntityManager e=JpaUtils_24162099.getEntityManager();EntityTransaction t=e.getTransaction();try{t.begin();Rating_24162099 old=e.find(Rating_24162099.class,new RatingId_24162099(r.getUserId(),r.getBookId()));if(old==null)e.persist(r);else{old.setRating(r.getRating());old.setReviewText(r.getReviewText());}t.commit();}catch(Exception x){if(t.isActive())t.rollback();throw x;}finally{e.close();}}
}
