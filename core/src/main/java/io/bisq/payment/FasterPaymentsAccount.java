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

package io.bisq.payment;

import io.bisq.app.Version;
import io.bisq.locale.FiatCurrency;
import io.bisq.payload.payment.FasterPaymentsAccountContractData;
import io.bisq.payload.payment.PaymentAccountContractData;
import io.bisq.payload.payment.PaymentMethod;
import io.bisq.user.Preferences;

public final class FasterPaymentsAccount extends PaymentAccount {
    // That object is saved to disc. We need to take care of changes to not break deserialization.
    private static final long serialVersionUID = Version.LOCAL_DB_VERSION;

    public FasterPaymentsAccount() {
        super(PaymentMethod.FASTER_PAYMENTS);
        setSingleTradeCurrency(new FiatCurrency("GBP", Preferences.getDefaultLocale()));
    }

    @Override
    protected PaymentAccountContractData setContractData() {
        return new FasterPaymentsAccountContractData(paymentMethod.getId(), id, paymentMethod.getMaxTradePeriod());
    }

    public void setSortCode(String value) {
        ((FasterPaymentsAccountContractData) contractData).setSortCode(value);
    }

    public String getSortCode() {
        return ((FasterPaymentsAccountContractData) contractData).getSortCode();
    }

    public void setAccountNr(String value) {
        ((FasterPaymentsAccountContractData) contractData).setAccountNr(value);
    }

    public String getAccountNr() {
        return ((FasterPaymentsAccountContractData) contractData).getAccountNr();
    }
}