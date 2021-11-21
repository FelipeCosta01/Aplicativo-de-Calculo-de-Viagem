import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO {
	

	public List<cliente> listaCli(cliente c) throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codcli, nomcli, email_cli ");
        sql.append("FROM trab_cliente ");
        sql.append("WHERE nomcli LIKE ? ");
        sql.append("ORDER BY nomcli ");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setString(1, "%" + c.getNomcli()+ "%");

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Cria uma lista para armazenar o resultado da consulta */
        List<cliente> lista = new ArrayList<cliente>();

        /* Percorre o resultado armazenando os valores em uma lista */
        while (resultado.next()) {
            /* Cria um objeto para armazenar uma linha da consulta */
            cliente linha = new cliente();
            linha.setCodcli(resultado.getInt("cod_cliente"));
            linha.setNomcli(resultado.getString("nome_cliente"));
            linha.setEmail_cli(resultado.getString("email_cliente"));
            /* Armazena a linha lida em uma lista */
            lista.add(linha);
        }

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return lista;
    }
	
	public void adicionaCli(cliente c) throws Exception {
		 String sql = "INSERT INTO trab_cliente(nomcli,email_cli) VALUES(?,?)";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setString(1, c.getNomcli());
	            comando.setString(2, c.getEmail_cli());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
	public void editaCli(cliente c) throws Exception {
		 String sql = "update trab_cliente set nomcli=?, email_cli=? where codcli=? ";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setString(1, c.getNomcli());
	            comando.setString(2, c.getEmail_cli());
	            comando.setInt(3, c.getCodcli());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
	public void removeCli(cliente c) throws Exception {
		 String sql = "delete from trab_cliente where codcli=?";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setInt(1, c.getCodcli());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
}
