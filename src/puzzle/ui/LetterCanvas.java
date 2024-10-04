package puzzle.ui;

import puzzle.model.Position;
import puzzle.model.Puzzle;
import puzzle.model.StatefulObject;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.List;

public class LetterCanvas extends Canvas {
    private List<StatefulObject> objects;

    public LetterCanvas() {
        setBounds(new Rectangle(InputInterface.WIDTH, InputInterface.HEIGHT));
    }

    public void update(List<StatefulObject> objects) {
        this.objects = objects;

        this.repaint(1000);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(.2f, .2f, .2f, 1f));
        g.fillRect(0, 0, InputInterface.WIDTH, InputInterface.HEIGHT);
        g.setFont(new JLabel().getFont().deriveFont(Font.PLAIN, 25));

        g.setColor(new Color(0f, 0f, 0f, 1f));

        for (StatefulObject obj : objects) {
            Position pos = obj.getPosition();
            String display = obj.getState().get("display");
            g.drawString(display, pos.x * 35, 25 + pos.y * 35);
        }
    }
}
