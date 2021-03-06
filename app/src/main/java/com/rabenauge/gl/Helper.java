/*
 * Copyright 2010-2011 bodo, eyebex, ralph, spotter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rabenauge.gl;

import android.opengl.GLU;
import java.nio.ByteBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * A class for various static helper methods.
 */
public class Helper {
    // Vertices and texture coordinates for rendering a bitmap in order UL, LL, LR, UR.
    private static final byte WIDTH=1, HEIGHT=1;
    private static final ByteBuffer VERTICES=ByteBuffer.allocateDirect(8).put((byte)0).put((byte)0).put((byte)0).put(HEIGHT).put(WIDTH).put(HEIGHT).put(WIDTH).put((byte)0);

    static {
        // Reset the position before using the buffer!
        VERTICES.position(0);
    }

    public static void drawScreenSpaceQuad(GL10 gl) {
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        GLU.gluOrtho2D(gl, 0, WIDTH, HEIGHT, 0);

        gl.glVertexPointer(2, GL10.GL_BYTE, 0, VERTICES);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

        gl.glPopMatrix();
    }

    public static void drawScreenSpaceTexture(Texture2D tex) {
        tex.makeCurrent();
        tex.gl.glTexCoordPointer(2, GL10.GL_BYTE, 0, VERTICES);

        drawScreenSpaceQuad(tex.gl);
    }

    public static void toggleState(GL10 gl, int cap, boolean state) {
        if (state) {
            gl.glEnable(cap);
        }
        else {
            gl.glDisable(cap);
        }
    }
}
