

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Sign in</title>

</head>

<body>
<%@ include file="/resources/include/table.jsp" %>
<h4><%
    String error = (String) request.getAttribute("error");
    if(error!=null) {out.print(error);}
%></h4>


</body>
</html>
