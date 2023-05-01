package com.rangiffler.model.enums;

public enum InvitationState {
    ACCEPT_INVITATION("Accept invitation"),
    DECLINE_INVITATION("Decline invitation");

    public final String text;

    InvitationState(String text) {
        this.text = text;
    }
}
