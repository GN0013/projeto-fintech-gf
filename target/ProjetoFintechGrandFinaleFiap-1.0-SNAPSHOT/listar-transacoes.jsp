<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    HttpSession sessao = (HttpSession) request.getSession(false);
    String usuario = (sessao != null) ? (String) sessao.getAttribute("usuario") : null;

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Minhas Transações</title>
</head>
<body>
<h2>Transações de <%= usuario %></h2>

<table border="1">
    <tr>
        <th>Tipo</th>
        <th>Valor</th>
        <th>Data</th>
        <th>Categoria</th>
        <th>Ação</th> <!-- nova coluna -->
    </tr>
    <c:forEach var="t" items="${transacoes}">
        <tr>
            <td>${t.tipo}</td>
            <td>${t.valor}</td>
            <td>${t.data}</td>
            <td>${t.nomeCategoria}</td>
            <td>
                <form action="excluir-transacao" method="post" onsubmit="return confirm('Tem certeza que deseja excluir?');">
                    <input type="hidden" name="id_transacao" value="${t.id}">
                    <input type="submit" value="Excluir">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br/>
<a href="dashboard.jsp">Voltar ao Dashboard</a>
</body>
</html>