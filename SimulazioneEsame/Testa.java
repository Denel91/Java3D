package SimulazioneEsame;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.*;

public class Testa extends Group  {
    public Testa() {
        // Sphere(float radius, int primflags, int divisions, Appearance ap)
        Sphere testa = new Sphere(0.28f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 1500, createAppearance());
        addChild(testa);
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
