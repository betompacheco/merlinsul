<%@ page isErrorPage="true" %>
<html>
<head><title>Error Page</title></head>
<body>
<%=exception.getMessage()%>
<%
System.out.println(exception.getClass().getName());
exception.printStackTrace();
%>
</body>
</html>
