package br.com.fintech.dao;

import br.com.fintech.model.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO {

    @Override
    public void inserir(Transacao transacao) {
        String sql = "INSERT INTO transacao (valor, tipo, id_usuario, id_categoria, data_transacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, transacao.getValor());
            stmt.setString(2, transacao.getTipo().trim().toLowerCase());
            stmt.setInt(3, transacao.getIdUsuario());
            stmt.setInt(4, transacao.getIdCategoria());
            stmt.setDate(5, new java.sql.Date(transacao.getData().getTime())); // Inclui data

            stmt.executeUpdate();
            System.out.println("✅ Transação inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir transação:");
            e.printStackTrace();
        }
    }

    @Override
    public List<Transacao> listarPorUsuario(int idUsuario) {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT t.ID_TRANSACAO, t.VALOR, t.TIPO, t.DATA_TRANSACAO, t.ID_USUARIO, " +
                "t.ID_CATEGORIA, c.NOME_CATEGORIA AS nome_categoria " +
                "FROM TRANSACAO t " +
                "JOIN CATEGORIA c ON t.ID_CATEGORIA = c.ID_CATEGORIA " +
                "WHERE t.ID_USUARIO = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("ID_TRANSACAO"));
                t.setValor(rs.getDouble("VALOR"));
                t.setTipo(rs.getString("TIPO"));
                t.setData(rs.getDate("DATA_TRANSACAO"));
                t.setIdUsuario(rs.getInt("ID_USUARIO"));
                t.setIdCategoria(rs.getInt("ID_CATEGORIA"));
                t.setNomeCategoria(rs.getString("nome_categoria"));
                transacoes.add(t);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar transações:");
            e.printStackTrace();
        }

        return transacoes;
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM transacao WHERE id_transacao = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("🗑️ Transação excluída com sucesso!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir transação:");
            e.printStackTrace();
        }
    }
    @Override
    public double calcularTotalPorTipo(int idUsuario, String tipo) {
        double total = 0.0;
        String sql = "SELECT NVL(SUM(valor), 0) FROM transacao WHERE id_usuario = ? AND tipo = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao calcular total de " + tipo + ":");
            e.printStackTrace();
        }

        return total;
    }
}