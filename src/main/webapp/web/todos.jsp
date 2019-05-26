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
  <title>Todo List</title>

  <style>
  #add_todo, #clear_todo {
    margin: 20px auto;
  }

  #listItems ul li input {
    float: right;
  }
  </style>

</head>

<body>

  <c:import url="header_navbar.jsp"></c:import>

  <div class="container">

    <input id="add_todo" type="button" value="Add Todo" onclick="showPopup()" class="btn btn-primary btn-lg btn-block">
    <input id="clear_todo" type="button" value="Clear TodoList" onclick="clearList()" class="btn btn-danger btn-lg btn-block">

    <div id="listItems" class="container">
      <ul class="list-group" id="list_group">
      <!-- List of Todos here using AJAX fetch -->
      </ul>
    </div>

    <div id="mask"></div>
    <div id="popup_content" class="container">
      <c:import url="todos_form_body.jsp"></c:import>
    </div>
  </div>
  <script src="../js/jquery-3.4.0.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/todos.js"></script>
  <script type="text/javascript">
    $(function () {
      showListItems()
    });
  </script>

</body>

</html>