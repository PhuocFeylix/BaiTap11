<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<nav class="navbar navbar-dark bg-primary">
		<div class="container">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/admin/books">QUẢN TRỊ</a>
			<div>
				<a class="btn btn-light btn-sm me-2"
					href="${pageContext.request.contextPath}/admin/books">Books</a> <a
					class="btn btn-light btn-sm me-2"
					href="${pageContext.request.contextPath}/admin/authors">Authors</a>
				<a class="btn btn-warning btn-sm me-2"
					href="${pageContext.request.contextPath}/home">Trang chủ</a> <a
					class="btn btn-danger btn-sm"
					href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
			</div>
		</div>
	</nav>
	<main class="container py-4">
		<sitemesh:write property="body" />
	</main>
	<footer class="border-top bg-white py-3 text-center text-muted"> Huỳnh Duy Phước - Đề 01</footer>
</body>
</html>
