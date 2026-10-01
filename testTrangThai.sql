USE web_exam_24162099;

SELECT 
    id,
    userid,
    status,
    payment_method,
    total_amount,
    order_date
FROM orders
ORDER BY id DESC;


-- Test don hang, thay bang id don hang muon test
-- Đơn hàng mới
UPDATE orders
SET status = 'NEW'
WHERE id = 10;

-- Đã xác nhận
UPDATE orders
SET status = 'CONFIRMED'
WHERE id = 10;

-- Chuẩn bị hàng
UPDATE orders
SET status = 'PREPARING'
WHERE id = 10;

-- Vận chuyển
UPDATE orders
SET status = 'SHIPPING'
WHERE id = 10;

-- Giao hàng
UPDATE orders
SET status = 'DELIVERING'
WHERE id = 10;

-- Đã giao
UPDATE orders
SET status = 'DELIVERED'
WHERE id = 10;

-- Đơn hàng hủy
UPDATE orders
SET status = 'CANCELLED'
WHERE id = 10;

-- Đơn hàng hoàn
UPDATE orders
SET status = 'RETURNED'
WHERE id = 10;