package Esercizio_3_8;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

public class Colonna extends Group {
    private TransformGroup TGAbaco;
    private TransformGroup TGFusto;
    private TransformGroup TGEchino;
    private Transform3D T3DEchino;
    private Transform3D T3DEchinoRotation;
    private Transform3D T3DAbaco;

    public Colonna(float radius, float height) {
        Fusto fusto = new Fusto(radius, height);
        Abaco abaco = new Abaco(height / 5, height / 30, height / 5);
        Echinus echino = new Echinus(radius / 5, height / 6);

        TGAbaco = new TransformGroup();
        TGFusto = new TransformGroup();
        TGEchino = new TransformGroup();
        TGAbaco.addChild(abaco);
        TGFusto.addChild(fusto);
        TGEchino.addChild(echino);

        T3DEchino = new Transform3D();
        T3DEchinoRotation = new Transform3D();
        T3DAbaco = new Transform3D();
        T3DAbaco.setTranslation(new Vector3d(0f, height / 2 + height / 15, 0f));
        TGAbaco.setTransform(T3DAbaco);

        T3DEchino.setTranslation(new Vector3d(0f, height / 3 + 0.1, 0f));
        T3DEchinoRotation.rotX(-Math.PI);
        T3DEchino.mul(T3DEchinoRotation);
        TGEchino.setTransform(T3DEchino);

        addChild(TGAbaco);
        addChild(TGFusto);
        addChild(TGEchino);
    }
}
