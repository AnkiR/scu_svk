<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<html>
 
<body> 

<h2>Spring MVC - Uploading a file.. </h2>
    <c:if test="${filename != null}">
      <h3>You just uploaded ${filename}</h3>
    </c:if>
    <form:form method="POST" action="upload2" enctype="multipart/form-data">
 
        Upload your file please: 
        <input type="file" name="file" />
        Name: 
        <input type="text" name="name"><br /> 
        <input type="submit" value="upload" />
        <form:errors path="file" cssStyle="color: #ff0000;" />
    </form:form>
 
</body>
</html>