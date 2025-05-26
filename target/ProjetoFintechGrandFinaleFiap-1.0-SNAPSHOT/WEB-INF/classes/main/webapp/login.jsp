<form action="login" method="post">
    <h2>Login</h2>
    <input type="text" name="email" placeholder="Email" required><br>
    <input type="password" name="senha" placeholder="Senha" required><br>
    <input type="submit" value="Entrar"><br>
    <p><a href="cadastro.jsp">Não tem conta? Cadastre-se</a></p>
    <c:if test="${not empty erro}">
        <p style="color:red;">${erro}</p>
    </c:if>
</form>