// register button enable/disable
function checkAllFields() {
	var username = $('#username').val();
	var password = $('#password').val();
	var confirmPassword = $('#confirm_password').val();
	var checkName = username == null || username.length == 0
	var checkPassword = password == null || password.length == 0
	var checkConfirm = confirmPassword == null || confirmPassword.length == 0
	if(checkName && checkPassword && checkConfirm) {
		$('#register-btn').prop('disabled', true);
	} else {
		$('#register-btn').removeAttr('disabled');
	}
}

// register button click function
$('#register-btn').click(function() {
	var url = "handle_register.do";
	var data = 
		"username=" + $('#username').val() 
		+ "&password=" + $('#password').val() 
		+ "&email=" + $('#email').val() 
		+ "&phone=" + $('#phone').val();
	$.ajax({
		url: url,
		data: data,
		type: "POST",
		dataType: "json",
		success: function(jsonObj) {
			if(jsonObj.state == 1) {
				location.href = "login.do";
				
			} else {
				$('#showAlert').html(jsonObj.message);
				$('#showAlert').css('color', '#f00');
			}
		}
	});
});

// username onfocus clear all class
$('#username').focus(function() {
	$(this).removeClass();
	$(this).addClass('form-control');
	$('#username_feedback').html('');
	$('#username_feedback').removeClass();
});

// username onblur check if repeat, add classes
$('#username').blur(function() {
	$.ajax({
		url: "check_username.do",
		data: "username=" + $(this).val(),
		type: "GET",
		dataType: "json",
		success: function(jsonObj) {
			if(jsonObj.state == 1) {
				// success
				$('#username').addClass('is-valid');
				$('#username_feedback').text(jsonObj.message);
				$('#username_feedback').addClass('valid-feedback');
			} else {
				// fail
				$('#username').addClass('is-invalid');
				$('#username_feedback').text(jsonObj.message);
				$('#username_feedback').addClass('invalid-feedback');
			}
		}
	});
});

//password onfocus clear all classes
$('#password').focus(function() {
	$(this).removeClass();
	$(this).addClass('form-control');
	$('#password_feedback').html('');
	$('#password_feedback').removeClass();
});

// confirm password onfocus clear all classes
$('#confirm_password').focus(function() {
	$(this).removeClass();
	$(this).addClass('form-control');
	$('#confirmPassword_feedback').html('');
	$('#confirmPassword_feedback').removeClass();
});

// password and confirm password check
$('#password').blur(function() {
	$(this).addClass('is-valid');
	if($(this).val() != null && $(this).val().length > 0 && $('#confirm_password').val() != null && $('#confirm_password').val().length > 0) {
		var status = checkForConfirmPassword();
		if(!status) {
			$('#register-btn').prop('disabled', true);
			$('#password').removeClass('is-valid').addClass('is-invalid');
			$('#password_feedback').text('內容與確認密碼不相符!');
			$('#password_feedback').addClass('invalid-feedback');
			$('#confirm_password').removeClass('is-valid').addClass('is-invalid');
			$('#confirmPassword_feedback').text('內容與密碼不相符!');
			$('#confirmPassword_feedback').addClass('invalid-feedback');
		} else {
			$('#register-btn').removeAttr('disabled');
			$('#password').removeClass('is-invalid').addClass('is-valid');
			$('#password_feedback').text('');
			$('#password_feedback').removeClass();
			$('#confirm_password').removeClass('is-invalid').addClass('is-valid');
			$('#confirmPassword_feedback').text('');
			$('#confirmPassword_feedback').removeClass();
		}
		
	}
});

$('#confirm_password').blur(function() {
	$(this).addClass('is-valid');
	if($(this).val() != null && $(this).val().length > 0 && $('#password').val() != null && $('#password').val().length > 0) {
		var status = checkForConfirmPassword();
		if(!status) {
			$('#register-btn').prop('disabled', true);
			$('#password').removeClass('is-valid').addClass('is-invalid');
			$('#password_feedback').text('內容與確認密碼不相符!');
			$('#password_feedback').addClass('invalid-feedback');
			$('#confirm_password').removeClass('is-valid').addClass('is-invalid');
			$('#confirmPassword_feedback').text('內容與密碼不相符!');
			$('#confirmPassword_feedback').addClass('invalid-feedback');
		} else {
			$('#register-btn').removeAttr('disabled');
			$('#password').removeClass('is-invalid').addClass('is-valid');
			$('#password_feedback').text('');
			$('#password_feedback').removeClass();
			$('#confirm_password').removeClass('is-invalid').addClass('is-valid');
			$('#confirmPassword_feedback').text('');
			$('#confirmPassword_feedback').removeClass();
		}
		
	}
});

function checkForConfirmPassword() {
	var password = $('#password').val();
	var confirmPassword = $('#confirm_password').val();
	return password === confirmPassword ? true : false;
}