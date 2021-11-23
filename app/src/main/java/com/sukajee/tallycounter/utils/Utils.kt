val <T> T.exhaustive: T     //this is used to make ui event collection compile time safe.
    get() = this            // if not done, code compiles even if we have not collected events
// those were added in the events sealed class