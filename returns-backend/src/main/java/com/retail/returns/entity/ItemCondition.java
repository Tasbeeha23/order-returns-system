package com.retail.returns.entity;

/**
 * Physical condition of the item being returned.
 * Drives the approval decision together with the return window check.
 */
public enum ItemCondition {
    UNUSED,
    OPENED,
    DAMAGED
}
