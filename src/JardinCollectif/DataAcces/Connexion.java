package JardinCollectif.DataAcces;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import JardinCollectif.IFT287Exception;

/**
 * Gestionnaire d'une connexion avec une BD relationnelle via JDBC.<br><br>
 * 
 * Cette classe ouvre une connexion avec une BD via JDBC.<br>
 * La méthode serveursSupportes() indique les serveurs supportés.<br>
 * <pre>
 * Pré-condition
 *   Le driver JDBC approprié doit être accessible.
 * 
 * Post-condition
 *   La connexion est ouverte en mode autocommit false et sérialisable, 
 *   (s'il est supporté par le serveur).
 * </pre>
 * <br>
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * @author Marc Frappier - Université de Sherbrooke
 * @version Version 2.0 - 13 novembre 2004
 * 
 * 
 * @author Vincent Ducharme - Université de Sherbrooke
 * @version Version 3.0 - 21 mai 2016
 */
public class Connexion
{
	 private MongoClient mc;


    /**
     * Ouverture d'une connexion en mode autocommit false et sérialisable (si
     * supporté)
     * 
     * @param serveur Le type de serveur SQL à utiliser (Valeur : local, dinf).
     * @param bd      Le nom de la base de données sur le serveur.
     * @param user    Le nom d'utilisateur à utiliser pour se connecter à la base de données.
     * @param pass    Le mot de passe associé à l'utilisateur.
     */
    public Connexion(String serveur, String bd, String user, String pass)
            throws IFT287Exception
    {
    	
        try
        {
            
            if (serveur.equals("local"))
            {
            	mc = new MongoClient();
            }
            else if (serveur.equals("dinf"))
            {
            	MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + pass + "@bd-info2.dinf.usherbrooke.ca:6136/bd?options");
            	mc = new MongoClient(uri);
            }
            else
            {
                throw new IFT287Exception("Serveur inconnu");
            }

	        
	        System.out.println("Ouverture de la connexion :\n"
	                + "Connecte sur la BD ObjectDB "
	                + bd + " avec l'utilisateur " + user);
        }

        catch (Exception e)
        {
            e.printStackTrace(System.out);
            throw new IFT287Exception("EMF non instancié");
        }
    }

    /**
     * Fermeture d'une connexion
     */
    public void fermer()
    {
        System.out.println("Connexion fermee ");
    }


    /**
     * Retourne la Connection mongoDB
     */
    public MongoDatabase getConnection()
    {
    	return mc.getDatabase("db");

    }
    




}