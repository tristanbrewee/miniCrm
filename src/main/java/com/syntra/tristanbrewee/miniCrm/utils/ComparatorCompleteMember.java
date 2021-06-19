package com.syntra.tristanbrewee.miniCrm.utils;

import com.syntra.tristanbrewee.miniCrm.model.dtos.CompleteMember;

import java.util.Comparator;

public class ComparatorCompleteMember implements Comparator<CompleteMember>{

    @Override
    public int compare(CompleteMember o1, CompleteMember o2) {
        if (o1.getUntil() == null && o2.getUntil() == null)
            return 0;
        if (o1.getUntil() != null && o2.getUntil() != null)
            return 0;
        if (o1.getUntil() != null && o2.getUntil() == null)
            return 1;
        if (o1.getUntil() == null && o2.getUntil() != null)
            return -1;
        else
            return 0;
    }
}
