<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="card bg-light">
	<div class="card-body">
	    <form method="POST" id="form-edit">
	    	<input type="hidden" name="id" id="id"/>
	    	
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
	
	        <input id="edit-btn" onclick="postForm()" type="button" value="EDIT" class="btn btn-primary"/>
	     	<a href="###" onclick="dismissPopup()" class="btn btn-danger">CANCEL</a>
	    </form>
	</div>
</div>