<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title" /></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<sitemesh:write property="head" />
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand fw-bold"
				href="${pageContext.request.contextPath}/home">BOOK STORE</a>
			<div class="navbar-nav me-auto">
				<a class="nav-link" href="${pageContext.request.contextPath}/home">Trang
					Chủ</a> <a class="nav-link"
					href="${pageContext.request.contextPath}/products">Sản phẩm</a>
				<c:if
					test="${not empty sessionScope.account && sessionScope.account.admin}">
					<a class="nav-link text-warning"
						href="${pageContext.request.contextPath}/admin/books">Trang
						quản trị</a>
				</c:if>
			</div>
			<div class="navbar-nav">
				<c:choose>
					<c:when test="${empty sessionScope.account}">
						<a class="nav-link"
							href="${pageContext.request.contextPath}/login">Đăng nhập</a>
						<a class="nav-link"
							href="${pageContext.request.contextPath}/register">Đăng ký</a>
					</c:when>
					<c:otherwise>
						<span class="navbar-text text-white me-3">${sessionScope.account.fullname}</span>
                        <a class="nav-link text-warning"
                           href="${pageContext.request.contextPath}/cart">🛒 Giỏ hàng</a>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/orders">Đơn hàng</a>
						<a class="nav-link"
							href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<main class="container py-4">
		<sitemesh:write property="body" />
	</main>
	<footer class="border-top bg-white py-3 mt-4">
		<div class="container text-center text-muted">Họ tên: Huỳnh Duy Phước | MSSV: 24162099 | Mã đề: 01</div>
	</footer>
</body>
</html>
