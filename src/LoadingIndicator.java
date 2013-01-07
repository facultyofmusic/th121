import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LoadingIndicator {
	private static Image snow, english, japanese;
	private static boolean jP, eP, sP;

	public static void reset() {
		try {
			if (snow == null) {
				Image loading = new Image("data/images/loading.png");
				japanese = loading.getSubImage(0, 0, 128, 32);
				english = loading.getSubImage(0, 32, 128, 32);
				snow = loading.getSubImage(0, 64, 32, 32).getScaledCopy(2.5f);
			}

			snow.setAlpha(0);
			japanese.setAlpha(0);
			english.setAlpha(0);
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}

	public static void draw(Graphics g) throws SlickException {
		g.drawImage(snow, 450, 350);
		snow.setRotation(snow.getRotation() + 2f);
		if (snow.getAlpha() >= 0.6) {
			sP = false;
		} else if (snow.getAlpha() <= 0.2f) {
			sP = true;
		}

		if (sP) {
			snow.setAlpha(snow.getAlpha() + 0.02f);
		} else {
			snow.setAlpha(snow.getAlpha() - 0.02f);
		}

		g.drawImage(japanese, 450, 380);
		if (japanese.getAlpha() >= 1) {
			jP = false;
		} else if (japanese.getAlpha() <= 0.3f) {
			jP = true;
		}

		if (jP) {
			japanese.setAlpha(japanese.getAlpha() + 0.04f);
		} else {
			japanese.setAlpha(japanese.getAlpha() - 0.04f);
		}

		g.drawImage(english, 480, 400);
		if (english.getAlpha() >= 1) {
			eP = false;
		} else if (english.getAlpha() <= 0.3f) {
			eP = true;
		}

		if (eP) {
			english.setAlpha(english.getAlpha() + 0.06f);
		} else {
			english.setAlpha(english.getAlpha() - 0.06f);
		}
	}
}