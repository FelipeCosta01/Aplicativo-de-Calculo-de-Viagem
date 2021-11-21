import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vendasDAO {

	public List<vendas> listaVendas(vendas va) throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT pedido, codcli, codven, vtotal ");
        sql.append("FROM trab_vendas ");
        sql.append("WHERE pedido = ? ");
        sql.append("ORDER BY pedido ");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, va.getPedido());

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Cria uma lista para armazenar o resultado da consulta */
        List<vendas> listaVendas = new ArrayList<vendas>();

        /* Percorre o resultado armazenando os valores em uma lista */
        while (resultado.next()) {
            /* Cria um objeto para armazenar uma linha da consulta */
        	vendas linha = new vendas();
            linha.setPedido(resultado.getInt("pedido"));
            linha.setCodcli(resultado.getInt("codcli"));
            linha.setCodven(resultado.getInt("codven"));
            linha.setVtotal(resultado.getFloat("vtotal"));
            /* Armazena a linha lida em uma lista */
            listaVendas.add(linha);
        }

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return listaVendas;
    }
	
	public void adicionaVendas(vendas va) throws Exception {
		 String sql = "INSERT INTO trab_vendas(codcli,codven,vtotal) VALUES(?,?,?)";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setInt(1, va.getCodcli());
	            comando.setInt(2, va.getCodven());
	            comando.setFloat(3, va.getVtotal());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
	public void editaVen(vendas va) throws Exception {
		 String sql = "update trab_vendas set codcli=?, codven=?, vtotal=? where codven=? ";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setInt(1, va.getCodcli());
	            comando.setInt(2, va.getCodven());
	            comando.setFloat(3, va.getVtotal());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
	public void removeVendas(vendas va) throws Exception {
		 String sql = "delete from trab_vendas where pedido=?";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setInt(1, va.getPedido());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
}
