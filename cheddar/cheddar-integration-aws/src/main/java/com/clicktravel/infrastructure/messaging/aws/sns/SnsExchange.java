/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.clicktravel.infrastructure.messaging.aws.sns;

import com.amazonaws.AmazonClientException;
import com.clicktravel.cheddar.infrastructure.messaging.Exchange;
import com.clicktravel.cheddar.infrastructure.messaging.Message;
import com.clicktravel.cheddar.infrastructure.messaging.exception.MessagePublishException;

/**
 * AWS SNS implementation for an {@link Exchange}. This class is implemented as an adapter for a
 * {@link SnsTopicResource}.
 * @param <T> message type accepted by this exchange
 */
public abstract class SnsExchange<T extends Message> implements Exchange<T> {

    private final SnsTopicResource snsTopicResource;

    public SnsExchange(final SnsTopicResource snsTopicResource) {
        this.snsTopicResource = snsTopicResource;
    }

    abstract protected SnsSubjectAndMessage toSnsSubjectAndMessage(T message);

    @Override
    public void route(final T message) throws MessagePublishException {
        final SnsSubjectAndMessage snsSubjectAndMessage = toSnsSubjectAndMessage(message);
        final String subject = snsSubjectAndMessage.getSubject();
        final String snsMessage = snsSubjectAndMessage.getMessage();
        try {
            snsTopicResource.publish(subject, snsMessage);
        } catch (final AmazonClientException e) {
            throw new MessagePublishException("Could not publish to SNS: [" + snsTopicResource.getTopicName() + "]", e);
        }
    }

    @Override
    public String getName() {
        return snsTopicResource.getTopicName();
    }

}
