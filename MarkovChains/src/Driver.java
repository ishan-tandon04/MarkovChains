/**
 Drive Class for MarkovChain
 Author: Ishan Tandon
 Date: 4/4/22
 Collaborators: Trent Blair
 On My Honor, I confirm that I followed all collaboration policy guidelines, and that the work I am submitting is mine:IT
 **/
public class Driver {
        public static void main(String[] args) {
            MarkovChain markovChain = new MarkovChain(); //create a test of markovChain
            markovChain.readTxt();//reads file
            markovChain.addDict();//looks at dictionary class
            System.out.println(markovChain.create()); //prints out generated words
        }
}