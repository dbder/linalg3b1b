package linal.helpers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * as a matter of fact, it is always possible to represent a translation by a matrix, up to embedding in one direction more:
 *
 * (x,y,z) --> (x+t, y+t, z+t)
 *
 * is the same as the matrix product
 * ( 1 0 0 t ) (x)
 * ( 0 1 0 t ) (y)
 * ( 0 0 1 t ) (z)
 *             (1)
 *------------------------------
 *
 *
 *             (that transform is not linear : increasing the vector by 1 break linearity
 * but if you always work with Vector4 and 1 is fixed, you'll always have matrices of the kind:
 *
 * ( 1 0 0 t ) (x)
 * ( 0 1 0 t ) (y)
 * ( 0 0 1 t ) (z)
 * ( 0 0 0 1 ) (1)
 *
 * (and rotations are matrices of the kind, for example:
 *
 * ( cos  0 sin 0 )
 * ( -sin 0 cos 0 )
 * (  0   1  0  0 )
 * (  0   0  0  1 )
 *
 * what's interesting is that translation then rotation then ...  ; can just be encoded as matrix multiplications
 * [2:51 PM]
 * (so you can compute the entire transform once
 * and then applying on all the nodes
 *
 * instead of applying transforms on all the nodes, every time you need to transform)
 * -----------------------------
 *
 * if you encode what it means to be a Transform , you could just have a way to chain them (like Function#then)
 * (internally it computes matrix products)
 *
 * then you just apply your composed transform
 * (but you'll need my trick about Vector4 to benefit from it for translations too)
 *
 *
 *
 * --------------
 * t's mainly if you want to let people apply different transforms successively
 *
 * instead of doing:
 * for(var transform: transforms)
 *   for(var point: points)
 *     transform.apply(point);
 *
 * the idea is to do this:
 * var composedTransform = transforms.reduce(Transform::then);
 *
 * for(var point: points)
 *   composedTransform .apply(point);
 *
 *
 * I think (I hope) 3b1b will show you how to compose (#then) linear transforms
 * [3:05 PM]
 * but translations are not linear transforms, so if you want them to be part of the flux (like, rotation by 90°, then translate, then rotate by 60° ; and visualise this), you're a bit stuck
 * [3:06 PM]
 * (and basically, the "trick" I gave above, is how you can see a translation as a linear transform in a space of larger dimension. you "just" need to pay an extra coordinate fixed at 1)
 */
public class JudeHelper {
    static Vector3 embed(Vector2 v, int newDimensionIndex) {
        assert newDimensionIndex >= 0;
        assert newDimensionIndex <= 2;
        switch(newDimensionIndex) {
            case 0: return new Vector3(0, v.x, v.y);
            case 1: return new Vector3(v.x, 0, v.y);
            case 2: return new Vector3(v.x, v.y, 0);
        } return null;
    }

    static Vector2 project(Vector3 v, int dimensionIndexToDrop) {
        assert dimensionIndexToDrop >= 0;
        assert dimensionIndexToDrop <= 2;
        switch(dimensionIndexToDrop) {
            case 0: return new Vector2(v.y, v.z);
            case 1: return new Vector2(v.x, v.z);
            case 2: return new Vector2(v.x, v.y);
        } return null;
    }

    static Vector2 planeRotation(Vector2 v, float radianAngle) {
        var sin = (float) Math.sin(radianAngle);
        var cos = (float) Math.cos(radianAngle);
        return new Vector2(
                v.x*cos - v.y*sin,
                v.x*sin + v.y*cos
        );
    }

    static Vector3 canonicalRotation(Vector3 v3, float planeAngle, int fixedDirectionIndex) {
        assert fixedDirectionIndex >= 0;
        assert fixedDirectionIndex <= 2;
        return embed(
                planeRotation(
                        project(v3, fixedDirectionIndex),
                        planeAngle
                ),
                fixedDirectionIndex
        );
    }
}
