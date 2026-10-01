<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>Xác thực OTP</title>
    <style>
        .auth-page { min-height:72vh; display:flex; align-items:center; justify-content:center; padding:30px 0; }
        .auth-card { width:100%; max-width:480px; border:0; border-radius:24px; overflow:hidden; box-shadow:0 18px 50px rgba(15,23,42,.12); }
        .auth-top { text-align:center; padding:34px 30px 24px; background:linear-gradient(135deg,#6f42c1,#0d6efd); color:#fff; }
        .auth-icon { width:68px;height:68px;border-radius:22px;background:rgba(255,255,255,.18);display:flex;align-items:center;justify-content:center;font-size:32px;margin:0 auto 16px; }
        .auth-body { padding:30px 34px 34px; background:#fff; }
        .otp-input { text-align:center; font-size:28px; letter-spacing:12px; font-weight:700; border-radius:14px; padding:13px 10px 13px 20px; }
        .otp-input:focus { border-color:#8b5cf6; box-shadow:0 0 0 .2rem rgba(111,66,193,.13); }
        .auth-btn { border-radius:12px;padding:12px;font-weight:700; }
        .otp-help { color:#64748b; font-size:.92rem; text-align:center; }
    </style>
</head>
<body>
<div class="auth-page">
    <div class="auth-card">
        <div class="auth-top">
            <div class="auth-icon">✉️</div>
            <h2 class="fw-bold mb-2">Xác thực email</h2>
            <p class="mb-0 opacity-75">Chỉ còn một bước để kích hoạt tài khoản.</p>
        </div>

        <div class="auth-body">
            <div class="otp-help mb-4">
                Nhập mã OTP <strong>6 số</strong> đã được gửi đến email của bạn.
            </div>

            <%
            if (request.getAttribute("error") != null) {
            %>
            <div class="alert alert-danger rounded-3" role="alert">
                <%=request.getAttribute("error")%>
            </div>
            <%
            }
            %>

            <form method="post">
                <input type="hidden" name="email"
                       value="<%=request.getAttribute("email") == null ? "" : request.getAttribute("email")%>">

                <div class="mb-4">
                    <input id="otpInput" name="otp" maxlength="6" inputmode="numeric"
                           pattern="[0-9]{6}" class="form-control otp-input"
                           placeholder="••••••" autocomplete="one-time-code"
                           required autofocus>
                </div>

                <button type="submit" class="btn btn-primary w-100 auth-btn">
                    ✓ Kích hoạt tài khoản
                </button>
            </form>

            <div class="text-center mt-4">
                <small class="text-muted">Mã OTP có thời hạn theo quy định của hệ thống.</small>
            </div>
        </div>
    </div>
</div>

<script>
document.getElementById("otpInput").addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, "").slice(0, 6);
});
</script>
</body>
</html>
