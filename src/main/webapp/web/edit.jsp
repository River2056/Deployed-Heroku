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
    <title>Edit</title>

    <style>
    .card {
        margin: 20% 0 10% 0;
    }
    
    .form-group {
    	position: relative;
    }
    
    .form-group .btn-dark {
    	position: absolute;
    	right: 0;
    	top: 32px;
    }
    </style>

</head>

<body>
    <c:import url="header_navbar.jsp"></c:import>

    <div class="container">
        <div class="card bg-light">
            <div class="card-body">
                <form method="POST" id="form-edit">
                	<input type="hidden" name="id" value="${note.id }"/>
                	
                	<div class="form-group">
                        <label for="day">日期</label>
                        <input type="text" id="day" name="day" class="form-control" value="${note.day }">
                    </div>

                    <div class="form-group">
                        <label for="comment">工作內容</label>
                        <input type="text" id="comment" name="comment" placeholder="重點內容註記" class="form-control" value="${note.comment }">
                        <div id="comment_feedback" class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="work">上班</label>
                        <input type="text" id="work" name="start" placeholder="開始時間" class="form-control" value="${note.start }">
                        <input type="button" id="work-time" value="打卡" class="btn btn-dark">                        
                    </div>

                    <div class="form-group">
                        <label for="rest">下班</label>
                        <input type="text" id="rest" name="end" placeholder="結束時間" class="form-control" value="${note.end }">
                        <input type="button" id="rest-time" value="打卡" class="btn btn-dark">
                    </div>

                    <input id="edit-btn" type="button" value="EDIT" class="btn btn-primary"/>
                    <a href="${pageContext.request.contextPath }/note/list.do" class="btn btn-danger">CANCEL</a>
                </form>
                
            </div>
        </div>

    </div>

    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/autoTimeInput.js"></script>
    <script src="../js/checkForComment.js"></script>
    <script type="text/javascript">
 	// edit button function
 	$('#edit-btn').click(function() {
 		var url = "handle_edit.do";
 		var data = $('#form-edit').serialize();
 		console.log(data);
 		$.ajax({
 			url: url,
 			data: data,
 			type: "POST",
 			dataType: "json",
 			success: function(jsonObj) {
 				if(jsonObj.state != 1) {
 					alert(jsonObj.message);
 				}
 				location.href = "list.do";
 			},
 			error: function() {
 				alert("登入訊息已過期, 請重新登入!");
 				location.href = "../user/login.do";
 			}
 		});
 	});
 	
 	
    </script>
</body>

</html>