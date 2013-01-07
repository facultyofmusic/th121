/**
 * Copyright (c) 2010 L.A.N.E.X Doujin team.
 * 
 * ERM (External resource manager)
 * 
 * This class is used to manage resources external to the game application
 * such as images, music, sound effects, etc.  The ERM uses deferred loading
 * to allow the progress to be shown, as resources usually take a very long 
 * time to load.  
 * 
 * Resources are loaded into their respective Hashmaps according to their file
 * type.  Every time the loader is told to load new resources, the old resources
 * are abandoned.
 * 
 * @author Daniel Gen Li
 */

package th121;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public abstract class ERM {
	/**
	 * Images
	 */
	private static HashMap<String, Image> images;
	
	/**
	 * Sound effects (non-streamed audio)
	 */
	private static HashMap<String, Sound> soundEffects;
	
	/**
	 * Music files (streamed audio)
	 */
	private static HashMap<String, Music> music;
	
	/**
	 * Sprite sheet indexes
	 */
	private static HashMap<String, SpriteIndex[]> spriteSheetIndex;
	
	/**
	 * State variable to see if the loading is still loading.
	 */
	private static boolean doneLoading = false;
	
	/**
	 * The list of files to load.
	 */
	private static String[] toLoad;
	
	/**
	 * Sets the resources to load by giving the address of the resource index file.
	 * 
	 * @param listAddress	the address of the resource index file.
	 */
	public static void set (String listAddress){
		// get a list of resources to load
		try{
			toLoad = new BufferedReader(new FileReader(listAddress)).readLine().trim().split(";");
			THApp.THC.newl();
			THApp.THC.append("ERM: Preparing to load \"" + listAddress + "\"");
			THApp.THC.newl();
		}catch(IOException ex){
			System.err.println("ERROR: missing resources.");
			ex.printStackTrace();
		}

		// Makes the hashmaps if they don't yet exist
		if (images == null) {
			images = new HashMap<String, Image>();
		}
		if (soundEffects == null) {
			soundEffects = new HashMap<String, Sound>();
		}
		if (music == null) {
			music = new HashMap<String, Music>();
		}
		if (spriteSheetIndex == null) {
			spriteSheetIndex = new HashMap<String, SpriteIndex[]>();
		}

		// clear them
		images.clear();
		soundEffects.clear();
		music.clear();
		spriteSheetIndex.clear();
		
		doneLoading = false;
	}

	/**
	 * Checks if ERM is done loading it's assigned resources.
	 * 
	 * @return  True is done loading and is safe to proceed
	 */
	public static boolean isDoneLoading() {
		return doneLoading;
	}

	/**
	 * Loads the next resource in the list.
	 * 
	 * @return  True if the last resource is loaded.
	 */
	public static boolean loadNext() {
		if (doneLoading){
			return doneLoading;
		}
		
		try {
			for (int x = 0; x < toLoad.length; x++) {
				if (toLoad[x] != null) {
					THApp.THC.append("ERM: loading >> " + toLoad[x]);
					if (toLoad[x].endsWith(".png")){
						images.put(toLoad[x].split("/")[toLoad[x].split("/").length-1], new Image(toLoad[x]));
					}else if (toLoad[x].endsWith(".ssic")){
						try {
							BufferedReader in = new BufferedReader(new FileReader(
									toLoad[x]));
							ArrayList<SpriteIndex> list = new ArrayList<SpriteIndex>();

							String str;
							while ((str = in.readLine()) != null) {
								String[] s = str.split(",");
								list.add(new SpriteIndex(Integer.parseInt(s[0].trim()),
										Integer.parseInt(s[1].trim()), Integer
												.parseInt(s[2].trim()), Integer
												.parseInt(s[3].trim())));
							}
							
							spriteSheetIndex.put(toLoad[x].split("/")[toLoad[x].split("/").length-1], list.toArray(new SpriteIndex[0]));
							in.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}else{
						if (toLoad[x].endsWith(".se.ogg")){
							soundEffects.put(toLoad[x].split("/")[toLoad[x].split("/").length-1], new Sound(toLoad[x]));
						}else{
							music.put(toLoad[x].split("/")[toLoad[x].split("/").length-1], new Music(toLoad[x], true));
						}
					}
					toLoad[x] = null;
					return false;
				}
			}
		} catch (SlickException ex) {
			ex.printStackTrace();
		}

		THApp.THC.newl();
		THApp.THC.append("ERM: Resources loaded.");
		doneLoading = true;
		return doneLoading;
	}

	/**
	 * returns the image with the specified key.
	 * 
	 * @param key	The key
	 * @return  The image requested.
	 */
	public static Image getImage(String key) {
		return images.get(key);
	}

	/**
	 * returns the sfx with the specified key.
	 * 
	 * @param key	The key
	 * @return  The sfx requested.
	 */
	public static Sound getSound(String key) {
		return soundEffects.get(key);
	}

	/**
	 * returns the music with the specified key.
	 * 
	 * @param key	The key
	 * @return  The music requested.
	 */
	public static Music getMusic(String key) {
		return music.get(key);
	}
	
	/**
	 * A shortcut method to play a sound effect
	 * 
	 * @param key	The key of the sfx
	 */
	public static void sfx(String key){
		soundEffects.get(key).play();
	}
	
	/**
	 * Gets a index of sprite sheet coordinates
	 * 
	 * @param sheetID	The ID if the sheet (file name)
	 * @param index		Enumerated index
	 * @return			The index
	 */
	public static SpriteIndex getIndex(String sheetID, int index) {
		System.out.println("REQUESTED SHEET: " + sheetID);
		return spriteSheetIndex.get(sheetID)[index];
	}
}
