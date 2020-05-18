package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {



    protected CellType[][] labyrinth;
    protected int width ;
    protected int height;
    protected Coordinate playerStartPosition;

    public LabyrinthImpl() {

    }

    @Override
    public int getWidth() {
       if (width <= 0){
           return -1;
       }
        return width;
    }

    @Override
    public int getHeight() {
       if (height <= 0){
           return -1;
       }
        return height;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());
            this.width = width;
            this.height = height;
            labyrinth = new CellType[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    labyrinth[i][j] = CellType.EMPTY;
                }
            }
            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            labyrinth[hh][ww] = CellType.WALL;
                            break;
                        case 'E':
                            labyrinth[hh][ww] = CellType.END;
                            break;
                        case 'S':
                            labyrinth[hh][ww] = CellType.START;
                            playerStartPosition = new Coordinate(hh, ww);
                            break;

                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getRow() >= height || c.getCol() >= width || c.getCol() < 0 || c.getRow() < 0){
            throw new CellException(c.getRow(), c.getCol(), "hibas bemeneti parameter");
        }
        return labyrinth[c.getRow()][c.getCol()];
    }

    @Override
    public void setSize(int width, int height) {
       this.width = width;
       this.height = height;
       labyrinth = new CellType[this.height][this.width];
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
       if (c.getRow() >= height || c.getCol() >= width || c.getCol() < 0 || c.getRow() < 0){
           throw new CellException(c.getRow(), c.getCol(), "hibas bemeneti parameter");
       }

        labyrinth[c.getRow()][c.getCol()] = type;

    }

    @Override
    public Coordinate getPlayerPosition() {
        return null;
    }

    @Override
    public boolean hasPlayerFinished() {
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        ArrayList<Direction> directionList = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(labyrinth[i + 1].equals(CellType.EMPTY) || labyrinth[j + 1].equals(CellType.EMPTY)){
                 //   directionList.add(new Direction())
                }
            }
        }
        return null;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {

    }


}
