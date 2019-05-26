<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="card bg-light">
	<div class="card-body">
		<form method="POST" id="form-todo">

			<div class="form-group">
				<label for="day">日期</label> <input type="text" id="day" name="day"
					class="form-control">
			</div>

			<div class="form-group">
				<label for="todo">待辦事項</label> <input type="text" id="todo"
					name="todo" placeholder="內容註記" class="form-control">
				<div id="comment_feedback" class="invalid-feedback"></div>
			</div>

			<input id="addTodo-btn" onclick="postForm()" type="button"
				value="ADD" class="btn btn-primary" /> <a href="###"
				onclick="dismissPopup()" class="btn btn-danger">CANCEL</a>
		</form>
	</div>
</div>