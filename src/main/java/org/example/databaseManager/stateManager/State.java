package org.example.databaseManager.stateManager;

public enum State {
    CHOOSE(-1), FIND(2), FIND_ALL(1), DELETE(4), CREATE(3), EXIT(0);

    final int number;
    State(int number){
        this.number = number;
    }
}
