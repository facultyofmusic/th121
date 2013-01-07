package custom;

import java.util.HashMap;

import javax.swing.JPanel;

public class VideoPane extends JPanel {
	public VideoPane() {

	}

	public static void checkValues(HashMap<String, String> values) throws Exception {
		Boolean.parseBoolean(values.get("V_SYNC"));
		Boolean.parseBoolean(values.get("FULLSCREEN"));
		Boolean.parseBoolean(values.get("FORCE_FRAMERATE"));
		int res = Integer.parseInt(values.get("RESOLUTION"));
		if (!(res == 640 || res == 960 || res == 1280)) {
			throw new Exception("Bad resolution: " + res);
		}
	}
}
