import java.io.*;
import java.net.*;

class UDPServerSoma {
  public static void main(String args[]) throws Exception
    {
     try
     {
      DatagramSocket serverSocket = new DatagramSocket(9876);

      byte[] receiveData = new byte[1024];
      byte[] sendData  = new byte[1024];

      while(true)
        {

          receiveData = new byte[1024];

          DatagramPacket receivePacket =
             new DatagramPacket(receiveData, receiveData.length);

          System.out.println ("Waiting for datagram packet");

          serverSocket.receive(receivePacket);

          String op1 = new String(receivePacket.getData());
					System.out.println ("op1 " + op1);


          serverSocket.receive(receivePacket);

          String op2 = new String(receivePacket.getData());
					System.out.println ("op2 " + op2);

          InetAddress IPAddress = receivePacket.getAddress();
          int port = receivePacket.getPort();

          System.out.println ("From: " + IPAddress + ":" + port);
          int a = 0;
					int b = 0;
					try {

					  a = Integer.parseInt(op1.trim());
					  b = Integer.parseInt(op2.trim());
					} catch (NumberFormatException e) {
					}
					int soma = a+b;

					String resposta = Integer.toString(soma);

          sendData = resposta.getBytes();

          DatagramPacket sendPacket =
             new DatagramPacket(sendData, sendData.length, IPAddress,
                               port);

          serverSocket.send(sendPacket);

        }

     }
      catch (SocketException ex) {
        System.out.println("UDP Port 9876 is occupied.");
        System.exit(1);
      }

    }
}
