<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html><head><title>Giỏ hàng</title></head><body>
<h2 class="mb-3">🛒 Giỏ hàng của tôi</h2>
<p class="text-muted">Tài khoản: <b>${sessionScope.account.fullname}</b></p>
<c:if test="${not empty sessionScope.cartMessage}"><div class="alert alert-success">${sessionScope.cartMessage}</div><c:remove var="cartMessage" scope="session"/></c:if>
<c:if test="${not empty sessionScope.cartError}"><div class="alert alert-danger">${sessionScope.cartError}</div><c:remove var="cartError" scope="session"/></c:if>
<c:choose>
<c:when test="${empty cartItems}">
  <div class="alert alert-info">Giỏ hàng đang trống. <a href="${pageContext.request.contextPath}/home">Tiếp tục mua sách</a>.</div>
</c:when>
<c:otherwise>
<form method="post" action="${pageContext.request.contextPath}/cart/update">
<div class="table-responsive"><table class="table table-bordered align-middle bg-white">
<thead><tr><th>Sản phẩm</th><th>Đơn giá</th><th style="width:180px">Số lượng</th><th>Thành tiền</th><th></th></tr></thead>
<tbody>
<c:set var="total" value="0"/>
<c:forEach var="item" items="${cartItems}">
<tr>
<td><div class="d-flex align-items-center gap-3"><img src="${item.coverImage}" style="width:70px;height:90px;object-fit:cover" class="rounded"><b>${item.title}</b></div></td>
<td><fmt:formatNumber value="${item.price}" type="number" maxFractionDigits="0"/> đ</td>
<td><input class="form-control" type="number" name="quantity_${item.bookId}" min="1" max="${item.stock}" value="${item.quantity}"><small class="text-muted">Tồn kho: ${item.stock}</small></td>
<td><fmt:formatNumber value="${item.subtotal}" type="number" maxFractionDigits="0"/> đ</td>
<td><button class="btn btn-outline-danger btn-sm" type="submit" formaction="${pageContext.request.contextPath}/cart/remove" name="bookId" value="${item.bookId}">Xóa</button></td>
</tr>
<c:set var="total" value="${total + item.subtotal}"/>
</c:forEach>
</tbody></table></div>
<div class="d-flex justify-content-between align-items-center"><a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/home">← Tiếp tục mua</a><div class="text-end"><div class="fs-5">Tổng tiền: <b><fmt:formatNumber value="${total}" type="number" maxFractionDigits="0"/> đ</b></div><div class="mt-2"><button class="btn btn-primary" type="submit">Cập nhật số lượng</button><a class="btn btn-success ms-2" href="${pageContext.request.contextPath}/checkout">Thanh toán COD</a></div></div></div>
</form>
<form method="post" action="${pageContext.request.contextPath}/cart/clear" class="mt-2"><button class="btn btn-sm btn-outline-danger">Xóa toàn bộ giỏ hàng</button></form>
</c:otherwise></c:choose>
</body></html>
