<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File success Page</title>
<style>
<style>
header {
    color: black;
    text-align: left;
    font-size: 14px;
    text-align:left;
    padding:5px;	 
}
nav {
    line-height:30px;
    background-color:#eeeeee;
    height:500px;
    width:200px;
    float:left;
    padding:5px;	      
}
section {
    background-color:#eeeeee;
    height:500px;
    width:900px;
    float:left;
    padding:10px;	 	 
}
footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
    padding:5px;	 	 
}
</style>
</head>
<body>
<header>
<h1>SEARS VIRTUAL KITCHEN</h1>
<div style="text-align:right">Hello ${fullName}</div>
<div style="text-align:right"><a href="/svk/login">Logout</a></div>
<hr>
</header>

<nav>
Your Appliances
</nav>


<section>
<h3>Your Design Space</h3>
<!-- <img src="${img_src}"/>-->
<!-- <img src="${img_src}" width="500" height="400"/>-->
<img src="data:image/jpeg;base64,${image_src}" alt="..." width="900" height="400">
<br><br><br><br><br><br><br><br><br><br>
</section>


<footer>
Copyright © sears.com
</footer>
</body>
</html>

