package engtelecom;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor que espera por uma mensagem do cliente (C ou Java) (String em bytes) e depois
 * envia uma String de resposta, tambem em bytes
 *
 * 2014-08-24
 * @author Emerson Ribeiro de Mello
 */
public class ServidorBytes {

    public static void main(String[] args) throws IOException {

        /* Registra servico na porta 1234 e aguarda por conexoes */
        ServerSocket servidor = new ServerSocket(1234);

        System.out.println("Aguardando por conexoes");
        Socket conexao = servidor.accept();
        System.out.println("Cliente conectou " + conexao);
        /*********************************************************/
        /* Estabelece fluxos de entrada e saida */
        DataInputStream fluxoEntrada = new DataInputStream(
                new BufferedInputStream(conexao.getInputStream()));
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        /*********************************************************/
        /* inicia a comunicacao */
        byte[] b = new byte[1024];
        fluxoEntrada.read(b);
        String mensagem = new String(b);
        System.out.println("Cliente> " + mensagem);
        mensagem = "Oi, eu sou o servidor!";
        fluxoSaida.write(mensagem.getBytes());
        /*********************************************************/

        /* Fecha fluxos e socket */
        fluxoEntrada.close();
        fluxoSaida.close();
        conexao.close();
        servidor.close();
    }

}