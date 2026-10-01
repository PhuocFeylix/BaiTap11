<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>Đăng nhập</title>
    <style>
        .auth-page { min-height: 72vh; display:flex; align-items:center; justify-content:center; padding:30px 0; }
        .auth-card { width:100%; max-width:460px; border:0; border-radius:24px; overflow:hidden; box-shadow:0 18px 50px rgba(15,23,42,.12); }
        .auth-top { padding:32px 32px 24px; background:linear-gradient(135deg,#0d6efd,#6f42c1); color:#fff; }
        .auth-icon { width:58px;height:58px;border-radius:18px;background:rgba(255,255,255,.18);display:flex;align-items:center;justify-content:center;font-size:28px;margin-bottom:18px; }
        .auth-body { padding:30px 32px 32px; background:#fff; }
        .auth-label { font-weight:600;color:#334155;margin-bottom:7px; }
        .auth-input { border-radius:12px;padding:12px 14px;border:1px solid #dbe2ea; }
        .auth-input:focus { border-color:#86b7fe; box-shadow:0 0 0 .2rem rgba(13,110,253,.12); }
        .auth-btn { border-radius:12px;padding:12px;font-weight:700; }
        .auth-switch { text-align:center;color:#64748b;margin-top:20px; }
        .auth-switch a { font-weight:700;text-decoration:none; }
    </style>
</head>
<body>
<div class="auth-page">
    <div class="auth-card">
        <div class="auth-top">
            <div class="auth-icon">🔐</div>
            <h2 class="fw-bold mb-2">Chào mừng trở lại!</h2>
            <p class="mb-0 opacity-75">Đăng nhập để tiếp tục sử dụng BOOK STORE.</p>
        </div>
        <div class="auth-body">
            <%
            if (request.getAttribute("error") != null) {
            %>
            <div class="alert alert-danger rounded-3" role="alert">
                <strong>Đăng nhập thất bại:</strong>
                <%=request.getAttribute("error")%>
            </div>
            <%
            }
            %>
            <%
            if (request.getAttribute("success") != null) {
            %>
            <div class="alert alert-success rounded-3" role="alert">
                <%=request.getAttribute("success")%>
            </div>
            <%
            }
            %>

            <form method="post">
                <div class="mb-3">
                    <label class="auth-label">Email</label>
                    <input name="email" type="email" class="form-control auth-input"
                           placeholder="Nhập email của bạn" autocomplete="email" required>
                </div>

                <div class="mb-4">
                    <label class="auth-label">Mật khẩu</label>
                    <div class="input-group">
                        <input id="loginPassword" name="password" type="password"
                               class="form-control auth-input" placeholder="Nhập mật khẩu"
                               autocomplete="current-password" required>
                        <button type="button" class="btn btn-outline-secondary"
                                style="border-radius:0 12px 12px 0"
                                onclick="togglePassword('loginPassword', this)">Hiện</button>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary w-100 auth-btn">
                    Đăng nhập
                </button>
            </form>

            <div class="auth-switch">
                Chưa có tài khoản?
                <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
            </div>
        </div>
    </div>
</div>

<script>
function togglePassword(id, btn) {
    const input = document.getElementById(id);
    const hidden = input.type === "password";
    input.type = hidden ? "text" : "password";
    btn.textContent = hidden ? "Ẩn" : "Hiện";
}
</script>
</body>
</html>
