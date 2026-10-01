<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html>
<head>
<title>CRUD Books</title>
</head>
<body>
	<h2>Quản lý Books</h2>
	<div class="card mb-4">
		<div class="card-body">
			<h5>${empty book ? 'Thêm sách' : 'Cập nhật sách'}</h5>
			<form method="post"
				action="${pageContext.request.contextPath}/admin/book-save">
				<input type="hidden" name="id" value="${book.id}">
				<div class="row g-2">
					<div class="col-md-4">
						<input name="isbn" class="form-control" placeholder="ISBN"
							value="${book.isbn}" required>
					</div>
					<div class="col-md-8">
						<input name="title" class="form-control" placeholder="Tiêu đề"
							value="${book.title}" required>
					</div>
					<div class="col-md-4">
						<input name="publisher" class="form-control"
							placeholder="Publisher" value="${book.publisher}">
					</div>
					<div class="col-md-4">
						<input name="price" type="number" step="0.01" class="form-control"
							placeholder="Price" value="${book.price}" required>
					</div>
					<div class="col-md-4">
						<input name="quantity" type="number" class="form-control"
							placeholder="Quantity" value="${book.quantity}" required>
					</div>
					<div class="col-md-4">
						<input name="publishDate" type="date" class="form-control"
							value="${book.publishDate}">
					</div>
					<div class="col-md-8">
						<input name="coverImage" class="form-control"
							placeholder="Cover image URL" value="${book.coverImage}">
					</div>
					<div class="col-12">
						<textarea name="description" class="form-control"
							placeholder="Description">${book.description}</textarea>
					</div>
					<div class="col-12">
						<label class="form-label">Authors</label>
						<div class="d-flex flex-wrap gap-3">
							<c:forEach var="a" items="${authors}">
								<label><input type="checkbox" name="authorIds"
									value="${a.id}" <c:if test="${selectedAuthorIds.contains(a.id)}">checked</c:if>>${a.name}</label>
							</c:forEach>
						</div>
					</div>
				</div>
				<button class="btn btn-primary mt-3">Lưu</button>
				<c:if test="${not empty book}">
					<a class="btn btn-secondary mt-3"
						href="${pageContext.request.contextPath}/admin/books">Hủy</a>
				</c:if>
			</form>
		</div>
	</div>
	<table class="table table-bordered bg-white">
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>ISBN</th>
				<th>Quantity</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${pageResult.items}">
				<tr>
					<td>${b.id}</td>
					<td>${b.title}</td>
					<td>${b.isbn}</td>
					<td>${b.quantity}</td>
					<td><a class="btn btn-sm btn-warning"
						href="${pageContext.request.contextPath}/admin/book-edit?id=${b.id}">Sửa</a>
						<a class="btn btn-sm btn-danger"
						onclick="return confirm('Xóa sách?')"
						href="${pageContext.request.contextPath}/admin/book-delete?id=${b.id}">Xóa</a></td>
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
