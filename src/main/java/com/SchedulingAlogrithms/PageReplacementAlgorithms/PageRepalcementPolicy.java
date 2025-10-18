package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;

public interface PageRepalcementPolicy {
    Page apply(ArrayList<Page> frame, int needPageId);
}
