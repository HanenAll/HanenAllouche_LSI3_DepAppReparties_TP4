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

			//reception des messages des autres clients
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length); //on prepare le Packet vide pour la reception des données
			socket.receive(receivePacket); // la reception  d'un datagram envoyer par le serveur et la lecture des octects qui se trouve dans le port 
			// la methode receive necessite dans ses param un packet vide
			String message= new String(receivePacket.getData(),0,receivePacket.getLength()); /* on recupere le message envoyer par le serveur 
			et on fait sa conversion de byte en String par le constructeur du String , la methode getData() elle me donne le tableau d'octect ,
			 0 c'est l'indice de depart et reveivePakcet.getlength() c'est l'indice de fin , on faot ici on va lire et convertir le tableau du premier
			  indice jusq'au sa fin */
			
			socket.close();// fermeture du socket
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
