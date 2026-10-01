package vn.feylix.repository;

import jakarta.persistence.*;
import vn.feylix.entity.*;
import vn.feylix.util.JpaUtils_24162099;
import java.util.*;

public class BookRepositoryImpl_24162099 implements BookRepository_24162099 {
	private void close(EntityManager e) {
		e.close();
	}

	public PageResult_24162099<Book_24162099> findPage(int page, int size) {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			long total = e.createQuery("select count(b) from Book_24162099 b", Long.class).getSingleResult();
			int pages = (int) Math.ceil(total / (double) size);
			page = Math.max(1, Math.min(page, Math.max(1, pages)));
			List<Book_24162099> list = e
					.createQuery("select distinct b from Book_24162099 b order by b.publishDate desc, b.id desc", Book_24162099.class)
					.setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
			for (Book_24162099 b : list) {
				b.setAverageRating(avg(e, b.getId()));
				b.setReviewCount(countReviews(e, b.getId()));
			}
			return new PageResult_24162099<>(list, page, pages);
		} finally {
			close(e);
		}
	}

	public List<Book_24162099> findAll() {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			return e.createQuery("select b from Book_24162099 b order by b.publishDate desc, b.id desc", Book_24162099.class)
					.getResultList();
		} finally {
			close(e);
		}
	}

	public Book_24162099 findById(Long id) {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			Book_24162099 b = e.find(Book_24162099.class, id);
			if (b != null) {
				b.getAuthors().size();
				b.setAverageRating(avg(e, id));
				b.setReviewCount(countReviews(e, id));
			}
			return b;
		} finally {
			close(e);
		}
	}

	public void save(Book_24162099 b) {
		tx(b, true);
	}

	public void update(Book_24162099 b) {
		tx(b, false);
	}

	public void delete(Long id) {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		EntityTransaction t = e.getTransaction();
		try {
			t.begin();
			Book_24162099 b = e.find(Book_24162099.class, id);
			if (b != null)
				e.remove(b);
			t.commit();
		} catch (Exception x) {
			if (t.isActive())
				t.rollback();
			throw x;
		} finally {
			close(e);
		}
	}

	private void tx(Book_24162099 b, boolean ins) {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		EntityTransaction t = e.getTransaction();
		try {
			t.begin();
			if (ins)
				e.persist(b);
			else
				e.merge(b);
			t.commit();
		} catch (Exception x) {
			if (t.isActive())
				t.rollback();
			throw x;
		} finally {
			close(e);
		}
	}

	public long count() {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			return e.createQuery("select count(b) from Book_24162099 b", Long.class).getSingleResult();
		} finally {
			close(e);
		}
	}

	public List<Book_24162099> findByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty())
			return List.of();
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			return e.createQuery("select b from Book_24162099 b where b.id in :ids", Book_24162099.class)
					.setParameter("ids", ids).getResultList();
		} finally {
			close(e);
		}
	}

	public double averageRating(Long id) {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			return avg(e, id);
		} finally {
			close(e);
		}
	}

	public long reviewCount(Long id) {
		EntityManager e = JpaUtils_24162099.getEntityManager();
		try {
			return countReviews(e, id);
		} finally {
			close(e);
		}
	}

	private double avg(EntityManager e, Long id) {
		Double v = e.createQuery("select avg(r.rating) from Rating_24162099 r where r.bookId=:id", Double.class)
				.setParameter("id", id).getSingleResult();
		return v == null ? 0 : v;
	}

	private long countReviews(EntityManager e, Long id) {
		return e.createQuery("select count(r) from Rating_24162099 r where r.bookId=:id", Long.class)
				.setParameter("id", id).getSingleResult();
	}
}
