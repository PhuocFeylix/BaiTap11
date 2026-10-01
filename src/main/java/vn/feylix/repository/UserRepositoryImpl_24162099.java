package vn.feylix.repository;
import jakarta.persistence.*;
import vn.feylix.entity.User_24162099; import vn.feylix.util.JpaUtils_24162099;
public class UserRepositoryImpl_24162099 implements UserRepository_24162099{
    public User_24162099 findByEmail(String email){EntityManager em=JpaUtils_24162099.getEntityManager();try{return em.createQuery("select u from User_24162099 u where lower(u.email)=lower(:e)",User_24162099.class).setParameter("e",email).getResultStream().findFirst().orElse(null);}finally{em.close();}}
    public void save(User_24162099 u){tx(u,true);}
    public void update(User_24162099 u){tx(u,false);}
    public void deleteByEmail(String email){
        EntityManager em=JpaUtils_24162099.getEntityManager();
        EntityTransaction t=em.getTransaction();
        try{
            t.begin();
            User_24162099 u=em.createQuery("select u from User_24162099 u where lower(u.email)=lower(:e)",User_24162099.class)
                    .setParameter("e",email).getResultStream().findFirst().orElse(null);
            if(u!=null) em.remove(u);
            t.commit();
        }catch(Exception e){if(t.isActive())t.rollback();throw e;}finally{em.close();}
    }
    private void tx(User_24162099 u,boolean insert){EntityManager em=JpaUtils_24162099.getEntityManager();EntityTransaction t=em.getTransaction();try{t.begin();if(insert)em.persist(u);else em.merge(u);t.commit();}catch(Exception e){if(t.isActive())t.rollback();throw e;}finally{em.close();}}
}
