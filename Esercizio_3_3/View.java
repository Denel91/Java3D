package Esercizio_3_3;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class View extends Applet {
    public View() {
        setLayout(new BorderLayout(1024, 1080));
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
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
        transform.addChild(new ColorCube(0.3d));
        return transform;
    }

    public TransformGroup createViewBranch(SimpleUniverse simpleU) {
        TransformGroup viewTransformGroup = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D vanishingPoints = new Transform3D();
        Point3d eye = new Point3d(3.0d, 3.0d, 3.0d);// coordinate dell’osservatore.
        Point3d center = new Point3d(0.0, 0.0, 0.0);// coordinate del punto verso cui guardare.
        Vector3d up = new Vector3d(0.0, 1.0, 0.0);// direzione in cui si trova l’alto.
        vanishingPoints.lookAt(eye, center, up);
        vanishingPoints.invert();
        viewTransformGroup.setTransform(vanishingPoints);
        return viewTransformGroup;
    }

    public static void main(String[] args) {
        new MainFrame(new View(), 860, 770);
    }
}
