<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%

    String usuario = (session != null) ? (String) session.getAttribute("usuario") : null;
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
<h2>Bem-vindo, <%= usuario %>!</h2>

<ul>
    <li><a href="form-transacao">Cadastrar Transação</a></li>
    <li><a href="listar-transacoes">Ver Transações</a></li>
    <li><a href="saldo">Saldo</a></li>
    <li><a href="logout">Sair</a></li>
</ul>
</body>
</html>