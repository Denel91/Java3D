package Esercizio_3_5;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class Projections extends Applet {
    public Projections() {
        setLayout(new BorderLayout());
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        TransformGroup viewTransformGroup = simpleU.getViewingPlatform().getViewPlatformTransform();
        // Comportamento predefinito di rotazione legata agli eventi del mouse
        MouseRotate rotateBehavior = new MouseRotate();
        // Legame fa il comportamento e il TranformGroup.
        rotateBehavior.setTransformGroup(viewTransformGroup);

        // Zona in cui tenere conto degli eventi. Sfera di raggio 1 con centro nello 0
        rotateBehavior.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0));

        // TranformGroup da legare alla rotazione interattiva.
        TransformGroup mainTG = new TransformGroup();
        mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        BranchGroup objRoot = new BranchGroup();
        objRoot.addChild(mainTG);
        objRoot.addChild(rotateBehavior);

        // Accedo all'oggetto view del SimpleUniverse
        View myView = simpleU.getViewer().getView();
        // Impostazione del campo visivo
        myView.setFieldOfView(Math.PI / 4);
        // Impostazione della distanza dal piano dello sfondo
        myView.setBackClipDistance(10.0);
        // Impostazione della distanza dal piano frontale
        myView.setFrontClipDistance(0.1);
        //Impostazione del tipo di proiezione
        //myView.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        myView.setProjectionPolicy(View.PARALLEL_PROJECTION);
        myView.setVisibilityPolicy(View.VISIBILITY_DRAW_ALL);

        createViewBranch(simpleU);
        simpleU.addBranchGraph(scene);
        simpleU.addBranchGraph(objRoot);
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
        Vector3d up = new Vector3d(0.0, 1.0, 0.0);   // direzione in cui si trova l’alto.
        vanishingPoints.lookAt(eye, center, up);
        vanishingPoints.invert();
        viewTransformGroup.setTransform(vanishingPoints);
        return viewTransformGroup;
    }

    public static void main(String[] args) {
        new MainFrame(new Projections(), 1024, 1080);
    }
}
