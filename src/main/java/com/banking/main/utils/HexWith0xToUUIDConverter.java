package com.banking.main.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class HexWith0xToUUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String source) {

        // Remove 0x prefix if present
        if (source.startsWith("0x") || source.startsWith("0X")) {
            source = source.substring(2);
        }

        // If already contains hyphens, treat as normal UUID
        if (source.contains("-")) {
            return UUID.fromString(source);
        }

        // Convert hex → byte[]
        byte[] bytes = hexToBytes(source);

        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long high = bb.getLong();
        long low = bb.getLong();

        return new UUID(high, low);
    }

    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] =
                    (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                            + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }
}