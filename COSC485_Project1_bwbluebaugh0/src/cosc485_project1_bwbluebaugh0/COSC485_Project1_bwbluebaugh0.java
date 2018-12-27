package cosc485_project1_bwbluebaugh0;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author bbluebaugh
 */
public class COSC485_Project1_bwbluebaugh0 {
    
    ArrayList<ListNode> nodeList = new ArrayList<ListNode>();
    ArrayList<String> possible = new ArrayList<String>();
    ArrayList<String> Alphabet = new ArrayList<String>();
    Scanner scan = new Scanner(System.in);
    String word = scan.nextLine();
    int sState;
    ArrayList<Integer> fState = new ArrayList<Integer>();
    boolean correctWord = false;    
    Scanner checkAutomata = new Scanner(System.in);
    //Scanner checkString = new Scanner(System.in);
    //String stringLocation = checkString.nextLine();
    String location = checkAutomata.nextLine();
    //Scanner userOutput = new Scanner(System.in);
    //String outputName = userOutput.nextLine();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        
        COSC485_Project1_bwbluebaugh0 thing = new COSC485_Project1_bwbluebaugh0();
        //Scanner scan = new Scanner(System.in);
        /*
        while(scan.hasNextLine()){
                String string = scan.nextLine();
                String subString[] = string.split("[\\r\\n]");
                String stringsToCheck = subString[subString.length];
                thing.word = stringsToCheck;
            }
        */
        //thing.word = stringsToCheck;
        thing.getInput();
        thing.checkWord(thing.nodeList.get(thing.sState), thing.word);
        System.out.println(thing.correctWord);

        //Scanner checkAutomata = new Scanner(System.in);
        //String location = checkAutomata.nextLine();
    }
    
    //testing the output of some of the variables to be sure it prints correctly
    public void testOutputs() {
        for(int i = 0; i < 3; i++) {
            System.out.println(nodeList.get(i).getStart());
        }
    }
    
    public void getInput() throws UnsupportedEncodingException {
        //get the file location from the user
        File userFile = new File(location);
        //File userFile = new File("NFA.txt");
        //File automata = new File(location);
        //File stringCheck = new File(stringLocation);
        //File userOuptutFile = new File(outputName);
        try{
            int y = 0;
            
            
            Scanner userInput = new Scanner(userFile);
            //Scanner userString = new Scanner(stringCheck);
            //Scanner userOutputFilef = new Scanner(userOuptutFile);
            while(userInput.hasNextLine()) {
                String line = userInput.nextLine();
                //use a char sequence to properly
                //split the file at the correct place
                //
                CharSequence s = "States =";
                CharSequence a = "Alphabet =";
                CharSequence ss = "Starting State =";
                CharSequence f = "Final States =";
                CharSequence t = "Transition Functions =";
                if(line.contains(a)) {
                    ArrayList<String> alphabet = new ArrayList<String>();
                    String pieces[] = line.split("[{}]");
                    String states = pieces[pieces.length-2];
                    states = states.replaceAll("\\s+","");
                    for(String str: states.split(",")) {
                        alphabet.add(str);
                    }
                    alphabet.add("e");
                    for(int i = 0; i < alphabet.size(); i++) {
                        nodeList.get(i).setAlphabet(alphabet);
                    }
                    Alphabet = alphabet;
                }
                else if(line.contains(ss)) {
                    String pieces[] = line.split("=");
                    String startingState = pieces[pieces.length-1];
                    startingState = startingState.replaceAll("\\s+","");
                    startingState = startingState.replaceAll(",","");
                    for(int i = 0; i < nodeList.size(); i++) {
                        if(nodeList.get(i).getState().equals(startingState)) {
                            boolean isTrue = true;
                            nodeList.get(i).setStartingState(isTrue);
                            sState = i;
                        }
                    }
                }
                else if(line.contains(f)) {
                    ArrayList<String> finalStates = new ArrayList<String>();
                    String pieces[] = line.split("[{}]");
                    String finalS = pieces[pieces.length-2];
                    finalS = finalS.replaceAll("\\s+","");
                    for(String str: finalS.split(",")) {
                        finalStates.add(str);
                    }
                    for(int i = 0; i < nodeList.size(); i++) {
                        for(int j = 0; j < finalStates.size(); j++) {
                            if(nodeList.get(i).getState().equals(finalStates.get(j))) {
                                boolean isTrue = true;
                                nodeList.get(i).setFinalState(isTrue);
                                fState.add(i);
                            }
                        }
                    }
                }
                else if(line.contains(s)) {
                    ArrayList<String> state = new ArrayList<String>();
                    String pieces[] = line.split("[{}]");
                    String states = pieces[pieces.length-2];
                    states = states.replaceAll("\\s+","");
                    for(String str: states.split(",")) {
                        state.add(str);
                    }
                    for(int i = 0; i < state.size(); i++) {
                        nodeList.add(new ListNode(state.get(i)));
                    }
                    y++;
                }
                else if(line.contains(t)) {
                    int n = 0;
                    while(userInput.hasNextLine()) {
                        String nLine = userInput.nextLine();
                        ArrayList<String> transitions = new ArrayList<String>();
                        CharSequence thing = "(";
                        if(nLine.contains(thing)) {
                            String pieces[] = nLine.split("[()]");
                            String edge = pieces[1];
                            edge = edge.replaceAll("\\s+","");
                            for(String str: edge.split(",")) {
                                transitions.add(str);
                            }
                            for(int i = 0; i < nodeList.size(); i++) {
                                if((nodeList.get(i).getState()).equals(transitions.get(0))) {
                                    //Loop through list of node objects till you find 0
                                    // call set edge (tranistions.get(2), tranistions.get(1))
                                    // create reference with (node, sigma)
                                    for (int j = 0; j < nodeList.size(); j++) {
                                        if((nodeList.get(j).state).equals(transitions.get(2))) {
                                            nodeList.get(i).setEdges(nodeList.get(j), transitions.get(1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            /**
            while(userString.hasNextLine()){
                String string = userString.nextLine();
                String subString[] = string.split("[\\r\\n]");
                String stringsToCheck = subString[subString.length];
                word = stringsToCheck;
            }
            while(userOutputFilef.hasNextLine()){
                PrintWriter writer = new PrintWriter(outputName, "UTF-8");
                writer.println(correctWord);
            }
            */
            userInput.close();
            //userString.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void checkWord(ListNode startingNode, String word) {
        if(word.length() == 0) {
            if(startingNode.getFinal() == true) {
                correctWord = true;
                return;
            }
        } else {
            for(int i = 0; i < startingNode.transitions.size(); i++) {
                //System.out.println("T: " + Alphabet.get(startingNode.transitions.indexOf(i)));
                if(Alphabet.get(i).equals(word.substring(0, 1))) {
                    for(int j = 0; j < startingNode.transitions.get(i).size(); j++) {
                        ListNode temp1 = startingNode.transitions.get(i).get(j);
                        String temp2;
                        if(word.length() == 1) {
                            temp2 = "";
                        } else {
                            temp2 = word.substring(1);
                        }
                        checkWord(temp1, temp2);
                    }
                }
            }
        }
    }
    
}
