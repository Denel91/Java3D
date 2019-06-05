package Esercizio_4_4;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Colonnade extends Group {
    private TransformGroup[] TGColonne;
    private Transform3D[] T3DColonne;
    private Colonna[] colonna;
    private Abaco abaco;
    private TransformGroup TGAbaco;
    private Transform3D T3DAbaco;

    public Colonnade() {
        // Definizione dei TransformGroup e dei Transform3D delle colonne
        colonna = new Colonna[13];
        TGColonne = new TransformGroup[13];
        T3DColonne = new Transform3D[13];

        float radius = 0.6f;
        for (int i = 0; i < colonna.length; i++) {
            colonna[i] = new Colonna(radius, 1.0f);
            // Definizione dei TransformGroup e dei Transform3D delle colonne
            TGColonne[i] = new TransformGroup();
            T3DColonne[i] = new Transform3D();
            T3DColonne[i].setTranslation(new Vector3f(i * 0.4f, 0.75f, 2.8f));
            TGColonne[i].setTransform(T3DColonne[i]);
            TGColonne[i].addChild(colonna[i]);
        }

        // Aggiungo alla scena le colonne
        for (int j = 0; j < colonna.length; j++) {
            addChild(TGColonne[j]);
        }


        // Definizione dei TransformGroup e dei Transform3D dell'abaco
        float xdim = 2.80f;
        float ydim = 1.0f / 6.0f;
        float zdim = 1.0f / 14.2f;
        abaco = new Abaco(xdim, ydim, zdim);
        TGAbaco = new TransformGroup();
        T3DAbaco = new Transform3D();
        T3DAbaco.setTranslation(new Vector3d(1.5f, 1.51f, 2.8f));
        TGAbaco.setTransform(T3DAbaco);
        TGAbaco.addChild(abaco);
        addChild(TGAbaco); // Aggiungo alla scena l'abaco superiore
    }
}
