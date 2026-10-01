package vn.feylix.entity;

import java.io.Serializable;
import java.util.Objects;
import vn.feylix.entity.*;

public class RatingId_24162099 implements Serializable {
	private Long userId;
	private Long bookId;

	public RatingId_24162099() {
	}

	public RatingId_24162099(Long u, Long b) {
		userId = u;
		bookId = b;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long v) {
		userId = v;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long v) {
		bookId = v;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RatingId_24162099 x))
			return false;
		return Objects.equals(userId, x.userId) && Objects.equals(bookId, x.bookId);
	}

	public int hashCode() {
		return Objects.hash(userId, bookId);
	}
}
