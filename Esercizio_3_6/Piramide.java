package Esercizio_3_6;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Piramide extends Group {
    protected static final int num_tronchi = 9; //n° tronchi
    protected Tronco[] pir = new Tronco[num_tronchi + 1];
    protected TransformGroup[] move = new TransformGroup[num_tronchi + 1];

    public Piramide() {
        TransformGroup root = new TransformGroup();
        double size = 2.5;
        for (int i = 0; i < pir.length - 1; i++) {
            pir[i] = new Tronco(size, 0.2, 0.1);
            size = size - 0.2;
            move[i] = new TransformGroup();
            Transform3D moveUp = new Transform3D();
            moveUp.setTranslation(new Vector3d(0, i * 0.4, 0));
            move[i].setTransform(moveUp);
            move[i].addChild(pir[i]);
            root.addChild(move[i]);
        }

        addChild(root);
    }
}
