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
        #register-form {
            margin: 20% 0 10% 0;
        }

        #register-btn {
            float: right;
        }
    </style>

</head>

<body>

   <c:import url="header_login_register_navbar.jsp"></c:import>

    <div class="container">

        <div id="register-form" class="card card-body bg-light">
            <form method="POST" id="form-input">

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" class="form-control" id="username" placeholder="Enter Username">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Enter Password">
                </div> 
                
                <div class="form-group">
                    <label for="email">E-mail</label>
                    <input type="text" name="email" class="form-control" id="email" placeholder="Enter E-mail">
                </div>

                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" name="phone" class="form-control" id="phone" placeholder="Enter Phone number">
                </div>

                <div>
                    <h6 id="showAlert"></h6>
                </div>

                <input id="register-btn" type="button" class="btn btn-primary" value="Register" />
            </form>
        </div>
    </div>
    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $('#register-btn').click(function() {
    	var url = "handle_register.do";
    	var data = $('#form-input').serialize();
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
    </script>
</body>

</html>