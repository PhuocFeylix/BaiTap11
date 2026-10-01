# 24162099_made - Bài kiểm tra quá trình

## Chức năng User đã bổ sung
- Giỏ hàng: thêm sản phẩm, xóa sản phẩm, cập nhật số lượng, giới hạn theo tồn kho.
- Thanh toán COD: nhập thông tin nhận hàng, kiểm tra tồn kho lại khi đặt hàng, tạo đơn và trừ tồn kho trong transaction.
- Lịch sử đặt hàng: xem danh sách, lọc theo trạng thái và xem chi tiết đơn hàng.
- Trạng thái đơn: NEW, CONFIRMED, PREPARING, SHIPPING, DELIVERING, DELIVERED, CANCELLED, RETURNED.

## Cài đặt database
Chạy toàn bộ `database.sql` trên MySQL. Nếu database đã tồn tại, chạy thêm phần tạo bảng `orders` và `order_items` ở cuối file.

## Tài khoản demo
- Admin: admin@example.com / admin123

## Luồng kiểm tra User
1. Đăng ký/kích hoạt và đăng nhập User.
2. Vào Trang chủ hoặc Chi tiết sách -> chọn số lượng -> Thêm vào giỏ.
3. Vào Giỏ hàng -> thử tăng quá tồn kho để kiểm tra giới hạn -> cập nhật/xóa.
4. Chọn Thanh toán COD -> nhập tên, SĐT, địa chỉ -> Đặt hàng.
5. Mở Lịch sử đặt hàng -> xem đơn mới.
6. Dùng MySQL thay đổi `orders.status` để kiểm tra bộ lọc:
   - NEW: Đơn hàng mới
   - CONFIRMED: Đã xác nhận
   - PREPARING: Chuẩn bị hàng
   - SHIPPING: Vận chuyển
   - DELIVERING: Giao hàng
   - DELIVERED: Đã giao
   - CANCELLED: Đơn hàng hủy
   - RETURNED: Đơn hàng hoàn

Ví dụ:
```sql
UPDATE orders SET status='CONFIRMED' WHERE id=1;
UPDATE orders SET status='PREPARING' WHERE id=1;
UPDATE orders SET status='SHIPPING' WHERE id=1;
UPDATE orders SET status='DELIVERING' WHERE id=1;
UPDATE orders SET status='DELIVERED' WHERE id=1;
```


## Chức năng User - Giỏ hàng
- User phải đăng nhập mới được truy cập `/cart`.
- Giỏ hàng được lưu trong HttpSession và gắn với `cartOwnerId` của tài khoản đang đăng nhập.
- Có thêm, cập nhật số lượng, xóa từng sản phẩm và xóa toàn bộ.
- Số lượng không được vượt quá tồn kho.
- Khi logout, session và giỏ hàng bị xóa.
- Khi login, giỏ hàng mới được tạo cho tài khoản vừa đăng nhập.
- Navbar của User có nút `🛒 Giỏ hàng` và `Đơn hàng`.
