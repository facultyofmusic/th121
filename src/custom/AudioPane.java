package custom;

import java.util.HashMap;

import javax.swing.JPanel;

public class AudioPane extends JPanel {

	public static void checkValues(HashMap<String, String> values) throws Exception {
		Integer.parseInt(values.get("MUSIC_VOLUME"));
		Integer.parseInt(values.get("SFX_VOLUME"));
		String s = values.get("SOUND_SYSTEM");
		if (!(s.equals("java") || s.equals("openal"))){
			throw new Exception("Bad sound system argument");
		}
	}
}
