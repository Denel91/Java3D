package Esercizio_3_4;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class Projection extends Applet {
    public Projection() {
        setLayout(new BorderLayout(1024, 1080));
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();

        // Accedo all'oggetto view del SimpleUniverse
        View myView = simpleU.getViewer().getView();
        // Setto la proprietà Compatibility mode a true per modificare la matrice di proiezione
        myView.setCompatibilityModeEnable(true);

        // Creazione di una trasformazione
        Transform3D proj = new Transform3D();
        // Proiezione ortografica con diverse profondità
        //proj.ortho(-3.0, 3.0, -3.0, 3.0, 3.0, 10.0);
        //proj.ortho(-1.0, 1.0, -1.0, 1.0, 3, 4);
        //proj.ortho(-2.0, 2.0, -2.0, 2.0, 2.1, 10.0);

        // Per modificare la proiezione prospettica bisogna assegnare
        // valori differenti alla variabile fovx.
        double aspect = 1024 / 768;
        double fovx = -Math.PI / 2;
        // Proiezione prospettica
        proj.perspective(fovx, aspect, 2.0, 4.0);
        myView.setLeftProjection(proj);

        createViewBranch(simpleU);
        simpleU.addBranchGraph(scene);
    }

    // Funzione che crea il sottografo
    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        TransformGroup TG = createSubGraph();
        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        node.addChild(TG);
        return node;
    }

    public TransformGroup createSubGraph() {
        TransformGroup transform = new TransformGroup(); // crea l'oggetto TG
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(new Vector3d(0, 0, 10));
        Transform3D t3d2 = new Transform3D();
        t3d2.rotY(Math.PI / 4);
        t3d2.invert();
        t3d2.mul(t3d);
        transform.addChild(new ColorCube(0.3d));
        return transform;
    }

    public TransformGroup createViewBranch(SimpleUniverse simpleU) {
        TransformGroup viewTransformGroup = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D vanishingPoints = new Transform3D();
        Point3d eye = new Point3d(2.0d, 2.0d, 2.0d); // coordinate dell’osservatore.
        Point3d center = new Point3d(0.0, 0.0, 0.0); // coordinate del punto verso cui guardare.
        Vector3d up = new Vector3d(0.0, 1.0, 0.0); // direzione in cui si trova l’alto.
        vanishingPoints.lookAt(eye, center, up);
        vanishingPoints.invert();
        viewTransformGroup.setTransform(vanishingPoints);
        return viewTransformGroup;
    }

    public static void main(String[] args) {
        new MainFrame(new Projection(), 860, 870);
    }
}
