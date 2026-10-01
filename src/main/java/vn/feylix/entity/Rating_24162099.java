package vn.feylix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
@IdClass(RatingId_24162099.class)
public class Rating_24162099 {
	@Id
	@Column(name = "userid")
	private Long userId;
	@Id
	@Column(name = "bookid")
	private Long bookId;
	private int rating;
	@Column(name = "review_text")
	private String reviewText;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User_24162099 user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookid", insertable = false, updatable = false)
	private Book_24162099 book;

	public Rating_24162099() {
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

	public int getRating() {
		return rating;
	}

	public void setRating(int v) {
		rating = v;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String v) {
		reviewText = v;
	}

	public User_24162099 getUser() {
		return user;
	}

	public void setUser(User_24162099 v) {
		user = v;
	}

	public Book_24162099 getBook() {
		return book;
	}

	public void setBook(Book_24162099 v) {
		book = v;
	}
}
