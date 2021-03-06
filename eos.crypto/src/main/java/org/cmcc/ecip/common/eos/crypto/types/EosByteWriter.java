/*
 * Copyright (c) 2017-2018 PlayerOne.
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cmcc.ecip.common.eos.crypto.types;

import java.util.Collection;


/**
 */

public class EosByteWriter implements EosType.Writer {
    private byte[] _buf;
    private int _index;

    public EosByteWriter(int capacity) {
        _buf = new byte[capacity];
        _index = 0;
    }

    public EosByteWriter(byte[] buf) {
        _buf = buf;
        _index = buf.length;
    }

    private void ensureCapacity(int capacity) {
        if (_buf.length - _index < capacity) {
            byte[] temp = new byte[_buf.length * 2 + capacity];
            System.arraycopy(_buf, 0, temp, 0, _index);
            _buf = temp;
        }
    }

    public void put(byte b) {
        ensureCapacity(1);
        _buf[_index++] = b;
    }

    public void putShortLE(short value) {
        ensureCapacity(2);
        _buf[_index++] = (byte) (0xFF & (value));
        _buf[_index++] = (byte) (0xFF & (value >> 8));
    }

    public void putIntLE(int value) {
        ensureCapacity(4);
        _buf[_index++] = (byte) (0xFF & (value));
        _buf[_index++] = (byte) (0xFF & (value >> 8));
        _buf[_index++] = (byte) (0xFF & (value >> 16));
        _buf[_index++] = (byte) (0xFF & (value >> 24));
    }

    public void putLongLE(long value) {
        ensureCapacity(8);
        _buf[_index++] = (byte) (0xFFL & (value));
        _buf[_index++] = (byte) (0xFFL & (value >> 8));
        _buf[_index++] = (byte) (0xFFL & (value >> 16));
        _buf[_index++] = (byte) (0xFFL & (value >> 24));
        _buf[_index++] = (byte) (0xFFL & (value >> 32));
        _buf[_index++] = (byte) (0xFFL & (value >> 40));
        _buf[_index++] = (byte) (0xFFL & (value >> 48));
        _buf[_index++] = (byte) (0xFFL & (value >> 56));
    }

    public void putBytes(byte[] value) {
        ensureCapacity(value.length);
        System.arraycopy(value, 0, _buf, _index, value.length);
        _index += value.length;
    }

    public void putBytes(byte[] value, int offset, int length) {
        ensureCapacity(length);
        System.arraycopy(value, offset, _buf, _index, length);
        _index += length;
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[_index];
        System.arraycopy(_buf, 0, bytes, 0, _index);
        return bytes;
    }

    public int length() {
        return _index;
    }

    public void putString(String value){
        if ( null == value ){
            putVariableUInt( 0 );
            return;
        }

        // array count ??? variable int ??? ????????? ??????.
        byte[] strBytes = value.getBytes(); // don't value.length() ! because str.length() != bytes.length for multi-byte chars. ( asian characters )
        putVariableUInt( strBytes.length );

        if ( strBytes.length > 0 ) {
            putBytes(strBytes);
        }
    }

    public void putCollection(Collection<? extends EosType.Packer> collection){
        if ( null == collection){
            putVariableUInt( 0 );
            return;
        }

        // element count ??? variable int ??? ????????? ??????.
        putVariableUInt( collection.size() );

        for ( EosType.Packer type : collection) {
            type.pack( this );
        }
    }

    public void putVariableUInt(long val ) {

        do {
            byte b = (byte)((val) & 0x7f);
            val >>= 7;
            b |= ( ((val > 0) ? 1 : 0 ) << 7 );
            put(b);
        } while( val != 0 );
    }

}
