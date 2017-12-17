package com.wrx.mytest.opengl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by wu_ru on 2017/6/1.
 */

public class Triangle {

   /* private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";*/
    // 下面的代码是 着色语言GLSL，一种类似于c语言的计算机语言
    // 片段着色器代码
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    // 顶点着色器代码
    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";
    // Use to access and set the view transformation
    private int mMVPMatrixHandle;

    //浮点型数据缓冲区，buffer可以用来存放多个float类型，存储的数量在创建时设置
    private FloatBuffer vertexBuffer;

    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {   // in counterclockwise order:
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    public Triangle() {
        // initialize vertex byte buffer for shape coordinates
        // 创建直接字节缓冲区，(创建非直接字节缓冲区，则是调用 allocate())
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                // 翻译上句： 每个浮点类型是 4字节。所以，这里创建一个triangleCoords长度乘以4（一共36字节）的buffer
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        // 使用设备硬件的本地字节顺序,
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        // 将字节缓冲器转成浮点缓冲器
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        // 设置当前位置为下标0处的浮点数据，由于上句代码已经将triangleCoords放进去了，
        // 所以，这里position下标0，就是triangleCoords数组的第一个数据
        // 值得注意的是，还有另一个同名无参方法position(), 这个方法是用来获取当前位置的
        // vertexBuffer.get()调用，拿到当前position的数据之后，当前position会自动向下进1，
        // 此时调用无参position()方法，拿到的位置是下标1
        vertexBuffer.position(0);

        // 加载着色器，查了很多资料，好像着色器的type只有两种 GL_VERTEX_SHADER 和 GL_FRAGMENT_SHADER
        // GL_VERTEX_SHADER类型的着色器是用于在可编程顶点处理器上运行的着色器。
        // GL_FRAGMENT_SHADER类型的着色器是用于在可编程片段处理器上运行的着色器。
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        // 创建一个着色程序对象
        mProgram = GLES20.glCreateProgram();

        // 将顶点／片段两种着色加到mProgram上
        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);
        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);
        // creates OpenGL ES program executables
        // 对（着色）程序对象执行链接操作
        GLES20.glLinkProgram(mProgram);
    }

    private int loadShader(int type, String shaderCode){
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // add the source code to the shader and compile it
        // 加入着色语言，并编译它
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public void draw() {
        // Add program to OpenGL ES environment
        // 将OpenGL渲染管道切换到着色器模式,并使用做好的（着色）程序对象。
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    public void draw(float[] mvpMatrix) { // pass in the calculated transformation matrix

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }



}
