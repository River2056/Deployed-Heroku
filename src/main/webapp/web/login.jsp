<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/button.css">
    <title>WorkTracker System</title>

    <style>
    #login-form {
        margin: 20% 0 10% 0;
    }

    #login-btn {
        float: right;
    }
    
    </style>

</head>
<body>
    <c:import url="header_login_register_navbar.jsp"></c:import>

    <div class="container">

        <div id="login-form" class="card card-body bg-light">
            <form method="POST" id="form-input">

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" class="form-control" id="username" placeholder="Enter Username">
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Enter Password">
                </div>
                
<!--                  <div class="choose"> -->
<!--                     <input type="checkbox" class="checkbox" id="ck_rmbUser"/>Auto Login -->
<!--                 </div> -->
                
                <div class="custom-control custom-checkbox">
  					<input type="checkbox" class="custom-control-input" id="ck_rmbUser">
  					<label class="custom-control-label" for="ck_rmbUser">Auto Login</label>
				</div>
                
                <div>
                    <h6 id="showAlert"></h6>
                </div>
                
                <input id="login-btn" type="button" class="btn btn-primary" value="Login"/>
            </form>
        </div>
    </div>
    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
        if ($.cookie("rmbUser") == "true") {
            $("#ck_rmbUser").attr("checked", true);
            $("#username").val($.cookie("username"));
            $("#password").val($.cookie("password"));
        }
    });
    
    // login button function
    $('#login-btn').click(function() {
    	var url = "handle_login.do";
    	var data = $('#form-input').serialize();
    	console.log(data);
    	$.ajax({
    		url: url,
    		data: data,
    		type: "POST",
    		dataType: "json",
    		success: function(jsonObj) {
    			if(jsonObj.state == 1) {
    				saveCookie();
    				location.href = "../main/index.do";
    				
    			} else {
    				$('#showAlert').html(jsonObj.message);
    				$('#showAlert').css('color', '#f00');
    				
    			}
    		}
    	});
    });
    
    function saveCookie() {
    	// 判斷是否勾選了"自動登入"
        if ($("#ck_rmbUser").prop("checked")) {
        	// 勾選了自動登入
        	// 獲取輸入框中的內容
            var str_username = $("#username").val();
            var str_password = $("#password").val();
            // 在Cookie中記錄"自動登入", "用戶名"和"密碼"
            $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("username", str_username, { expires: 7 });
            $.cookie("password", str_password, { expires: 7 });
        } else {
        	// 沒有勾選自動登入
        	// 設置或清除cookie中的數據, 並設置過期
            $.cookie("rmbUser", "false", { expire: -1 });
            $.cookie("username", "", { expires: -1 });
            $.cookie("password", "", { expires: -1 });
        }
    };
    
    </script>
</body>
</html>