package io.github.hylexus.jt.jt808.spec.impl;

import io.github.hylexus.jt.config.Jt808ProtocolVersion;
import io.github.hylexus.jt.jt808.spec.Jt808MsgHeader;
import io.github.hylexus.jt.utils.JtProtocolUtils;

/**
 * @author hylexus
 */
public class DefaultJt808MsgBodyProps implements Jt808MsgHeader.Jt808MsgBodyProps {

    private final int intValue;

    public DefaultJt808MsgBodyProps(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public int intValue() {
        return intValue;
    }

    @Override
    public String toString() {
        return "MsgBodyProps{"
               + "intValue=" + intValue
               + ", msgBodyLength=" + msgBodyLength()
               + ", hasSubPackage=" + hasSubPackage()
               + ", encryptionType=" + encryptionType()
               + '}';
    }

    public static class DefaultJt808MsgBodyPropsBuilder implements Jt808MsgHeader.Jt808MsgBodyPropsBuilder {
        private int intValue;

        public DefaultJt808MsgBodyPropsBuilder() {
        }

        public DefaultJt808MsgBodyPropsBuilder(int intValue) {
            this.intValue = intValue;
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder msgBodyLength(int msgBodyLength) {
            this.intValue = JtProtocolUtils.setBitRange(msgBodyLength, 10, intValue, 0);
            return this;
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder encryptionType(int encryptionType) {
            this.intValue = JtProtocolUtils.setBitRange(encryptionType, 3, intValue, 10);
            return this;
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder subPackageIdentifier(int subPackageIdentifier) {
            this.intValue = JtProtocolUtils.setBitRange(subPackageIdentifier, 1, intValue, 13);
            return this;
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder subPackageIdentifier(boolean hasSubPackage) {
            return this.subPackageIdentifier(hasSubPackage ? 1 : 0);
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder versionIdentifier(int versionIdentifier) {
            this.intValue = JtProtocolUtils.setBitRange(versionIdentifier, 1, intValue, 14);
            return this;
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder versionIdentifier(Jt808ProtocolVersion version) {
            return this.versionIdentifier(version.getVersionBit());
        }

        @Override
        public Jt808MsgHeader.Jt808MsgBodyPropsBuilder reversedBit15(int reversedBit15) {
            this.intValue = JtProtocolUtils.setBitRange(reversedBit15, 1, intValue, 15);
            return this;
        }

        public Jt808MsgHeader.Jt808MsgBodyProps build() {
            return new DefaultJt808MsgBodyProps(intValue);
        }
    }
}
