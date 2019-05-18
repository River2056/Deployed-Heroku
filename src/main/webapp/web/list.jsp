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
    <link rel="stylesheet" href="../css/list.css">
    <title>List</title>

</head>

<body>

<c:import url="header_navbar.jsp"></c:import>
<div class="container">

    <div id="listItems" class="container">
		<!-- List of notes here using AJAX fetch -->
    </div>
    
    <div id="mask"></div>
	<div id="popup_content" class="container">
		<c:import url="list_form_body.jsp"></c:import>		
	</div>
</div>
    <script src="../js/jquery-3.4.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/list.js"></script>
    <script src="../js/listAutoTimeInput.js"></script>
    <script type="text/javascript">
    $(function() {
    	showListItems()
    });
    </script>
    
</body>

</html>