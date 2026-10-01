<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>Đăng ký tài khoản</title>
    <style>
        .auth-page { min-height:72vh; display:flex; align-items:center; justify-content:center; padding:30px 0; }
        .auth-card { width:100%; max-width:560px; border:0; border-radius:24px; overflow:hidden; box-shadow:0 18px 50px rgba(15,23,42,.12); }
        .auth-top { padding:30px 34px 24px; background:linear-gradient(135deg,#198754,#0d6efd); color:#fff; }
        .auth-icon { width:58px;height:58px;border-radius:18px;background:rgba(255,255,255,.18);display:flex;align-items:center;justify-content:center;font-size:28px;margin-bottom:16px; }
        .auth-body { padding:30px 34px 34px; background:#fff; }
        .auth-label { font-weight:600;color:#334155;margin-bottom:7px; }
        .auth-input { border-radius:12px;padding:12px 14px;border:1px solid #dbe2ea; }
        .auth-input:focus { border-color:#86b7fe; box-shadow:0 0 0 .2rem rgba(13,110,253,.12); }
        .auth-btn { border-radius:12px;padding:12px;font-weight:700; }
        .auth-note { background:#f8fafc;border-radius:14px;padding:12px 14px;color:#64748b;font-size:.9rem; }
        .auth-switch { text-align:center;color:#64748b;margin-top:20px; }
        .auth-switch a { font-weight:700;text-decoration:none; }
    </style>
</head>
<body>
<div class="auth-page">
    <div class="auth-card">
        <div class="auth-top">
            <div class="auth-icon">📚</div>
            <h2 class="fw-bold mb-2">Tạo tài khoản</h2>
            <p class="mb-0 opacity-75">Đăng ký để khám phá kho sách của BOOK STORE.</p>
        </div>

        <div class="auth-body">
            <%
            if (request.getAttribute("error") != null) {
            %>
            <div class="alert alert-danger rounded-3" role="alert">
                <strong>Không thể đăng ký:</strong>
                <%=request.getAttribute("error")%>
            </div>
            <%
            }
            %>

            <form method="post">
                <div class="mb-3">
                    <label class="auth-label">Họ và tên</label>
                    <input name="fullname" class="form-control auth-input"
                           placeholder="Nguyễn Văn A" autocomplete="name" required>
                </div>

                <div class="mb-3">
                    <label class="auth-label">Email</label>
                    <input name="email" type="email" class="form-control auth-input"
                           placeholder="example@gmail.com" autocomplete="email" required>
                </div>

                <div class="mb-3">
                    <label class="auth-label">Số điện thoại <span class="text-muted fw-normal">(không bắt buộc)</span></label>
                    <input name="phone" class="form-control auth-input"
                           placeholder="09xxxxxxxx" autocomplete="tel">
                </div>

                <div class="mb-3">
                    <label class="auth-label">Mật khẩu</label>
                    <div class="input-group">
                        <input id="registerPassword" name="password" type="password"
                               class="form-control auth-input" placeholder="Tối thiểu 6 ký tự"
                               autocomplete="new-password" required>
                        <button type="button" class="btn btn-outline-secondary"
                                style="border-radius:0 12px 12px 0"
                                onclick="togglePassword('registerPassword', this)">Hiện</button>
                    </div>
                </div>

                <div class="auth-note mb-4">
                    ✉️ Sau khi đăng ký, hệ thống sẽ gửi <strong>mã OTP 6 số</strong> đến email để kích hoạt tài khoản.
                </div>

                <button type="submit" class="btn btn-success w-100 auth-btn">
                    Đăng ký và nhận OTP
                </button>
            </form>

            <div class="auth-switch">
                Đã có tài khoản?
                <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
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
