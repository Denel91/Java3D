package Esercizio_4_4;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Scalinata extends Group {
    private float sizeX;
    private float sizeY;
    private float sizeZ;
    private float scala;
    private Base[] base;
    private TransformGroup[] TGBase;
    private Transform3D[] T3DBase;

    public Scalinata() {
        // Definizione dei TransformGroup e dei Transform3D della base
        base = new Base[3];
        TGBase = new TransformGroup[3];
        T3DBase = new Transform3D[3];

        sizeX = 1.2f;
        sizeY = 0.04f;
        sizeZ = 3.0f;
        scala = 0.05f;
        for (int i = 0; i < base.length; i++) {
            base[i] = new Base(sizeX, sizeY, sizeZ);
            sizeX = sizeX - scala;
            sizeZ = sizeZ - scala;
            TGBase[i] = new TransformGroup();
            T3DBase[i] = new Transform3D();
            T3DBase[i].setTranslation(new Vector3f(0.0f, i * 0.10f, 0.0f));
            TGBase[i].setTransform(T3DBase[i]);
            TGBase[i].addChild(base[i]);
        }

        // Aggiungo alla scena la base
        for (int i = 0; i < base.length; i++) {
            addChild(TGBase[i]);
        }
    }
}
