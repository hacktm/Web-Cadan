package com.meeting.notes.server.models.ws;

public enum WsMessageType {
    updateMeetingNotes(0),
    userLeaveSession(1),
    ownerEndSession(2),
    newUserJoinedSession(3),
    timer(4),
    new_comment(5);

    private int intValue;
    WsMessageType(int intValue) {
        this.intValue = intValue;
    }

    public static int getIntValue(WsMessageType msgType) {
        return msgType.intValue;
    }

}
