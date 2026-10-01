package vn.feylix.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User_24162099 implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	private String fullname;
	private String phone;
	@Column(name = "passwd", nullable = false)
	private String password;
	@Column(name = "signup_date")
	private LocalDateTime signupDate;
	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	@Column(name = "is_admin")
	private boolean admin;
	@Column(name = "otp_code")
	private String otpCode;
	@Column(name = "otp_expiry")
	private LocalDateTime otpExpiry;
	private boolean active;

	public User_24162099() {}

	public Long getId() {
		return id;
	}

	public void setId(Long v) {
		id = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		email = v;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String v) {
		fullname = v;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String v) {
		phone = v;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String v) {
		password = v;
	}

	public LocalDateTime getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(LocalDateTime v) {
		signupDate = v;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime v) {
		lastLogin = v;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean v) {
		admin = v;
	}

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String v) {
		otpCode = v;
	}

	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(LocalDateTime v) {
		otpExpiry = v;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean v) {
		active = v;
	}
}
