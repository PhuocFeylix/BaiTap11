package vn.feylix.util;

import jakarta.persistence.*;

public final class JpaUtils_24162099 {
	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("ExamWebPU");

	private JpaUtils_24162099() {
	}

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
