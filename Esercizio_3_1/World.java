package Esercizio_3_1;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class World extends Applet {
    public World() {
        setLayout(new BorderLayout(1024, 1080));
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        // Codice per permettere la rotazione dell'oggetto con il mouse
        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleU.getViewingPlatform().setViewPlatformBehavior(orbit);
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
        TransformGroup cuboTG = new TransformGroup();
        // Transform3D per la traslazione
        Transform3D cuboT3D = new Transform3D();
        // Transform3D per lo scaling
        Transform3D scaling3D = new Transform3D();
        // Transform3D per le rotazioni
        Transform3D cubeTD = new Transform3D();
        // Traslazione in basso a destra
        //cuboT3D.setTranslation(new Vector3d(1.0d, 0.3d, -1.0d));
        //cuboTG.setTransform(cuboT3D);
        // Rotazione di 90° su x
        //cubeTD.rotX(Math.PI / 6);
        // Rotazione di 45° su y
        //cubeTD.rotY(-Math.PI / 3);
        // Rotazione di 22° su z
        //cubeTD.rotZ(-Math.PI / 6);
        // Scaling sui singoli assi
        scaling3D.setScale(new Vector3d(1.0d,1.0d, -2.0d)); //def. scaling
        cuboTG.setTransform(scaling3D);
        // Combinazione delle trasformazioni di traslazione e rotazione
        //cuboT3D.mul(cubeTD);
        //cuboTG.setTransform(cuboT3D);
        // Aggiungo il cubo come figlio al TransformGroup
        cuboTG.addChild(new ColorCube(0.2d));
        transform.addChild(cuboTG);
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new World(), 860, 768);
    }
}
