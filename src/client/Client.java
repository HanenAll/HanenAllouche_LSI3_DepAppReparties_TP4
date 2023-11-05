package client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
//activite 4.1
public class Client {
	private static final int PORT =1234; // port du serveur
     private static byte[] receiveData = new byte[1024]; // Tampon pour les données reçues
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(); // creation d'une socket
			InetAddress inetAddress = InetAddress.getByName("localhost"); //cherche moi la machine localhsot
			
			
			//traitement
			Scanner scanner = new Scanner(System.in);
			System.out.println("ecrivez votre nom et prenom:");
			String msg=scanner.nextLine();
			scanner.close();
			byte[] sendData=msg.getBytes(); // la methode getByte() fait la convertion du msg en bytes
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,inetAddress,PORT); /* on met les données dans un packet et on specifie 
			l'@ de la machine et le port */
			socket.send(sendPacket); // envoit du packet au serveur
			
			// reception du reponse
			DatagramPacket receivePacket= new DatagramPacket(receiveData,receiveData.length);//on prepare le Packet vide pour la reception des données
			socket.receive(receivePacket); // la reception du packet envoyer par le serveur, 
			// la methode receive necessite dans ses param un packet vide
			String reponse = new String(receivePacket.getData(),0,receivePacket.getLength());/* on recupere le message envoyer par le client 
			et on fait sa conversion de byte en String par le constructeur du String , la methode getData() elle me donne le tableau d'octect ,
			 0 c'est l'indice de depart et reveivePakcet.getlength() c'est l'indice de fin , on faot ici on va lire et convertir le tableau du premier
			  indice jusq'au sa fin */
			System.out.println(" reponse du serveur :"+reponse);
			socket.close();// fermeture du socket
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
