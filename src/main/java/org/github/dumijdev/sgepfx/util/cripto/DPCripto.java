package org.github.dumijdev.sgepfx.util.cripto;

import java.util.HashMap;
import java.util.Map;

public class DPCripto {
    private static final Map<String, String> s = new HashMap<>();

    static {
        //Minuscula
        s.put("a", "u,");
        s.put("b", ",/");
        s.put("c", ",r");
        s.put("d", "OI");
        s.put("e", "0x");
        s.put("f", "G7");
        s.put("g", "57");
        s.put("h", "Bg");
        s.put("i", "u6");
        s.put("j", "1I");
        s.put("k", "Sk");
        s.put("l", "0r");
        s.put("m", "C6");
        s.put("n", "CK");
        s.put("o", "P8");
        s.put("p", "Nf");
        s.put("q", "76");
        s.put("r", "ss");
        s.put("s", "sD");
        s.put("t", "Zu");
        s.put("u", "10");
        s.put("v", "MZ");
        s.put("w", "0z");
        s.put("x", "5v");
        s.put("y", "MG");
        s.put("z", "G1");
        //Minuscula fim

        //Maiuscula
        s.put("A", "13");
        s.put("B", "44");
        s.put("C", "32");
        s.put("D", "57");
        s.put("E", "23");
        s.put("F", "15");
        s.put("G", "97");
        s.put("H", "54");
        s.put("I", "86");
        s.put("J", "32");
        s.put("K", "63");
        s.put("L", "74");
        s.put("M", "36");
        s.put("N", "21");
        s.put("O", "11");
        s.put("P", "22");
        s.put("Q", "44");
        s.put("R", "33");
        s.put("S", "77");
        s.put("T", "88");
        s.put("U", "00");
        s.put("V", "33");
        s.put("W", "15");
        s.put("X", "89");
        s.put("Y", "80");
        s.put("Z", "22");
        //Maiuscula fim

        //Numeros
        s.put("0", "bj");
        s.put("1", "ws");
        s.put("2", "ur");
        s.put("3", "bd");
        s.put("4", "ms");
        s.put("5", "tu");
        s.put("6", "gr");
        s.put("7", "xz");
        s.put("8", "lt");
        s.put("9", "wd");
    }

    public static String codifica(String seed, String pass) {
        pass = seed + pass + seed;
        for (var key : s.keySet())
            pass = pass.replaceAll(key, s.get(key));

        return pass;
    }

}
