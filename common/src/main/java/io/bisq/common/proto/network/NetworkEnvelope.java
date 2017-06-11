/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.common.proto.network;

import com.google.protobuf.Message;
import io.bisq.common.Envelope;
import io.bisq.generated.protobuffer.PB;
import lombok.EqualsAndHashCode;

import static com.google.common.base.Preconditions.checkArgument;

@EqualsAndHashCode
public abstract class NetworkEnvelope implements Envelope {

    protected final int messageVersion;


    ///////////////////////////////////////////////////////////////////////////////////////////
    // PROTO BUFFER
    ///////////////////////////////////////////////////////////////////////////////////////////

    protected NetworkEnvelope(int messageVersion) {
        this.messageVersion = messageVersion;
    }

    public PB.NetworkEnvelope.Builder getNetworkEnvelopeBuilder() {
        return PB.NetworkEnvelope.newBuilder().setMessageVersion(messageVersion);
    }

    @Override
    public Message toProtoMessage() {
        return getNetworkEnvelopeBuilder().build();
    }

    // todo remove
    public PB.NetworkEnvelope toProtoNetworkEnvelope() {
        return getNetworkEnvelopeBuilder().build();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////////////////////

    public int getMessageVersion() {
        // -1 is used for the case that we use an envelope message as payload (mailbox)
        // so we check only against 0 which is the default value if not set
        checkArgument(messageVersion != 0, "messageVersion is not set (0).");
        return messageVersion;
    }

}