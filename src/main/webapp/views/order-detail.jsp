<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html>
<head>
<title>Chi tiết đơn hàng</title>
</head>
<body>
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h2>Chi tiết đơn #${order.id}</h2>
		<c:choose>

			<%-- Đơn hàng mới: XÁM --%>
			<c:when test="${order.status == 'NEW'}">
				<span class="badge bg-secondary"> Đơn hàng mới </span>
			</c:when>

			<%-- Đã xác nhận: XANH DƯƠNG --%>
			<c:when test="${order.status == 'CONFIRMED'}">
				<span class="badge bg-primary"> Đã xác nhận </span>
			</c:when>

			<%-- Chuẩn bị hàng: XANH DƯƠNG --%>
			<c:when test="${order.status == 'PREPARING'}">
				<span class="badge bg-primary"> Chuẩn bị hàng </span>
			</c:when>

			<%-- Vận chuyển: XANH DƯƠNG --%>
			<c:when test="${order.status == 'SHIPPING'}">
				<span class="badge bg-primary"> Đang vận chuyển </span>
			</c:when>

			<%-- Giao hàng: XANH DƯƠNG --%>
			<c:when test="${order.status == 'DELIVERING'}">
				<span class="badge bg-primary"> Đang giao hàng </span>
			</c:when>

			<%-- Đã giao: XANH LÁ ĐẬM --%>
			<c:when test="${order.status == 'DELIVERED'}">
				<span class="badge bg-success"> ✓ Đã giao </span>
			</c:when>

			<%-- Hủy: ĐỎ --%>
			<c:when test="${order.status == 'CANCELLED'}">
				<span class="badge bg-danger"> ✕ Đơn hàng đã hủy </span>
			</c:when>

			<%-- Hoàn: VÀNG --%>
			<c:when test="${order.status == 'RETURNED'}">
				<span class="badge bg-warning text-dark"> ↩ Đơn hàng đã hoàn trả </span>
			</c:when>

		</c:choose>
	</div>
	<div class="card mb-3">
		<div class="card-body">
			<h5>Thông tin giao hàng</h5>
			<p class="mb-1">
				<b>Người nhận:</b> ${order.recipientName}
			</p>
			<p class="mb-1">
				<b>SĐT:</b> ${order.phone}
			</p>
			<p class="mb-1">
				<b>Địa chỉ:</b> ${order.shippingAddress}
			</p>
			<p class="mb-0">
				<b>Thanh toán:</b> COD
			</p>
		</div>
	</div>
	<div class="card">
		<div class="card-body">
			<h5>Sản phẩm</h5>
			<c:forEach var="item" items="${order.items}">
				<div class="d-flex justify-content-between border-bottom py-2">
					<span>${item.book.title} × ${item.quantity}</span><span><fmt:formatNumber
							value="${item.subtotal}" type="number" maxFractionDigits="0" />
						đ</span>
				</div>
			</c:forEach>
			<div class="text-end fs-5 mt-3">
				<b>Tổng: <fmt:formatNumber value="${order.totalAmount}"
						type="number" maxFractionDigits="0" /> đ
				</b>
			</div>
		</div>
	</div>
	<a class="btn btn-outline-secondary mt-3"
		href="${pageContext.request.contextPath}/orders">← Lịch sử đặt
		hàng</a>
</body>
</html>
