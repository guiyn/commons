package org.cmcc.ecip.common.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {

	public static final byte[] EMPTY_BYTES = new byte[0];

	public static final void read(InputStream in, byte[] b, int offset, int length) throws IOException {
		for (int got = 0; length > 0;) {
			got = in.read(b, offset, length);
			if (got < 0) {
				throw new EOFException();
			}
			offset += got;
			length -= got;
		}
	}

	public static final byte read(InputStream in) throws IOException {
		int got = in.read();
		if (got < 0) {
			throw new EOFException();
		}
		return (byte) (got & 0xff);
	}

	public static final int readInt(InputStream in) throws IOException {
		byte[] b = new byte[4];
		read(in, b, 0, b.length);
		return ByteUtil.getInt(b);
	}

	public static final float readFloat(InputStream in) throws IOException {
		return Float.intBitsToFloat(readInt(in));
	}

	public static final long readLong(InputStream in) throws IOException {
		byte[] b = new byte[8];
		read(in, b, 0, b.length);
		return ByteUtil.getLong(b);
	}

	public static final double readDouble(InputStream in) throws IOException {
		return Double.longBitsToDouble(readLong(in));
	}

	public static final void write(OutputStream out, byte b) throws IOException {
		out.write(b & 0xff);
	}

	public static final void write(OutputStream out, byte[] src) throws IOException {
		out.write(src);
	}

	public static final void writeInt(OutputStream out, int i) throws IOException {
		byte[] b = new byte[4];
		b[0] = (byte) (i & 0xff);
		b[1] = (byte) (i >>> 8);
		b[2] = (byte) (i >>> 16);
		b[3] = (byte) (i >>> 24);
		out.write(b);
	}

	public static final void writeFloat(OutputStream out, float f) throws IOException {
		writeInt(out, Float.floatToIntBits(f));
	}

	public static final void writeLong(OutputStream out, long l) throws IOException {
		byte[] b = new byte[8];
		b[0] = (byte) (l & 0xff);
		b[1] = (byte) (l >>> 8);
		b[2] = (byte) (l >>> 16);
		b[3] = (byte) (l >>> 24);
		b[4] = (byte) (l >>> 32);
		b[5] = (byte) (l >>> 40);
		b[6] = (byte) (l >>> 48);
		b[7] = (byte) (l >>> 56);
		out.write(b);
	}

	public static final void writeDouble(OutputStream out, double d) throws IOException {
		writeLong(out, Double.doubleToLongBits(d));
	}
}
