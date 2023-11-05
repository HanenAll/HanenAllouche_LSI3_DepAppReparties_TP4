package serveur;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Date;
import java.text.SimpleDateFormat;
// activite 4.2
public class Serveur2 {
	// des param propre au serveur
	private static final int PORT=1234; 
	private static byte[] receiveData= new byte[1024]; // Tampon pour les données reçues 
	public static void main(String[] args) {
		try {
			DatagramSocket socket= new DatagramSocket(PORT); // creation d'une socket en utilisant le port 1234
			System.out.println("Demarrage du serveur:");
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length); //on prepare le Packet vide pour la reception des données
				
				socket.receive(receivePacket); // la reception  d'un datagram envoyer par le client et la lecture des octects qui se trouve dans le port 
				// la methode receive necessite dans ses param un packet vide
				
				String message= new String(receivePacket.getData(),0,receivePacket.getLength()); /* on recupere le message envoyer par le client 
				et on fait sa conversion de byte en String par le constructeur du String , la methode getData() elle me donne le tableau d'octect ,
				 0 c'est l'indice de depart et reveivePakcet.getlength() c'est l'indice de fin , on faot ici on va lire et convertir le tableau du premier
				  indice jusq'au sa fin */
				System.out.println(receivePacket.getAddress()+" : "+message);
				
				// Réponse au client
				String time=null;
				try {
					time = getCurrentTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				byte[] sendData=time.getBytes(); // conversion de reponse de string en byte 
				DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,receivePacket.getAddress(),receivePacket.getPort());
				socket.send(sendPacket); // envoit du reponse au client
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String getCurrentTime() {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date= new Date(0);
		return dateFormat.format(date);
		
	}
}

