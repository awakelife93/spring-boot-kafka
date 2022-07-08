package com.awakelife93.kafka.service;

import javax.annotation.Nullable;

import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {

  @Nullable private final MessageListenerContainer messageListenerContainer;

  public Boolean start() {
    if (messageListenerContainer != null && messageListenerContainer.isRunning()) {
      return false;
    }

    messageListenerContainer.start();
    return true;
  }

  public Boolean stop() {
    if (messageListenerContainer != null && !messageListenerContainer.isRunning()) {
      return false;
    }

    messageListenerContainer.stop();
    return true;
  }

  public Boolean pause() {
    if (messageListenerContainer != null && messageListenerContainer.isContainerPaused()) {
      return false;
    }

    messageListenerContainer.pause();
    return true;
  }

  public Boolean resume() {
    if (messageListenerContainer != null &&  !messageListenerContainer.isContainerPaused()) {
      return false;
    }

    messageListenerContainer.resume();
    return true;
  }
}
