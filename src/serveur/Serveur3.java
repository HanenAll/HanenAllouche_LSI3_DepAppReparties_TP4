package serveur;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;
// activite 4.3
public class Serveur3 {
	private static final int PORT=1234; 
	private static byte[] receiveData= new byte[1024]; // Tampon pour les données reçues
	private static HashSet<InetSocketAddress> clients = new HashSet<>(); //ensemble pour stocker des clients connectés
    // des param propre au serveur 
	public static void main(String[] args) {
		try {
			DatagramSocket serverSocket= new DatagramSocket(PORT); // creation d'une socket en utilisant le port 1234
			System.out.println("Demarrage du serveur sur le port :"+ PORT);
			while(true) {
				//pour pouvoir accepter plusieurs clients
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length); //on prepare le Packet vide pour la reception des données
				
				serverSocket.receive(receivePacket); // la reception  d'un datagram envoyer par le client et la lecture des octects qui se trouve dans le port 
				// la methode receive necessite dans ses param un packet vide
				
				String message= new String(receivePacket.getData(),0,receivePacket.getLength()); /* on recupere le message envoyer par le client 
				et on fait sa conversion de byte en String par le constructeur du String , la methode getData() elle me donne le tableau d'octect ,
				 0 c'est l'indice de depart et reveivePakcet.getlength() c'est l'indice de fin , on faot ici on va lire et convertir le tableau du premier
				  indice jusq'au sa fin */
				
				InetSocketAddress senderAddress= new InetSocketAddress(receivePacket.getAddress(),receivePacket.getPort()); 
				if(!clients.contains(senderAddress)) //Si le client n'est pas deja enregistrer
				{
					clients.add(senderAddress); //on ajoute à la liste des clients connectés
					System.out.println("Nouveau client connecté : "+senderAddress);
				}
				// affiche le message reçu de l'expediteur
				System.out.println("Message reçu de "+senderAddress+" : "+message);
				// Réachemine le message à tous les autres clients connectés, à l'exception de l'expediteur 
				for(InetSocketAddress client: clients)
				{
					if(!client.equals(senderAddress)) {
						DatagramPacket sendPacket=new DatagramPacket(receivePacket.getData(),receivePacket.getLength(),client.getAddress(),client.getPort());
						serverSocket.send(sendPacket);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
