package com.company;
import java.util.Scanner;

/**
 * Mancala
 */
public class Mancala {

    Board board;

    public Mancala() {
        int[] boardArray = new int[14];
        for(int i=0; i<boardArray.length; i++) {
            boardArray[i] = 4;
        }
        boardArray[6] = 0;
        boardArray[13] = 0;

        board = new Board(boardArray, true);
    }

    public Board computerMove() {
        board = board.move(12);
        return board;
    }

    public Board userMove(Scanner scanner) {
        System.out.print("Make a move: ");
        int move = scanner.nextInt();
        if(move == 0) {
            board = board.move(0);
        } else if(move == 1) {
            board = board.move(1);
        } else if(move == 2) {
            board = board.move(2);
        } else if(move == 3) {
            board = board.move(3);
        } else if(move == 4) {
            board = board.move(4);
        } else {
            board = board.move(5);
        }
        return board;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while(!board.isDone()) {
            // See board
            System.out.println(board.toString());
            // Make a move
            int[] move;
            if(!board.xTurn) {
                board = userMove(scanner);
            } else {
                board = computerMove();
            }
            System.out.println();
        }
        System.out.println(board.toString());
        if (board.humanWins()){
            System.out.println("Congratulations, you won!!!");
        } else {
            System.out.println("You lost, Good Game");
        }
        scanner.close();
    }


    private class Board {

        int[] board;
        boolean xTurn;

        public Board(int[] board, boolean xTurn) {
            this.board = board;
            this.xTurn = xTurn;
        }

        public String toString() {
            String rv = "";
            rv += "-----------------\n";
            rv += "| |" + board[12] + "|" + board[11] + "|" + board[10] + "|" + board[9] + "|" + board[8] + "|" + board[7] + "| |\n";
            rv += "|" + board[13] + "-------------" + board[6] + "|\n";
            rv += "| |" + board[0] + "|" + board[1] + "|" + board[2] + "|" + board[3] + "|" + board[4] + "|" + board[5] + "| |\n";
            rv += "-----------------\n";
            rv += "   " + 0 + " " + 1 + " " + 2 + " " + 3 + " " + 4 + " " + 5 + " " + "    ";
            return rv;
        }

        public Board move(int a) {

            if (a > 6) {
                for(int i=7; i!=13; i++){
                    if (i + board[i] == 13) {
                        a = i;
                        break;
                    }
                }
                for(int i=7; i!=13; i++){
                    if ((i + board[i]) < 13 && board[i + board[i]] == 0) {
                        a = i;
                        break;
                    }
                }
            }


            if(a > 6 && board[a] == 0) {
                for(int i=12; i!=6; i--){
                    if(board[i] != 0){
                        a = i;
                        break;
                    } else {
                        a -= 1;
                    }
                }

            }

            int stones = board[a];

            if(stones == 0){
                System.out.println("Invalid move, try again");
                Board rv = new Board(board, xTurn);
                return rv;
            } else if(a < 6 && a + stones < 6 && board[a + stones] == 0 && board[12 - (stones + a)] != 0) {
                 board[6] += board[12 - (a + stones)] + 1;
                 board[12 - (a + stones)] = 0;
                 board[a + stones] -= 1;
                for (int i=board[a]; i!=0; i--){
                    if (a < 6) {
                        if(a + i > 13) {
                            board[a + i - 14] += 1;
                        } else {
                            if (a + i != 13) {
                                board[a + i] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        }
                    } else {
                        if(a + i > 13) {
                            if (a + i - 14 != 6) {
                                board[a + i - 14] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        } else {
                            board[a + i] += 1;
                        }
                    }
                    board[a] -= 1;
                }
                 Board rv = new Board(board, !xTurn);
                 return rv;
             } else if(a > 6 && a < 13 && a + stones > 6 && a + stones < 13 && board[a + stones] == 0 && board[12 - (stones + a)] != 0) {
                board[13] += board[12 - (a + stones)] + 1;
                board[12 - (a + stones)] = 0;
                board[a + stones] -= 1;
                for (int i=board[a]; i!=0; i--){
                    if (a < 6) {
                        if(a + i > 13) {
                            board[a + i - 14] += 1;
                        } else {
                            if (a + i != 13) {
                                board[a + i] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        }
                    } else {
                        if(a + i > 13) {
                            if (a + i - 14 != 6) {
                                board[a + i - 14] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        } else {
                            board[a + i] += 1;
                        }
                    }
                    board[a] -= 1;
                }
                Board rv = new Board(board, !xTurn);
                return rv;
            } else if((a + stones == 13 && a > 6) || (a + stones == 6 && a < 6)) {
                for (int i=board[a]; i!=0; i--){
                    if (a < 6) {
                        if(a + i > 13) {
                            board[a + i - 14] += 1;
                        } else {
                            if (a + i != 13) {
                                board[a + i] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        }
                    } else {
                        if(a + i > 13) {
                            if (a + i - 14 != 6) {
                                board[a + i - 14] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        } else {
                            board[a + i] += 1;
                        }
                    }
                    board[a] -= 1;
                }
                Board rv = new Board(board, xTurn);
                return rv;
            } else {
                for (int i=board[a]; i!=0; i--){
                    if (a < 6) {
                        if(a + i > 13) {
                            board[a + i - 14] += 1;
                        } else {
                            if (a + i != 13) {
                                board[a + i] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        }
                    } else {
                        if(a + i > 13) {
                            if (a + i - 14 != 6) {
                                board[a + i - 14] += 1;
                            } else {
                                board[a + stones + 1 - 14] += 1;
                            }
                        } else {
                            board[a + i] += 1;
                        }
                    }
                    board[a] -= 1;
                }
                Board rv = new Board(board, !xTurn);
                return rv;
            }
        }

        boolean isDone() {
            if(board[0] + board[1] + board[2] + board[3] + board[4] + board[5] == 0 || board[7] + board[8] + board[9] + board[10] + board[11] + board[12] == 0) {
                return true;
            } else {
                return false;
            }
        }

        boolean humanWins() {
            if (board[6] > board[13]) {
                return true;
            } else {
                return false;
            }
        }

    }

}