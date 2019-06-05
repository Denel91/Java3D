package Esercizio_4_4;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class Tempio extends Group {
    private TransformGroup TGScalinata;
    private TransformGroup TGFacciataAnteriore;
    private TransformGroup TGFacciataPosteriore;
    private Transform3D T3DFacciataPosteriore;
    private TransformGroup TGColonnatoDestro;
    private Transform3D T3DColonnatoDestro;
    private Transform3D Tr3DColonnatoDestro;
    private TransformGroup TGColonnatoSinistro;
    private Transform3D T3DColonnatoSinistro;
    private Transform3D Tr3DColonnatoSinistro;
    private Scalinata scalinata;
    private FacciataTempio facciataTempioAnteriore;
    private FacciataTempio facciataTempioPosteriore;
    private Colonnade colonnadeDestro;
    private Colonnade colonnadeSinistro;

    public Tempio(){
        TGScalinata = new TransformGroup();

        TGFacciataAnteriore = new TransformGroup();

        TGFacciataPosteriore = new TransformGroup();
        T3DFacciataPosteriore = new Transform3D();

        TGColonnatoDestro = new TransformGroup();
        T3DColonnatoDestro = new Transform3D();
        Tr3DColonnatoDestro = new Transform3D();

        TGColonnatoSinistro = new TransformGroup();
        T3DColonnatoSinistro = new Transform3D();
        Tr3DColonnatoSinistro = new Transform3D();

        scalinata = new Scalinata();
        facciataTempioAnteriore = new FacciataTempio();
        facciataTempioPosteriore = new FacciataTempio();
        colonnadeDestro = new Colonnade();
        colonnadeSinistro = new Colonnade();

        TGScalinata.addChild(scalinata);
        addChild(TGScalinata);

        TGFacciataAnteriore.addChild(facciataTempioAnteriore);
        addChild(TGFacciataAnteriore);

        T3DFacciataPosteriore.setTranslation(new Vector3f(0.0f,0.0f,-5.6f));
        TGFacciataPosteriore.setTransform(T3DFacciataPosteriore);
        TGFacciataPosteriore.addChild(facciataTempioPosteriore);
        addChild(TGFacciataPosteriore);

        T3DColonnatoDestro.rotY(Math.PI / 2);
        Tr3DColonnatoDestro.setTranslation(new Vector3f(-1.4f,0.0f,-1.78f));
        T3DColonnatoDestro.mul(Tr3DColonnatoDestro);
        TGColonnatoDestro.setTransform(T3DColonnatoDestro);
        TGColonnatoDestro.addChild(colonnadeDestro);
        addChild(TGColonnatoDestro);

        T3DColonnatoSinistro.rotY(Math.PI / 2);
        Tr3DColonnatoSinistro.setTranslation(new Vector3f(-1.4f,0.0f,-3.78f));
        T3DColonnatoSinistro.mul(Tr3DColonnatoSinistro);
        TGColonnatoSinistro.setTransform(T3DColonnatoSinistro);
        TGColonnatoSinistro.addChild(colonnadeSinistro);
        addChild(TGColonnatoSinistro);
    }
}
