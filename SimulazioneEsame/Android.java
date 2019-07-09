package SimulazioneEsame;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Android extends Group {
    private TransformGroup TGCorpo;
    private Corpo corpo;
    private float radius = 2.6f;
    private float height = 0.5f;

    private TransformGroup TGTesta;
    private Transform3D T3DTesta;
    private Testa testa;

    private TransformGroup TGpiedeDestro;
    private TransformGroup TGpiedeSinistro;
    private Transform3D T3Ddx;
    private Transform3D T3Dsx;
    private Corpo piedeDestro;
    private Corpo piedeSinistro;

    private TransformGroup TGBraccioDestro;
    private TransformGroup TGBraccioSinistro;
    private Transform3D T3DBdestro;
    private Transform3D T3DBsinistro;
    private Corpo braccioDestro;
    private Corpo braccioSinistro;

    private TransformGroup TGAntennaDestra;
    private Transform3D T3DAntennaDestra;
    private Transform3D T3DAntennaDx;
    private Corpo antennaDestra;

    private TransformGroup TGAntennaSinistra;
    private Transform3D T3DAntennaSinistra;
    private Transform3D T3DAntennaSx;
    private Corpo antennaSinistra;

    public Android() {
        TGCorpo = new TransformGroup();
        corpo = new Corpo(radius, height);
        TGCorpo.addChild(corpo);
        addChild(TGCorpo);

        TGTesta = new TransformGroup();
        T3DTesta = new Transform3D();
        testa = new Testa();
        T3DTesta.setTranslation(new Vector3f(0.0f, 0.2f, 0.0f));
        TGTesta.setTransform(T3DTesta);
        TGTesta.addChild(testa);
        addChild(TGTesta);

        TGpiedeDestro = new TransformGroup();
        TGpiedeSinistro = new TransformGroup();
        T3Ddx = new Transform3D();
        T3Dsx = new Transform3D();
        piedeDestro = new Corpo(0.4f, 0.3f);
        piedeSinistro = new Corpo(0.4f, 0.3f);
        T3Ddx.setTranslation(new Vector3f(0.2f, -0.29f, 0.0f));
        T3Dsx.setTranslation(new Vector3f(-0.2f, -0.29f, 0.0f));
        TGpiedeDestro.setTransform(T3Ddx);
        TGpiedeSinistro.setTransform(T3Dsx);
        TGpiedeDestro.addChild(piedeDestro);
        TGpiedeSinistro.addChild(piedeSinistro);
        addChild(TGpiedeDestro);
        addChild(TGpiedeSinistro);

        TGBraccioDestro = new TransformGroup();
        TGBraccioSinistro = new TransformGroup();
        T3DBdestro = new Transform3D();
        T3DBsinistro = new Transform3D();
        braccioDestro = new Corpo(0.3f, 0.29f);
        braccioSinistro = new Corpo(0.3f, 0.29f);
        T3DBdestro.setTranslation(new Vector3f(0.30f, 0.0f, 0.0f));
        T3DBsinistro.setTranslation(new Vector3f(-0.30f, 0.0f, 0.0f));
        TGBraccioDestro.setTransform(T3DBdestro);
        TGBraccioSinistro.setTransform(T3DBsinistro);
        TGBraccioDestro.addChild(braccioDestro);
        TGBraccioSinistro.addChild(braccioSinistro);
        addChild(TGBraccioDestro);
        addChild(TGBraccioSinistro);

        TGAntennaDestra = new TransformGroup();
        TGAntennaSinistra = new TransformGroup();
        T3DAntennaDestra = new Transform3D();
        T3DAntennaSinistra = new Transform3D();
        T3DAntennaDx = new Transform3D();
        T3DAntennaSx = new Transform3D();
        antennaDestra = new Corpo(0.05f, 0.30f);
        antennaSinistra = new Corpo(0.1f, 0.30f);
        T3DAntennaDestra.setTranslation(new Vector3f(0.15f, 0.5f, 0.0f));
        T3DAntennaSinistra.setTranslation(new Vector3f(-0.15f, 0.5f, 0.0f));
        T3DAntennaDx.rotZ(-Math.PI / 9);
        T3DAntennaSx.rotZ(Math.PI / 9);
        T3DAntennaDestra.mul(T3DAntennaDx);
        T3DAntennaSinistra.mul(T3DAntennaSx);
        TGAntennaDestra.setTransform(T3DAntennaDestra);
        TGAntennaSinistra.setTransform(T3DAntennaSinistra);
        TGAntennaDestra.addChild(antennaDestra);
        TGAntennaSinistra.addChild(antennaSinistra);
        addChild(TGAntennaDestra);
        addChild(TGAntennaSinistra);
    }
}
