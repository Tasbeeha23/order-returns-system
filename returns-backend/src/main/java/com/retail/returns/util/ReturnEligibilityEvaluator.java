package com.retail.returns.util;

import com.retail.returns.entity.ItemCondition;
import com.retail.returns.entity.ReturnStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Encapsulates the return eligibility business rule in one place.
 *
 * Rule precedence:
 *   1. If purchase date is more than 30 days ago -> REJECTED, regardless of condition.
 *   2. Otherwise, decide by item condition:
 *        UNUSED   -> APPROVED
 *        OPENED   -> PENDING_REVIEW
 *        DAMAGED  -> REJECTED
 *
 * Isolating this in its own class (rather than in the service or entity) keeps the
 * rule unit-testable in isolation and easy to extend if return policy changes later.
 */
@Component
public class ReturnEligibilityEvaluator {

    public static final int RETURN_WINDOW_DAYS = 30;

    public static class Decision {
        private final ReturnStatus status;
        private final String reason;

        public Decision(ReturnStatus status, String reason) {
            this.status = status;
            this.reason = reason;
        }

        public ReturnStatus getStatus() {
            return status;
        }

        public String getReason() {
            return reason;
        }
    }

    public Decision evaluate(LocalDate purchaseDate, ItemCondition condition) {
        long daysSincePurchase = ChronoUnit.DAYS.between(purchaseDate, LocalDate.now());

        if (daysSincePurchase > RETURN_WINDOW_DAYS) {
            return new Decision(
                    ReturnStatus.REJECTED,
                    String.format("Return window expired: %d days since purchase exceeds the %d-day limit",
                            daysSincePurchase, RETURN_WINDOW_DAYS)
            );
        }

        return switch (condition) {
            case UNUSED -> new Decision(
                    ReturnStatus.APPROVED,
                    "Item is unused and within the 30-day return window"
            );
            case OPENED -> new Decision(
                    ReturnStatus.PENDING_REVIEW,
                    "Item has been opened - flagged for manual inspection before a final decision"
            );
            case DAMAGED -> new Decision(
                    ReturnStatus.REJECTED,
                    "Item is damaged and not eligible for return"
            );
        };
    }
}
