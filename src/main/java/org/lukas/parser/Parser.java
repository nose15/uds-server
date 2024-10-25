package org.lukas.parser;

import org.lukas.dtos.Message;
import org.lukas.enums.MessageType;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Parser {
    public static ByteBuffer encode(Message message) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(message.getContentLength() + 8);

        byte messageTypeByte = message.getMessageType().getHex();

        byteBuffer.put(0, messageTypeByte);
        byteBuffer.putInt(4, message.getContentLength());
        byteBuffer.put(8, message.getContent().getBytes());

        return byteBuffer;
    }

    public static Message decode(ByteBuffer byteBuffer) {
        MessageType messageType = MessageType.fromHex(byteBuffer.get(0));
        int messageLength = byteBuffer.getInt(4);

        byte[] contentBytes = new byte[messageLength];
        byteBuffer.get(8, contentBytes, 0, messageLength);
        String content = new String(contentBytes);

        return new Message(messageType, content);
    }
}
