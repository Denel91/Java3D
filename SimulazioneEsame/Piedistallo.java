package SimulazioneEsame;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Piedistallo extends Group {
    private TransformGroup TGBase;
    private Transform3D T3DBase;
    private TransformGroup TGFusto;
    private Transform3D T3DFusto;
    private Transform3D Tr3DFusto;
    private TransformGroup TGEchino;
    private Transform3D T3DEchino;
    private Transform3D Tr3DEchino;
    private Base base;
    private Fusto fusto;
    private Echinus echinus;
    private float xdim = 1.0f;
    private float ydim = 0.2f;
    private float zdim = 1.0f;
    private int steps = 10000;
    private float radius = 0.6f;
    private float bottomRadius = 1.0f;
    private float height = 2.0f;

    public Piedistallo(){
        TGBase = new TransformGroup();
        T3DBase = new Transform3D();

        TGFusto = new TransformGroup();
        T3DFusto = new Transform3D();
        Tr3DFusto = new Transform3D();

        TGEchino = new TransformGroup();
        T3DEchino = new Transform3D();
        Tr3DEchino = new Transform3D();

        base = new Base(xdim,ydim,zdim);
        fusto = new Fusto(steps, radius);
        echinus = new Echinus(bottomRadius, height);

        T3DBase.setTranslation(new Vector3f(0.0f,-0.8f,0.0f));
        TGBase.setTransform(T3DBase);
        TGBase.addChild(base);
        addChild(TGBase);

        T3DFusto.rotX(Math.PI / 2);
        Tr3DFusto.setTranslation(new Vector3f(0.0f,0.0f,-1.4f));
        T3DFusto.mul(Tr3DFusto);
        TGFusto.setTransform(T3DFusto);
        TGFusto.addChild(fusto);
        addChild(TGFusto);

        T3DEchino.rotX(Math.PI);
        Tr3DEchino.setTranslation(new Vector3f(0.0f,-2.80f,0.0f));
        T3DEchino.mul(Tr3DEchino);
        TGEchino.setTransform(T3DEchino);
        TGEchino.addChild(echinus);
        addChild(TGEchino);
    }
}
