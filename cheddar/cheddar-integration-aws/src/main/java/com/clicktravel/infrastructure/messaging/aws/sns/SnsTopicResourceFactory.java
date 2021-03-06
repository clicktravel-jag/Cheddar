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

public interface SnsTopicResourceFactory {

    /**
     * Creates a {@link SnsTopicResource} representing an existing actual AWS SNS topic.
     * @param name Topic name
     * @return {@link SnsTopicResource} with the given name
     */
    SnsTopicResource createSnsTopicResource(String name);

    /**
     * Creates a {@link SnsTopicResource} and the actual AWS SNS topic if it does not already exist with the given name.
     * @param name Topic name
     * @return {@link SnsTopicResource} with the given name
     */
    SnsTopicResource createSnsTopicResourceAndAwsSnsTopicIfAbsent(String name);

}
