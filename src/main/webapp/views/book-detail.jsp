<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html>
<head>
<title>Chi tiết sách</title>
</head>
<body>
	<c:set var="b" value="${book}" />
	<div class="row g-4">
		<div class="col-md-4">
			<img src="${b.coverImage}" class="img-fluid rounded shadow cover">
		</div>
		<div class="col-md-8">
			<h2>${b.title}</h2>
			<p>
				<b>Mã isbn:</b> ${b.isbn}
			</p>
			<p>
				<b>Tác giả:</b>
				<c:forEach var="a" items="${b.authors}" varStatus="s">${a.name}<c:if
						test="${!s.last}">, </c:if>
				</c:forEach>
			</p>
			<p>
				<b>Publisher:</b> ${b.publisher}
			</p>
			<p>
				<b>Publisher_date:</b> ${b.publishDate}
			</p>
			<p>
				<b>Quantity:</b> ${b.quantity}
			</p>
			<p class="rating">Reviews (${b.reviewCount}) -
				${b.averageRating}/10</p>
			<p>${b.description}</p>
            <c:choose>
                <c:when test="${b.quantity > 0}">
                    <form method="post" action="${pageContext.request.contextPath}/cart/add" class="mt-3">
                        <input type="hidden" name="bookId" value="${b.id}">
                        <div class="input-group" style="max-width:300px"><input class="form-control" type="number" name="quantity" min="1" max="${b.quantity}" value="1"><button class="btn btn-primary">Thêm vào giỏ hàng</button></div>
                        <small class="text-muted">Còn ${b.quantity} sản phẩm</small>
                    </form>
                </c:when>
                <c:otherwise><span class="badge text-bg-danger">Hết hàng</span></c:otherwise>
            </c:choose>
		</div>
	</div>
	<hr>
	<h4>Reviews</h4>
	<c:forEach var="r" items="${reviews}">
		<div class="border rounded p-3 mb-2">
			<b>${r.user.fullname}</b> - ${r.rating}
			<div>${r.reviewText}</div>
		</div>
	</c:forEach>
	<c:if test="${not empty sessionScope.account}">
		<h5>Thêm review</h5>
		<form method="post">
			<div>
				<label>Đánh giá: <span id="ratingValue">10</span>/10
				</label> <input type="range" name="rating" id="rating" min="1" max="10"
					value="10">


			</div>
			<script>
				const rating = document.getElementById("rating");
				const ratingValue = document.getElementById("ratingValue");

				rating.addEventListener("input", function() {
					ratingValue.textContent = this.value;
				});
			</script>
			<textarea name="reviewText" class="form-control mb-2"
				placeholder="Nội dung review"></textarea>
			<button class="btn btn-primary">Submit</button>
		</form>
	</c:if>
</body>
</html>
