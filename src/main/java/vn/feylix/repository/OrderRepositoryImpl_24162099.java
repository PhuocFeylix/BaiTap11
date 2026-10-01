package vn.feylix.repository;

import jakarta.persistence.*;
import vn.feylix.entity.*;
import vn.feylix.util.JpaUtils_24162099;
import java.util.List;

public class OrderRepositoryImpl_24162099 implements OrderRepository_24162099 {
    public void save(Order_24162099 order) {
        EntityManager em = JpaUtils_24162099.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(order);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    public List<Order_24162099> findByUser(Long userId, OrderStatus_24162099 status) {
        EntityManager em = JpaUtils_24162099.getEntityManager();
        try {
            String jpql = "select distinct o from Order_24162099 o left join fetch o.items i " +
                    "left join fetch i.book where o.user.id=:uid";
            if (status != null) jpql += " and o.status=:status";
            jpql += " order by o.orderDate desc, o.id desc";
            TypedQuery<Order_24162099> q = em.createQuery(jpql, Order_24162099.class)
                    .setParameter("uid", userId);
            if (status != null) q.setParameter("status", status);
            return q.getResultList();
        } finally { em.close(); }
    }

    public Order_24162099 findByIdAndUser(Long orderId, Long userId) {
        EntityManager em = JpaUtils_24162099.getEntityManager();
        try {
            return em.createQuery("select distinct o from Order_24162099 o left join fetch o.items i " +
                    "left join fetch i.book where o.id=:oid and o.user.id=:uid", Order_24162099.class)
                    .setParameter("oid", orderId).setParameter("uid", userId)
                    .getResultStream().findFirst().orElse(null);
        } finally { em.close(); }
    }
}
