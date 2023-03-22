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

	private MediaPlayerSingleton() {
		holder = new HashMap<>();
	}

	public static MediaPlayerSingleton getInstance() {
		if (instance == null) {
			instance = new MediaPlayerSingleton();
		}
		return instance;
	}

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
		mediaPlayer.play();
		return mediaPlayer;
	}
	
	public void setMute(boolean mute) {
		for (Entry<String, MediaPlayer> entry : holder.entrySet()) {
			entry.getValue().setMute(mute);
		}
		this.mute = mute;
	}

	public boolean isMute() {
		return mute;
	}
	
	
	

}
