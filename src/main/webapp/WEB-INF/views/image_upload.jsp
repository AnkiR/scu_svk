<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload File Request Page</title>
<style>
header {
    color: black;
    text-align: left;
    font-size: 14px;
    text-align:left;
    padding:5px;	 
}

section {
    background-color:#eeeeee;
    height:400px;
    width:1200px;
    padding:20px;
    text-align:center;
    align:justify;
    font-size:100% 	 
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


 <section >

<h3>Upload Images</h3>

    <form method="post" action="uploadFile?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
        File1 to upload: <input type="file" name="fileUpload"><br /> 
        File2 to upload: <input type="file" name="fileUpload"><br /> 
        File3 to upload: <input type="file" name="fileUpload"><br /> 
        File4 to upload: <input type="file" name="fileUpload"><br /> 
        File5 to upload: <input type="file" name="fileUpload"><br /> <br />
         Press here to upload the file!<input type="submit" value="Upload">
              
    </form>
  <br /><br /><br /><br />
</section> 




<footer>
Copyright ©sears.com
</footer> 
</body>
</html>

