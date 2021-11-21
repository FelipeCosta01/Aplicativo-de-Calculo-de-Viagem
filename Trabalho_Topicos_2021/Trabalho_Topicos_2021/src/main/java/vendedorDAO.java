import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vendedorDAO {

	public List<vendedor> listaVen(vendedor v) throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codven, nomven, email_ven, percent_comissao ");
        sql.append("FROM trab_vendedor ");
        sql.append("WHERE nomven LIKE ? ");
        sql.append("ORDER BY nomven ");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setString(1, "%" + v.getNomven()+ "%");

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Cria uma lista para armazenar o resultado da consulta */
        List<vendedor> listaVen = new ArrayList<vendedor>();

        /* Percorre o resultado armazenando os valores em uma lista */
        while (resultado.next()) {
            /* Cria um objeto para armazenar uma linha da consulta */
        	vendedor linha = new vendedor();
            linha.setCodven(resultado.getInt("codven"));
            linha.setNomven(resultado.getString("nomeven"));
            linha.setEmail_ven(resultado.getString("email_ven"));
            linha.setPercent_comissao(resultado.getFloat("percent_comissao"));
            /* Armazena a linha lida em uma lista */
            listaVen.add(linha);
        }

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return listaVen;
    }
	
	public void adicionaVen(vendedor v) throws Exception {
		 String sql = "INSERT INTO trab_vendedor(nomven,email_ven,percent_comissao) VALUES(?,?,?)";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setString(1, v.getNomven());
	            comando.setString(2, v.getEmail_ven());
	            comando.setFloat(3, v.getPercent_comissao());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
	public void editaVen(vendedor v) throws Exception {
		 String sql = "update trab_vendedor set nomven=?, email_ven=?, percent_comissao=? where codven=? ";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setString(1, v.getNomven());
	            comando.setString(2, v.getEmail_ven());
	            comando.setFloat(3, v.getPercent_comissao());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
	public void removeVen(vendedor v) throws Exception {
		 String sql = "delete from trab_vendedor where codven=?";
		 Connection conn = conexao.abrir();
	        try { 
	            PreparedStatement comando = conn.prepareStatement(sql);
	            comando.setInt(1, v.getCodven());
	            comando.execute();
	            comando.close();
	        } 
	        catch (SQLException u) { 
	            throw new RuntimeException(u);
	        } 
	}
	
}
