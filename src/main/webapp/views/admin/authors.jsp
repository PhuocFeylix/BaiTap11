<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html>
<head>
<title>CRUD Authors</title>
</head>
<body>
	<h2>Quản lý Authors</h2>
	<div class="card mb-4">
		<div class="card-body">
			<h5>${empty author ? 'Thêm tác giả' : 'Cập nhật tác giả'}</h5>
			<form method="post"
				action="${pageContext.request.contextPath}/admin/author-save">
				<input type="hidden" name="id" value="${author.id}">
				<div class="row g-2">
					<div class="col-md-7">
						<input name="name" class="form-control" placeholder="Tên tác giả"
							value="${author.name}" required>
					</div>
					<div class="col-md-5">
						<input name="dateOfBirth" type="date" class="form-control"
							value="${author.dateOfBirth}">
					</div>
				</div>
				<button class="btn btn-primary mt-3">Lưu</button>
				<c:if test="${not empty author}">
					<a class="btn btn-secondary mt-3"
						href="${pageContext.request.contextPath}/admin/authors">Hủy</a>
				</c:if>
			</form>
		</div>
	</div>
	<table class="table table-bordered bg-white">
		<thead>
			<tr>
				<th>ID</th>
				<th>Author name</th>
				<th>Date of birth</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${pageResult.items}">
				<tr>
					<td>${a.id}</td>
					<td>${a.name}</td>
					<td>${a.dateOfBirth}</td>
					<td><a class="btn btn-sm btn-warning"
						href="${pageContext.request.contextPath}/admin/author-edit?id=${a.id}">Sửa</a>
						<a class="btn btn-sm btn-danger"
						onclick="return confirm('Xóa tác giả?')"
						href="${pageContext.request.contextPath}/admin/author-delete?id=${a.id}">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav>
		<ul class="pagination">
			<c:forEach begin="1" end="${pageResult.totalPages}" var="p">
				<li class="page-item ${p==pageResult.page?'active':''}"><a
					class="page-link" href="?page=${p}">${p}</a></li>
			</c:forEach>
		</ul>
	</nav>
</body>
</html>
