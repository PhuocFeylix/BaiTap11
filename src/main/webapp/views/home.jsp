<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html>
<head>
<title>Trang chủ - Sách</title>
</head>
<body>
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h2>Tất cả sách</h2>
		<span>6 sản phẩm / trang</span>
	</div>
	<div class="row g-4">
		<c:forEach var="b" items="${pageResult.items}">
			<div class="col-md-4">
				<div class="card h-100 shadow-sm book-card">
					<img src="${b.coverImage}" class="card-img-top" alt="${b.title}">
					<div class="card-body">
						<h5>
							<a
								href="${pageContext.request.contextPath}/book-detail?id=${b.id}">${b.title}</a>
						</h5>
						<p class="mb-1">
							<b>Mã ISBN:</b> ${b.isbn}
						</p>
						<p class="mb-1">
							<b>Tác giả:</b>
							<c:forEach var="a" items="${b.authors}" varStatus="s">${a.name}<c:if
									test="${!s.last}">, </c:if>
							</c:forEach>
						</p>
						<p class="mb-1">
							<b>Publisher:</b> ${b.publisher}
						</p>
						<p class="mb-1">
							<b>Publisher_date:</b> ${b.publishDate}
						</p>
						<p class="mb-1">
							<b>Quantity:</b> ${b.quantity}
						</p>
						<p class="rating">★ ${b.averageRating} / 5 (${b.reviewCount}
							review)</p>
                        <c:if test="${b.quantity > 0}">
                            <form method="post" action="${pageContext.request.contextPath}/cart/add" class="mt-2">
                                <input type="hidden" name="bookId" value="${b.id}">
                                <div class="input-group"><input class="form-control" type="number" name="quantity" min="1" max="${b.quantity}" value="1"><button class="btn btn-primary">Thêm giỏ</button></div>
                            </form>
                        </c:if>
                        <c:if test="${b.quantity <= 0}"><span class="badge text-bg-danger">Hết hàng</span></c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<nav class="mt-4">
		<ul class="pagination justify-content-center">
			<c:forEach begin="1" end="${pageResult.totalPages}" var="p">
				<li class="page-item ${p==pageResult.page?'active':''}"><a
					class="page-link"
					href="${pageContext.request.contextPath}/home?page=${p}">${p}</a></li>
			</c:forEach>
		</ul>
	</nav>
</body>
</html>
