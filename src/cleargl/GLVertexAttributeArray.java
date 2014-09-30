package cleargl;

import java.nio.FloatBuffer;
import java.util.Arrays;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLException;

public class GLVertexAttributeArray	implements
																		GLCloseable,
																		GLInterface
{

	private GLAttribute mGLAttribute;
	private int[] mVertexAttributeBuffersId;

	private int mElementsPerIndex;

	public GLVertexAttributeArray(GLAttribute pGLAttribute,
																final int pElementsPerIndex)
	{
		super();
		mGLAttribute = pGLAttribute;
		mElementsPerIndex = pElementsPerIndex;
		mVertexAttributeBuffersId = new int[1];
		mGLAttribute.getGL()
								.glGenBuffers(1, mVertexAttributeBuffersId, 0);
	}

	@Override
	public void close() throws GLException
	{
		mGLAttribute.getGL().glDeleteBuffers(	1,
																					mVertexAttributeBuffersId,
																					0);
	}

	public void copyFrom(FloatBuffer pFloatBuffer)
	{
		bind();
		getGL().glBufferData(	GL.GL_ARRAY_BUFFER,
													pFloatBuffer.remaining() * (Float.SIZE / 8),
													pFloatBuffer,
													GL.GL_STATIC_DRAW);
	}

	public void bind()
	{
		mGLAttribute.getGL().glBindBuffer(GL.GL_ARRAY_BUFFER,
																			mVertexAttributeBuffersId[0]);
	}

	public void unbind()
	{
		mGLAttribute.getGL().glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
	}

	@Override
	public GL3 getGL()
	{
		return mGLAttribute.getGL();
	}

	@Override
	public int getId()
	{
		return mVertexAttributeBuffersId[0];
	}

	public GLAttribute getAttribute()
	{
		return mGLAttribute;
	}

	public int getElementsPerIndex()
	{
		return mElementsPerIndex;
	}

	@Override
	public String toString()
	{
		return "GLVertexAttributeArray [mGLAttribute=" + mGLAttribute
						+ ", mVertexAttributeBuffersId="
						+ Arrays.toString(mVertexAttributeBuffersId)
						+ ", mElementsPerIndex="
						+ mElementsPerIndex
						+ "]";
	}

}
