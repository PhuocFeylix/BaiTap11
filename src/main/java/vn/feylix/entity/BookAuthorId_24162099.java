package vn.feylix.entity;
import java.io.Serializable;
import java.util.Objects;
public class BookAuthorId_24162099 implements Serializable {
    public Long bookId; public Long authorId;
    public BookAuthorId_24162099(){}
    public BookAuthorId_24162099(Long b,Long a){bookId=b;authorId=a;}
    public boolean equals(Object o){if(this==o)return true;if(!(o instanceof BookAuthorId_24162099 x))return false;return Objects.equals(bookId,x.bookId)&&Objects.equals(authorId,x.authorId);}
    public int hashCode(){return Objects.hash(bookId,authorId);}
}
