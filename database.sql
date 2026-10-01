CREATE DATABASE IF NOT EXISTS web_exam_24162099 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE web_exam_24162099;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255),
    phone VARCHAR(30),
    passwd VARCHAR(255) NOT NULL,
    signup_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login DATETIME NULL,
    is_admin TINYINT(1) NOT NULL DEFAULT 0,
    otp_code VARCHAR(10) NULL,
    otp_expiry DATETIME NULL,
    active TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE books (
    bookid BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(50) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    price DECIMAL(12,2) DEFAULT 0,
    description TEXT,
    publish_date DATE,
    cover_image VARCHAR(500),
    quantity INT DEFAULT 0
);

CREATE TABLE author (
    author_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    date_of_birth DATE
);

CREATE TABLE book_author (
    bookid BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY(bookid, author_id),
    CONSTRAINT fk_ba_book FOREIGN KEY(bookid) REFERENCES books(bookid) ON DELETE CASCADE,
    CONSTRAINT fk_ba_author FOREIGN KEY(author_id) REFERENCES author(author_id) ON DELETE CASCADE
);

CREATE TABLE rating (
    userid BIGINT NOT NULL,
    bookid BIGINT NOT NULL,
    rating INT NOT NULL,
    review_text VARCHAR(1000),
    PRIMARY KEY(userid, bookid),
    CONSTRAINT fk_rating_user FOREIGN KEY(userid) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_rating_book FOREIGN KEY(bookid) REFERENCES books(bookid) ON DELETE CASCADE
);

-- Admin demo. Password: admin123 (SHA-256 below)
INSERT INTO users(email, fullname, phone, passwd, is_admin, active)
VALUES ('admin@example.com','Administrator','0900000000',
'240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',1,1);

-- Tài khoản admin demo: admin@example.com / admin123
INSERT INTO author(author_name, date_of_birth) VALUES
('Nguyễn Nhật Ánh','1955-05-07'),
('Nam Cao','1915-10-29'),
('J.K. Rowling','1965-07-31'),
('Haruki Murakami','1949-01-12');

INSERT INTO books(isbn,title,publisher,price,description,publish_date,cover_image,quantity) VALUES
('978000000001','Cho tôi xin một vé đi tuổi thơ','NXB Trẻ',85000,'Tiểu thuyết dành cho tuổi thơ.','2008-01-01','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIkK1SeIa6v5koZ2n_ecbcJ2dt0k0agCYfVlmkLpBC7A&s=10',20),
('978000000002','Mắt biếc','NXB Trẻ',90000,'Tác phẩm nổi tiếng của Nguyễn Nhật Ánh.','1990-01-01','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSddy6Wa01ptzZp6mlOeegRMcipBxWWb-1QmTQFAABXZw&s=10',15),
('978000000003','Lão Hạc','NXB Văn học',50000,'Truyện ngắn của Nam Cao.','1943-01-01','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQjdbXTD00GJKIDpxL6fwqVaIkdAorRrgTQeEWTYeOQw&s=10',30),
('978000000004','Harry Potter và Hòn đá Phù thủy','Bloomsbury',180000,'Tập đầu của Harry Potter.','1997-06-26','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwYThiJbcrFFoL4Amr30hKnC2HJ_V9D2FID3DSdOCyLw&s=10',12),
('978000000005','Rừng Na Uy','Kodansha',160000,'Tiểu thuyết của Haruki Murakami.','1987-09-04','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnCjd9mJHmkbROwG6N8gzf_WPX6hWNEI_ejcPQDzQoHA&s=10',10),
('978000000006','Dế Mèn phiêu lưu ký','Kim Đồng',70000,'Tác phẩm thiếu nhi kinh điển.','1941-01-01','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe7NCFLC-1D1UTeizT4_-yACaH6zbQlyv1Mywdn_G5AQ&s=10',25),
('978000000007','Tôi thấy hoa vàng trên cỏ xanh','NXB Trẻ',95000,'Tác phẩm của Nguyễn Nhật Ánh.','2010-01-01','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMcp9i00D4Y7v7XkoNHYVsdesqqbYtvUCjhOyl3RxqFQ&s=10',18);

INSERT INTO book_author(bookid,author_id) VALUES
(1,1),(2,1),(3,2),(4,3),(5,4),(6,2),(7,1);

-- ==================== CHUC NANG GIO HANG / DAT HANG ====================
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid BIGINT NOT NULL,
    order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'NEW',
    payment_method VARCHAR(20) NOT NULL DEFAULT 'COD',
    total_amount DECIMAL(12,2) NOT NULL DEFAULT 0,
    recipient_name VARCHAR(255) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    shipping_address VARCHAR(500) NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY(userid) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    bookid BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_order_items_order FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_book FOREIGN KEY(bookid) REFERENCES books(bookid)
);

CREATE INDEX idx_orders_user_status ON orders(userid, status);
CREATE INDEX idx_orders_date ON orders(order_date);
