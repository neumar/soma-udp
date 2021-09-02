import java.io.*;
import java.net.*;

class UDPClientSoma {
    public static void main(String args[]) throws Exception
    {
     try {
        String serverHostname = new String ("localhost");

        if (args.length > 0)
           serverHostname = args[0];

      BufferedReader inFromUser =
        new BufferedReader(new InputStreamReader(System.in));

      DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = InetAddress.getByName(serverHostname); 
      System.out.println ("Attemping to connect to " + IPAddress +
                          ") via UDP port 9876");

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];

      System.out.print("Informe operando 1: ");
      String sentence = inFromUser.readLine();
      sendData = sentence.getBytes();

		  DatagramPacket sendPacket =
         new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

      clientSocket.send(sendPacket);

			 System.out.print("Informe operando 2: ");
			 sentence = inFromUser.readLine();
			 sendData = sentence.getBytes();

			 sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

			 clientSocket.send(sendPacket);

			 //recebe resposta
			 DatagramPacket receivePacket =
         new DatagramPacket(receiveData, receiveData.length);

      System.out.println ("Waiting for return packet");
      clientSocket.setSoTimeout(10000);

      try {
           clientSocket.receive(receivePacket);
           String resposta =
               new String(receivePacket.getData());

           System.out.println("Message: " + resposta);

          }
      catch (SocketTimeoutException ste)
          {
           System.out.println ("Timeout Occurred: Packet assumed lost");
      }

      clientSocket.close();
     }
   catch (UnknownHostException ex) {
     System.err.println(ex);
    }
   catch (IOException ex) {
     System.err.println(ex);
    }
  }
}
