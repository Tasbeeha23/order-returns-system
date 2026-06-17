package com.retail.returns.util;

import com.retail.returns.entity.ItemCondition;
import com.retail.returns.entity.ReturnStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the core return eligibility business rule.
 * No Spring context needed - this is a plain object, which is the point of isolating it.
 */
class ReturnEligibilityEvaluatorTest {

    private final ReturnEligibilityEvaluator evaluator = new ReturnEligibilityEvaluator();

    @Test
    void unusedItemWithinWindow_isApproved() {
        LocalDate purchaseDate = LocalDate.now().minusDays(5);
        var decision = evaluator.evaluate(purchaseDate, ItemCondition.UNUSED);
        assertEquals(ReturnStatus.APPROVED, decision.getStatus());
    }

    @Test
    void openedItemWithinWindow_isPendingReview() {
        LocalDate purchaseDate = LocalDate.now().minusDays(5);
        var decision = evaluator.evaluate(purchaseDate, ItemCondition.OPENED);
        assertEquals(ReturnStatus.PENDING_REVIEW, decision.getStatus());
    }

    @Test
    void damagedItemWithinWindow_isRejected() {
        LocalDate purchaseDate = LocalDate.now().minusDays(5);
        var decision = evaluator.evaluate(purchaseDate, ItemCondition.DAMAGED);
        assertEquals(ReturnStatus.REJECTED, decision.getStatus());
    }

    @Test
    void unusedItemOutsideWindow_isRejectedRegardlessOfCondition() {
        LocalDate purchaseDate = LocalDate.now().minusDays(31);
        var decision = evaluator.evaluate(purchaseDate, ItemCondition.UNUSED);
        assertEquals(ReturnStatus.REJECTED, decision.getStatus());
    }

    @Test
    void exactlyThirtyDays_isStillWithinWindow() {
        LocalDate purchaseDate = LocalDate.now().minusDays(30);
        var decision = evaluator.evaluate(purchaseDate, ItemCondition.UNUSED);
        assertEquals(ReturnStatus.APPROVED, decision.getStatus());
    }

    @Test
    void thirtyOneDays_isOutsideWindow() {
        LocalDate purchaseDate = LocalDate.now().minusDays(31);
        var decision = evaluator.evaluate(purchaseDate, ItemCondition.OPENED);
        assertEquals(ReturnStatus.REJECTED, decision.getStatus());
    }
}
