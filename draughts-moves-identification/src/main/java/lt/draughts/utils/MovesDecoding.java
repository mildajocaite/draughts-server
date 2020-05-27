package lt.draughts.utils;

import org.springframework.stereotype.Component;

@Component
public class MovesDecoding {

    public String getLetterByNumber(int number){
        switch (number) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            default:
                return "h";
        }
    }

    public String getSimpleMove (int previousRow, int previousColumn, int currentRow, int currentColumn){
        String previousLetter = getLetterByNumber(previousColumn);
        String currentLetter = getLetterByNumber(currentColumn);
        int numberPrevious =  8 - previousRow;
        int numberAdded =  8 - currentRow;
        return previousLetter+numberPrevious+"-"+currentLetter+numberAdded;
    }

    public String getJumps (String previousMove, int previousRow, int previousColumn, int currentRow, int currentColumn){
        String previousLetter = getLetterByNumber(previousColumn);
        String currentLetter = getLetterByNumber(currentColumn);
        int numberPrevious =  8 - previousRow;
        int numberAdded =  8 - currentRow;
        if(previousMove == null){
            return previousLetter+numberPrevious+":"+currentLetter+numberAdded;
        }
        else {
            return previousMove+":"+currentLetter+numberAdded;
        }
    }
}
