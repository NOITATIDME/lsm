<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="/join"  method="post">
	  <div class="form-group">
	    <label>Username:</label>
	    <input type="text" name="username" class="form-control" placeholder="Enter username"  required="required">
	  </div>
	  <br>
	  <div class="form-group">
	    <label >Password:</label>
	    <input type="password" name="password" class="form-control" placeholder="Enter password"  required="required">
	  </div>
	    <br>
	  <div class="form-group">
	    <label >Email:</label>
	    <input type="email" name="email" class="form-control" placeholder="Enter email" >
	  </div>
	  
	  <button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>