package vn.feylix.cart;

import vn.feylix.entity.Book_24162099;
import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem_24162099 implements Serializable {
    private Long bookId;
    private String title;
    private String coverImage;
    private BigDecimal price;
    private int quantity;
    private int stock;

    public CartItem_24162099() {}

    public CartItem_24162099(Book_24162099 book, int quantity) {
        this.bookId = book.getId();
        this.title = book.getTitle();
        this.coverImage = book.getCoverImage();
        this.price = book.getPrice();
        this.stock = book.getQuantity();
        this.quantity = quantity;
    }

    public Long getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getCoverImage() { return coverImage; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public BigDecimal getSubtotal() { return price.multiply(BigDecimal.valueOf(quantity)); }
}
