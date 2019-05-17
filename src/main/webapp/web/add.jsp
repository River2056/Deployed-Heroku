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
    <title>Add</title>

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
                <form method="POST" id="form-add">
                
                	<div class="form-group">
                        <label for="day">日期</label>
                        <input type="text" id="day" name="day" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="comment">工作內容</label>
                        <input type="text" id="comment" name="comment" placeholder="重點內容註記" class="form-control">
                        <div id="comment_feedback" class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="work">上班</label>
                        <input type="text" id="work" name="start" placeholder="開始時間" class="form-control">
                        <input type="button" id="work-time" value="打卡" class="btn btn-dark">                        
                    </div>

                    <div class="form-group">
                        <label for="rest">下班</label>
                        <input type="text" id="rest" name="end" placeholder="結束時間" class="form-control">
                        <input type="button" id="rest-time" value="打卡" class="btn btn-dark">
                    </div>

                    <input id="add-btn" type="button" value="ADD" class="btn btn-primary"/>
                    <a href="${pageContext.request.contextPath }/main/index.do" class="btn btn-danger">CANCEL</a>
                </form>
                
            </div>
        </div>

    </div>

    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/autoTimeInput.js"></script>
    <script src="../js/checkForComment.js"></script>
    <script type="text/javascript">
    // day auto input value
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month < 10) { 
        month = "0" + month; 
    }
    if (day < 10) { 
        day = "0" + day; 
    }
    var line = year + "-" + month + "-" + day;
    $('#day').val(line);
    
    
    // add button ajax function
    $('#add-btn').click(function() {
    	var url = "handle_add.do";
    	var data = $('#form-add').serialize();
    	
    	$.ajax({
    		url: url,
    		data: data,
    		type: "POST",
    		dataType: "json",
    		success: function(jsonObj) {
    			if(jsonObj.state == 1) {
    				location.href = "list.do";
    			}
    		}
    	});
    });
    </script>
    
    
    
</body>

</html>