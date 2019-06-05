package Esercizio_4_4;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

public class Poseidon extends Applet {
    public Poseidon() {
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
        // mouse behaviour
        MouseRotate mouse = new MouseRotate(TG);
        mouse.setSchedulingBounds(new BoundingSphere());
        // key nav behaviour
        KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(TG); //Imposto il bound del behavior
        keyNav.setSchedulingBounds(new BoundingSphere(new Point3d(), 50.0)); //Aggiungo il behavior alla scena
        TG.addChild(keyNav);
        TG.addChild(mouse);
        node.addChild(TG);
        node.addChild(directionalLight());
        node.addChild(ambientLight());
        return node;
    }

    public TransformGroup createViewBranch(SimpleUniverse simpleU) {
        TransformGroup viewTransformGroup = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D vanishingPoints = new Transform3D();
        Point3d eye = new Point3d(2.0d, 1.0d, -10.0d); // coordinate dell’osservatore.
        Point3d center = new Point3d(0.0, 0.0, 0.0); // coordinate del punto verso cui guardare.
        Vector3d up = new Vector3d(0.0, 1.0, 0.0); // direzione in cui si trova l’alto.
        vanishingPoints.lookAt(eye, center, up);
        vanishingPoints.invert();
        viewTransformGroup.setTransform(vanishingPoints);
        return viewTransformGroup;
    }

    public DirectionalLight directionalLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 120.0d);
        // creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        lightD1.setColor(white);
        // Impostazione della direzione della luce
        lightD1.setDirection(new Vector3f(-4.5f, -1.0f, -1.5f));
        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    public AmbientLight ambientLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.d, 0.d, 0.d), 1200.d);
        AmbientLight lightP1 = new AmbientLight();
        Color3f color3f = new Color3f(1.0f, 1.0f, 1.0f);
        lightP1.setColor(color3f);
        lightP1.setInfluencingBounds(bounds);
        return lightP1;
    }

    public TransformGroup createSubGraph() {
        TransformGroup transform = new TransformGroup(); // crea l'oggetto TG
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3f(0.0f, -0.8f, 0.0f));
        transform.setTransform(transform3D);
        transform.addChild(new Tempio());
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new Poseidon(), 1024, 1080);
    }
}
