package com.example.RegisterLogin.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Constants {

    public enum TaskActions {
        START,
        PAUSE,
        END
    }

    public enum NewTaskActions {
        START(1),
        PAUSE(2),
        END(3);

        private final int value;

        NewTaskActions(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
