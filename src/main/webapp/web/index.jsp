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
    <title>Index</title>

    <style>
    .jumbotron {
        margin: 10% 0;
    }
    
    .card-body {
    	padding: 0.7rem;
    }
    
    .card .card-body #current_time {
    	display: flex;
    	justify-content: center;
    	font-size: 1.7rem;
    }
    </style>
    
</head>
<body>
    <c:import url="header_navbar.jsp"></c:import>

    <div class="container">
		<div class="jumbotron text-center bg-light">
			<h3>WTS Note System</h3>
			<p class="lead">Welcome, ${sessionScope.username }</p>
		<div class="btn-group">
			<button type="button" class="btn btn-primary btn-lg dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
			  aria-expanded="false">
			  ADD
			</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${pageContext.request.contextPath }/note/add.do">Add WorkDays</a>
				<a class="dropdown-item" href="${pageContext.request.contextPath }/todo/list.do">Todo List</a>
			</div>
		</div>
		</div>
		
		<div class="jumbotron text-center bg-light">
			<h3>Top things to do~</h3>
			<ul class="list-group" id="list_group">
				<c:forEach items="${todoList }" var="list" end="2">
					<li class="list-group-item list-group-item-primary">${list.day } ${list.todo }</li>
				</c:forEach>
      		</ul>
		</div>

        <div class="card bg-light">
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <h4 id="current_time">2019-05-11 18:30:55</h4>
                </blockquote>
            </div>
        </div>

    </div>
    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script>
    function currentTime() {
        let time = new Date();
        let year = time.getFullYear();
        let month = time.getMonth() + 1;
        let day = time.getDate();
        let hours = time.getHours();
        let minutes = time.getMinutes();
        let seconds = time.getSeconds();

        if (month < 10) { 
            month = "0" + month; 
        }
        if (day < 10) { 
            day = "0" + day; 
        }
        if (hours < 10) { 
            hours = "0" + hours; 
        } 
        if (minutes < 10) { 
            minutes = "0" + minutes; 
        } 
        if (seconds < 10) {
            seconds = "0" + seconds; 
        }
		let timeLine = year + "-" + month + "-" + day + " &nbsp;&nbsp; " + hours + ":" + minutes + ":" + seconds;
        $('#current_time').html(timeLine);
    }
        
    setInterval(currentTime, 100);
    </script>
</body>
</html>