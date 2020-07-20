package com.continuousx.utils.rulecheck

class RuleBook {

    List<RuleBookEntry> entryList = []

    RuleBook addEntry(final RuleBookEntry entry) {
        Objects.requireNonNull(entry)
        this.entryList << entry
        this
    }

}
