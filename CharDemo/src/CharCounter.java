
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.IntStream;

public class CharCounter {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL("http://numbersapi.com/random/trivia");
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String string = sb.toString();

        char[] chars = string.toCharArray();
        char [] copy = new char[chars.length];
        int counter = 0;
        for (char c : chars)
        {
            if(c != ' ')
            {
                copy[counter] = c;
                counter++;
            }
        }
        char[] result = Arrays.copyOf(copy, counter);
        Character[] charsArray = IntStream.range(0, result.length)
                .mapToObj(i -> result[i]).toArray(Character[]::new);
        Map<Character,Integer> map = new HashMap<>();
        List<Character> characterList = new ArrayList<>();
        characterList.addAll(Arrays.asList(charsArray));

        for(int i = 0; i < characterList.size(); i++) {
            Character tempChar = characterList.get(i);
            if(!map.containsKey(tempChar)) {
                map.put(tempChar, 1);
            } else {
                map.put(tempChar,map.get(tempChar) + 1);
            }
        }
        System.out.println("Частоты:");
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("Среднее значение частоты: " + characterList.size() + "/" + map.size() +
                " = " + ((double) characterList.size() / map.size()));
        double freq = (double) characterList.size() / map.size();
        freq = Math.round(freq);
        System.out.println("Символы, которые соответствуют условию наиболее близкого значения частоты к среднему значению:");
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            if(entry.getValue() == freq) {
                System.out.print(entry.getKey() + "(" + ((int) entry.getKey())  + ") ");
            }
        }





    }
}
