package com.merlin.util;

import java.awt.Color;
import java.awt.Graphics;

public class BarCodeGenerator {

    private static final String startPattern = "NNNN";
    private static final String stopPattern = "WNN";
    public static final int DEFAULT_BARCODE_HEIGHT = 25;
    public static final int DEFAULT_QUIETZONE_WIDTH = 22;
    public static final int WIDE_ELEMENT_WIDTH = 4;
    public static final int NARROW_ELEMENT_WIDTH = 2;
    private int quietZoneWidth = DEFAULT_QUIETZONE_WIDTH;
    private static final String[] I25Pattern = {"NNWWN", "WNNNW", "NWNNW", "WWNNN", "NNWNW", "WNWNN", "NWWNN", "NNNWW", "WNNWN", "NWNWN"};
    private String pattern;

    public void setValue(String s) {
        if ((s.length() == 0) || ((s.length() % 2) != 0)) {
            pattern = "";
        }

        char[] c = s.toCharArray();
        StringBuffer encoded = new StringBuffer();

        encoded.append("!");
        encoded.append(startPattern);

        for (int i = 0; i < c.length; i += 2) {
            if (!Character.isDigit(c[i]) || !Character.isDigit(c[i + 1])) {
                pattern = "";
            }

            String d1 = I25Pattern[c[i] - 48];
            String d2 = I25Pattern[c[i + 1] - 48];

            for (int n = 0; n <= 4; n++) {
                encoded.append(d1.charAt(n));
                encoded.append(d2.charAt(n));
            }
        }

        encoded.append(stopPattern);
        encoded.append("!");

        pattern = encoded.toString();
    }

    public void render(Graphics g, int x, int y, int height) {
        boolean bBar = false;
        int iX = x;
        int iW = 0;

        g.setColor(Color.black);
        for (int i = 0; i < pattern.length(); i++) {
            switch (pattern.charAt(i)) {
                case '!': /* zona de silencio */
                    iW = quietZoneWidth;
                    bBar = false;
                    break;
                case 'N': /* barra fina */
                    iW = NARROW_ELEMENT_WIDTH;
                    break;
                case 'W': /* barra larga */
                    iW = WIDE_ELEMENT_WIDTH;
                    break;
                default:
                    return;
            }

            if (bBar) {
                g.fillRect(iX, y, iW, y + height);
            }

            iX += iW;
            bBar = !bBar;
        }
    }
}
