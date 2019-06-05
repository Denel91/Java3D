package Esercizio_4_4;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Colonna extends Group {
    private TransformGroup TGAbaco;
    private Transform3D T3DAbaco;
    private TransformGroup TGFusto;
    private Transform3D T3DFusto;
    private TransformGroup TGEchino;
    private Transform3D T3DEchino;
    private Transform3D T3DEchinoRotation;

    public Colonna(float radius, float height) {
        Fusto fusto = new Fusto(radius, height);
        Abaco abaco = new Abaco(height / 6, height / 30, height / 6);
        Echinus echino = new Echinus(radius / 5, height / 6);

        TGAbaco = new TransformGroup();
        TGFusto = new TransformGroup();
        TGEchino = new TransformGroup();
        TGAbaco.addChild(abaco);
        TGFusto.addChild(fusto);
        TGEchino.addChild(echino);

        T3DAbaco = new Transform3D();
        T3DAbaco.setTranslation(new Vector3d(-1.0f, height / 2 + height / 15, 0.0f));
        TGAbaco.setTransform(T3DAbaco);

        T3DEchino = new Transform3D();
        T3DEchinoRotation = new Transform3D();
        T3DEchino.setTranslation(new Vector3d(-1.0f, height / 2 - 0.05f, 0.0f));
        T3DEchinoRotation.rotX(-Math.PI);
        T3DEchino.mul(T3DEchinoRotation);
        TGEchino.setTransform(T3DEchino);

        T3DFusto = new Transform3D();
        T3DFusto.setTranslation(new Vector3d(-1.0f, 0.0f, 0.0f));
        TGFusto.setTransform(T3DFusto);

        addChild(TGAbaco);
        addChild(TGFusto);
        addChild(TGEchino);
    }
}
