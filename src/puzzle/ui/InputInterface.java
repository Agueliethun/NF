package puzzle.ui;

import puzzle.model.Puzzle;
import puzzle.util.ObjectUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class InputInterface implements KeyListener {
    public static final int WIDTH = 640, HEIGHT = 480;

    private final Puzzle puzzle;

    private JFrame frame;
    private LetterCanvas displayArea;

    public InputInterface(Puzzle puzzle) {
        this.puzzle = puzzle;

        displayArea = new LetterCanvas();
        displayArea.addKeyListener(this);
        displayArea.update(puzzle.getState().getAllObjects());

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(displayArea);

        frame.setBounds((screenDimension.width - WIDTH) / 2, (screenDimension.height - HEIGHT) / 2, WIDTH, HEIGHT);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'j':
                puzzle.input(Puzzle.Input.LEFT);
                break;
            case 'i':
                puzzle.input(Puzzle.Input.UP);
                break;
            case 'k':
                puzzle.input(Puzzle.Input.DOWN);
                break;
            case 'l':
                puzzle.input(Puzzle.Input.RIGHT);
                break;
            case 'v':
                puzzle.input(Puzzle.Input.ONE);
                break;
            case 'c':
                puzzle.input(Puzzle.Input.TWO);
                break;
            case 'q':
                frame.setVisible(false);
                System.exit(0);
                break;
            default:
        }

        displayArea.update(ObjectUtil.copyList(puzzle.getState().getAllObjects()));
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
