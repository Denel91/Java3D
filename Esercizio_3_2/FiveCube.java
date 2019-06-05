package Esercizio_3_2;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class FiveCube extends Applet {
    public FiveCube() {
        setLayout(new BorderLayout(1024,1080));
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    // Funzione che crea il sottografo
    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        double n = 5.0d;
        for (int i = 0; i < n; i++) {
            TransformGroup TG = createSubGraph(n, i);
            TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            node.addChild(TG);
        }

        return node;
    }

    public TransformGroup createSubGraph(double n, int x) {
        TransformGroup transform = new TransformGroup(); // crea l'oggetto TG
        Transform3D translation = new Transform3D(); // crea l'oggetto Transform3D
        // Impostazione della Traslazione attorno alla circonferenza
        translation.setTranslation(new Vector3d((0d + Math.cos(x * 2 * Math.PI / n)), (0d + Math.sin(x * 2 * Math.PI / n)), -1.0d));
        transform.setTransform(translation);
        transform.addChild(new ColorCube(0.2d));
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new FiveCube(), 860, 768);
    }
}
