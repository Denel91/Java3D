package Esercizio_3_8;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;

public class Fusto extends Cylinder {
    public Fusto(float radius, float height) {
        // Cylinder(float radius, float height, int primflags, int xdivision, int ydivision, Appearance ap)
        super(radius / 9, height, Primitive.GENERATE_NORMALS, 20, 1, createAppearance());
    }

    public static Appearance createAppearance() {
        Appearance app = new Appearance();
        Material material = new Material();
        material.setCapability(Material.AMBIENT_AND_DIFFUSE);
        ColoringAttributes color = new ColoringAttributes();
        color.setColor(1.0f, 0.6f, 0.2f);
        app.setColoringAttributes(color);
        material.setAmbientColor(210 / 255f, 180 / 255f, 140 / 255f);
        material.setDiffuseColor(210 / 255f, 180 / 255f, 140 / 255f);
        app.setMaterial(material);
        return app;
    }
}
