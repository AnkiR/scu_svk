<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload File Request Page</title>
</head>
<body>
 
    <form method="post" action="uploadFile?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
        File1 to upload: <input type="file" name="fileUpload"><br /> 
        File2 to upload: <input type="file" name="fileUpload"><br /> 
        File3 to upload: <input type="file" name="fileUpload"><br /> 
        File4 to upload: <input type="file" name="fileUpload"><br /> 
        File5 to upload: <input type="file" name="fileUpload"><br /> 
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
     
</body>
</html>