<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Saldo</title>
</head>
<body>
<h2>Resumo Financeiro</h2>

<p><strong>Total de Entradas:</strong> R$ ${entrada}</p>
<p><strong>Total de Saídas:</strong> R$ ${saida}</p>
<p><strong>Saldo Atual:</strong> R$ ${saldo}</p>

<br/>
<a href="dashboard.jsp">Voltar ao Dashboard</a>
</body>
</html>