package SimulazioneEsame;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.*;

public class Corpo extends Cylinder {
    public Corpo(float radius, float height) {
        // Cylinder(float radius, float height, int primflags, int xdivision, int ydivision, Appearance ap)
        super(radius / 9, height, Primitive.GENERATE_NORMALS, 20, 1, createAppearance());
    }

    public static Appearance createAppearance() {
        Appearance app = new Appearance();
        Material material = new Material();
        PolygonAttributes polyAttrbutes = new PolygonAttributes();
        //The PolygonAttributes object defines attributes for rendering polygon primitives.
        polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
        app.setPolygonAttributes(polyAttrbutes);
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        material.setAmbientColor(0.0f, 0.5f, 0.0f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setSpecularColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setLightingEnable(true);
        app.setMaterial(material);
        return app;
    }
}
