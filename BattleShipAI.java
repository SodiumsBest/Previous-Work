//Need to get last guess direction working
public class BattleShipAI{
    public static String makeGuess(char[][] guesses)
    {

        boolean hunt = false;
        String target;
        int rowx = 0;
        int colx = 0;
        int i = 0;
        int j = 0;
        boolean track = false;

        int dir = (int)(Math.random() * 2) + 1;
        //if( dir == 1) {
        for (i = 0; i <guesses.length; i++){
            for (j = 0; j <guesses[i].length; j++){

                if (guesses[i][j] == 'X') {

                    rowx = i;
                    colx = j;
                    if (j<9){
                        if (guesses[i][j+1] =='.'){
                            hunt = true;

                        }
                    }
                    if (i<9) {
                        if (guesses[i+1][j] =='.'){
                            hunt = true;

                        }
                    }
                    if (i>0) {
                        if (guesses[i-1][j] =='.'){
                            hunt = true;

                        }
                    }
                    if (j>0) {
                        if (guesses[i][j-1] =='.'){
                            hunt = true;

                        }
                    }

                }
                if (hunt == true){
                    break;
                }
            }
            if (hunt == true){
                break;
            }
            //}
        }
        /*} else {
        for (i = 9; i >= 0; i--){
        for (j = 9; j >= 0; j--){

        if (guesses[i][j] == 'X') {

        rowx = i;
        colx = j;
        if (j<9){
        if (guesses[i][j+1] =='.'){
        hunt = true;

        }
        }
        if (i<9) {
        if (guesses[i+1][j] =='.'){
        hunt = true;

        }
        }
        if (i>0) {
        if (guesses[i-1][j] =='.'){
        hunt = true;

        }
        }
        if (j>0) {
        if (guesses[i][j-1] =='.'){
        hunt = true;

        }
        }

        }
        if (hunt == true){
        break;
        }
        }
        if (hunt == true){
        break;
        }
        //}*/
        // }
        //}
        if (hunt == true) {
            target = Hunt(guesses, rowx, colx, track);
        } else {
            target = Search(guesses);
        }
        track = false;
        return target;
    }

    public static String Search (char [][] guesses) {
        int row, col;
        row = 0;
        col = 0;
        int time = 1;
        while(guesses[row][col] != '.'){
            //if (time > 3) {
            row = (int)(Math.random() * 10);
            col = (int)(Math.random() * 10);
            if((col % 2 == 0 && row % 2 == 1) || (col % 2 == 1 && row % 2 == 0)){

            } else {
                col = 0;
                row = 0;
            }

            continue;
            //}
            /*if (guesses[row][col] == '.'){
            break;
            } else if (time == 1) {
            col ++;
            row ++;
            col ++;
            row ++;
            col ++;
            row ++;
            } else if (time == 2) {
            row --;
            col++;
            row --;
            col++;
            row --;
            col++;
            } else if (time == 3) {
            col = 4;
            row ++;
            row ++;
            row ++;
            }
            if (col > 9 && row > 9 && time == 1){
            row = 9;
            col = 0;
            time ++;
            }
            if (col > 9 && row < 0 && time == 2){
            row = 0;
            col = 0;
            time ++;
            }
            if(time == 3 && row >9) {
            row = 0;
            col = 0;
            time ++;
            }*/
        }
        char a = (char)((int)'A' + row);

        String guess = a + Integer.toString(col+1);
        return guess;

    }

    public static String Hunt (char[][] guesses,int rowx,int colx,boolean track) {
        int row, col;
        int way = 0;
        row = rowx;
        col = colx;
        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;
        int times = 0;
        while (guesses[row][col] != '.' ) {
            row = rowx;
            col = colx;
            way = 0;
            if (row > 0 && row < 9){

                if(guesses[row - 1][col] == 'X'  && guesses[row + 1][col] == '.' && down == true) {
                    way = 3;
                }
            }
            if (col > 0 && col < 9) {

                if (guesses[row][col - 1] == 'X' && guesses[row][col + 1] == '.' && right == true) {
                    way = 2;
                } 
            }
            if (col > 0 && col < 9) {
                if (guesses[row][col + 1] == 'X' && guesses[row][col - 1] == '.' && left == true) {
                    way = 4;
                }
            }
            if (row > 0 && row < 9){
                if (guesses[row + 1][col] == 'X' && guesses[row - 1][col] == '.' && up == true) {
                    way = 1;
                }
            }
            if (way == 0) {
                way = Dir(guesses, row, col); 
            }

            if (way == 1 ) {
                if ( row > 0) {
                    row --;

                } else{
                    up = false;
                    //continue;
                }
            }
            if (way == 2) {
                if ( col < 9) {
                    col ++;

                }else{
                    right = false;
                    //continue;
                }
            }
            if (way == 3) {
                if(row < 9) {
                    row ++;

                } else{
                    down = false;
                    //continue;
                }
            }
            if (way == 4 ) {
                if (col > 0) {
                    col --;

                }else{
                    left = false;
                    //continue;
                }
            }
            if (times > 100) {
                row = (int)(Math.random() * 10);
                col = (int)(Math.random() * 10);
            }
            times ++;
        } 

        char a = (char)((int)'A' + row);
        String guess = a + Integer.toString(col+1);
        return guess;
    }

    public static int Dir (char[][] guesses, int row, int col){
        int way;
        way = (int) (Math.random() * 4) + 1;

        return way;
    }

}