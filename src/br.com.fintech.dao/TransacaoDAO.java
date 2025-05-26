package br.com.fintech.dao;

import br.com.fintech.model.Transacao;

import java.util.List;

public interface TransacaoDAO {
    void inserir(Transacao transacao);
    List<Transacao> listarPorUsuario(int idUsuario);
    void excluir(int idTransacao);
    double calcularTotalPorTipo(int idUsuario, String tipoTransacao);
}
