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
    <title>List</title>

    <style>
    .card {
        margin: 20px 0 0 0;
    }

    .card .card-header {
        font-size: 24px;
    }
    </style>

</head>

<body>

<c:import url="header_navbar.jsp"></c:import>

    <div class="container">
    
		<c:forEach items="${noteList }" var="note">
	        <div class="card bg-light">
	            <div class="card-header">
	                ${note.day }
	            </div>
	            <div class="card-body">
	                <blockquote class="blockquote mb-0">
	                    <p>${note.comment }</p>
	                    <p>上班: <span id="work">${note.start }</span> 下班: <span id="rest">${note.end }</span></p>
	                    <a href="edit.do?id=${note.id }" class="btn btn-primary">EDIT</a>
	                    <input type="button" value="DELETE" onclick="deleteNote(${note.id})" class="btn btn-danger">
	                </blockquote>
	            </div>
	        </div>
		</c:forEach>
		
    </div>

    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function deleteNote(id) {
    	var checkIfDelete = confirm('確定要刪除嗎?');
    	var url = "handle_delete.do";
    	var data = "id=" + id;
    	if(checkIfDelete) {
	    	$.ajax({
	    		url: url,
	    		data: data,
	    		type: "GET",
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
    	}
    }
    </script>
</body>

</html>