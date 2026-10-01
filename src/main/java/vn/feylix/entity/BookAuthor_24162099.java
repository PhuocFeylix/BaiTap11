package vn.feylix.entity;
import jakarta.persistence.*;
@Entity @Table(name="book_author")
@IdClass(BookAuthorId_24162099.class)
public class BookAuthor_24162099 {
    @Id @Column(name="bookid") private Long bookId;
    @Id @Column(name="author_id") private Long authorId;
    public BookAuthor_24162099(){}
    public BookAuthor_24162099(Long b,Long a){bookId=b;authorId=a;}
    public Long getBookId(){return bookId;} public void setBookId(Long v){bookId=v;}
    public Long getAuthorId(){return authorId;} public void setAuthorId(Long v){authorId=v;}
}
