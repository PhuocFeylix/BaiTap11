<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html><html><head><title>Thanh toán COD</title></head><body>
<h2>Thanh toán đơn hàng</h2>
<p class="text-muted">Phương thức thanh toán: <b>COD - Thanh toán khi nhận hàng</b></p>
<c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
<div class="row g-4">
<div class="col-md-7"><div class="card shadow-sm"><div class="card-body"><h5>Thông tin nhận hàng</h5>
<form method="post" action="${pageContext.request.contextPath}/checkout">
<label class="form-label">Họ tên người nhận</label><input class="form-control mb-3" name="recipientName" value="${sessionScope.account.fullname}" required>
<label class="form-label">Số điện thoại</label><input class="form-control mb-3" name="phone" value="${sessionScope.account.phone}" required>
<label class="form-label">Địa chỉ nhận hàng</label><textarea class="form-control mb-3" name="address" rows="4" required placeholder="Nhập địa chỉ giao hàng">${param.address}</textarea>
<button class="btn btn-success w-100">Đặt hàng - COD</button>
</form></div></div></div>
<div class="col-md-5"><div class="card shadow-sm"><div class="card-body"><h5>Đơn hàng</h5><c:set var="total" value="0"/>
<c:forEach var="item" items="${cartItems}"><div class="d-flex justify-content-between border-bottom py-2"><span>${item.title} × ${item.quantity}</span><span><fmt:formatNumber value="${item.subtotal}" type="number" maxFractionDigits="0"/> đ</span></div><c:set var="total" value="${total + item.subtotal}"/></c:forEach>
<div class="fs-5 mt-3 text-end">Tổng: <b><fmt:formatNumber value="${total}" type="number" maxFractionDigits="0"/> đ</b></div></div></div></div>
</div></body></html>
