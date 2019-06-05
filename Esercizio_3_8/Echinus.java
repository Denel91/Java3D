package Esercizio_3_8;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;

public class Echinus extends Cone {
    public Echinus(float bottomRadius, float height) {
        // Cone(float radius, float height, int primflags, int xdivision, int ydivision, Appearance ap)
        super(bottomRadius, height, Primitive.GENERATE_NORMALS, 1500,1500, createAppearance());
    }

    public static Appearance createAppearance() {
        Appearance app = new Appearance();
        Material material = new Material();
        ColoringAttributes color = new ColoringAttributes();
        color.setColor(1.0f, 0.6f, 0.2f);
        app.setColoringAttributes(color);
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        material.setAmbientColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        app.setMaterial(material);
        return app;
    }
}
