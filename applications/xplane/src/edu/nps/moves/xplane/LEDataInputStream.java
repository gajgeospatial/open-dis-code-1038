
package edu.nps.moves.xplane;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Little endian data stream, which is a drop-in replacement for DataInput
 * Stream. This is used to read primitives saved in Intel format byte order
 * (little-endian), as opposed to network or java byte order (big endian).
 * <p>
 * This uses the DataInput interface, so it should, in most instances, be
 * drop-in compatible with DataInputStream, so long as you use the interface.<p>
 * 
 * @author DMcG
 */
public final class LEDataInputStream implements DataInput
{
    protected final DataInputStream dis;
    protected final InputStream is;
    protected final byte[] scratch;

    public static String readUTF( DataInput in ) throws IOException
    {
        return DataInputStream.readUTF(in);
    }

    /** Constructor */
    public LEDataInputStream( InputStream in )
        {
        this.is = in;
        this.dis = new DataInputStream( in );
        scratch = new byte[8];
        }

   
    public final void close() throws IOException
    {
        dis.close();
    }

    public final int read( byte buffer[], int offset, int length ) throws IOException
    {
        return is.read( buffer, offset, length );
    }

    public final boolean readBoolean() throws IOException
    {
        return dis.readBoolean();
    }

    public final byte readByte() throws IOException
    {
        return dis.readByte();
    }

     public final char readChar() throws IOException
     {
        dis.readFully( scratch, 0, 2 );
        return ( char ) ( ( scratch[1] & 0xff ) << 8 | ( scratch[0] & 0xff ) );
     }

    public final double readDouble() throws IOException
    {
        return Double.longBitsToDouble(this.readLong());
    }

    public final float readFloat() throws IOException
    {
        return Float.intBitsToFloat(this.readInt());
    }

    public final void readFully( byte buffer[] ) throws IOException
    {
        dis.readFully( buffer, 0, buffer.length );
    }

    public final void readFully(byte buffer[], int offset, int length ) throws IOException
    {
        dis.readFully( buffer, offset, length);
    }

    public final int readInt() throws IOException
    {
        dis.readFully( scratch, 0, 4 );
        return ( scratch[3] ) << 24
               | ( scratch[2] & 0xff ) << 16
               | ( scratch[1] & 0xff ) << 8
               | ( scratch[0] & 0xff );
    }

    /**
     * @deprecated Deprecated sometime around JDK 1.2
     */
    public final String readLine() throws IOException
    {
        return dis.readLine();
    }

    
    public final long readLong() throws IOException
    {
        dis.readFully( scratch, 0, 8 );
        return ( long ) ( scratch[ 7 ] ) << 56
               |
               /* long cast needed or shift done modulo 32 */
               ( long ) ( scratch[6] & 0xff ) << 48
               | ( long ) ( scratch[5] & 0xff ) << 40
               | ( long ) ( scratch[4] & 0xff ) << 32
               | ( long ) ( scratch[3] & 0xff ) << 24
               | ( long ) ( scratch[2] & 0xff ) << 16
               | ( long ) ( scratch[1] & 0xff ) << 8
               | ( long ) ( scratch[0] & 0xff );
    }

    public final short readShort() throws IOException
    {
        dis.readFully( scratch, 0, 2 );
        return ( short ) ( ( scratch[ 1 ] & 0xff ) << 8 | ( scratch[ 0 ] & 0xff ) );
    }

    public final String readUTF() throws IOException
    {
        return dis.readUTF();
    }

    public final int readUnsignedByte() throws IOException
    {
        return dis.readUnsignedByte();
    }

    public final int readUnsignedShort() throws IOException
    {
        dis.readFully( scratch, 0, 2 );
        return ( (scratch[1] & 0xff) << 8 |
                 (scratch[0] & 0xff) );
    }

    public final int skipBytes( int numberOfBytes ) throws IOException
    {
        return dis.skipBytes(numberOfBytes);
    }
}