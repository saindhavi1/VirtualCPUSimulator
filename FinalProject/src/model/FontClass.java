
package model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/* This class creates the font used in the simulator's GUI
 * I used the same logic Megan used for her Font Class on our last 
 * group project
 */
public class FontClass {
	// graphics environment
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

	// initialize the fonts
	public static Font bold50;
	public static Font medium25;
	public static Font medium30;
	public static Font medium40;
	public static Font regular30;
	public static Font regular20;

	public FontClass() {

		// create the font

		try {
			// create the regular size 20
			regular20 = Font.createFont(Font.TRUETYPE_FONT, new File("Font/KumbhSans-Regular.ttf")).deriveFont(20f);
			ge.registerFont(regular20); // register the font

			// create regular size 30
			regular30 = Font.createFont(Font.TRUETYPE_FONT, new File("Font/KumbhSans-Regular.ttf")).deriveFont(30f);
			ge.registerFont(regular30); // register the font

			// create medium size 25
			medium25 = Font.createFont(Font.TRUETYPE_FONT, new File("Font/KumbhSans-Medium.ttf")).deriveFont(25f);
			ge.registerFont(medium25); // register the font

			// create medium size 30
			medium30 = Font.createFont(Font.TRUETYPE_FONT, new File("Font/KumbhSans-Medium.ttf")).deriveFont(30f);
			ge.registerFont(medium30); // register the font

			// create medium size 40
			medium40 = Font.createFont(Font.TRUETYPE_FONT, new File("Font/KumbhSans-Medium.ttf")).deriveFont(40f);
			ge.registerFont(medium40); // register the font

			// create bold size 50
			bold50 = Font.createFont(Font.TRUETYPE_FONT, new File("Font/KumbhSans-Bold.ttf")).deriveFont(50f);
			ge.registerFont(bold50); // register the font

		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

	}
}
