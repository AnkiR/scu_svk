<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#header {
    background-color:white;
    color:black;
    text-align:left;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:white;
    width:60%;
    float:left;
    padding:5px;	      
}
#section {
    background-color:white;
    width:30%;
    float:left;
    padding:10px;	 	 
}
#footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}
</style>
</head>

<body>
<div id="header">
<h1>SEARS VIRTUAL KITCHEN</h1>
<div style="text-align:right">Hello ${fullName}</div>
<div style="text-align:right"><a href="/svk/login">Logout</a></div><hr></div>


<div id="nav">
<img src="images/home.jpg" alt="Home Screen">
<br><br><br><br>
</div>

<div id="section">
<h2>Welcome to Sears Kitchen</h2>
<p>
Sears Virtual Kitchen helps you design your kitchen space with various appliances using potential color and layout options.Start designing your space by uploading your kitchen image.
</p>
<span style="text-align:right"><a href="upload"><button style="background-color: #FF6600; color: white">Launch Virtual Kitchen</button></a></span>
<br><br>
<span style="text-align:right">
<a href="instructions">Instructions to upload your image</a></span>
<br><br><br><br>
</div>



<div id="footer">
Copyright � sears.com
</div>


</body>
</html>