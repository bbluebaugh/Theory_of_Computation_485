
package cosc485_project1_bwbluebaugh0;

/**
 *
 * @author bbluebaugh
 */
public class StateEdge {
    String startingEdge = "";
    String endingEdge = "";
    String valueOfEdge = "";
    
    //initialize all of the variables
    public StateEdge(String start, String end, String value) {
        startingEdge = start;
        endingEdge = end;
        valueOfEdge = value;
    }
    
    //gets the value in the edge
    public String getValue() {
        return valueOfEdge;
    }
    
    //gets the start of the edges
    public String getStart() {
        return startingEdge;
    }
    
    //gets the end of the edges
    public String getEnd() {
        return endingEdge;
    }
}
