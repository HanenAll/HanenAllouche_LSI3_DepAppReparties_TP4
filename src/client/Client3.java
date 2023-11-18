package client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
//activite 4.3
public class Client3 {
	private static final int PORT =1234; // port du serveur
     private static byte[] receiveData = new byte[1024]; // Tampon pour les données reçues
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(); // creation d'une socket
			InetAddress inetAddress = InetAddress.getByName("localhost"); //cherche moi la machine localhsot
			
			
			//traitement
			Scanner scanner = new Scanner(System.in);
			System.out.println("ecrivez votre nom et prenom:");
			String nom=scanner.nextLine();
			System.out.println(" conversation :");
			String msg= scanner.nextLine();
			scanner.close();
			//preparation de messsage a envoyé
			String msgcomplet="[" + nom + "]: " + msg;
			byte[] sendData=msgcomplet.getBytes(); // la methode getByte() fait la convertion du msg en bytes
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,inetAddress,PORT); /* on met les données dans un packet et on specifie 
			l'@ de la machine et le port */
			socket.send(sendPacket); // envoit du packet au serveur
			socket.close();// fermeture du socket
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
