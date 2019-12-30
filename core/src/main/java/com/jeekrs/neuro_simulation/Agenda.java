package com.jeekrs.neuro_simulation;

public class Agenda {
    private int number;
    private boolean isAI = false;
    private boolean isPlayer = true;

    private boolean isFriendlyToAll = false;
    private boolean isHarmfulToAll = false;

    public boolean isAI() {

        return isAI;
    }
    public boolean isFriendly(Agenda agenda) {
        if (isFriendlyToAll)
            return true;
        if (isHarmfulToAll)
            return false;
        return this == agenda;
    }

    private boolean isRemote = false;

    public boolean isRemote() {
        return isRemote;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }

    public void setFriendlyToAll(boolean friendlyToAll) {
        isFriendlyToAll = friendlyToAll;
    }

    final static public Agenda NEUTRAL_HOSTILITY = new Agenda() {{
        setAI(false);
        setPlayer(false);
        setHarmfulToAll(true);
        setFriendlyToAll(false);
        setNumber(0);
    }};


    final static public Agenda NEUTRAL_FRIENDLY = new Agenda() {{
        setAI(false);
        setPlayer(false);
        setHarmfulToAll(false);
        setFriendlyToAll(true);
        setNumber(0);
    }};

    public void setNumber(int num) {
        number = num;
    }

    public void setHarmfulToAll(boolean harmfulToAll) {
        isHarmfulToAll = harmfulToAll;
    }

    public int getNumber() {
        return number;
    }
}
