package Esercizio_4_2;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class FacciataTempio extends Group {
    private float width;
    private float height;
    private float depth;
    private Base[] base;
    private TransformGroup[] TGBase;
    private Transform3D[] T3DBase;
    private TransformGroup[] TGColonne;
    private Transform3D[] T3DColonne;
    private Colonna[] colonna;
    private Abaco abaco;
    private TransformGroup TGAbaco;
    private Transform3D T3DAbaco;
    private Timpano timpano;
    private TransformGroup TGTimpano;
    private Transform3D T3DTimpano;

    public FacciataTempio() {
        width = 2.3f;
        height = 1.0f / 2.0f;
        depth = 0.5f;
        // Definizione dei TransformGroup e dei Transform3D della base
        base = new Base[3];
        TGBase = new TransformGroup[3];
        T3DBase = new Transform3D[3];
        // Definizione dei TransformGroup e dei Transform3D delle colonne
        colonna = new Colonna[6];
        TGColonne = new TransformGroup[6];
        T3DColonne = new Transform3D[6];

        float sizeX = 1.2f;
        float sizeZ = 0.3f;
        float scala = 0.05f;
        for (int i = 0; i < base.length; i++) {
            base[i] = new Base(sizeX, 0.05f, sizeZ);
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

        float radius = 0.6f;
        for (int i = 0; i < colonna.length; i++) {
            colonna[i] = new Colonna(radius, 1.0f);
            // Definizione dei TransformGroup e dei Transform3D delle colonne
            TGColonne[i] = new TransformGroup();
            T3DColonne[i] = new Transform3D();
            T3DColonne[i].setTranslation(new Vector3f(i * 0.4f, 0.75f, 0.1f));
            TGColonne[i].setTransform(T3DColonne[i]);
            TGColonne[i].addChild(colonna[i]);
        }

        // Aggiungo alla scena le colonne
        for (int j = 0; j < colonna.length; j++) {
            addChild(TGColonne[j]);
        }

        // Definizione dei TransformGroup e dei Transform3D dell'abaco
        float xdim = 1.09f;
        float ydim = 1.0f / 6.0f;
        float zdim = 1.0f / 8.0f;
        abaco = new Abaco(xdim, ydim, zdim);
        TGAbaco = new TransformGroup();
        T3DAbaco = new Transform3D();
        T3DAbaco.setTranslation(new Vector3d(0.0f, 1.51f, 0.1f));
        TGAbaco.setTransform(T3DAbaco);
        TGAbaco.addChild(abaco);
        addChild(TGAbaco); // Aggiungo alla scena l'abaco superiore

        // Definizione dei TransformGroup e dei Transform3D del timpano
        timpano = new Timpano(width, height, depth);
        TGTimpano = new TransformGroup();
        T3DTimpano = new Transform3D();
        T3DTimpano.setTranslation(new Vector3d(0.0f, 1.93f, 0.1f));
        TGTimpano.setTransform(T3DTimpano);
        TGTimpano.addChild(timpano);
        addChild(TGTimpano); // Aggiungo alla scena il timpano
    }
}
