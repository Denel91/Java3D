package Esercizio_3_7;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.awt.*;

public class BoundsSphere extends Applet {
    public BoundsSphere() {
        setLayout(new BorderLayout(1024, 1080));
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
        BranchGroup root = new BranchGroup();
        TransformGroup TG = createSubGraph();
        //root.addChild(ambientLight());
        root.addChild(directionalLight());
        //root.addChild(spotLight());
        TransformGroup rootTG = new TransformGroup();
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(0f, 2f, -8f));
        rootTG.setTransform(transform3D);
        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                TransformGroup sphereTG = new TransformGroup();
                Transform3D sphere3D = new Transform3D();
                Sphere sphere = new Sphere(.4f, Primitive.GEOMETRY_NOT_SHARED | Primitive.GENERATE_NORMALS, 1000, createAppearance());
                sphere3D.setTranslation(new Vector3d(i - 2, j - 4, 0));
                sphereTG.addChild(sphere);
                sphereTG.setTransform(sphere3D);
                rootTG.addChild(sphereTG);
            }
        }

        root.addChild(rootTG);
        return root;
    }

    public AmbientLight ambientLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.d, 0.d, 0.d), 30.0d);
        AmbientLight lightP1 = new AmbientLight();
        //Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
        Color3f cyan = new Color3f(0.0f, 1.0f, 1.0f);
        lightP1.setColor(cyan);
        lightP1.setInfluencingBounds(bounds);
        return lightP1;
    }

    public DirectionalLight directionalLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 7.7d);
        // creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        //Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f cyan = new Color3f(0.0f, 1.0f, 1.0f);
        lightD1.setColor(cyan);
        // Impostazione della direzione della luce
        lightD1.setDirection(new Vector3f(-1.0f, 0.0f, -1.0f));
        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    public SpotLight spotLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 10.0d);
        SpotLight spotLightSP1 = new SpotLight();
        //Setto posizione della luce e angolo di apertura
        spotLightSP1.setPosition(new Point3f(-1.0f, 0.0f, -8f));
        spotLightSP1.setSpreadAngle((float) Math.PI / 3.3f);
        spotLightSP1.setInfluencingBounds(bounds);
        spotLightSP1.setDirection(new Vector3f(1.0f, 1.0f, 0.0f));
        return spotLightSP1;
    }

    public Appearance createAppearance() {
        Appearance app = new Appearance();
        Material material = new Material();
        app.setMaterial(material);
        return app;
    }

    public TransformGroup createSubGraph() {
        TransformGroup transform = new TransformGroup(); // crea l'oggetto TG
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new BoundsSphere(), 1024, 1080);
    }
}
