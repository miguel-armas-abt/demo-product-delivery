package com.demo.poc.commons.custom.states.enums;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    CONTACT_DATA {
        public Set<State> nextStates() {
            return Set.of(AVAILABLE_DATES);
        }
    },
    AVAILABLE_DATES {
        public Set<State> nextStates() {
            return Set.of(RESERVATION);
        }
    },
    RESERVATION {
        public Set<State> nextStates() {
            return Set.of(RETRY, COORDINATION);
        }
    },
    RETRY {
        public Set<State> nextStates() {
            return Set.of(RETRY, COORDINATION);
        }
    },
    COORDINATION {
        public Set<State> nextStates() {
            return Set.of();
        }
    };

    public abstract Set<State> nextStates();

    public static State firstSate() {
        return CONTACT_DATA;
    }

    public static State lastState() {
        return COORDINATION;
    }
}
