package org.lukas;

import org.lukas.dtos.Message;
import org.lukas.handler.Handler;

import java.util.Optional;

public class ClearMessageHandler implements Handler {

    @Override
    public Optional<Message> handle(Message message) {
        return Optional.empty();
    }
}
