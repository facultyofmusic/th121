package custom;

import java.util.HashMap;

import javax.swing.JPanel;

public class KeyConfigPane extends JPanel{

	public static void checkValues(HashMap <String, String> values) throws Exception{
		int[] keys = new int [8];

		keys[0] = Integer.parseInt(values.get("KEY_FOCUS"));
		keys[1] = Integer.parseInt(values.get("KEY_A"));
		keys[2] = Integer.parseInt(values.get("KEY_B"));
		keys[3] = Integer.parseInt(values.get("KEY_UP"));
		keys[4] = Integer.parseInt(values.get("KEY_DOWN"));
		keys[5] = Integer.parseInt(values.get("KEY_LEFT"));
		keys[6] = Integer.parseInt(values.get("KEY_RIGHT"));
		keys[7] = Integer.parseInt(values.get("KEY_QUIT"));
		
		for(int x = 0; x < 7; x++){
			for(int y = x + 1; y < 8; y++){
				if (keys[x] == keys[y]){
					throw new Exception ("Conflicting keys");
				}
			}
		}
	}
}
