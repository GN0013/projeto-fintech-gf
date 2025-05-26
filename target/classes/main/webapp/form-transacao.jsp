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
    <title>Nova Transação</title>
</head>
<body>
<h2>Cadastrar Nova Transação</h2>

<form action="cadastrar-transacao" method="post">
    <label>Valor:</label><br>
    <input type="number" name="valor" step="0.01" required><br><br>

    <label>Tipo:</label><br>
    <select name="tipo" required>
        <option value="entrada">Entrada</option>
        <option value="saida">Saída</option>
    </select><br><br>

    <label>Categoria:</label><br>
    <select name="id_categoria" required>
        <c:forEach var="categoria" items="${categorias}">
            <option value="${categoria.id}">${categoria.nome}</option>
        </c:forEach>
    </select><br><br>

    <input type="submit" value="Salvar"><br><br>

    <c:if test="${not empty erro}">
        <p style="color:red;">${erro}</p>
    </c:if>
</form>

<br>
<a href="dashboard.jsp">Voltar ao Dashboard</a>
</body>
</html>