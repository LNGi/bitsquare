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

package io.bisq.core.btc.wallet;

import io.bisq.core.btc.BaseCurrencyNetwork;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.TransactionOutput;

import javax.annotation.Nullable;

public class WalletUtils {
    private static BaseCurrencyNetwork baseCurrencyNetwork;

    public static NetworkParameters getParameters() {
        return getBaseCurrencyNetwork().getParameters();
    }

    public static boolean isOutputScriptConvertableToAddress(TransactionOutput output) {
        return output.getScriptPubKey().isSentToAddress() ||
                output.getScriptPubKey().isPayToScriptHash();
    }

    @Nullable
    public static Address getAddressFromOutput(TransactionOutput output) {
        return isOutputScriptConvertableToAddress(output) ?
                output.getScriptPubKey().getToAddress(getParameters()) : null;
    }

    @Nullable
    public static String getAddressStringFromOutput(TransactionOutput output) {
        return isOutputScriptConvertableToAddress(output) ?
                output.getScriptPubKey().getToAddress(getParameters()).toString() : null;
    }

    public static void setBaseCurrencyNetwork(BaseCurrencyNetwork baseCurrencyNetwork) {
        WalletUtils.baseCurrencyNetwork = baseCurrencyNetwork;
    }

    public static BaseCurrencyNetwork getBaseCurrencyNetwork() {
        return baseCurrencyNetwork;
    }
}