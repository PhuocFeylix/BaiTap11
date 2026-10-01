package vn.feylix.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
@Entity @Table(name="books")
public class Book_24162099 {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="bookid") private Long id;
    private String isbn; private String title; private String publisher; private BigDecimal price;
    @Column(columnDefinition="TEXT") private String description;
    @Column(name="publish_date") private LocalDate publishDate;
    @Column(name="cover_image") private String coverImage;
    private int quantity;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="book_author", joinColumns=@JoinColumn(name="bookid"), inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Author_24162099> authors = new ArrayList<>();
    @Transient private double averageRating;
    @Transient private long reviewCount;
    public Book_24162099(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getIsbn(){return isbn;} public void setIsbn(String v){isbn=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getPublisher(){return publisher;} public void setPublisher(String v){publisher=v;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public LocalDate getPublishDate(){return publishDate;} public void setPublishDate(LocalDate v){publishDate=v;}
    public String getCoverImage(){return coverImage;} public void setCoverImage(String v){coverImage=v;}
    public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
    public List<Author_24162099> getAuthors(){return authors;} public void setAuthors(List<Author_24162099> v){authors=v;}
    public double getAverageRating(){return averageRating;} public void setAverageRating(double v){averageRating=v;}
    public long getReviewCount(){return reviewCount;} public void setReviewCount(long v){reviewCount=v;}
}
