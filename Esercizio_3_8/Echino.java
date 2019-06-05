package Esercizio_3_8;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Echino extends Shape3D {
    protected Point3f v[] = null;
    protected TriangleStripArray triangleStrip = null;
    protected PolygonAttributes polyAttrbutes = new PolygonAttributes();
    protected Appearance app = new Appearance();

    public Echino(int steps, float topRadius, float bottomRadius, float height) {
        float top = height / 2;
        float bottom = -height / 2;
        v = new Point3f[(steps + 1) * 2];

        for (int i = 0; i < steps; i++) {
            double angle = 2.0 * Math.PI * (double) i / (double) steps;
            float xTop = (float) Math.sin(angle) * topRadius;
            float yTop = (float) Math.cos(angle) * topRadius;
            float xBottom = (float) Math.sin(angle) * bottomRadius;
            float yBottom = (float) Math.cos(angle) * bottomRadius;
            v[i * 2 + 0 ] = new Point3f(xBottom, yBottom, bottom);
            v[i * 2 + 1] = new Point3f(xTop, yTop, top);
        }
        v[steps * 2 + 0] = new Point3f(0.0f, bottomRadius, bottom);
        v[steps * 2 + 1] = new Point3f(0.0f, topRadius, top);
        int[] stripCounts = {(steps + 1) * 2};
        triangleStrip = new TriangleStripArray((steps + 1) * 2,
                GeometryArray.COORDINATES, stripCounts);
        triangleStrip.setCoordinates(0, v);
        GeometryInfo geometry = new GeometryInfo(triangleStrip);
        NormalGenerator normal = new NormalGenerator();
        normal.generateNormals(geometry);
        setGeometry(geometry.getGeometryArray());
        setGeometry(triangleStrip);
        // Impostazione aspetto wireframe
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
        app.setPolygonAttributes(polyAttrbutes);

        // Appearance
        Material material = new Material();
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        ColoringAttributes color = new ColoringAttributes();
        Color3f blend = new Color3f(0.5f,0.4f,0.3f);
        color.setColor(blend);
        app.setColoringAttributes(color);
        material.setAmbientColor(220 / 255f, 190 / 255f, 130 / 255f);
        material.setDiffuseColor(220 / 255f, 190 / 255f, 130 / 255f);
        app.setMaterial(material);
        setAppearance(app);
    }
}