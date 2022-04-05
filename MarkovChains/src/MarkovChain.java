/**
 Dictionary
 Creates MarkovChain Class
 Author: Ishan Tandon
 Date: 4/4/22
 Collaborators: Trent Blair
 On My Honor, I confirm that I followed all collaboration policy guidelines, and that the work I am submitting is mine:IT
 **/

import java.io.File; //importing things I will need to use for code
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class MarkovChain { //makes markov chain class
    private Scanner scan;
    private Random ran;
    private Dictionary dict;
    private File file;
    private String fileName;
    private int wordsToGenerate;
    private String saveFile;
    private String words;
    String[] arr; //instance data for all variables

    public MarkovChain(){ //default constructor,
        scan = new Scanner(System.in); //asks for file with text in it
        System.out.println("File name:");
        fileName = scan.nextLine(); //getting user response
        file = new File(fileName);
        ran = new Random(); //random getting words from text file
        words = "";
        dict = new Dictionary();
        System.out.println("Enter file name to store results ");
        saveFile = scan.nextLine();
        System.out.println("How many words do you want? "); //generating words from stored file
        wordsToGenerate = scan.nextInt();
        Random r = new Random();
    }


    public boolean readTxt(){//read text method
        boolean work = true; //checking to see if the method is able to produce the correct result
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                words += read.nextLine();
                words += " ";
            }
        } catch (FileNotFoundException e) { //checks to see if they entered a wrong file, wont work
            System.out.println("error");
            e.printStackTrace();
            work = false;
        }
        return work;
    }

    public void addDict(){ //method
        arr = words.split(" ");
        for(int i = 0;i<arr.length;i++){ //used for adding words to Dictionary class
            ArrayList<String> values = new ArrayList<>();//values put in for array list
            if(i == arr.length-1){ //if else statements to run through values
                dict.put(arr[i], values);
            } else if(dict.contains(arr[i])){
                ((ArrayList)dict.get(arr[i])).add(arr[i+1]);
            } else {
                values.add(arr[i+1]);
                dict.put(arr[i], values);
            }
        }
    }

    public String create(){ //method to create words
        int words = ran.nextInt(arr.length);
        String start = arr[words];
        String s = start + " ";
        String word = start;
        for(int i = 0; i<wordsToGenerate-1; i++){ //for loop to assess how large the words are and how to add them
            //to the following words
            int val;
            int size = ((ArrayList)dict.get(word)).size();
            val = ran.nextInt(size);
            s += ((ArrayList)dict.get(word)).get(val);
            s += " ";
            word = (String)((ArrayList)dict.get(word)).get(val);
        }

        try {//checking to see if file is available
            FileWriter write = new FileWriter(saveFile);
            write.write(s);
            write.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return s;//return value
    }
}
