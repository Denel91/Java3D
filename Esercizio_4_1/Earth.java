package Esercizio_4_1;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

public class Earth extends Applet {
    public Earth() {
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
        // mouse behaviour
        MouseRotate mouse = new MouseRotate(TG);
        mouse.setSchedulingBounds(new BoundingSphere());
        // key nav
        KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(TG); //Imposto il bound del behavior
        keyNav.setSchedulingBounds(new BoundingSphere(new Point3d(), 10000.0)); //Aggiungo il behavior alla scena
        TG.addChild(keyNav);
        TG.addChild(mouse);
        node.addChild(TG);
        node.addChild(directionalLight());
        node.addChild(createBackground());
        return node;
    }

    public TransformGroup createViewBranch(SimpleUniverse simpleU) {
        TransformGroup viewTransformGroup = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D vanishingPoints = new Transform3D();
        Point3d eye = new Point3d(2.0d, 2.0d, -6.0d); // coordinate dell’osservatore.
        Point3d center = new Point3d(0.0, 0.0, 0.0); // coordinate del punto verso cui guardare.
        Vector3d up = new Vector3d(0.0, 1.0, 0.0); // direzione in cui si trova l’alto.
        vanishingPoints.lookAt(eye, center, up);
        vanishingPoints.invert();
        viewTransformGroup.setTransform(vanishingPoints);
        return viewTransformGroup;
    }

    public Background createBackground() {
        // Creazione BACKGROUND
        TextureLoader loader = new TextureLoader("C:\\Users\\utente\\IdeaProjects\\Java3D\\src\\images\\StarsHearth.jpg", "RGB", new Container());
        ImageComponent2D image = loader.getImage();
        // Impostazione del BACKGROUND
        Background background = new Background();
        background.setImage(image);
        // L'immagine di sfondo viene ridimensionata in modo uniforme per adattarsi
        // alla finestra in modo tale da riempire l'intera finestra.
        background.setImageScaleMode(Background.SCALE_FIT_MAX);
        BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 1000.0);
        background.setApplicationBounds(boundingSphere);
        return background;
    }

    // Luce Direzionale
    public DirectionalLight directionalLight() {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 10.0d);
        // Creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        // Impostazione del colore della luce
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        lightD1.setColor(white);
        // Impostazione della direzione della luce
        lightD1.setDirection(new Vector3f(0.0f, -1.2f, 1.3f));
        // Impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    public TransformGroup createSubGraph() {
        TransformGroup transform = new TransformGroup(); // crea l'oggetto TG
        transform.addChild(new Terra());
        // TransformGroup lunaTG = new TransformGroup();
        // Transform3D luna3D = new Transform3D();
        // luna3D.setTranslation(new Vector3d(0.0d,0.0d,0.0d));
        // lunaTG.setTransform(luna3D);
        // lunaTG.addChild(new Luna());
        // transform.addChild(lunaTG);
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new Earth(), 1024, 1080);
    }
}
