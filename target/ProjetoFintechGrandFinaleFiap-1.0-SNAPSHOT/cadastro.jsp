<form action="cadastrar-usuario" method="post">
    <h2>Cadastro</h2>
    <input type="text" name="nome" placeholder="Nome" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="senha" placeholder="Senha" required><br>
    <input type="submit" value="Cadastrar"><br>
    <c:if test="${not empty erro}">
        <p style="color:red;">${erro}</p>
    </c:if>
</form>