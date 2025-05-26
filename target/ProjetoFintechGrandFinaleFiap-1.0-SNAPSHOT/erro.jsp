<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erro</title>
</head>
<body>
<h2>Ocorreu um erro.</h2>

<p>Desculpe, algo deu errado durante a execução.</p>

<c:if test="${not empty erro}">
    <p style="color:red;">${erro}</p>
</c:if>

<p><a href="dashboard.jsp">Voltar ao início</a></p>
</body>
</html>
