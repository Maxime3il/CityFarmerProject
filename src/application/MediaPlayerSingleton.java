/**
* Cette classe représente un Singleton MediaPlayer, qui permet de jouer des sons avec la bibliothèque JavaFX.
* Elle contient un HashMap pour stocker les MediaPlayer créés, afin de ne pas avoir à recréer un MediaPlayer à chaque fois que le même son est joué.
* Elle peut également vérifier si d'autres sons sont en train d'être joués et les arrêter avant de jouer un nouveau son.
*/
package application;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerSingleton {

	private static MediaPlayerSingleton instance = null;
	
	private boolean mute;
	
	Map<String, MediaPlayer> holder;

	/**
	 * Constructeur privé pour empêcher l'instanciation directe de la classe en dehors de celle-ci.
	 * Initialise le HashMap pour stocker les MediaPlayers.
	 */
	private MediaPlayerSingleton() {
		holder = new HashMap<>();
	}

	/**
	 * Méthode pour récupérer l'instance unique du Singleton MediaPlayerSingleton.
	 * Si l'instance n'existe pas encore, elle est créée avant d'être renvoyée.
	 * @return L'instance unique du Singleton MediaPlayerSingleton.
	 */
	public static MediaPlayerSingleton getInstance() {
		if (instance == null) {
			instance = new MediaPlayerSingleton();
		}
		return instance;
	}

	/**
	 * Méthode pour jouer un son avec le chemin de fichier spécifié.
	 * Si le son a déjà été joué et que le MediaPlayer correspondant est déjà stocké dans le HashMap, il est récupéré.
	 * Sinon, un nouveau MediaPlayer est créé pour le son et stocké dans le HashMap.
	 * La méthode vérifie également si d'autres sons sont en train d'être joués, les arrête si c'est le cas, et joue ensuite le nouveau son.
	 * @param path Le chemin de fichier du son à jouer.
	 * @return Le MediaPlayer pour le son joué.
	 */
	public MediaPlayer jouerSon(String path) {
		MediaPlayer mediaPlayer = null;
		if (holder.containsKey(path)) {
			mediaPlayer = holder.get(path);
		} else {
			Media media = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			holder.put(path, mediaPlayer);
		}
		mediaPlayer.setMute(mute);
		
		// Verifie s'il y a d'autres sons en train de jouer
	    boolean isPlaying = false;
	    for (MediaPlayer player : holder.values()) {
	        if (player.getStatus() == MediaPlayer.Status.PLAYING) {
	            isPlaying = true;
	            break;
	        }
	    }

	    // Si oui, il l'arrete et après il joue le son
	    if (isPlaying) {
	        for (MediaPlayer player : holder.values()) {
	            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
	                player.stop();
	            }
	        }
	    }
		mediaPlayer.play();
		return mediaPlayer;
	}

	/**
	 * Méthode pour activer ou désactiver le mode muet pour tous les MediaPlayers stockés dans le HashMap.
	 * @param mute true pour activer le mode muet, false pour le désactiver.
	 */
	public void setMute(boolean mute) {
		for (Entry<String, MediaPlayer> entry : holder.entrySet()) {
			entry.getValue().setMute(mute);
		}
		this.mute = mute;
	}

	/**
	*	Cette méthode permet de savoir si l'objet est en mode muet ou non.
	* 	@return true si l'objet est en mode muet, false sinon.
	*/
	public boolean isMute() {
		return mute;
	}
	
	
	

}