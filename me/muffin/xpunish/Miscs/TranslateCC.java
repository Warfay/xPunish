package me.muffin.xpunish.Miscs;

public class TranslateCC {

	public static void useAlternateColorCodes(String colorcode, String text) {
		
		if (text.contains(colorcode)) {
			
			text.replaceAll(colorcode, "§");
			
		}
		
	}
	
}
