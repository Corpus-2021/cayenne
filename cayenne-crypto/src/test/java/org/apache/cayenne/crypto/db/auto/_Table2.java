package org.apache.cayenne.crypto.db.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Table2 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Table2 extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    @Deprecated
    public static final String CRYPTO_BYTES_PROPERTY = "cryptoBytes";
    @Deprecated
    public static final String PLAIN_BYTES_PROPERTY = "plainBytes";

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<byte[]> CRYPTO_BYTES = new Property<byte[]>("cryptoBytes");
    public static final Property<byte[]> PLAIN_BYTES = new Property<byte[]>("plainBytes");

    public void setCryptoBytes(byte[] cryptoBytes) {
        writeProperty("cryptoBytes", cryptoBytes);
    }
    public byte[] getCryptoBytes() {
        return (byte[])readProperty("cryptoBytes");
    }

    public void setPlainBytes(byte[] plainBytes) {
        writeProperty("plainBytes", plainBytes);
    }
    public byte[] getPlainBytes() {
        return (byte[])readProperty("plainBytes");
    }

}