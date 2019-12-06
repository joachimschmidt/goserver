package JADevelopmentTeam.server;

import JADevelopmentTeam.common.Intersection;

import java.util.ArrayList;

public abstract class GameLogicCalculator {
    public static int calculateLiberties(Stone stone, Board board) {
        int liberties = 0;
        ArrayList<Intersection> possibleLiberties = getNeighborIntersections(stone, board);
        for (Intersection intersection : possibleLiberties) {
            if (!intersection.isHasStone()) liberties++;
        }
        return liberties;
    }

    public static int calculateLiberties(Intersection intersection, Board board) {
        int liberties = 0;
        ArrayList<Intersection> possibleLiberties = getNeighborIntersections(intersection, board);
        for (Intersection temp : possibleLiberties) {
            if (!temp.isHasStone()) liberties++;
        }
        return liberties;
    }

    public static int calculateLiberties(StoneChain stoneChain, Board board) {
        int liberties = 0;
        for (Stone stone : stoneChain.getStones()) {
            liberties += calculateLiberties(stone, board);
        }
        return liberties;
    }

    public static ArrayList<StoneChain> getNeighborChains(Stone stone, Board board, ArrayList<StoneChain> stoneChains, ArrayList<Stone> stones) {
        ArrayList<Intersection> neighbors = getNeighborIntersections(stone, board);
        ArrayList<Stone> neighborStones = new ArrayList<>();
        ArrayList<StoneChain> neighborStoneChains = new ArrayList<>();
        for (Intersection intersection : neighbors) {
            Stone stoneOnIntersection = getStoneForIntersection(intersection, stones);
            if (stoneOnIntersection != null) neighborStones.add(stoneOnIntersection);
        }

        for (Stone neighborStone : neighborStones) {
            if (getStoneChainIDForStone(neighborStone, stoneChains) != -1) {
                StoneChain neighborStoneChain = stoneChains.get(getStoneChainIDForStone(neighborStone, stoneChains));
                if (!neighborStoneChains.contains(neighborStoneChain)) {
                    neighborStoneChains.add(neighborStoneChain);
                }
            }
        }
        return neighborStoneChains;
    }

    private static Stone getStoneForIntersection(Intersection intersection, ArrayList<Stone> stones) {
        for (Stone stone : stones) {
            if (stone.getXCoordinate() == intersection.getXCoordinate()
                    && stone.getYCoordinate() == intersection.getYCoordinate()) {
                return stone;
            }
        }
        return null;
    }

    private static int getStoneChainIDForStone(Stone stone, ArrayList<StoneChain> stoneChains) {
        for (StoneChain stoneChain : stoneChains) {
            if (stoneChain.getStones().indexOf(stone) != -1) {
                return stoneChains.indexOf(stoneChain);
            }
        }
        return -1;
    }


    private static ArrayList<Intersection> getNeighborIntersections(Stone stone, Board board) {
        int x = stone.getXCoordinate();
        int y = stone.getYCoordinate();
        ArrayList<Intersection> neighbors = new ArrayList<>();
        Intersection[][] intersections = board.getIntersections();
        int size = intersections.length;
        if (x < size - 1) neighbors.add(intersections[x + 1][y]);
        if (y < size - 1) neighbors.add(intersections[x][y + 1]);
        if (x > 0) neighbors.add(intersections[x - 1][y]);
        if (y > 0) neighbors.add(intersections[x][y - 1]);
        return neighbors;
    }

    private static ArrayList<Intersection> getNeighborIntersections(Intersection intersection, Board board) {
        int x = intersection.getXCoordinate();
        int y = intersection.getYCoordinate();
        Stone stone = new Stone(x, y);
        return getNeighborIntersections(stone, board);

    }

//    private static ArrayList<Stone> getNeighborStones(Stone stone, Board board){
//        ArrayList<Intersection> intersections = getNeighborIntersections(stone,board);
//        ArrayList<Stone> neighborStones = new ArrayList<>();
//        for (Intersection intersection : intersections) {
//            Stone temp = getStoneForIntersection(intersection,)
//
//        }
//    }

    public static StoneChain generateSuperStoneChain(ArrayList<StoneChain> stoneChains, Stone connector) {
        StoneChain superStoneChain = new StoneChain(connector);
        for (StoneChain stoneChain : stoneChains) {
            superStoneChain.addStones(stoneChain.getStones());
        }
        return superStoneChain;
    }

    public static int checkIfMoveIsValid(Intersection chosenIntersection, GameManager gameManager, int turn) {
        Stone testStone = new Stone(chosenIntersection.getXCoordinate(), chosenIntersection.getYCoordinate());
        //TODO implement some real validation
        if (gameManager.getBoardAsIntersections()[chosenIntersection.getXCoordinate()][chosenIntersection.getYCoordinate()].isHasStone()) {
            return 1;
        }
        if (calculateLiberties(chosenIntersection, gameManager.getBoard()) > 0) {
            return 0;
        } else {
            System.out.println("Myślę");
//            gameManager.processMove(chosenIntersection, turn);
//            gameManager.processStonesLiberties();
//            if (turn == 0) {
//                for (Stone stone : gameManager.playerOneStones) {
//                    if (stone.getLiberties() == 0) {
//                        gameManager.removeStone(getStoneForIntersection(chosenIntersection, gameManager.playerOneStones));
//                        return 2;
//                    }
//                    gameManager.removeStone(getStoneForIntersection(chosenIntersection, gameManager.playerOneStones));
//                }
//            } else {
//                for (Stone stone : gameManager.playerTwoStones) {
//                    if (stone.getLiberties() == 0) {
//                        gameManager.removeStone(getStoneForIntersection(chosenIntersection, gameManager.playerTwoStones));
//                        return 2;
//                    }
//                    gameManager.removeStone(getStoneForIntersection(chosenIntersection, gameManager.playerTwoStones));
//                }
//            }
//            System.out.println("Wymyśliłem");
            return 0;
        }
    }
}