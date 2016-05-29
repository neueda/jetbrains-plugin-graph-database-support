package lv.neueda.jetbrains.plugin.graphdb.visualization.events;

import prefuse.controls.Control;
import prefuse.controls.ControlAdapter;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class NodePopup extends ControlAdapter implements Control {

    public static final int PAN_X = 260;
    public static final int PAN_Y = 260;

    public void itemClicked(VisualItem item, MouseEvent e) {
        if (item instanceof NodeItem) {
            String id = (String) item.get("id");

            JPopupMenu jpub = new JPopupMenu();
            jpub.add("Id: " + id);
            jpub.show(e.getComponent(), (int) item.getX() + PAN_X, (int) item.getY() + PAN_Y);
        }
    }

}
