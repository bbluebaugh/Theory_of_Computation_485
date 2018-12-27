package cosc485_project1_bwbluebaugh0;
import java.util.*;//import arraylist to store things
//import a linked list to store nodes for states of DFA

/**
 *
 * @author bbluebaugh
 */
public class ListNode {
  
    public ListNode next;
    public Object data;
    String state = "";
    boolean isStartingState = false;//start the state at false to check later if it is true
    boolean isFinalState = false;//start the state at false to check later if it is true    
    ArrayList<String> alphabet = new ArrayList<String>();
    int edgeNum = 0;
    LinkedList<LinkedList<ListNode>> transitions;
    
    public ListNode(String thisState){
        state = thisState;
        transitions = new LinkedList<LinkedList<ListNode>>();
    }
    
    //sets the Alphabet of the DFA
    public void setAlphabet(ArrayList<String> a) {
        alphabet = a;
        makeList();
    }
    
    //Sets the starting state of the DFA
    public void setStartingState(boolean thisStartingState) {
        isStartingState = thisStartingState;
    }
    
    //gets the start of the DFA
    public boolean getStart() {
        return isStartingState;
    }
    
    //gets the current state of the DFA
    public String getState() {
        return state;
    }
    
    //sets the final state of the DFA based on last state
    public void setFinalState(boolean thisFinalState) {
        isFinalState = thisFinalState;
    }
    
    //returns the final state of the current DFA
    public boolean getFinal() {
        return isFinalState;
    }
    
    //sets the edges of the DFA
    public void setEdges(ListNode e, String sigma) {
        for(int i = 0; i < alphabet.size(); i++) {
            if((alphabet.get(i)).equals(sigma)) {
                transitions.get(i).add(e);
            }
        }    
    }
    
    //make a linked list of items for the DFA
    //based on the aplphabet
    public void makeList() {
        for(int i = 0; i < alphabet.size(); i++){
            transitions.add(new LinkedList<ListNode>());
        }
    }
    
    //gets the alphabet for the DFA
    public String getAlphabet(int index) {
        return alphabet.get(index);
    }
    
}
